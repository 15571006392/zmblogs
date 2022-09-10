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
}
