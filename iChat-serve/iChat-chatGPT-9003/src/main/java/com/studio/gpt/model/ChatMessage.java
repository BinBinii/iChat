package com.studio.gpt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: BinBin
 * @Date: 2023/03/31/09:25
 * @Description: 存放发送的消息信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    //消息角色
    String role;
    //消息内容
    String content;
}