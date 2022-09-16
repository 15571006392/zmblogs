package com.dao;

import com.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zm-Mmm
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 登录验证
     * 查询和用户名匹配的密码存不存在
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    User findByUsernameAndPassword(String username, String password);
}
