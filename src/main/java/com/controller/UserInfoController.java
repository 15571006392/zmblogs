package com.controller;

import com.bean.Detail;
import com.bean.UserDetail;
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
        // 查询用户信息
        UserInfo userInfo = userInfoService.findUserById(id);
        // 查询热门博客
        List<Detail> userDetail = userInfoService.findUserDetail(id);
        // 查询最近更新的博客
        List<UserDetail> userLateDetail = userInfoService.findUserLateDetail(id);
        // 查询用户推荐的博客
        List<UserDetail> userRecommendDetail = userInfoService.findUserRecommendDetail(id);
        model.addAttribute("userinfo",userInfo);
        model.addAttribute("userDetail",userDetail);
        model.addAttribute("userLateDetail",userLateDetail);
        model.addAttribute("userRecommendDetail",userRecommendDetail);
        return "userinfo";
    }
}
