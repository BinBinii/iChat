package com.studio.common.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2023/04/03/09:41
 * @Description:
 */
@Data
@Accessors(chain = true)
public class FriendReqVo implements Serializable {
    private String req;         // 发起人ID

    private String hand;        // 接受人ID

    private Integer status;     // 状态 0：未通过 1：已通过 2:已拒绝

    private Date create_time;   // 创建时间

    private Date pass_time;     // 通过时间

    private String username;        // 用户名（账号）

    private Integer sex;            // 性别

    private String image;           // 头像

    private String nickname;        // 昵称
}
