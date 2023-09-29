package com.awan.pznspring.mvc;

import com.awan.pznspring.mvc.middleware.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    private SessionInterceptor sessionInterceptor;

    @Autowired
    public void setSessionInterceptor(SessionInterceptor sessionInterceptor) {
        this.sessionInterceptor = sessionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/mdw/user/*");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
