package com.controller;

import com.bean.Detail;
import com.bean.User;
import com.bean.UserDetail;
import com.bean.UserInfo;
import com.service.SignService;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class UserInfoController {

    @Autowired
    private SignService signService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private HttpSession session;

    /**
     * 根据ID查询用户信息
     *
     * @return
     */
    @GetMapping("/user/{id}")
    public String info(@PathVariable(name = "id") int id, Model model) {
        String rightNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        User user = (User) session.getAttribute("user");
        // 查询用户信息
        UserInfo userInfo = userInfoService.findUserById(id);
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
        Map<String, Object> signByDate = signService.getSignByDate(user.getId(), rightNow);
        model.addAttribute("signByDate", signByDate);
        return "userinfo";
    }

    /**
     * 签到
     * @return
     */
    @GetMapping("/sign")
    public String sign() {
        String rightNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        User user = (User) session.getAttribute("user");
        signService.doSign(user.getId(), rightNow);
        return "redirect:/user/" + user.getId();
    }
}