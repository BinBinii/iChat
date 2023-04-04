package com.studio.chat.controller;

import com.studio.chat.service.FriendService;
import com.studio.common.model.pojo.TbUser;
import com.studio.common.model.vo.FriendReqVo;
import com.studio.common.model.vo.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/04/03/09:00
 * @Description:
 */
@RestController
@RequestMapping(value = "/friend/")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Secured("ROLE_USER")
    @GetMapping("list")
    public Object getFriendListData(@RequestParam("userId") String userId) {
        List<TbUser> result = friendService.getFriendListData(userId);
        return Render.ok(result);
    }

    @Secured("ROLE_USER")
    @GetMapping("list/req")
    public Object getFriendRequestListData(@RequestParam("userId") String userId) {
        List<FriendReqVo> result = friendService.getFriendRequestListData(userId);
        return Render.ok(result);
    }

    @Secured("ROLE_USER")
    @PostMapping("add")
    public Object addFriend(@RequestParam("userId") String userId, @RequestParam("hand") String hand) {
        boolean result = friendService.addFriend(userId, hand);
        if (!result) {
            return Render.fail("add friend fail");
        }
        return Render.ok(true);
    }

    @Secured("ROLE_USER")
    @PostMapping("req/pass")
    public Object pass(@RequestParam("req") String req, @RequestParam("hand") String hand) {
        boolean result = friendService.pass(req, hand);
        if (!result) {
            return Render.fail("pass friend fail");
        }
        return Render.ok(true);
    }

    @Secured("ROLE_USER")
    @PostMapping("req/fail")
    public Object fail(@RequestParam("req") String req, @RequestParam("hand") String hand) {
        boolean result = friendService.fail(req, hand);
        if (!result) {
            return Render.fail("refuse friend fail");
        }
        return Render.ok(true);
    }

    @Secured("ROLE_USER")
    @PostMapping("del")
    public Object del(@RequestParam("userId") String userId, @RequestParam("hand") String hand) {
        boolean result = friendService.delFriend(userId, hand);
        if (!result) {
            return Render.fail("del friend fail");
        }
        return Render.ok(true);
    }

}
