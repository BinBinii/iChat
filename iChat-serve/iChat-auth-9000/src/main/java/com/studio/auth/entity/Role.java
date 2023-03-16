package com.studio.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: BinBin
 * @Date: 2023/03/16/22:36
 * @Description:
 */
@Data
public class Role implements GrantedAuthority {

    private Integer id;
    private String roleName;
    private String roleDesc;

    /**
     * 如果授予的权限可以当作一个String的话，就可以返回一个String
     * @return
     */
    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }
}

