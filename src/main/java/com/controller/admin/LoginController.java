package com.controller.admin;

import com.bean.User;
import com.service.UserInfoService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping
    public String loginPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "admin/login";
        } else {
            return "admin/index";
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);

        if (user != null) {
            // 验证成功
            // 防止密码返回到前端
            user.setPassword(null);
            session.setAttribute("user", user);
            userInfoService.updateUserUpdateTime(new Date(), user.getId());
            // 放入redis
            stringRedisTemplate.opsForHash().putIfAbsent("currentUser", user.getNickname(), user.toString());
            // 设置超时时间 30分钟
            stringRedisTemplate.expire("currentUser",30*60, TimeUnit.SECONDS);
            return "admin/index";
        } else {
            // 验证失败
            attributes.addFlashAttribute("msg", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        // 删除redis中在线用户
        stringRedisTemplate.opsForHash().delete("currentUser", user.getNickname());
        session.removeAttribute("user");
        return "redirect:/";
    }
}
