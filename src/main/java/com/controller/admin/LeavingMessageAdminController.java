package com.controller.admin;

import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class LeavingMessageAdminController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/leavingmessage")
    public String show(@PageableDefault(size = 10,sort = {"ct"},direction = Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("page",messageService.listLeavingMessage(pageable));
        return "admin/leaving-message";
    }

    @GetMapping("/leavingmessage/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("leavingmessage",messageService.getLeavingMessage(id));
        return "admin/leaving-message-input";
    }

    @GetMapping("/leavingmessage/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        messageService.deleteLeavingMessage(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/leavingmessage";
    }
}
