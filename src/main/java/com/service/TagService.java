package com.service;

import com.bean.Tag;
import com.bean.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface TagService {

    /**
     * 查询所有标签
     * @return 所有标签的list集合
     */
    List<TagEntity> findTag();

    /**
     * 查找指定博客的所有标签
     * @param id 博客id
     * @return 标签集合
     */
    List<TagEntity> findTagByDetail(Integer id);

    /**
     * 新增标签
     * @param tag 标签
     * @return 标签
     */
    Tag saveTag(Tag tag);

    /**
     * 根据ID查询标签
     * @param id 标签id
     * @return 标签
     */
    Tag getTag(Long id);
    /**
     * 分页查询
     * @param pageable 分页
     * @return 标签
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 查询所有标签
     * @return 标签
     */
    List<Tag> listTag();

    /**
     * 根据id查询标签
     * @param ids 标签的id
     * @return 标签
     */
    List<Tag> listTag(String ids);

    /**
     * 根据指定大小
     * 查询指定数量的标签
     * @param size 大小
     * @return 标签
     */
    List<Tag> listTagTop(Integer size);

    /**
     * 修改标签
     * @param id 标签id
     * @param tag 标签对象
     * @return 标签
     */
    Tag updateTag(Long id,Tag tag);

    /**
     * 删除标签
     * @param id 标签id
     */
    void deleteTag(Long id);

    /**
     * 通过名字查询标签
     * @param name 标签名
     * @return 标签
     */
    Tag getTagByName(String name);
}
