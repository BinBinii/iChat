package com.studio.common.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2023/03/21/11:31
 * @Description: 消息表
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbMessages implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;             // 自增ID

    private String post_message;    // 发送文本

    private Integer status;         // 接收状态 0:未读 1:已读

    private Date send_time;         // 发送时间

    private String from_user;       // 发送人ID

    private String to_user;         // 接收人ID
}
