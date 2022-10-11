package com.service.impl;

import com.NotFoundException;
import com.bean.UserEntity;
import com.dao.UserInfoModifyDao;
import com.service.UserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 用户信息查询
     * @param id 用户id
     * @param userId 当前登录用户id，用于校验
     * @return 用户信息
     */
    @Override
    public UserEntity findAllInfo(int id,Long userId) {
        // 校验是否是登录用户访问，避免查不到或者越级
        if (id != userId){
            throw new NotFoundException("用户不存在或者无权限访问");
        }
        return userInfoModifyDao.findAllInfo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyAvatar(int id, String avatar) {
        userInfoModifyDao.modifyAvatar(id, avatar);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyInfo(int id, String nickname, String email) {
        userInfoModifyDao.modifyInfo(id, nickname, email);
    }
}
