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

    @Autowired
    private BlogService blogService;

    /**
     * 返回归档页面
     * @param model
     * @return
     */
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }
}
