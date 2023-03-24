package com.studio.common.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2023/03/24/11:26
 * @Description:
 */
@Data
@Accessors(chain = true)
public class MessagesToVo implements Serializable {
    private Integer id;         // 自增ID

    private String userId;      // 用户ID

    private String hand;        // 指向用户ID或者群组ID

    private Integer status;     // 0:用户 1:群组

    private String nickname;

    private String toMsg;

    private Date sendTime;
}
