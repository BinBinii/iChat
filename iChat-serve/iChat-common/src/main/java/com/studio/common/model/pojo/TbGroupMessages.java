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
 * @Date: 2023/03/21/11:13
 * @Description: 群组消息表
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbGroupMessages implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;                 // 自增ID

    private String group_id;            // 群组ID

    private String text;                // 消息内容

    private String from_user;           // 发送人ID

    private String from_nickname;       // 发送人昵称

    private Date create_time;           // 发送时间
}
