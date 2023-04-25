package com.studio.chat.service;

import com.studio.common.model.pojo.TbMessages;
import com.studio.common.model.pojo.TbMessagesTo;
import com.studio.common.model.vo.DataTablesResult;
import com.studio.common.model.vo.MessagesToVo;
import com.studio.common.model.vo.MessagesVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/24/10:40
 * @Description:
 */
@Service
public interface MessagesService {

    /**
     * 获取所有聊天记录
     * @param userId
     * @return
     */
    List<MessagesToVo> getMessageToList(String userId);

    /**
     * 获取聊天
     * @param fromUser
     * @param toUser
     * @param start
     * @return
     */
    DataTablesResult getMessages(String fromUser, String toUser, Integer start);

    /**
     * 已读消息
     * @param ids
     * @return
     */
    boolean readMessages(Integer[] ids);

    /**
     * 新建聊天记录
     * @param userId
     * @param hand
     * @return
     */
    boolean newMessagesTo(String userId, String hand, Integer status);


}
