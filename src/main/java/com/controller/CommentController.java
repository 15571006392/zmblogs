package com.controller;

import com.bean.Comment;
import com.bean.User;
import com.service.BlogService;
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

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    /**
     * 头像
     */
    @Value("${comment.avatar}")
    private String avatar;

    /**
     * 查找指定博客的所有评论
     * @param blogId
     * @param model
     * @return
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        model.addAttribute("blog", blogService.getAndConvert(blogId));
        return "detail :: commentList";
    }

    /**
     * 发送，回复评论
     * @param comment
     * @return
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
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
        }else{
            // 设置为默认头像
            comment.setAvatar(avatar);
        }
        // 保存
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
