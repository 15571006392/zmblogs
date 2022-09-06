package com.controller.admin;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.bean.Detail;
import com.bean.User;
import com.bean.UserDetail;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BlogService;
import com.service.TagService;
import com.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 博客页面跳转
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String list(Model model, HttpServletRequest request, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum){
        // 所有分类
        model.addAttribute("types",typeService.listType());
        // 所有博客
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 分页查询
        PageHelper.startPage(pageNum,10);
        List<UserDetail> userDetails = blogService.selectDetailFromUserIdLimit(user.getId());
        // 得到分页结果对象
        PageInfo<UserDetail> pageInfo = new PageInfo<>(userDetails);
        model.addAttribute("page",pageInfo);
        return "admin/admin-blog";
    }

    /**
     * 博客图片上传方法
     * @param file 图片
     * @param guid 上传的图片的名字
     * @return 上传成功或失败的json
     * @throws IOException
     */
    @PostMapping("/blogsImage")
    @ResponseBody
    public Map fileUpload(@RequestParam("editormd-image-file") MultipartFile file, String guid) throws IOException {
        Map<String, Object> hashMap = new LinkedHashMap<>();

        // 获得后缀类型
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
        // 获得文件上传路径
        String path = System.getProperty("user.dir")+"/src/main/resources/static/BlogInputImages/";
        // 将路径传给 File 对象
        File realPath = new File(path);
        // 判断路径上的文件夹是否存在，不存在就创建
        if (!realPath.exists()){
            realPath.mkdir();
        }
        // 设置上传的文件名字
        String filename = guid + type;
        //通过CommonsMultipartFile的方法直接写文件
        file.transferTo(new File(realPath +"/"+ filename));

        // 返回上传路径
        hashMap.put("url","/BlogInputImages/" + filename);
        // 返回是否成功
        hashMap.put("success", 1);
        // 返回信息提示
        hashMap.put("message", "upload success!");
        return hashMap;
    }

    /**
     * 分页搜索博客
     * @param model
     * @param detail
     * @param pageable
     * @return
     */
    /*@PostMapping("/blogs/search")
    public String search(Model model, BlogQuery detail,@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("page",blogService.listBlog(pageable, detail));
        return "admin/admin-blog :: blogList";
    }*/

    /**
     * 提交新博客页面跳转
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog",new Detail());
        return "admin/admin-create";
    }

    /**
     * 修改博客功能
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Detail blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/admin-create";
    }

    /**
     * 发布-编辑功能公用
     * @param detail
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/blogs")
    public String post(Detail detail, HttpSession session, RedirectAttributes attributes){
        detail.setUser((User) session.getAttribute("user"));
        detail.setType(typeService.getType(detail.getType().getId()));
        detail.setTags(tagService.listTag(detail.getTagIds()));
        Detail detail1;
        // 发布，编辑判断
        // 过滤创建时间，浏览量，防止属性重复覆盖
        if(detail.getId() == null){
            // 发布博客
            detail1 = blogService.saveBlog(detail);
        }else{
            // 编辑博客
            detail1 = blogService.updateBlog(detail.getId(),detail);
        }
        if(detail1 == null){
            // 保存失败
            attributes.addFlashAttribute("message","操作失败");
        }else{
            // 保存成功
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 删除博客
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }
}
