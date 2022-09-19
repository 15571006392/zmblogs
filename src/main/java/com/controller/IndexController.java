package com.controller;

import com.bean.BlogEntity;
import com.bean.TagEntity;
import com.bean.TypeEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BlogService;
import com.service.TagService;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zm-Mmm
 */
@Controller
public class IndexController {

    private final BlogService blogService;

    private final TypeService typeService;

    private final TagService tagService;

    private final RedisTemplate redisTemplate;

    @Autowired
    public IndexController(BlogService blogService, TypeService typeService, TagService tagService, RedisTemplate redisTemplate) {
        this.blogService = blogService;
        this.typeService = typeService;
        this.tagService = tagService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 主页跳转
     *
     * @param model   容器
     * @param pageNum 分页
     * @return index页
     */
    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum) {
        // 分页查询所有博客
        PageHelper.startPage(pageNum, 10);
        List<BlogEntity> blogEntities = blogService.findAllBlogs();
        // 得到分页结果对象
        PageInfo<BlogEntity> pageInfo = new PageInfo<>(blogEntities);
        model.addAttribute("page", pageInfo);

        // 查找前6个分类，过滤草稿状态博客
        // 先从redis中找
        List<TypeEntity> indexTypes = (List<TypeEntity>) redisTemplate.opsForHash().get("index", "types");
        if (indexTypes == null) {
            // 从mysql中找
            List<TypeEntity> indexType = typeService.findIndexType(6);
            // 添加到redis
            redisTemplate.opsForHash().put("index", "types", indexType);
            model.addAttribute("types", indexType);
        } else {
            // 找到了
            model.addAttribute("types", indexTypes);
        }

        // 查找前10个标签，过滤草稿状态博客
        // 先从redis中找
        List<TagEntity> indexTags = (List<TagEntity>) redisTemplate.opsForHash().get("index", "tags");
        if (indexTags == null) {
            // 从mysql中找
            List<TagEntity> indexTag = tagService.findIndexTag(10);
            // 添加到redis
            redisTemplate.opsForHash().put("index", "tags", indexTag);
            model.addAttribute("tags", indexTag);
        } else {
            // 找到了
            model.addAttribute("tags", indexTags);
        }

        // 查找前8个推荐的博客，过滤草稿状态博客
        // 先从redis中找
        List<BlogEntity> indexRecommends = (List<BlogEntity>) redisTemplate.opsForHash().get("index", "recommends");
        if (indexRecommends == null) {
            // 从mysql中找
            List<BlogEntity> indexRecommendBlog = blogService.findIndexRecommendBlog(8);
            // 添加到redis
            redisTemplate.opsForHash().put("index", "recommends", indexRecommendBlog);
            model.addAttribute("recommendBlogs", indexRecommendBlog);
        } else {
            // 找到了
            model.addAttribute("recommendBlogs", indexRecommends);
        }
        return "index";
    }

    /**
     * 搜索功能
     *
     * @param pageNum 分页
     * @param model   容器
     * @param query   用户输入的内容
     * @return 页面
     */
    @RequestMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model, @RequestParam String query) {
        // 分页查询，从标题、内容、描述中查询，通过博客更新时间倒序排序
        PageHelper.startPage(pageNum, 10);
        List<BlogEntity> blogEntities = blogService.searchBlogs(query);
        PageInfo<BlogEntity> blogEntityPageInfo = new PageInfo<>(blogEntities);
        model.addAttribute("page", blogEntityPageInfo);
        // 用户输入的内容
        model.addAttribute("query", query);
        return "search";
    }

    /**
     * 根据id查找指定博客
     *
     * @param id    博客id
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
