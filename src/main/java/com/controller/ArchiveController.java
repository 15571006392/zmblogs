package com.controller;

import com.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Zm-Mmm
 */
@Controller
public class ArchiveController {

    @Autowired
    private BlogService blogService;

    /**
     * 返回归档页面
     * @param model
     * @return
     */
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());

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
        return "archives";
    }
}
