package com.controller.admin;

import com.service.MessageService;
import com.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class LeavingMessageAdminController {

    private final MessageService messageService;

    private final RedisTemplate redisTemplate;

    @Autowired
    public LeavingMessageAdminController(MessageService messageService, RedisTemplate redisTemplate) {
        this.messageService = messageService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 留言页面跳转
     *
     * @param pageable 分页
     * @param model 容器
     * @return 主页
     */
    @GetMapping("/leavingmessage")
    public String show(@PageableDefault(size = 10, sort = {"ct"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", messageService.listLeavingMessage(pageable));
        return "admin/leaving-message";
    }

    /**
     * 留言详情页面跳转
     *
     * @param id 留言id
     * @param model 容器
     * @return 页面
     */
    @GetMapping("/leavingmessage/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("leavingmessage", messageService.getLeavingMessage(id));
        return "admin/leaving-message-input";
    }

    /**
     * 删除
     *
     * @param id 留言id
     * @param attributes 重定向容器
     * @return 重定向主页
     */
    @GetMapping("/leavingmessage/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        messageService.deleteLeavingMessage(id);
        // 清空留言缓存
        RedisUtil.flushRedisLeavingMessage(redisTemplate);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/leavingmessage";
    }
}
