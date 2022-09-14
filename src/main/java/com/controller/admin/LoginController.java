package com.controller.admin;

import com.bean.User;
import com.bean.UserEntity;
import com.service.UserInfoService;
import com.service.UserService;
import com.util.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    private final UserService userService;

    private final UserInfoService userInfoService;

    private final StringRedisTemplate stringRedisTemplate;

    private final SignService signService;

    private final HttpSession session;

    @Autowired
    public LoginController(UserService userService, UserInfoService userInfoService, StringRedisTemplate stringRedisTemplate, SignService signService, HttpSession session) {
        this.userService = userService;
        this.userInfoService = userInfoService;
        this.stringRedisTemplate = stringRedisTemplate;
        this.signService = signService;
        this.session = session;
    }

    /**
     * 用户登录跳转
     * @return 成功返回index页面，失败返回login页面
     */
    @GetMapping
    public String loginPage() {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "admin/login";
        } else {
            return "admin/index";
        }
    }

    /**
     * 登录功能
     * @param username 用户名
     * @param password 用户密码
     * @param attributes 重定向容器
     * @return 成功返回index，失败返回admin
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes attributes,Model model) {
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

            // 查询当日是否签到
            int id = user.getId().intValue();
            String rightNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            boolean isSign = signService.currentDaySign(id, rightNow);
            model.addAttribute("isSign",isSign);
            return "admin/index";
        } else {
            // 验证失败
            attributes.addFlashAttribute("msg", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销功能
     * @return 返回登录页
     */
    @GetMapping("/logout")
    public String logout() {
        User user = (User) session.getAttribute("user");
        // 删除redis中在线用户
        stringRedisTemplate.opsForHash().delete("currentUser", user.getNickname());
        session.removeAttribute("user");
        return "redirect:/";
    }
}
