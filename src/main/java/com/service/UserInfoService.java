package com.service;

import com.bean.Detail;
import com.bean.UserDetail;
import com.bean.UserInfo;

import java.util.Date;
import java.util.List;

public interface UserInfoService {

    UserInfo findUserById(Integer id);

    List<Detail> findUserDetail(Integer id);

    void updateUserUpdateTime(Date updateTime, Long id);

    List<UserDetail> findUserLateDetail(Integer id);

    List<UserDetail> findUserRecommendDetail(Integer id);
}
