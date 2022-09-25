package com.controller.admin;

import com.bean.User;
import com.bean.UserEntity;
import com.service.UserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    /**
     * 用户资料修改页跳转
     * @param id 用户id
     * @param model 容器
     * @param session session
     * @return 用户资料修改页
     */
    @GetMapping("/userinfoModify/{id}")
    public String show(@PathVariable(name = "id") int id, Model model, HttpSession session) {
        // 获取登录用户信息
        User user = (User) session.getAttribute("user");
        // 查询页面用户信息
        UserEntity userEntity = userInfoModifyService.findAllInfo(id,user.getId());
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