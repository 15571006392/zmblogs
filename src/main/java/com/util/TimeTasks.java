package com.util;

import com.service.AboutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;


/**
 * @author Zm-Mmm
 */
@Configuration
@EnableScheduling
public class TimeTasks {

    private final StringRedisTemplate stringRedisTemplate;

    private final AboutService aboutService;

    private static final Logger logger = LoggerFactory.getLogger(TimeTasks.class);

    @Autowired
    public TimeTasks(StringRedisTemplate stringRedisTemplate,AboutService aboutService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.aboutService = aboutService;
    }

    /**
     * 每天晚上12点执行
     * 统计用户日访问量和总访问量，持久化数据到数据库
     * // @Scheduled(cron = "0 0/1 * * * ?") 测试代码，每分钟执行一次
     */
    @Scheduled(cron = "00 00 00 * * ?")
    private void clearIpProperties() {
        logger.info("[定时任务-启动]");
        // 获取总访问量
        int countViews =  Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("countViews")));
        // 添加今日访问量
        countViews += stringRedisTemplate.opsForHash().keys("visitorIP").size();
        // 删除今日访问量
        stringRedisTemplate.delete("visitorIP");
        // 覆盖总访问量
        stringRedisTemplate.opsForValue().set("countViews", String.valueOf(countViews));

        // 获取注册用户数
        Integer userCount = aboutService.findUserCount();
        // 更新数据
        aboutService.insertAboutData(countViews,userCount);
        logger.info("[定时任务-结束]");
    }
}