package com.aspect;

import com.bean.LogBean;
import com.util.GetIp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 日志切面
 * @author Zm-Mmm
 */
@Aspect
@Component
public class LogAspect {

    private Map<String,Boolean> map = new HashMap<>();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截所有控制器下的所有方法
     */
    @Pointcut("execution(* com.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void Before(JoinPoint joinPoint) {
        // 获取Request对象
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 获取URL链接
        String url = request.getRequestURL().toString();
        // 获取IP地址
        String ip = GetIp.getIpAddr(request);
        // 获取调用的类名+方法名
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        // 存入对象
        LogBean logBean = new LogBean(url,ip,method,args);
        logger.info("<<AOP>>new request：" + logBean);
        Properties properties2 = new Properties();
        Properties properties = new Properties();
        try{
            File file = new File("D:\\ip.properties");
            if(!file.exists()){
                file.createNewFile();
            }
            properties2.load(new FileInputStream(file));
        }catch (Exception e){
            e.printStackTrace();
        }

        if(properties2.containsKey(ip)){
            // 该访客今日已访问过
            logger.info("该IP为旧访客");
        }else{
            try{
                // 该访客今日第一次访问
                properties2.put(ip,"true");
                FileOutputStream fileOutputStream = new FileOutputStream("D:\\ip.properties");
                properties2.store(fileOutputStream,"访客");
                fileOutputStream.close();
            }catch (Exception e){
                logger.error("访客统计出错！");
            }
            try {
                File file = new File("D:\\visitors.properties");
                if(!file.exists()){
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file,true);
                    fileOutputStream.write("count=0".getBytes());
                    fileOutputStream.close();
                }
                properties.load(new FileInputStream(file));
                int count = Integer.parseInt(properties.getProperty("count"));
                count += 1;
                properties.setProperty("count", String.valueOf(count));
                FileOutputStream fileOutputStream = new FileOutputStream("D:\\visitors.properties");
                properties.store(fileOutputStream,"访客数量");
                fileOutputStream.close();
            }catch (Exception e){
                logger.error("访客数量统计出错！");
            }
            logger.info("该IP为新访客");
        }
    }

    @AfterReturning(returning = "o",pointcut = "log()")
    public void Return(Object o){
        logger.info("return values:" + o);
    }
}
