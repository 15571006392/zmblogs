package com.controller;

import com.bean.MessageEntity;
import com.bean.User;
import com.service.MessageService;
import com.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Zm-Mmm
 */
@Controller
public class LeavingMessageController {

    private final MessageService messageService;

    private final RedisTemplate redisTemplate;

    @Autowired
    public LeavingMessageController(MessageService messageService,RedisTemplate redisTemplate) {
        this.messageService = messageService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 留言页面跳转
     *
     * @param model 容器
     * @return 留言页面
     */
    @GetMapping("/leavingmessage")
    public String show(Model model) {
        // 获取指定的留言数量
        List<MessageEntity> messageByCount = messageService.findMessageByCount(5);
        model.addAttribute("newLeavingMessage", messageByCount);
        return "leavingmessage";
    }

    /**
     * 提交留言
     * @param yourName 名称
     * @param email 邮箱
     * @param yourMessage 留言内容
     * @param avatar 头像
     * @param attributes 容器
     * @param session session
     * @return 重定向到留言页面
     */
    @PostMapping("/leavingmessage/getmessage")
    public String getMessage(@RequestParam String yourName, @RequestParam String email, @RequestParam String yourMessage, @Value("${comment.avatar}") String avatar, RedirectAttributes attributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            yourName = user.getNickname();
            email = user.getEmail();
            avatar = user.getAvatar();
        }
        if (messageService.insertLeavingMessage(yourName,email,yourMessage,avatar) == 1) {
            attributes.addFlashAttribute("avatar",avatar);
            // 清空留言缓存
            RedisUtil.flushRedisLeavingMessage(redisTemplate);
            attributes.addFlashAttribute("success", "提交成功，感谢反馈！");
        } else {
            attributes.addFlashAttribute("fail", "提交失败，服务器出错！");
        }
        return "redirect:/leavingmessage";
    }
}
