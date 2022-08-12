package com.controller;

import com.bean.BlogQuery;
import com.bean.Type;
import com.service.BlogService;
import com.service.TypeService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, @PathVariable Long id) {
        // 2333代指所有分类数据
        List<Type> list = typeService.listTypeTop(2333);
        /*
        如果是通过导航栏跳转
        id默认为-1
        默认赋值给所有分类中的第一个分类
         */
        if(id == -1){
            id = list.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",list);
        // 分页查询
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        // 当前活跃的id
        model.addAttribute("activeTypeId",id);

        return "type";
    }
}
