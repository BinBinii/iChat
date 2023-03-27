package com.studio.common.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2023/03/26/23:54
 * @Description:
 */
@Data
@Accessors(chain = true)
public class MessagesVo implements Serializable {
    private Integer id;             // 自增ID

    private String post_message;    // 发送文本

    private Integer status;         // 接收状态 0:未读 1:已读

    private Date send_time;         // 发送时间

    private String from_user;       // 发送人ID

    private String to_user;         // 接收人ID

    private String from_user_nickname;
}
