package com.service.impl;

import com.bean.BlogEntity;
import com.bean.Detail;
import com.dao.BlogRepository;
import com.dao.DetailDao;
import com.service.BlogService;
import com.util.Markdown;
import com.util.NullBeanProperties;
import com.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @author Zm-Mmm
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    private final DetailDao detailDao;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, DetailDao detailDao) {
        this.blogRepository = blogRepository;
        this.detailDao = detailDao;
    }

    @Override
    public List<BlogEntity> findIndexRecommendBlog(Integer count) {
        return detailDao.findIndexRecommendBlog(count);
    }

    @Override
    public List<BlogEntity> findAllBlogs() {
        return detailDao.findAllBlogs();
    }

    @Override
    public List<BlogEntity> findAllBlogsByType(int id) {
        return detailDao.findAllBlogsByType(id);
    }

    @Override
    public List<BlogEntity> findAllBlogsByTag(int id) {
        return detailDao.findAllBlogsByTag(id);
    }

    /**
     * 根据id获取博客
     *
     * @param id 博客id
     * @return 博客详情
     */
    @Override
    public Detail getBlog(Long id) {
        Optional<Detail> byId = blogRepository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundException("不存在博客");
        }
        return byId.get();
    }

    /**
     * 根据id获取博客
     * 校验该博客是不是当前用户的博客
     *
     * @param id     博客id
     * @param userId 用户id
     * @return 博客详情
     */
    @Override
    public Detail getBlog(Long id, Long userId) {
        Optional<Detail> byId = blogRepository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundException("博客不存在");
        }
        if (!byId.get().getUser().getId().equals(userId)) {
            throw new NotFoundException("权限不够");
        }
        return byId.get();
    }

    @Override
    public List<BlogEntity> selectDetailFromUserIdLimit(Long id) {
        return detailDao.selectDetailFromUserIdLimit(id);
    }

    /**
     * markdown转html
     *
     * @param id 博客id
     * @return 博客详情
     */
    @Override
    @Transactional
    public Detail getAndConvert(Long id) {
        Optional<Detail> byId = blogRepository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundException("该博客不存在");
        }
        if(!byId.get().isPublished()){
            throw new NotFoundException("该博客已下架");
        }
        Detail detail = byId.get();
        Detail b = new Detail();
        BeanUtils.copyProperties(detail, b);
        String content = b.getContent();
        b.setContent(Markdown.markdownToHtmlExtensions(content));

        /*
          访问次数磊加
         */
        blogRepository.updateViews(id);
        return b;
    }

    /**
     * 搜索博客
     *
     * @param query 用户输入
     * @return 博客列表
     */
    @Override
    public List<BlogEntity> searchBlogs(String query) {
        return detailDao.searchBlogs(query);
    }

    @Override
    public List<Detail> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Map<String, List<Detail>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Detail>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    /**
     * 总数
     *
     * @return 博客总数
     */
    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    /**
     * 保存博客
     *
     * @param detail 博客
     * @return 博客对象
     */
    @Override
    @Transactional
    public Detail saveBlog(Detail detail) {
        // 首次创建，初始化属性
        if (detail.getId() == null) {
            detail.setCreateTime(new Date());
            detail.setUpdateTime(new Date());
            detail.setViews(0);
        } else {
            detail.setUpdateTime(new Date());
        }
        return blogRepository.save(detail);
    }

    /**
     * 新增博客
     *
     * @param id     博客id
     * @param detail 博客
     * @return 新增博客对象
     */
    @Override
    @Transactional
    public Detail updateBlog(Long id, Detail detail) {
        Optional<Detail> byId = blogRepository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundException("该博客不存在");
        }
        Detail detail1 = byId.get();
        // 获取非空元素，复制，防止覆盖createTime和views
        BeanUtils.copyProperties(detail, detail1, NullBeanProperties.getNullProperties(detail));
        // 更新时间
        detail1.setUpdateTime(new Date());
        return blogRepository.save(detail1);
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
