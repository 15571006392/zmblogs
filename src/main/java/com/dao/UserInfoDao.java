package com.dao;

import com.bean.Detail;
import com.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoDao {

    UserInfo findUserById(Integer id);

    List<Detail> findUserDetail(Integer id);
}
