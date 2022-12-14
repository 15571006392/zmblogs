package com.controller;

import com.bean.Comment;
import com.bean.User;
import com.service.BlogService;
import com.service.CommentDeleteService;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Zm-Mmm
 */
@Controller
public class CommentController {

    private final CommentService commentService;

    private final CommentDeleteService commentDeleteService;

    private final BlogService blogService;

    /**
     * 头像
     */
    @Value("${comment.avatar}")
    private String avatar;

    @Autowired
    public CommentController(CommentService commentService, CommentDeleteService commentDeleteService, BlogService blogService) {
        this.commentService = commentService;
        this.commentDeleteService = commentDeleteService;
        this.blogService = blogService;
    }

    /**
     *
     * @param blogId 博客ID
     * @param model 容器
     * @return 查找指定博客的所有评论
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        model.addAttribute("blog", blogService.getAndConvert(blogId));
        return "detail :: commentList";
    }

    /**
     * 删除评论
     * @param id 评论ID
     * @return 博客查询页
     */
    @GetMapping("/comments/delete/{id}/{blogId}")
    public String deleteComments(@PathVariable int id, @PathVariable String blogId){
        commentDeleteService.deleteComment(id);
        return "redirect:/blog/" + blogId;
    }

    /**
     * 发送，回复评论
     * @param comment 评论对象
     * @return 博客页
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        // 获取该评论关联的博客的id
        Long blogId = comment.getDetail().getId();
        // 保存博客的其他信息
        comment.setDetail(blogService.getBlog(blogId));
        // 判断是不是已登录的用户
        User user = (User)session.getAttribute("user");
        if(user != null){
            // 设置评论内部头像为已登录用户的头像
            comment.setAvatar(user.getAvatar());
            // 设置为有头像
            comment.setAdminComment(true);
            // 校验登录的用户评论姓名与博主名字是否相同,避免博主无法发送评论
            if(!user.getNickname().equals(comment.getDetail().getUser().getNickname())){
                // 二次校验
                if(comment.getNickname().equals(comment.getDetail().getUser().getNickname())){
                    return "redirect:/blog/" + blogId;
                }
            }
        }else{
            // 校验评论姓名与博主名字是否相同
            if(comment.getNickname().equals(comment.getDetail().getUser().getNickname())){
                return "redirect:/blog/" + blogId;
            }
            // 设置为默认头像
            comment.setAvatar(avatar);
        }
        // 保存
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
