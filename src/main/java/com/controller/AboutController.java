package com.controller;

import com.bean.User;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
     *
     * @param model
     * @return
     */
    @GetMapping("/about")
    public String about(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int count = userInfoService.findUserCount();
        int visitorCount = stringRedisTemplate.opsForHash().keys("visitorIP").size();
        if(stringRedisTemplate.opsForValue().get("countViews") == null){
            stringRedisTemplate.opsForValue().set("countViews",String.valueOf(0));
        }
        int countViews = Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("countViews")));
        // 如果有人登录了
        if(user != null){
            // 如果该用户已过期则放入redis
            stringRedisTemplate.opsForHash().putIfAbsent("currentUser", user.getNickname(), user.toString());
            // 设置超时时间 30分钟
            stringRedisTemplate.expire("currentUser",30*60, TimeUnit.SECONDS);
            int currentUser = stringRedisTemplate.opsForHash().keys("currentUser").size();
            model.addAttribute("currentUserCount",currentUser);
        }else{
            model.addAttribute("currentUserCount",0);
        }
        model.addAttribute("count", count);
        model.addAttribute("visitorCount", visitorCount);
        model.addAttribute("countViews", countViews);
        return "about";
    }
}
