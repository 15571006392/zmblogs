package com.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * @author Zm-Mmm
 */
@Configuration
@EnableScheduling
public class TimedTasks {

    private Properties properties = new Properties();

    @Scheduled(cron = "00 00 00 * * ?")
    private void clearIpProperties(){
        try {
            File file = new File("C:\\ip.properties");
            properties.load(new FileInputStream(file));
            properties.clear();
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\ip.properties");
            properties.store(fileOutputStream,"已清空");

            System.out.println("执行定时任务:" + LocalDateTime.now());
        }catch (Exception e){
            System.out.println("日访客数据清除失败");
        }

    }
}
