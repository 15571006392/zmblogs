package com.controller;

import com.NotFoundException;
import com.bean.BlogEntity;
import com.bean.TypeEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BlogService;
import com.service.TypeService;
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
public class TypeShowController {

    private final TypeService typeService;

    private final BlogService blogService;

    @Autowired
    public TypeShowController(TypeService typeService, BlogService blogService) {
        this.typeService = typeService;
        this.blogService = blogService;
    }

    @GetMapping("/types/{id}")
    public String types(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model, @PathVariable int id) {
        // 查询所有分类，按照分类ID，分类name进行分组
        List<TypeEntity> list = typeService.findType();
        /*
        如果是通过导航栏跳转
        id默认为-1
        默认赋值给所有分类中的第一个分类
         */
        if (id == -1) {
            id = list.get(0).getId();
        }
        model.addAttribute("types", list);
        // 分页查询
        PageHelper.startPage(pageNum, 10);
        List<BlogEntity> allBlogsByType = blogService.findAllBlogsByType(id);
        if (allBlogsByType.size() == 0) {
            throw new NotFoundException("分类不存在");
        }
        // 得到分页结果对象
        PageInfo<BlogEntity> pageInfo = new PageInfo<>(allBlogsByType);
        model.addAttribute("page", pageInfo);
        // 当前活跃的id
        model.addAttribute("activeTypeId", id);
        return "type";
    }
}
