package com.studio.chat.controller;

import com.studio.chat.service.UserService;
import com.studio.common.model.pojo.TbUser;
import com.studio.common.model.vo.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/14:02
 * @Description:
 */
@RestController
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("friend/list")
    public Object getFriendListData(@RequestParam("userId") String userId) {
        List<TbUser> result = userService.getFriendListData(userId);
        return Render.ok(result);
    }


}
