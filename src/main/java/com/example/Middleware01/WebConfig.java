package com.example.Middleware01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    APILoggingInterceptor apiLoggingInterceptor;
    @Autowired
    LegacyInterceptor legacyInterceptor;

    @Override
    public  void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(legacyInterceptor);
        registry.addInterceptor(apiLoggingInterceptor);
    }
}
