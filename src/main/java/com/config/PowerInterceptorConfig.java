package com.config;

import com.interceptor.PowerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zm-Mmm
 */
@Configuration
public class PowerInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new PowerInterceptor())
                    .addPathPatterns(("/admin/**"))
                    .excludePathPatterns("/admin")
                    .excludePathPatterns("/admin/login")
                    .excludePathPatterns("/admin/logout")
                    .excludePathPatterns("/admin/blogs")
                    .excludePathPatterns("/admin/blogs/**")
                    .excludePathPatterns("/admin/userinfoModify/**")
                    .excludePathPatterns("/admin/blogComments");
    }
}
