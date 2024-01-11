package com.studio.chat.service.impl;

import com.studio.chat.service.UserService;
import com.studio.common.model.pojo.TbUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: BinBin
 * @Date: 2023/05/30/10:54
 * @Description:
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void getUserInfo() {
        TbUser result = userService.getUserInfo("4150901");
        System.out.println(result);
    }
}