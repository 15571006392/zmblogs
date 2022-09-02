package com.controller;

import com.NotFoundException;
import com.bean.Detail;
import com.bean.UserDetail;
import com.bean.UserInfo;
import com.util.SignService;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author Zm-Mmm
 */
@Controller
public class UserInfoController {

    private final SignService signService;

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(SignService signService, UserInfoService userInfoService) {
        this.signService = signService;
        this.userInfoService = userInfoService;
    }

    /**
     *
     * @param id 用户ID
     * @param model 容器
     * @return userinfo页面
     */
    @GetMapping("/user/{id}")
    public String info(@PathVariable(name = "id") int id, Model model) {
        String rightNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 查询用户信息
        UserInfo userInfo = userInfoService.findUserById(id);
        if(userInfo.getId() == null){
            throw new NotFoundException("用户不存在");
        }
        // 查询热门博客
        List<Detail> userDetail = userInfoService.findUserDetail(id);
        // 查询最近更新的博客
        List<UserDetail> userLateDetail = userInfoService.findUserLateDetail(id);
        // 查询用户推荐的博客
        List<UserDetail> userRecommendDetail = userInfoService.findUserRecommendDetail(id);
        model.addAttribute("userinfo", userInfo);
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("userLateDetail", userLateDetail);
        model.addAttribute("userRecommendDetail", userRecommendDetail);
        // 签到信息查询
        Map<String, Object> signByDate = signService.getSignByDate(id, rightNow);
        model.addAttribute("signByDate", signByDate);
        return "userinfo";
    }

    /**
     * 签到
     * @param id 用户ID
     * @return 重定向到上面的方法
     */
    @GetMapping("/sign/{id}")
    public String sign(@PathVariable("id") int id) {
        String rightNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        signService.doSign(id, rightNow);
        return "redirect:/user/" + id;
    }
}