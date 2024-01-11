package com.studio.chat.service.impl;

import com.studio.chat.service.MessagesService;
import com.studio.common.model.vo.DataTablesResult;
import com.studio.common.model.vo.MessagesToVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: BinBin
 * @Date: 2023/05/30/09:43
 * @Description:
 */
@SpringBootTest
class MessagesServiceImplTest {

    @Autowired
    private MessagesService messagesService;

    @Test
    void getMessages() {
        DataTablesResult result = messagesService.getMessages("4150901", "4150902", 1);
        result.getData().forEach(System.out::println);
    }

    @Test
    void readMessages() {
    }

    @Test
    void newMessagesTo() {
    }
}