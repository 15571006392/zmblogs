package com.controller.admin;

import com.bean.Type;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    private final TypeService typeService;

    private final RedisTemplate redisTemplate;

    @Autowired
    public TypeController(TypeService typeService,RedisTemplate redisTemplate) {
        this.typeService = typeService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 分类页面跳转
     * @param pageable 分页
     * @param model 容器
     * @return types页面
     */
    @GetMapping("/types")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    /**
     * 新增页面跳转
     * @param model 容器
     * @return types-create页面
     */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-create";
    }

    /**
     * 更新页面跳转
     * @param id 分类id
     * @param model 容器
     * @return 更新页面
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-create";
    }

    /**
     * 新增分类功能
     * @param type 分类
     * @param attributes 重定向容器
     * @param model 容器
     * @return 页面
     */
    @PostMapping("/types-create")
    public String post(Type type, RedirectAttributes attributes,Model model){
        Type type2 = typeService.getTypeByName(type.getName());
        if(type2 != null){
            model.addAttribute("cfmsg","不能重复添加分类");
            model.addAttribute("type",type2);
            return "admin/types-create";
        }
        Type type1 = typeService.saveType(type);
        if(type1 == null){
            // 保存失败
            attributes.addFlashAttribute("message","添加失败");
        }else{
            // 保存成功
            // 清空首页分类redis缓存
            redisTemplate.opsForHash().delete("index","types");
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 更新分类功能
     * @param type 分类
     * @param id 分类id
     * @param attributes 重定向容器
     * @param model 容器
     * @return 页面
     */
    @PostMapping("/types-create/{id}")
    public String editPost(Type type,@PathVariable Long id, RedirectAttributes attributes,Model model){
        Type type2 = typeService.getTypeByName(type.getName());
        if(type2 != null){
            model.addAttribute("cfmsg","不能重复添加分类");
            model.addAttribute("type",type2);
            return "admin/types-create";
        }
        Type type1 = typeService.updateType(id,type);
        if(type1 == null){
            // 保存失败
            attributes.addFlashAttribute("message","更新失败");
        }else{
            // 保存成功
            // 清空首页分类redis缓存
            redisTemplate.opsForHash().delete("index","types");
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除
     * @param id 分类id
     * @param attributes 重定向容器
     * @return 页面
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        // 清空首页分类redis缓存
        redisTemplate.opsForHash().delete("index","types");
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
