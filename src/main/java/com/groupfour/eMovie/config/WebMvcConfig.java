package com.groupfour.eMovie.config;

import com.groupfour.eMovie.authorization.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 配置拦截器拦截的API
*
* */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor).addPathPatterns("/users/**")
//                                                .excludePathPatterns("/users/login", "/users",
//                                                        "/users/password/**",
//                                                        "/users/rating",
//                                                        "/users/ratingrecord/**");
    }
}
