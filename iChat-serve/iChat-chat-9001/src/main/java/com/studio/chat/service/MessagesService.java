package com.studio.chat.service;

import com.studio.common.model.pojo.TbMessagesTo;
import com.studio.common.model.vo.MessagesToVo;
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

}
