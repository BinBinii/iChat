package com.studio.chat.controller;

import com.studio.chat.service.MessagesService;
import com.studio.common.model.vo.DataTablesResult;
import com.studio.common.model.vo.MessagesToVo;
import com.studio.common.model.vo.MessagesVo;
import com.studio.common.model.vo.Render;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Secured("ROLE_USER")
    @GetMapping("history")
    public Object getMessageToList(@RequestParam("userId") String userId) {
        List<MessagesToVo> result = messageService.getMessageToList(userId);
        return Render.ok(result);
    }

    @Secured("ROLE_USER")
    @GetMapping("list")
    public Object getMessagesList(@RequestParam("userId") String userId,
                                  @RequestParam("hand") String hand,
                                  @RequestParam("start") Integer start) {
        DataTablesResult result = messageService.getMessages(userId, hand, start);
        return result;
    }

    @Secured("ROLE_USER")
    @PostMapping("read")
    public Object readMessages(@RequestParam("ids") Integer[] ids) {
        boolean result = messageService.readMessages(ids);
        return Render.ok(result);
    }

    @Secured("ROLE_USER")
    @PostMapping("new/to")
    public Object newMessagesTo(@RequestParam("userId") String userId, @RequestParam("hand") String hand, @RequestParam("status") Integer status) {
        boolean result = messageService.newMessagesTo(userId, hand, status);
        if (!result) {
            return Render.fail("创建聊天失败");
        }
        return Render.ok(true);
    }


}
