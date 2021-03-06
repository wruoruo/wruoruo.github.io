package com.zsj.es.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 * @author zhangshuaijie
 * @since 2020-03-02
 */
@Configuration
public class CorsConfigurer implements WebMvcConfigurer {

    // 支持跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").maxAge(3600).allowedHeaders("Content-Type, x-requested-with, X-Custom-Header, Authorization");
    }

    // 支持PUT请求
    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

}