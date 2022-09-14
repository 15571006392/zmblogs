package com.dao;

import com.bean.BlogEntity;
import com.bean.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author Zm-Mmm
 */
@Mapper
public interface UserInfoDao {

    /**
     * 查询注册用户数量
     * @return 用户数量
     */
    Integer findUserCount();

    /**
     * 根据id查询用户
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
     * 更新用户上次在线时间
     * @param updateTime 在线时间
     * @param id 用户id
     */
    void updateUserUpdateTime(Date updateTime,Long id);

    /**
     * 用户最近更新
     * @param id 用户id
     * @return 更新的博客
     */
    List<BlogEntity> findUserLateDetail(Integer id);

    /**
     * 用户推荐博客
     * @param id 用户
     * @return 博客
     */
    List<BlogEntity> findUserRecommendDetail(Integer id);
}
