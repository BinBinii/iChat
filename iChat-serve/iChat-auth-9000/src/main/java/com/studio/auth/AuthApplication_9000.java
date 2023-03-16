package com.studio.auth;

import com.studio.auth.config.RsaKeyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/15:20
 * @Description:
 */
@MapperScan("com.studio.auth.mapper")
@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class AuthApplication_9000 {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication_9000.class, args);
    }
}
