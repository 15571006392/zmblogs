package com.service;

import com.bean.User;

/**
 * @author Zm-Mmm
 */
public interface UserService {

    /**
     * 登录验证
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    User checkUser(String username, String password);
}
