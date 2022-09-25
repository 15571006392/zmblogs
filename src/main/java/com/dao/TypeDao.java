package com.dao;

import com.bean.TypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zm-Mmm
 */
@Mapper
public interface TypeDao {

    /**
     * 查询所有分类
     * @return 所有分类的list集合
     */
    List<TypeEntity> findType();

    /**
     * 首页分类查询，指定数量，按照分类下的博客数量从大到小排序，过滤草稿状态博客
     * @param count 查询分类的数量
     * @return 分类结果
     */
    List<TypeEntity> findIndexType(Integer count);
}
