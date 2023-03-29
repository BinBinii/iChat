package com.studio.common.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2023/03/21/11:33
 * @Description: 消息关联表
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbMessagesTo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;         // 自增ID

    private String userId;      // 用户ID

    private String hand;        // 指向用户ID或者群组ID

    private Integer status;     // 0:用户 1:群组
}
