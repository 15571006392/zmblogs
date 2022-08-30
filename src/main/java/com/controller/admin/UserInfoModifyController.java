package com.controller.admin;

import com.bean.UserInfoModify;
import com.service.UserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/admin")
public class UserInfoModifyController {

    @Autowired
    UserInfoModifyService userInfoModifyService;

    @GetMapping("/userinfoModify/{id}")
    public String show(@PathVariable(name = "id") int id, Model model) {
        UserInfoModify userInfoModify = userInfoModifyService.findAllInfo(id);
        model.addAttribute("user", userInfoModify);
        return "admin/UserInfoModify";
    }

    @PostMapping("/userinfoModify/avatar/{id}")
    public String modifyAvatar(@PathVariable(name = "id") int id, @RequestParam("avatar") String avatar) {
        try {
            userInfoModifyService.modifyAvatar(id, avatar);
            TimeUnit.SECONDS.sleep(1); // 1
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/userinfoModify/{id}";
    }

    @PostMapping("/userinfoModify/info/{id}")
    public String modifyInfo(@PathVariable(name = "id") int id, @RequestParam("nickname") String nickname, @RequestParam("email") String email) {
        try {
            userInfoModifyService.modifyInfo(id, nickname, email);
            Thread.sleep(1000);//毫秒数
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/userinfoModify/{id}";
    }
}
