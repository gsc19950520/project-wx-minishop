package com.shop.config;

import com.shop.fliter.AuthValidFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class FilterConfig {

    @Bean("authValidFilter")
    @Order(1)
    public FilterRegistrationBean<AuthValidFilter> getAuthValidFilter() {
        FilterRegistrationBean<AuthValidFilter> registration = new FilterRegistrationBean<AuthValidFilter>();
        registration.setFilter(new AuthValidFilter());
        registration.addUrlPatterns("/*");
        registration.setName("AuthValidFilter");
        registration.setOrder(1);
        return registration;
    }
}
