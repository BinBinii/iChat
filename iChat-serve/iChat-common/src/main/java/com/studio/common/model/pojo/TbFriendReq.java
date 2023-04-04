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
 * @Date: 2023/04/03/09:15
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbFriendReq implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;         // 自增ID

    private String req;         // 发起人ID

    private String hand;        // 接受人ID

    private Integer status;     // 状态 0：未通过 1：已通过 2：已拒绝

    private Date create_time;   // 创建时间

    private Date pass_time;     // 通过时间
}
