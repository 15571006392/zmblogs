package com.dao;

import com.bean.BlogEntity;
import com.bean.BlogTagQuery;
import com.bean.UserDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DetailDao {

    /**
     * 查询指定用户的博客并分页
     * 过滤草稿状态博客
     * @param id
     * @return
     */
    List<UserDetail> selectDetailFromUserIdLimit(Long id);

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
    List<BlogTagQuery> findAllBlogsByTag(int id);
}
