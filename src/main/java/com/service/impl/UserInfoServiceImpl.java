package com.service.impl;

import com.bean.Detail;
import com.bean.UserInfo;
import com.dao.UserInfoDao;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findUserById(Integer id) {
        return userInfoDao.findUserById(id);
    }

    @Override
    public List<Detail> findUserDetail(Integer id) {
        return userInfoDao.findUserDetail(id);
    }

    @Override
    public void updateUserUpdateTime(Date updateTime, Long id) {
        userInfoDao.updateUserUpdateTime(updateTime,id);
    }
}

