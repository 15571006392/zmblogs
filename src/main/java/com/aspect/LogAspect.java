package com.aspect;

import com.bean.LogBean;
import com.util.GetIp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面
 * @author Zm-Mmm
 */


@Aspect
@Configuration
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截所有控制器下的所有方法
     */
    @Pointcut("execution(* com.controller.*.*(..))&&!execution(* com.controller.CommentController.*(..))")
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
        logger.info("[新请求]：" + logBean);
    }

    @AfterReturning(returning = "o",pointcut = "log()")
    public void Return(Object o){
        logger.info("return values:" + o);
    }
}

