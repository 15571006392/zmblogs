package com.controller.admin;

import com.bean.UserInfoModify;
import com.service.UserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
            Thread.sleep(1000);//毫秒数
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/userinfoModify/{id}";
    }

    @PostMapping("/userinfoModify/info/{id}")
    public String modifyInfo(@PathVariable(name = "id") int id, @RequestParam("nickname") String nickname, @RequestParam("email") String email) {
        try {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userInfoModifyService.modifyInfo(id, nickname, email,format.format(date));
            Thread.sleep(1000);//毫秒数
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/userinfoModify/{id}";
    }
}
