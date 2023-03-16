package com.studio.common.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2023/03/14/21:59
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbUser {
    @TableId(value = "userId")
    private String userId;         // 自增ID
    // 用户名
    private String username;
    // 密码
    private String password;
    // 性别
    private String sex;
    // 头像路径
    private String image;

    private String nickname;

    private Integer status;
}
