package com.controller;

import com.bean.User;
import com.service.BlogService;
import com.service.TagService;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Zm-Mmm
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 主页跳转
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, HttpSession session) {
        // 分页查询所有博客
        model.addAttribute("page", blogService.listBlog(pageable));
        // 查找前6个分类
        model.addAttribute("types", typeService.listTypeTop(6));
        // 查找前10个标签
        model.addAttribute("tags", tagService.listTagTop(10));
        // 查找前8个推荐的博客
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        // 返回session
        User user = (User)session.getAttribute("user");
        return "index";
    }

    /**
     * 搜索功能
     * @param pageable
     * @param model
     * @param query
     * @return
     */
    @PostMapping("/search")
    public String search(@PageableDefault(size = 100, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, @RequestParam String query) {
        // 分页查询，按标题和内容关键字查询
        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        // 用户输入的内容
        model.addAttribute("query", query);
        return "search";
    }

    /**
     * 根据id查找指定博客
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        // markdown格式转换
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "detail";
    }
}
