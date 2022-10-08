package com.service.impl;

import com.bean.BlogEntity;
import com.bean.Detail;
import com.bean.TagEntity;
import com.dao.BlogRepository;
import com.dao.DetailDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BlogService;
import com.service.TagService;
import com.util.Markdown;
import com.util.NullBeanProperties;
import com.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @author Zm-Mmm
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final TagService tagService;

    private final BlogRepository blogRepository;

    private final DetailDao detailDao;

    private final RedisTemplate redisTemplate;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, DetailDao detailDao, RedisTemplate redisTemplate, TagService tagService) {
        this.blogRepository = blogRepository;
        this.detailDao = detailDao;
        this.redisTemplate = redisTemplate;
        this.tagService = tagService;
    }

    /**
     * 查询首页推荐博客，指定数量，按照博客更新日期排序
     *
     * @param count 推荐数量
     * @return 推荐博客集合
     */
    @Override
    public List<BlogEntity> findIndexRecommendBlog(Integer count) {
        // 先从redis中找
        List<BlogEntity> indexRecommends = (List<BlogEntity>) redisTemplate.opsForHash().get("index", "recommends");
        if (indexRecommends == null) {
            // 从mysql中找
            List<BlogEntity> indexRecommendBlog = detailDao.findIndexRecommendBlog(count);
            // 添加到redis
            redisTemplate.opsForHash().put("index", "recommends", indexRecommendBlog);
            // 设置超时时间 1天
            redisTemplate.expire("index", 60 * 60 * 24, TimeUnit.SECONDS);
            return indexRecommendBlog;
        } else {
            // 找到了
            return indexRecommends;
        }
    }

    /**
     * 查询全部博客
     * 过滤博客状态为草稿的博客
     *
     * @param pageNum 页码
     * @param size    分页大小
     * @return 分页查询
     */
    @Override
    public PageInfo<BlogEntity> findAllBlogs(int pageNum, int size) {
        PageHelper.startPage(pageNum, 10);
        List<BlogEntity> allBlogs = detailDao.findAllBlogs();
        return new PageInfo<>(allBlogs);
    }

    /**
     * 查询指定分类下的所有博客
     * 过滤博客状态为草稿的博客
     *
     * @param pageNum 当前页数
     * @param size    分页大小
     * @param id      当前分裂id
     * @return 分页查询结果
     */
    @Override
    public PageInfo<BlogEntity> findAllBlogsByType(int pageNum, int size, int id) {
        PageHelper.startPage(pageNum, 10);
        List<BlogEntity> allBlogsByType = detailDao.findAllBlogsByType(id);
        if (allBlogsByType.size() == 0) {
            throw new NotFoundException("分类不存在");
        }
        return new PageInfo<>(allBlogsByType);
    }

    /**
     * 查询指定标签下的所有博客
     * 过滤博客状态为草稿的博客
     *
     * @param pageNum 当前页数
     * @param size    分页大小
     * @param id      当前标签id
     * @return 分页查询结果
     */
    @Override
    public PageInfo<BlogEntity> findAllBlogsByTag(int pageNum, int size, int id) {
        PageHelper.startPage(pageNum, 10);
        List<BlogEntity> allBlogsByTag = detailDao.findAllBlogsByTag(id);

        // 查询指定博客的所有标签并替换到分页结果对象
        allBlogsByTag.forEach(values -> {
            List<TagEntity> tagByDetail = tagService.findTagByDetail(values.getId());
            values.setTags(tagByDetail);
        });
        if (allBlogsByTag.size() == 0) {
            throw new NotFoundException("标签不存在");
        }
        return new PageInfo<>(allBlogsByTag);
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
    @Transactional(rollbackFor = Exception.class)
    public Detail getAndConvert(Long id) {
        Optional<Detail> byId = blogRepository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundException("该博客不存在");
        }
        if (!byId.get().isPublished()) {
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
     * @param pageNum 页码
     * @param size    分页大小
     * @param query   用户输入
     * @return 分页查询所有博客
     */
    @Override
    public PageInfo<BlogEntity> searchBlogs(int pageNum, int size, String query) {
        PageHelper.startPage(pageNum, 10);
        List<BlogEntity> blogEntities = detailDao.searchBlogs(query);
        return new PageInfo<>(blogEntities);
    }

    @Override
    public List<Detail> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Map<String, List<Detail>> archiveBlog() {
        // 先从redis中找
        Map<String, List<Detail>> archiveBlogs = (Map<String, List<Detail>>) redisTemplate.opsForHash().get("menu", "archiveBlogs");
        if (archiveBlogs == null) {
            // 查找博客年份
            List<String> years = blogRepository.findGroupYear();
            // 存放博客的map容器，key为博客年份
            Map<String, List<Detail>> map = new HashMap<>(countBlog().intValue());
            for (String year : years) {
                List<Detail> byYear = blogRepository.findByYear(year);
                // 把有关联关系的字段置为null
                byYear.forEach(values -> {
                    values.setTags(null);
                    values.setType(null);
                    values.setUser(null);
                    values.setContent(null);
                    values.setComments(null);
                });
                map.put(year, byYear);
            }
            // 存入redis
            redisTemplate.opsForHash().put("menu", "archiveBlogs", map);
            // 设置超时时间 1天
            redisTemplate.expire("menu", 60 * 60 * 24, TimeUnit.SECONDS);
            return map;
        }
        return archiveBlogs;
    }

    /**
     * 总数
     *
     * @return 博客总数
     */
    @Override
    public Long countBlog() {
        // 先从redis中找博客总数
        Integer archiveCount = (Integer) redisTemplate.opsForHash().get("menu", "archiveCount");
        if (archiveCount == null) {
            // 从mysql中查询
            long count = blogRepository.count();
            // 加入redis
            redisTemplate.opsForHash().put("menu", "archiveCount", count);
            // 设置超时时间 1天
            redisTemplate.expire("menu", 60 * 60 * 24, TimeUnit.SECONDS);
            return count;
        }
        return archiveCount.longValue();
    }

    /**
     * 保存博客
     *
     * @param detail 博客
     * @return 博客对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
