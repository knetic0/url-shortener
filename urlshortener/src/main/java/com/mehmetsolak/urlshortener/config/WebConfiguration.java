package com.mehmetsolak.urlshortener.config;

import com.mehmetsolak.urlshortener.constants.RequestMappingField;
import com.mehmetsolak.urlshortener.interceptor.SecurityKeyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final SecurityKeyInterceptor securityKeyInterceptor;

    public WebConfiguration(SecurityKeyInterceptor securityKeyInterceptor) {
        this.securityKeyInterceptor = securityKeyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityKeyInterceptor)
                .addPathPatterns(RequestMappingField.REGISTER, RequestMappingField.LOGIN);
    }
}
