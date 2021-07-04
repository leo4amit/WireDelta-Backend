package com.wiredelta.backend.configuration;

import com.wiredelta.backend.interceptor.UserSessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    UserSessionInterceptor userSessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userSessionInterceptor).addPathPatterns("/api/v1/jobs/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/","classpath:/webapp/");
    }
}
