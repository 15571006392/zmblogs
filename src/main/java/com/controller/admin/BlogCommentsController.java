package com.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zm-Mmm
 */
@Controller()
@RequestMapping("/admin")
public class BlogCommentsController {

    @GetMapping("/blogComments")
    public String index(){
        return "admin/blogComment";
    }
}
