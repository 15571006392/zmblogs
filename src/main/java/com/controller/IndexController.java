package com.controller;

import com.bean.BlogEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BlogService;
import com.service.TagService;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zm-Mmm
 */
@Controller
public class IndexController {

    private final BlogService blogService;

    private final TypeService typeService;

    private final TagService tagService;

    @Autowired
    public IndexController(BlogService blogService, TypeService typeService, TagService tagService) {
        this.blogService = blogService;
        this.typeService = typeService;
        this.tagService = tagService;
    }

    /**
     * 主页跳转
     * @param model 容器
     * @param pageNum 分页
     * @return index页
     */
    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum) {
        // 分页查询所有博客
        PageHelper.startPage(pageNum,10);
        List<BlogEntity> blogEntities = blogService.findAllBlogs();
        // 得到分页结果对象
        PageInfo<BlogEntity> pageInfo = new PageInfo<>(blogEntities);
        model.addAttribute("page", pageInfo);
        // 查找前6个分类
        model.addAttribute("types", typeService.listTypeTop(6));
        // 查找前10个标签
        model.addAttribute("tags", tagService.listTagTop(10));
        // 查找前8个推荐的博客
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    /**
     * 搜索功能
     * @param pageNum 分页
     * @param model 容器
     * @param query 用户输入的内容
     * @return 页面
     */
    @RequestMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model, @RequestParam String query) {
        // 分页查询，从标题、内容、描述中查询，通过博客更新时间倒序排序
        PageHelper.startPage(pageNum,10);
        List<BlogEntity> blogEntities = blogService.searchBlogs(query);
        PageInfo<BlogEntity> blogEntityPageInfo = new PageInfo<>(blogEntities);
        model.addAttribute("page", blogEntityPageInfo);
        // 用户输入的内容
        model.addAttribute("query", query);
        return "search";
    }

    /**
     * 根据id查找指定博客
     * @param id 博客id
     * @param model 容器
     * @return detail博客页
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        // markdown格式转换
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "detail";
    }
}
