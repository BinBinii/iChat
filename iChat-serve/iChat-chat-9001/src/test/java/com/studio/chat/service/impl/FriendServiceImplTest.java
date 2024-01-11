package com.studio.chat.service.impl;

import com.studio.chat.service.FriendService;
import com.studio.common.model.pojo.TbUser;
import com.studio.common.model.vo.FriendReqVo;
import com.studio.common.model.vo.Render;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: BinBin
 * @Date: 2023/05/30/09:09
 * @Description:
 */
@SpringBootTest
class FriendServiceImplTest {

    @Autowired
    private FriendService friendService;

    @Test
    void getFriendListData() {
        String userId = "4150901";
        List<TbUser> result = friendService.getFriendListData(userId);
        result.forEach(System.out::println);
    }

    @Test
    void getFriendRequestListData() {
        String userId = "4150901";
        List<FriendReqVo> result = friendService.getFriendRequestListData(userId);
        result.forEach(System.out::println);
    }

    @Test
    void addFriend() {
        String userId = "";
        String hand = "";
        boolean result = friendService.addFriend(userId, hand);
    }

    @Test
    void pass() {
        String req = "";
        String hand = "";
        boolean result = friendService.pass(req, hand);
    }

    @Test
    void fail() {
        String req = "";
        String hand = "";
        boolean result = friendService.fail(req, hand);
    }

    @Test
    void delFriend() {
        String userId = "";
        String hand = "";
        boolean result = friendService.delFriend(userId, hand);
    }
}