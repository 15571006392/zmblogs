package com.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));

        Properties properties = new Properties();
        Properties properties2 = new Properties();
        try {
            File file = new File("C:\\ip.properties");
            File file2 = new File("C:\\visitors.properties");
            properties.load(new FileInputStream(file));
            properties2.load(new FileInputStream(file2));
            int count = Integer.parseInt(properties2.getProperty("count"));
            int num = properties.size();
            model.addAttribute("count",count);
            model.addAttribute("mannum",num);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,Model model,@RequestParam String query){
        model.addAttribute("page",blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query",query);

        Properties properties = new Properties();
        Properties properties2 = new Properties();
        try {
            File file = new File("C:\\ip.properties");
            File file2 = new File("C:\\visitors.properties");
            properties.load(new FileInputStream(file));
            properties2.load(new FileInputStream(file2));
            int count = Integer.parseInt(properties2.getProperty("count"));
            int num = properties.size();
            model.addAttribute("count",count);
            model.addAttribute("mannum",num);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog",blogService.getAndConvert(id));

        Properties properties = new Properties();
        Properties properties2 = new Properties();
        try {
            File file = new File("C:\\ip.properties");
            File file2 = new File("C:\\visitors.properties");
            properties.load(new FileInputStream(file));
            properties2.load(new FileInputStream(file2));
            int count = Integer.parseInt(properties2.getProperty("count"));
            int num = properties.size();
            model.addAttribute("count",count);
            model.addAttribute("mannum",num);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "detail";
    }
}
