package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zm-Mmm
 */
@SpringBootApplication
public class BlogApplication {

    /*
      禁用Ping Method
      alibaba druid控制台频繁爆警告
     */
    static{
        System.setProperty("druid.mysql.usePingMethod","false");
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
