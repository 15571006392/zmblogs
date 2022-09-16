package com.service.impl;

import com.bean.User;
import com.dao.UserRepository;
import com.service.UserService;
import com.util.MessageDigestAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zm-Mmm
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 登录验证
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, MessageDigestAlgorithm.code(password));
    }
}
