package com.controller;

import com.bean.LeavingMessage;
import com.bean.User;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zm-Mmm
 */
@Controller
public class LeavingMessageController {

    private final MessageService messageService;

    @Autowired
    public LeavingMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 留言页面跳转
     *
     * @param model
     * @return
     */
    @GetMapping("/leavingmessage")
    public String show(Model model) {
        // 获取所有留言
        List<LeavingMessage> leavingMessages = messageService.getLeavingMessage();
        List<LeavingMessage> end = new ArrayList<>();
        // 指定展示的留言条数
        int size = 5;
        // 按留言提交时间展示最新的5条
        if (leavingMessages.size() > size) {
            end.add(leavingMessages.get(leavingMessages.size() - 1));
            end.add(leavingMessages.get(leavingMessages.size() - 2));
            end.add(leavingMessages.get(leavingMessages.size() - 3));
            end.add(leavingMessages.get(leavingMessages.size() - 4));
            end.add(leavingMessages.get(leavingMessages.size() - 5));
            model.addAttribute("newLeavingMessage", end);
        } else {
            model.addAttribute("newLeavingMessage", leavingMessages);
        }
        return "leavingmessage";
    }

    /**
     * 提交留言
     * @param yourName
     * @param email
     * @param yourMessage
     * @param avatar
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/leavingmessage/getmessage")
    public String getMessage(@RequestParam String yourName, @RequestParam String email, @RequestParam String yourMessage, @Value("${comment.avatar}") String avatar, RedirectAttributes attributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            yourName = user.getNickname();
            email = user.getEmail();
            avatar = user.getAvatar();
        }
        if (messageService.updateLeavingMessage(yourName, email, yourMessage, avatar) == 1) {
            attributes.addFlashAttribute("avatar",avatar);
            attributes.addFlashAttribute("success", "提交成功，感谢反馈！");
        } else {
            attributes.addFlashAttribute("fail", "提交失败，服务器出错！");
        }
        return "redirect:/leavingmessage";
    }
}
