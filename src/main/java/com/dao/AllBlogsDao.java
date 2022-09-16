package com.dao;

import com.bean.BlogEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zm-Mmm
 */
@Mapper
public interface AllBlogsDao {

    /**
     * 查询所有用户的博客，除了登录的用户
     * @param id 用户id
     * @return 博客列表
     */
    List<BlogEntity> findAllBlogsNotCurrentUser(Long id);

    /**
     * 修改博客状态
     * @param id 博客id
     */
    void modifyState(Long id);
}
