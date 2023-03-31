package com.studio.gpt.model;

import lombok.Data;

/**
 * @Author: BinBin
 * @Date: 2023/03/31/09:27
 * @Description: 接收ChatGPT返回的数据
 */
@Data
public class ChatCompletionChoice {

    Integer index;

    ChatMessage message;

    String finishReason;
}
