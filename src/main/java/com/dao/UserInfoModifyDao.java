package com.dao;

import com.bean.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zm-Mmm
 */
@Mapper
public interface UserInfoModifyDao {

    /**
     * 查找用户信息
     * @param id 用户id
     * @return 用户信息
     */
    UserEntity findAllInfo(int id);

    /**
     * 修改头像
     * @param id 用户id
     * @param avatar 新的头像
     */
    void modifyAvatar(int id,String avatar);

    /**
     * 修改信息
     * @param id 用户id
     * @param nickname 新的用户名
     * @param email 新的邮箱
     */
    void modifyInfo(int id, String nickname, String email);
}
