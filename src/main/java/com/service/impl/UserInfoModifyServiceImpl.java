package com.service.impl;

import com.bean.UserInfoModify;
import com.dao.UserInfoModifyDao;
import com.service.UserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoModifyServiceImpl implements UserInfoModifyService {

    @Autowired
    UserInfoModifyDao userInfoModifyDao;

    @Override
    public UserInfoModify findAllInfo(int id) {
        return userInfoModifyDao.findAllInfo(id);
    }

    @Override
    public void modifyAvatar(int id,String avatar) {
        userInfoModifyDao.modifyAvatar(id,avatar);
    }

    @Override
    public void modifyInfo(int id, String nickname, String email) {
        userInfoModifyDao.modifyInfo(id,nickname,email);
    }
}
