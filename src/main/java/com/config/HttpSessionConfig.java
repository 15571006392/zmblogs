package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 设置Session过期时间
 * maxInactiveIntervalInSeconds = 30 * 60 默认1800秒
 * @author Zm-Mmm
 */
@Configuration
@EnableRedisHttpSession()
public class HttpSessionConfig {

    @Bean
    public static ConfigureRedisAction configureRedisAction(){
        return ConfigureRedisAction.NO_OP;
    }
}
