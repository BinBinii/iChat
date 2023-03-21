package com.studio.common.model.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2023/03/21/11:05
 * @Description: 群组表
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbGroup implements Serializable {
    @TableId
    private String id;          // 群ID

    private String name;        // 群名称

    private Date create_time;   // 创建时间

    private String admin;       // 创建人ID

    private String notice;      // 群公告
}
