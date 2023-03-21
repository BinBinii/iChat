package com.studio.common.model.pojo;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2023/03/21/11:33
 * @Description: 消息关联表
 */
public class TbMessagesTo implements Serializable {
    private Integer id;         // 自增ID

    private String userId;      // 用户ID

    private String hand;        // 指向用户ID或者群组ID

    private Integer status;     // 0:用户 1:群组
}
