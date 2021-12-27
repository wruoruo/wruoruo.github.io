package com.zsj.es.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 图片
 *
 * @author 康创
 * @date 2021年12月25日 下午5:00:50
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${file.filePath}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  /home/file/**为前端URL访问路径  后面 file:xxxx为本地磁盘映射
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + uploadPath);
    }
}
