package com.service;

import com.bean.BlogEntity;
import com.bean.UserEntity;

import java.util.Date;
import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface UserInfoService {

    /**
     * 查询注册用户数量
     * @return 用户数量
     */
    Integer findUserCount();

    /**
     * 查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    UserEntity findUserById(Integer id);

    /**
     * 查询热门博客
     * @param id 用户id
     * @return 博客
     */
    List<BlogEntity> findUserDetail(Integer id);

    /**
     * 更新用户上次登录时间
     * @param updateTime 登录时间
     * @param id 用户id
     */
    void updateUserUpdateTime(Date updateTime, Long id);

    /**
     * 查询用户最近更新博客
     * @param id 用户id
     * @return 博客
     */
    List<BlogEntity> findUserLateDetail(Integer id);

    /**
     * 查询用户推荐博客
     * @param id 用户id
     * @return 博客
     */
    List<BlogEntity> findUserRecommendDetail(Integer id);
}
