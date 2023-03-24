package com.studio.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.studio.chat.mapper.TbGroupMessagesMapper;
import com.studio.chat.mapper.TbMessagesMapper;
import com.studio.chat.mapper.TbMessagesToMapper;
import com.studio.chat.mapper.TbUserMapper;
import com.studio.chat.service.MessagesService;
import com.studio.common.model.pojo.TbGroupMessages;
import com.studio.common.model.pojo.TbMessages;
import com.studio.common.model.pojo.TbMessagesTo;
import com.studio.common.model.vo.MessagesToVo;
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
                messagesToVo.setNickname(tbUserMapper.selectById(tbMessages.get(0).getFrom_user()).getNickname())
                        .setToMsg(tbMessages.get(0).getPost_message())
                        .setSendTime(tbMessages.get(0).getSend_time());
                messagesToVos.add(messagesToVo);
            } else if (tbMessagesTo.getStatus().equals(1)) {
                QueryWrapper<TbGroupMessages> tbGroupMessagesQueryWrapper = new QueryWrapper<>();
                tbGroupMessagesQueryWrapper.eq("group_id", tbMessagesTo.getHand()).orderByDesc("create_time");
                List<TbGroupMessages> tbGroupMessages = tbGroupMessagesMapper.selectList(tbGroupMessagesQueryWrapper);
                messagesToVo.setNickname(tbGroupMessages.get(0).getFrom_nickname())
                        .setToMsg(tbGroupMessages.get(0).getText())
                        .setSendTime(tbGroupMessages.get(0).getCreate_time());
                messagesToVos.add(messagesToVo);
            }
        }
        return messagesToVos;
    }
}
