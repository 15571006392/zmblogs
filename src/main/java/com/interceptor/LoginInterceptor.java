package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台页面拦截器
 * 未登录
 * @author Zm-Mmm
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String getMsg = "user";
        if(request.getSession().getAttribute(getMsg) == null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
