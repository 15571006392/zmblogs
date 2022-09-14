package com.service;

import com.bean.UserEntity;

/**
 * @author Zm-Mmm
 */
public interface UserInfoModifyService {

    /**
     * 查询所有用户信息
     * @param id 用户id
     * @return 用户信息
     */
    UserEntity findAllInfo(int id);

    /**
     * 修改用户头像
     * @param id 用户id
     * @param avatar 新的头像
     */
    void modifyAvatar(int id,String avatar);

    /**
     * 修改用户信息
     * @param id 用户id
     * @param nickname 新的名称
     * @param email 新的邮箱
     */
    void modifyInfo(int id, String nickname, String email);
}
