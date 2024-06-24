package com.example.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.basic.interceptor.IPCheckInterceptor;
import com.example.basic.interceptor.SignInCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private SignInCheckInterceptor signInCheckInterceptor;

    @Autowired
    private IPCheckInterceptor iPCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInCheckInterceptor).addPathPatterns("/main");

        registry.addInterceptor(iPCheckInterceptor).addPathPatterns("/visitor");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
