package com.dao;

import com.bean.BlogEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zm-Mmm
 */
@Mapper
public interface DetailDao {

    /**
     * 查询指定用户的博客并分页
     * 过滤草稿状态博客
     * @param id
     * @return
     */
    List<BlogEntity> selectDetailFromUserIdLimit(Long id);

    /**
     * 搜索博客功能
     * @param query
     * @return
     */
    List<BlogEntity> searchBlogs(String query);

    /**
     * 查询所有博客
     * 过滤草稿状态博客
     * @return
     */
    List<BlogEntity> findAllBlogs();

    /**
     * 根据分类查询博客
     * 过滤草稿状态博客
     * @param id 分类id
     * @return 博客列表
     */
    List<BlogEntity> findAllBlogsByType(int id);

    /**
     * 根据标签查询博客
     * 过滤草稿状态博客
     * @param id 标签id
     * @return 博客列表
     */
    List<BlogEntity> findAllBlogsByTag(int id);
}
