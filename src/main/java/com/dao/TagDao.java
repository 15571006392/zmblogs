package com.dao;

import com.bean.TagEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zm-Mmm
 */
@Mapper
public interface TagDao {

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
}