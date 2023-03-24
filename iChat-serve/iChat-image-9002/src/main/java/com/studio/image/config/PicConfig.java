package com.studio.image.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: BinBin
 * @Date: 2022/10/08/10:36
 * @Description:
 */
@Configuration
public class PicConfig implements WebMvcConfigurer {

    @Value("${pic.dir}")
    private String picDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:" + picDir);
    }
}