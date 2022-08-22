package com.dao;

import com.bean.Detail;
import com.bean.UserDetail;
import com.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserInfoDao {

    Integer findUserCount();

    UserInfo findUserById(Integer id);

    List<Detail> findUserDetail(Integer id);

    void updateUserUpdateTime(Date updateTime,Long id);

    List<UserDetail> findUserLateDetail(Integer id);

    List<UserDetail> findUserRecommendDetail(Integer id);
}
