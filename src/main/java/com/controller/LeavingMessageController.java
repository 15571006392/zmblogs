package com.controller;

import com.bean.LeavingMessage;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class LeavingMessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/leavingmessage")
    public String show(Model model){
        List<LeavingMessage> leavingMessages = messageService.getLeavingMessage();
        List<LeavingMessage> end = new ArrayList<>();
        if(leavingMessages.size() > 5){
            end.add(leavingMessages.get(leavingMessages.size()-1));
            end.add(leavingMessages.get(leavingMessages.size()-2));
            end.add(leavingMessages.get(leavingMessages.size()-3));
            end.add(leavingMessages.get(leavingMessages.size()-4));
            end.add(leavingMessages.get(leavingMessages.size()-5));
            model.addAttribute("newLeavingMessage",end);
        }else{
            model.addAttribute("newLeavingMessage",leavingMessages);
        }

        Properties properties = new Properties();
        Properties properties2 = new Properties();
        try {
            File file = new File("C:\\ip.properties");
            File file2 = new File("C:\\visitors.properties");
            properties.load(new FileInputStream(file));
            properties2.load(new FileInputStream(file2));
            int count = Integer.parseInt(properties2.getProperty("count"));
            int num = properties.size();
            model.addAttribute("count",count);
            model.addAttribute("mannum",num);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "leavingmessage";
    }

    @PostMapping("/leavingmessage/getmessage")
    public String getMessage(@RequestParam String yourName, @RequestParam String email, @RequestParam String yourMessage, RedirectAttributes attributes){
        if(messageService.updateLeavingMessage(yourName,email,yourMessage) == 1){
            attributes.addFlashAttribute("success","提交成功，感谢反馈！");
        }else{
            attributes.addFlashAttribute("fail","提交失败，服务器出错！");
        }
        return "redirect:/leavingmessage";
    }
}
