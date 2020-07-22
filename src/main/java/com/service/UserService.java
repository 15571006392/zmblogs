package com.service;

import com.bean.User;

public interface UserService {

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username,String password);
}
