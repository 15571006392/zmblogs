package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zm-Mmm
 */
@Configuration
public class BlogsImageInputConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir");
        registry.addResourceHandler("/BlogInputImages/**")
                .addResourceLocations("file:" + path + "/src/main/resources/static/BlogInputImages/");
    }
}
