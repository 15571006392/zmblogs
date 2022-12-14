package com.controller;

import com.NotFoundException;
import com.bean.BlogEntity;
import com.bean.TagEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BlogService;
import com.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Zm-Mmm
 */
@Controller
public class TagShowController {

    private final TagService tagService;

    private final BlogService blogService;

    @Autowired
    public TagShowController(TagService tagService, BlogService blogService) {
        this.tagService = tagService;
        this.blogService = blogService;
    }

    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model, @PathVariable int id) {
        // 查询所有标签，每个标签的博客数量不包含草稿状态，不显示博客为0的标签
        List<TagEntity> list = tagService.findTag();
        /*
        如果是通过导航栏跳转
        id默认为-1
        默认赋值给所有标签中的第一个标签
         */
        if (id == -1) {
            id = list.get(0).getId();
        }
        model.addAttribute("tags", list);

        // 分页查询 指定标签的所有博客
        PageInfo<BlogEntity> allBlogsByTag = blogService.findAllBlogsByTag(pageNum, 10, id);
        model.addAttribute("page", allBlogsByTag);

        // 当前活跃的id
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
