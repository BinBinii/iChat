package com.studio.common.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @Author: BinBin
 * @Date: 2023/03/14/21:59
 * @Description: 用户表
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbUser implements Serializable {
    @TableId
    private String userId;          // 自增ID

    private String username;        // 用户名（账号）

    private String password;        // 密码

    private Integer sex;            // 性别

    private String image;           // 头像

    private String nickname;        // 昵称

    private Integer status;         // 状态
}
