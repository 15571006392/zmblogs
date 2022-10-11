package com.service.impl;

import com.NotFoundException;
import com.alibaba.fastjson2.JSON;
import com.bean.Tag;
import com.bean.TagEntity;
import com.dao.TagDao;
import com.dao.TagRepository;
import com.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Zm-Mmm
 */
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    private final TagDao tagDao;

    private final RedisTemplate redisTemplate;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagDao tagDao, RedisTemplate redisTemplate) {
        this.tagRepository = tagRepository;
        this.tagDao = tagDao;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 查询首页标签，指定数量，按照标签的博客数量排序，过滤草稿状态博客
     *
     * @param count 标签数量
     * @return 标签结果
     */
    @Override
    public List<TagEntity> findIndexTag(Integer count) {
        // 先从redis中找
        List<TagEntity> indexTags = (List<TagEntity>) redisTemplate.opsForHash().get("index", "tags");
        if (indexTags == null) {
            // 从mysql中找
            List<TagEntity> indexTag = tagDao.findIndexTag(count);
            // 添加到redis
            redisTemplate.opsForHash().put("index", "tags", indexTag);
            // 设置超时时间 1天
            redisTemplate.expire("index", 60 * 60 * 24, TimeUnit.SECONDS);
            return indexTag;
        } else {
            // 找到了
            return indexTags;
        }
    }

    /**
     * 查询全部标签
     *
     * @return 标签的list集合
     */
    @Override
    public List<TagEntity> findTag() {
        // 先从redis找
        List<TagEntity> tagEntities = (List<TagEntity>) redisTemplate.opsForHash().get("menu", "tags");
        if (tagEntities == null) {
            // 从mysql中找
            List<TagEntity> tags = tagDao.findTag();
            // 加入到redis
            redisTemplate.opsForHash().put("menu", "tags", tags);
            // 设置超时时间 1天
            redisTemplate.expire("menu", 60 * 60 * 24, TimeUnit.SECONDS);
            return tags;
        }
        // 结果先转为字符串再转为list集合，避免java.util.LinkedHashMap cannot be cast to 实体类异常
        String tags = JSON.toJSONString(tagEntities);
        return JSON.parseArray(tags, TagEntity.class);
    }

    /**
     * 查找指定博客的所有标签
     *
     * @param id 博客id
     * @return 标签集合
     */
    @Override
    public List<TagEntity> findTagByDetail(Integer id) {
        return tagDao.findTagByDetail(id);
    }

    /**
     * 保存标签
     *
     * @param tag 标签
     * @return 标签
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /**
     * 根据id查询标签
     *
     * @param id 标签id
     * @return 标签
     */
    @Override
    public Tag getTag(Long id) {
        Optional<Tag> byId = tagRepository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundException("结果不存在");
        }
        return byId.get();
    }

    /**
     * 分页查询
     *
     * @param pageable 分页
     * @return 分页结果
     */
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    /**
     * 查找全部
     *
     * @return 全部标签
     */
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    /**
     * 通过多个ID查找
     *
     * @param ids 多个id
     * @return 结果集合
     */
    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "details.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] array = ids.split(",");
            for (String s : array) {
                list.add(new Long(s));
            }
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tag updateTag(Long id, Tag tag) {
        Optional<Tag> byId = tagRepository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundException("该标签不存在");
        }
        Tag tag1 = byId.get();
        BeanUtils.copyProperties(tag, tag1);
        return tagRepository.save(tag1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
