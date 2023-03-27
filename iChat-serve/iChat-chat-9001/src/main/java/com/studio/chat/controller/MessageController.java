package com.studio.chat.controller;

import com.studio.chat.service.MessagesService;
import com.studio.common.model.pojo.TbMessages;
import com.studio.common.model.vo.MessagesToVo;
import com.studio.common.model.vo.MessagesVo;
import com.studio.common.model.vo.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/24/10:43
 * @Description:
 */
@RestController
@RequestMapping(value = "/msg/")
public class MessageController {

    @Autowired
    private MessagesService messageService;

    @Secured("ROLE_USER")
    @GetMapping("history")
    public Object getMessageToList(@RequestParam("userId") String userId) {
        List<MessagesToVo> result = messageService.getMessageToList(userId);
        return Render.ok(result);
    }

    @Secured("ROLE_USER")
    @GetMapping("list")
    public Object getMessagesList(@RequestParam("userId") String userId, @RequestParam("hand") String hand) {
        List<MessagesVo> result = messageService.getMessages(userId, hand);
        return Render.ok(result);
    }

}
