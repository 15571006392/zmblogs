package com.controller;

import com.bean.Detail;
import com.bean.UserInfo;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据ID查询用户信息
     *
     * @return
     */
    @GetMapping("/user/{id}")
    public String info(@PathVariable(name = "id") int id, Model model) {
        UserInfo userInfo = userInfoService.findUserById(id);
        List<Detail> userDetail = userInfoService.findUserDetail(id);
        model.addAttribute("userinfo",userInfo);
        model.addAttribute("userDetail",userDetail);
        return "userinfo";
    }
}
