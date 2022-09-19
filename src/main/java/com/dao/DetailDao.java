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
     * 查询首页推荐博客，指定数量，按照博客更新日期排序
     * @param count
     * @return
     */
    List<BlogEntity> findIndexRecommendBlog(Integer count);

    /**
     * 查询指定用户的博客并分页
     * 过滤草稿状态博客
     * @param id 用户id
     * @return 博客
     */
    List<BlogEntity> selectDetailFromUserIdLimit(Long id);

    /**
     * 搜索博客功能
     * @param query 用户输入
     * @return 博客
     */
    List<BlogEntity> searchBlogs(String query);

    /**
     * 查询所有博客
     * 过滤草稿状态博客
     * @return 博客
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
