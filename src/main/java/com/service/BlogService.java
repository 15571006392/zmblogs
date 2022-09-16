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
    List<BlogEntity> findAllBlogsByTag(int id);

    /**
     * 根据主键获取指定博客
     * @param id 主键
     * @return 博客
     */
    Detail getBlog(Long id);

    /**
     *  通过用户id查询博客信息并分页
     *  过滤博客状态为草稿的博客
     * @param id 用户id
     * @return 装有用户的博客的list集合
     */
    List<BlogEntity> selectDetailFromUserIdLimit(Long id);

    /**
     * 根据id查找博客
     * 用来转换markdown
     * @param id id
     * @return 博客
     */
    Detail getAndConvert(Long id);

    /**
     * 搜索博客
     * @param query 用户输入
     * @return 博客列表
     */
    List<BlogEntity> searchBlogs(String query);

    /**
     * 根据指定的数量
     * 查找指定数量的推荐的博客
     * @param size 指定大小
     * @return 结果
     */
    List<Detail> listRecommendBlogTop(Integer size);

    /**
     * 归档功能
     * 查询博客，String为年份
     * 以年份为key，对应创建时间的博客为value
     * @return 结果
     */
    Map<String,List<Detail>> archiveBlog();

    /**
     * 博客数量
     * @return 博客数量
     */
    Long countBlog();

    /**
     * 保存博客
     * @param detail 博客
     * @return 博客
     */
    Detail saveBlog(Detail detail);

    /**
     * 根据id更新博客
     * @param id id
     * @param detail 博客
     * @return 博客
     */
    Detail updateBlog(Long id,Detail detail);

    /**
     * 根据id删除博客
     * @param id id
     */
    void deleteBlog(Long id);
}
