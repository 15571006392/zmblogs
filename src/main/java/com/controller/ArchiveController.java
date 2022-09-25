package com.controller;

import com.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Zm-Mmm
 */
@Controller
public class ArchiveController {

    private final BlogService blogService;

    @Autowired
    public ArchiveController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     *
     * @param model 容器
     * @return 返回归档页面
     */
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("blogCount",blogService.countBlog());
        model.addAttribute("archiveMap",blogService.archiveBlog());
        return "archives";
    }
}
