package com.controller;

import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Zm-Mmm
 */
@Controller
public class AboutController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 返回关于我页面
     * @param model
     * @return
     */
    @GetMapping("/about")
    public String about(Model model){
        int count = userInfoService.findUserCount();
        model.addAttribute("count",count);
        return "about";
    }
}
