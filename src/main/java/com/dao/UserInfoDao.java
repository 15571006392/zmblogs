package com.dao;

import com.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao {

    UserInfo findUserById(Integer id);
}
