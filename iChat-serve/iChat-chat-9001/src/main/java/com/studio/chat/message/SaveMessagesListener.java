package com.studio.chat.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.Channel;
import com.studio.chat.command.MessageRequestPacket;
import com.studio.chat.config.RabbitmqConfig;
import com.studio.chat.mapper.TbMessagesMapper;
import com.studio.common.model.pojo.TbMessages;
import com.studio.common.model.vo.MqMessageVo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: BinBin
 * @Date: 2023/03/28/14:51
 * @Description:
 */
@Component
public class SaveMessagesListener {
    @Autowired
    private TbMessagesMapper tbMessagesMapper;

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SAVE_MESSAGES})
    public void save_messages(Object msg, Message message, Channel channel) {
        MqMessageVo<MessageRequestPacket> mqMessageVo = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(new String(message.getBody()), new TypeToken<MqMessageVo<MessageRequestPacket>>(){}.getType());
        MessageRequestPacket messageRequestPacket = mqMessageVo.getData();
        TbMessages tbMessages = new TbMessages();
        tbMessages.setPost_message(messageRequestPacket.getMessage())
                .setStatus(0)
                .setSend_time(messageRequestPacket.getDate())
                .setFrom_user(messageRequestPacket.getFromUserId())
                .setTo_user(messageRequestPacket.getToUserId());
        tbMessagesMapper.insert(tbMessages);
    }
}
