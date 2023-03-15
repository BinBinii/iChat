package com.studio.common.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: BinBin
 * @Date: 2023/03/14/21:59
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbUser {
    private String userId;         // 自增ID
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 性别
    private String sex;
    // 头像路径
    private String image;

    private String nickName;
}
