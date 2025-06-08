package com.ljx.utils;

import com.ljx.common.LoginInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("注册拦截器，排除路径: /teacher/login，/student/login，/student/save");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("") // 拦截所有请求
                .excludePathPatterns(  // 排除以下路径
                        "/teacher/login", // 教师登录接口
                        "/student/login" , // 学生登录接口
                        "/student/save"
                );
    }
/*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("注册拦截器，排除路径: /teacher/login");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/teacher/login");
    }*/
}