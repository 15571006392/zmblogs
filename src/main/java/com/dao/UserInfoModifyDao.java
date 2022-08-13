package com.dao;

import com.bean.UserInfoModify;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoModifyDao {

    UserInfoModify findAllInfo(int id);

    void modifyAvatar(int id,String avatar);

    void modifyInfo(int id, String nickname, String email, String updateTime);
}
