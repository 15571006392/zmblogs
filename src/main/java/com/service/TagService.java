package com.service;

import com.bean.Tag;
import com.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
     * 查询所有
     */
    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    /**
     * 修改
     */
    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);

    /**
     * 通过名字查询
     */
    Tag getTagByName(String name);
}
