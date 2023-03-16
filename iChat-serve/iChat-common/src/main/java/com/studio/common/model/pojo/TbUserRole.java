package com.studio.common.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/15:14
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbUserRole {
    private Integer user_id;

    private Integer role_id;
}
