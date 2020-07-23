package com.controller.admin;

import com.bean.BlogQuery;
import com.bean.Detail;
import com.bean.User;
import com.service.BlogService;
import com.service.TagService;
import com.service.TypeService;
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

import javax.servlet.http.HttpSession;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 博客页面跳转
     * @param model
     * @param detail
     * @param pageable
     * @return
     */
    @GetMapping("/blogs")
    public String list(Model model, BlogQuery detail, @PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable){
        // 所有分类
        model.addAttribute("types",typeService.listType());
        // 分页查询，所有博客
        model.addAttribute("page",blogService.listBlog(pageable, detail));
        return "admin/admin-blog";
    }

    /**
     * 分页搜索博客
     * @param model
     * @param detail
     * @param pageable
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(Model model, BlogQuery detail,@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("page",blogService.listBlog(pageable, detail));
        return "admin/admin-blog :: blogList";
    }

    /**
     * 提交新博客页面跳转
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog",new Detail());
        return "admin/admin-create";
    }

    /**
     * 修改博客功能
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Detail blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/admin-create";
    }

    /**
     * 发布-编辑功能公用
     * @param detail
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/blogs")
    public String post(Detail detail, HttpSession session, RedirectAttributes attributes){
        detail.setUser((User) session.getAttribute("user"));
        detail.setType(typeService.getType(detail.getType().getId()));
        detail.setTags(tagService.listTag(detail.getTagIds()));
        Detail detail1;
        // 发布，编辑判断
        // 过滤创建时间，浏览量，防止属性重复覆盖
        if(detail.getId() == null){
            // 发布博客
            detail1 = blogService.saveBlog(detail);
        }else{
            // 编辑博客
            detail1 = blogService.updateBlog(detail.getId(),detail);
        }
        if(detail1 == null){
            // 保存失败
            attributes.addFlashAttribute("message","操作失败");
        }else{
            // 保存成功
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 删除博客
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }
}
