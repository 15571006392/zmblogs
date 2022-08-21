package com.controller;

import com.bean.Tag;
import com.service.BlogService;
import com.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Zm-Mmm
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 100, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, @PathVariable Long id) {
        // 2333代指所有标签数据
        List<Tag> list = tagService.listTagTop(2333);
        /*
        如果是通过导航栏跳转
        id默认为-1
        默认赋值给所有标签中的第一个标签
         */
        if(id == -1){
            id = list.get(0).getId();
        }
        model.addAttribute("tags",list);
        // 分页查询
        model.addAttribute("page",blogService.listBlog(id,pageable));
        // 当前活跃的id
        model.addAttribute("activeTagId",id);

        return "tags";
    }
}
