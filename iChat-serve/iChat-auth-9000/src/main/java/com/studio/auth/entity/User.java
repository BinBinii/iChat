package com.studio.auth.entity;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/19:28
 * @Description:
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;
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
@Accessors(chain = true)
public class User implements UserDetails {

    private String userId;         // 自增ID
    // 用户名
    private String username;
    // 密码
    private String password;
    // 性别
    private Integer sex;
    // 头像路径
    private String image;

    private String nickname;

    private Integer status;

    private List<Role> roles = new ArrayList<>();

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //下面这四个方法是根据某些条件来判断用户是否可用，方便起见，可以直接设为true
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() { //指示用户的帐户是否已过期
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {   //指示用户是否被锁定或解锁
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {  //指示用户的凭据（密码）是否已过期
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {    //指示用户是否被启用或禁用
        return status==1;
    }
}