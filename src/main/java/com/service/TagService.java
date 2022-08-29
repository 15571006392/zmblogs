package com.service;

import com.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface TagService {
    /**
     * 新增标签
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    Tag getTag(Long id);
    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 查询所有标签
     * @return
     */
    List<Tag> listTag();

    /**
     * 根据id查询标签
     * @param ids
     * @return
     */
    List<Tag> listTag(String ids);

    /**
     * 根据指定大小
     * 查询指定数量的标签
     * @param size
     * @return
     */
    List<Tag> listTagTop(Integer size);

    /**
     * 修改标签
     * @param id
     * @param tag
     * @return
     */
    Tag updateTag(Long id,Tag tag);

    /**
     * 删除标签
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 通过名字查询标签
     * @param name
     * @return
     */
    Tag getTagByName(String name);
}
