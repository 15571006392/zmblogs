package com.controller;

import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

/**
 * @author Zm-Mmm
 */
@Controller
public class AboutController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        int visitorCount = stringRedisTemplate.opsForHash().keys("visitorIP").size();
        int countViews =  Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("countViews")));
        model.addAttribute("count",count);
        model.addAttribute("visitorCount",visitorCount);
        model.addAttribute("countViews",countViews);
        return "about";
    }
}
