package com.studio.common.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2023/03/14/21:59
 * @Description: 好友表
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbFriend implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;         // 自增ID

    private String user_id;     // 用户ID

    private String friend_id;   // 好友的用户ID
}
