package com.controller.admin;

import com.bean.BlogEntity;
import com.bean.Detail;
import com.bean.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.obs.services.ObsClient;
import com.service.BlogService;
import com.service.TagService;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Zm-Mmm
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private final BlogService blogService;

    private final TypeService typeService;

    private final TagService tagService;

    private final RedisTemplate redisTemplate;

    private static String blogTitle;
    @Value("${huawei.obs.ak}")
    private String ak;

    @Value("${huawei.obs.sk}")
    private String sk;

    @Value("${huawei.obs.endPoint}")
    private String endPoint;
    @Value("${huawei.obs.bucketName}")
    private String bucketName;

    @Autowired
    public BlogController(BlogService blogService, TypeService typeService, TagService tagService, RedisTemplate redisTemplate) {
        this.blogService = blogService;
        this.typeService = typeService;
        this.tagService = tagService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 博客页面跳转
     *
     * @param model 容器
     * @return 页面
     */
    @GetMapping("/blogs")
    public String list(Model model, HttpServletRequest request, @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum) {
        // 所有分类
        model.addAttribute("types", typeService.listType());
        // 所有博客
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 分页查询
        PageHelper.startPage(pageNum, 10);
        List<BlogEntity> blogEntities = blogService.selectDetailFromUserIdLimit(user.getId());
        // 得到分页结果对象
        PageInfo<BlogEntity> pageInfo = new PageInfo<>(blogEntities);
        model.addAttribute("page", pageInfo);
        return "admin/admin-blog";
    }

    /**
     * 上传博客图片到华为云obs
     *
     * @param file 文件名
     * @return 成功或失败的json
     * @throws IOException io异常
     */
    @PostMapping("/blogsImage")
    @ResponseBody
    public Map fileUpload(@RequestParam("editormd-image-file") MultipartFile file) throws IOException {
        Map<String, Object> hashMap = new LinkedHashMap<>();
        // 创建obs对象
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        // 获得后缀类型
        String type = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf("."));
        // 设置上传的文件名字
        String rightNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-SSS"));
        String filename = blogTitle + rightNow + type;
        // 获取上传流
        InputStream inputStream = file.getInputStream();
        // 上传
        obsClient.putObject(bucketName, filename, inputStream);
        // 拼接url
        String url = "https://" + bucketName + "." + endPoint + "/" + filename;
        // 返回上传路径
        hashMap.put("url", url);
        // 返回是否成功
        hashMap.put("success", 1);
        // 返回信息提示
        hashMap.put("message", "upload success!");
        obsClient.close();
        inputStream.close();
        return hashMap;
    }

    /**
     * 提交新博客页面跳转
     *
     * @param model 容器
     * @return 页面
     */
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Detail());
        return "admin/admin-create";
    }

    /**
     * 修改博客功能
     *
     * @param id    博客id
     * @param model 容器
     * @return 页面
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model,HttpSession session) {
        // 校验跳转的博客是否是该用户的博客
        User user = (User) session.getAttribute("user");
        Detail blog = blogService.getBlog(id,user.getId());
        blog.init();
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        blogTitle = blog.getTitle();
        model.addAttribute("blog", blog);
        return "admin/admin-create";
    }

    /**
     * 发布-编辑功能公用
     *
     * @param detail     博客
     * @param session    session
     * @param attributes 重定向容器
     * @return 重定向页面
     */
    @PostMapping("/blogs")
    public String post(Detail detail, HttpSession session, RedirectAttributes attributes) {
        detail.setUser((User) session.getAttribute("user"));
        detail.setType(typeService.getType(detail.getType().getId()));
        detail.setTags(tagService.listTag(detail.getTagIds()));
        Detail detail1;
        // 发布，编辑判断
        // 过滤创建时间，浏览量，防止属性重复覆盖
        if (detail.getId() == null) {
            // 发布博客
            detail1 = blogService.saveBlog(detail);
        } else {
            // 编辑博客
            detail1 = blogService.updateBlog(detail.getId(), detail);
        }
        if (detail1 == null) {
            // 保存失败
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            // 保存成功
            // 清空首页分类、标签、推荐博客redis缓存
            redisTemplate.opsForHash().delete("index", "tags");
            redisTemplate.opsForHash().delete("index", "types");
            redisTemplate.opsForHash().delete("index", "recommends");
            // 清空所有分类、标签redis缓存
            redisTemplate.opsForHash().delete("menu","types");
            redisTemplate.opsForHash().delete("menu","tags");
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 删除博客
     *
     * @param id         博客id
     * @param attributes 重定向容器
     * @return 重定向
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        // 清空首页分类、标签、推荐博客redis缓存
        redisTemplate.opsForHash().delete("index", "tags");
        redisTemplate.opsForHash().delete("index", "types");
        redisTemplate.opsForHash().delete("index", "recommends");
        // 清空所有分类、标签redis缓存
        redisTemplate.opsForHash().delete("menu","types");
        redisTemplate.opsForHash().delete("menu","tags");
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
