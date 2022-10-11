package com.service.impl;

import com.dao.AboutDao;
import com.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author Zm-Mmm
 */
@Service
public class AboutServiceImpl implements AboutService {

    private final AboutDao aboutDao;

    private final RedisTemplate redisTemplate;

    @Autowired
    public AboutServiceImpl(AboutDao aboutDao,RedisTemplate redisTemplate) {
        this.aboutDao = aboutDao;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Integer findUserCount() {
        // redis
        Integer registerUserCount = (Integer) redisTemplate.opsForHash().get("menu", "registerUserCount");
        if(registerUserCount == null){
            // mysql
            Integer userCount = aboutDao.findUserCount();
            // 同步
            redisTemplate.opsForHash().put("menu","registerUserCount",userCount);
            // 设置超时时间 1天
            redisTemplate.expire("menu", 60 * 60 * 24, TimeUnit.SECONDS);
            return userCount;
        }
        return registerUserCount;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertAboutData(Integer allCount, Integer registerUserCount) {
        String onlineDate = "2020年7月8日";
        aboutDao.insertAboutData(allCount, registerUserCount, onlineDate);
    }
}
