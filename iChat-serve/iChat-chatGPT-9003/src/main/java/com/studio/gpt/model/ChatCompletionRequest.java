package com.studio.gpt.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/31/09:25
 * @Description: 发送的请求
 */
@Data
@Builder
public class ChatCompletionRequest {

    String model;

    List<ChatMessage> messages;

    Double temperature;

    Integer n;

    Boolean stream;

    List<String> stop;

    Integer max_tokens;

    String user;
}
