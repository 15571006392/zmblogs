package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserInfo {

    @GetMapping("/user/{id}")
    public String info(){
        return "userinfo";
    }
}
