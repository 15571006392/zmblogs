package com.service;

import com.bean.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Zm-Mmm
 */
public interface BlogService {

    /**
     * 查询全部博客
     * 过滤博客状态为草稿的博客
     * @return 装有博客信息的list集合
     */
    List<BlogEntity> findAllBlogs();

    /**
     * 查询指定分类下的所有博客
     * 过滤博客状态为草稿的博客
     * @param id 分类id
     * @return 装有博客信息的list集合
     */
    List<BlogEntity> findAllBlogsByType(int id);

    /**
     * 查询指定标签下的所有博客
     * 过滤博客状态为草稿的博客
     * @param id 标签的id
     * @return 装有博客信息的list集合
     */
    List<BlogTagQuery> findAllBlogsByTag(int id);

    /**
     * 根据主键获取指定博客
     * @param id
     * @return
     */
    Detail getBlog(Long id);

    /**
     *  通过用户id查询博客信息并分页
     *  过滤博客状态为草稿的博客
     * @param id 用户id
     * @return 装有用户的博客的list集合
     */
    List<UserDetail> selectDetailFromUserIdLimit(Long id);

    /**
     * 根据id查找博客
     * 用来转换markdown
     * @param id
     * @return
     */
    Detail getAndConvert(Long id);

    /**
     * 分页查找博客
     * 通过BlogQuery类查找
     * @param pageable
     * @param detail
     * @return
     */
    Page<Detail> listBlog(Pageable pageable, BlogQuery detail);

    /**
     * 分页查询博客
     * @param pageable
     * @return
     */
    Page<Detail> listBlog(Pageable pageable);

    /**
     * 根据标签的ID分页查询博客
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Detail> listBlog(Long tagId,Pageable pageable);

    /**
     * search搜索
     * 通过用户输入的内容分页查询博客
     * @param query
     * @param pageable
     * @return
     */
    Page<Detail> listBlog(String query,Pageable pageable);

    /**
     * 根据指定的数量
     * 查找指定数量的推荐的博客
     * @param size
     * @return
     */
    List<Detail> listRecommendBlogTop(Integer size);

    /**
     * 归档功能
     * 查询博客，String为年份
     * 以年份为key，对应创建时间的博客为value
     * @return
     */
    Map<String,List<Detail>> archiveBlog();

    /**
     * 博客数量
     * @return
     */
    Long countBlog();

    /**
     * 保存博客
     * @param detail
     * @return
     */
    Detail saveBlog(Detail detail);

    /**
     * 根据id更新博客
     * @param id
     * @param detail
     * @return
     */
    Detail updateBlog(Long id,Detail detail);

    /**
     * 根据id删除博客
     * @param id
     */
    void deleteBlog(Long id);
}
