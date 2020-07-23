package com.controller.admin;

import com.bean.Tag;
import com.bean.Type;
import com.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 标签页面跳转
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    /**
     * 新增页面跳转
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-create";
    }

    /**
     * 更新页面跳转
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-create";
    }

    /**
     * 新增标签功能
     * @param tag
     * @param attributes
     * @param model
     * @return
     */
    @PostMapping("/tags-create")
    public String post(Tag tag, RedirectAttributes attributes, Model model){
        Tag tag2 = tagService.getTagByName(tag.getName());
        if(tag2 != null){
            model.addAttribute("cfmsg","不能重复添加标签");
            model.addAttribute("tag",tag2);
            return "admin/tags-create";
        }
        Tag tag1 = tagService.saveTag(tag);
        if(tag1 == null){
            // 保存失败
            attributes.addFlashAttribute("message","添加失败");
        }else{
            // 保存成功
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 更新标签请求
     * @param tag
     * @param id
     * @param attributes
     * @param model
     * @return
     */
    @PostMapping("/tags-create/{id}")
    public String editPost(Tag tag,@PathVariable Long id, RedirectAttributes attributes,Model model){
        Tag tag2 = tagService.getTagByName(tag.getName());
        if(tag2 != null){
            model.addAttribute("cfmsg","不能重复添加标签");
            model.addAttribute("tag",tag2);
            return "admin/tags-create";
        }
        Tag tag1 = tagService.saveTag(tag);
        if(tag1 == null){
            // 保存失败
            attributes.addFlashAttribute("message","更新失败");
        }else{
            // 保存成功
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
