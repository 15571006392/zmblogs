package com.controller.admin;

import com.bean.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(username, password);

        if(user != null){
            // 验证成功
            // 防止密码返回到前端
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            // 验证失败
            attributes.addFlashAttribute("msg","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
