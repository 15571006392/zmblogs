package com.service;

import com.bean.User;

/**
 * @author Zm-Mmm
 */
public interface UserService {

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);
}
