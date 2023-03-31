package com.studio.gpt;

import com.unfbx.chatgpt.OpenAiStreamClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

/**
 * @Author: BinBin
 * @Date: 2023/03/31/09:23
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ChatGptApplication_9003 {

    @Value("${open.ai.token}")
    private String apiKey;
    @Value("${open.ai.url}")
    private String apiHost;

    public static void main(String[] args) {
        SpringApplication.run(ChatGptApplication_9003.class, args);
    }

    @Bean
    public OpenAiStreamClient openAiStreamClient() {
        return OpenAiStreamClient.builder().apiHost(apiHost).apiKey(apiKey).build();
    }
}
