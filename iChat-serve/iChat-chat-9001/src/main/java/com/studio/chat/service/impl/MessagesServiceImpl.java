package com.studio.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.studio.chat.mapper.*;
import com.studio.chat.service.MessagesService;
import com.studio.common.model.pojo.TbGroupMessages;
import com.studio.common.model.pojo.TbMessages;
import com.studio.common.model.pojo.TbMessagesTo;
import com.studio.common.model.vo.MessagesToVo;
import com.studio.common.model.vo.MessagesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/24/10:40
 * @Description:
 */
@Service
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private TbMessagesToMapper tbMessageToMapper;
    @Autowired
    private TbMessagesMapper tbMessagesMapper;
    @Autowired
    private TbGroupMessagesMapper tbGroupMessagesMapper;
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private TbGroupMapper tbGroupMapper;

    @Override
    public List<MessagesToVo> getMessageToList(String userId) {
        QueryWrapper<TbMessagesTo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        List<TbMessagesTo> tbMessagesTos = tbMessageToMapper.selectList(queryWrapper);
        List<MessagesToVo> messagesToVos = new ArrayList<>();
        for (TbMessagesTo tbMessagesTo:tbMessagesTos) {
            MessagesToVo messagesToVo = new MessagesToVo();
            messagesToVo.setId(tbMessagesTo.getId())
                    .setUserId(tbMessagesTo.getUserId())
                    .setHand(tbMessagesTo.getHand())
                    .setStatus(tbMessagesTo.getStatus());
            if (tbMessagesTo.getStatus().equals(0)) {
                QueryWrapper<TbMessages> tbMessagesQueryWrapper = new QueryWrapper<>();
                tbMessagesQueryWrapper.eq("from_user", tbMessagesTo.getUserId()).eq("to_user", tbMessagesTo.getHand())
                        .or().eq("from_user", tbMessagesTo.getHand()).eq("to_user", tbMessagesTo.getUserId()).orderByDesc("send_time");
                List<TbMessages> tbMessages = tbMessagesMapper.selectList(tbMessagesQueryWrapper);
                messagesToVo.setNickname(tbUserMapper.selectById(tbMessagesTo.getHand()).getNickname())
                        .setToMsg(tbMessages.get(0).getPost_message())
                        .setSendTime(tbMessages.get(0).getSend_time());
                messagesToVos.add(messagesToVo);
            } else if (tbMessagesTo.getStatus().equals(1)) {
                QueryWrapper<TbGroupMessages> tbGroupMessagesQueryWrapper = new QueryWrapper<>();
                tbGroupMessagesQueryWrapper.eq("group_id", tbMessagesTo.getHand()).orderByDesc("create_time");
                List<TbGroupMessages> tbGroupMessages = tbGroupMessagesMapper.selectList(tbGroupMessagesQueryWrapper);
                messagesToVo.setNickname(tbGroupMapper.selectById(tbGroupMessages.get(0).getGroup_id()).getName())
                        .setToMsg(tbGroupMessages.get(0).getText())
                        .setSendTime(tbGroupMessages.get(0).getCreate_time());
                messagesToVos.add(messagesToVo);
            }
        }
        return messagesToVos;
    }

    @Override
    public List<MessagesVo> getMessages(String userId, String hand) {
        QueryWrapper<TbMessages> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from_user", userId).eq("to_user", hand)
                .or().eq("from_user", hand).eq("to_user", userId).orderByAsc("send_time");
        List<TbMessages> tbMessagesList = tbMessagesMapper.selectList(queryWrapper);
        List<MessagesVo> messagesVos = new ArrayList<>();
        for (TbMessages tbMessages:tbMessagesList) {
            MessagesVo messagesVo = new MessagesVo();
            messagesVo.setId(tbMessages.getId())
                    .setPost_message(tbMessages.getPost_message())
                    .setStatus(tbMessages.getStatus())
                    .setSend_time(tbMessages.getSend_time())
                    .setFrom_user(tbMessages.getFrom_user())
                    .setTo_user(tbMessages.getTo_user())
                    .setFrom_user_nickname(tbUserMapper.selectById(tbMessages.getFrom_user()).getNickname());
            messagesVos.add(messagesVo);
        }
        return messagesVos;
    }
}
