package com.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zm-Mmm
 */
@Mapper
public interface AboutDao {

    /**
     * 查询注册用户数量
     * @return 用户数量
     */
    Integer findUserCount();

    /**
     * 持久化页面统计数据到数据库
     *
     * @param allCount          总访客数量
     * @param registerUserCount 注册用户数量
     * @param onlineDate        网站上线日期
     */
    void insertAboutData(Integer allCount, Integer registerUserCount, String onlineDate);
}
