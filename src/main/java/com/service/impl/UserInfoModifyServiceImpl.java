package com.service.impl;

import com.bean.UserEntity;
import com.dao.UserInfoModifyDao;
import com.service.UserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zm-Mmm
 */
@Service
public class UserInfoModifyServiceImpl implements UserInfoModifyService {

    final UserInfoModifyDao userInfoModifyDao;

    @Autowired
    public UserInfoModifyServiceImpl(UserInfoModifyDao userInfoModifyDao) {
        this.userInfoModifyDao = userInfoModifyDao;
    }

    @Override
    public UserEntity findAllInfo(int id) {
        return userInfoModifyDao.findAllInfo(id);
    }

    @Override
    public void modifyAvatar(int id, String avatar) {
        userInfoModifyDao.modifyAvatar(id, avatar);
    }

    @Override
    public void modifyInfo(int id, String nickname, String email) {
        userInfoModifyDao.modifyInfo(id, nickname, email);
    }
}
