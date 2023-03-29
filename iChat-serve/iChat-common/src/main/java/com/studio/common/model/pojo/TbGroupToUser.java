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
 * @Date: 2023/03/21/11:27
 * @Description: 群用户关联表
 *
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbGroupToUser implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;             // 自增ID

    private String userId;          // 用户ID

    private String group_id;        // 群组ID

    private Date join_time;         // 加入时间

    private String group_nickname;  // 群内昵称
}
