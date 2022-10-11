package com.service.impl;

import com.NotFoundException;
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
    public UserEntity findUserById(Integer id) {
        UserEntity userById = userInfoDao.findUserById(id);
        if (userById == null) {
            throw new NotFoundException("用户不存在");
        }
        return userById;
    }

    @Override
    public List<BlogEntity> findUserDetail(Integer id) {
        return userInfoDao.findUserDetail(id);
    }

    @Override
    public void updateUserUpdateTime(Date updateTime, Long id) {
        userInfoDao.updateUserUpdateTime(updateTime, id);
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

