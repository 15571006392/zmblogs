package com.controller.admin;

import com.bean.UserEntity;
import com.service.UserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class UserInfoModifyController {

    final UserInfoModifyService userInfoModifyService;

    @Autowired
    public UserInfoModifyController(UserInfoModifyService userInfoModifyService) {
        this.userInfoModifyService = userInfoModifyService;
    }

    @GetMapping("/userinfoModify/{id}")
    public String show(@PathVariable(name = "id") int id, Model model) {
        UserEntity userEntity = userInfoModifyService.findAllInfo(id);
        model.addAttribute("user", userEntity);
        return "admin/UserInfoModify";
    }

    @PostMapping("/userinfoModify/avatar/{id}")
    public String modifyAvatar(@PathVariable(name = "id") int id, @RequestParam("avatar") String avatar) {
        userInfoModifyService.modifyAvatar(id, avatar);
        return "redirect:/admin/userinfoModify/{id}";
    }

    @PostMapping("/userinfoModify/info/{id}")
    public String modifyInfo(@PathVariable(name = "id") int id, @RequestParam("nickname") String nickname, @RequestParam("email") String email) {
        userInfoModifyService.modifyInfo(id, nickname, email);
        return "redirect:/admin/userinfoModify/{id}";
    }
}