package com.studio.chat.controller;

import com.studio.chat.service.UserService;
import com.studio.common.model.pojo.TbUser;
import com.studio.common.model.vo.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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

    @Secured("ROLE_USER")
    @GetMapping("friend/list")
    public Object getFriendListData(@RequestParam("userId") String userId) {
        List<TbUser> result = userService.getFriendListData(userId);
        return Render.ok(result);
    }
    @Secured("ROLE_USER")
    @GetMapping("get/info")
    public Object getUserInfo(@RequestParam("userId") String userId) {
        TbUser result = userService.getUserInfo(userId);
        return Render.ok(result);
    }

    @Secured("ROLE_USER")
    @PostMapping("update")
    public Object update(@RequestBody TbUser tbUser) {
        boolean result = userService.update(tbUser);
        return Render.ok(result);
    }

}
