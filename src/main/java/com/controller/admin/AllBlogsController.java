package com.controller.admin;

import com.bean.User;
import com.service.AllBlogsService;
import com.service.BlogService;
import com.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Zm-Mmm
 */
@Controller()
@RequestMapping("/admin")
public class AllBlogsController {

    private final AllBlogsService allBlogsService;

    private final BlogService blogService;

    private final RedisTemplate redisTemplate;

    @Autowired
    public AllBlogsController(AllBlogsService allBlogsService, BlogService blogService,RedisTemplate redisTemplate) {
        this.allBlogsService = allBlogsService;
        this.blogService = blogService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 查询非当前登录用户的其他人的博客
     * @param model 容器
     * @param session session
     * @return 页面
     */
    @GetMapping("/allBlogs")
    public String list(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("allBlogs",allBlogsService.findAllBlogsNotCurrentUser(user.getId()));
        return "admin/admin-AllBlogs";
    }

    /**
     * 删除博客
     * @param id 博客id
     * @param attributes 重定向容器
     * @return 重定向
     */
    @GetMapping("/allBlogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        RedisUtil.flushAllRedisMenu(redisTemplate);
        RedisUtil.flushAllRedisIndex(redisTemplate);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/allBlogs";
    }

    /**
     * 修改博客发布状态
     * @param id 博客id
     * @param attributes 重定向容器
     * @return 重定向
     */
    @GetMapping("/allBlogs/{id}/modifyState")
    public String modifyState(@PathVariable Long id, RedirectAttributes attributes){
        allBlogsService.modifyState(id);
        RedisUtil.flushAllRedisMenu(redisTemplate);
        RedisUtil.flushAllRedisIndex(redisTemplate);
        attributes.addFlashAttribute("message","下架成功，已将该博客改为草稿状态");
        return "redirect:/admin/allBlogs";
    }
}
