package com.service.impl;

import com.bean.BlogEntity;
import com.bean.UserEntity;
import com.dao.UserInfoDao;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Zm-Mmm
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoDao userInfoDao;

    @Autowired
    public UserInfoServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public Integer findUserCount() {
        return userInfoDao.findUserCount();
    }

    @Override
    public UserEntity findUserById(Integer id) {
        return userInfoDao.findUserById(id);
    }

    @Override
    public List<BlogEntity> findUserDetail(Integer id) {
        return userInfoDao.findUserDetail(id);
    }

    @Override
    public void updateUserUpdateTime(Date updateTime, Long id) {
        userInfoDao.updateUserUpdateTime(updateTime,id);
    }

    @Override
    public List<BlogEntity> findUserLateDetail(Integer id) {
        return userInfoDao.findUserLateDetail(id);
    }

    @Override
    public List<BlogEntity> findUserRecommendDetail(Integer id) {
        return userInfoDao.findUserRecommendDetail(id);
    }
}

