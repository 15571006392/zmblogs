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

    @Autowired
    private UserRepository userRepository;

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MessageDigestAlgorithm.code(password));
        return user;
    }
}
