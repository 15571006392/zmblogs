package com.service;

import com.bean.Detail;
import com.bean.UserInfo;

import java.util.List;

public interface UserInfoService {

    UserInfo findUserById(Integer id);

    List<Detail> findUserDetail(Integer id);
}
