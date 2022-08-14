package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Zm-Mmm
 */
@Controller
public class AboutController {

    /**
     * 返回关于我页面
     * @param model
     * @return
     */
    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }
}
