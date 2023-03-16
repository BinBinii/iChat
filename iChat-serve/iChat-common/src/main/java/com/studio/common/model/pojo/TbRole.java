package com.studio.common.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/15:14
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbRole implements GrantedAuthority {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;         // 自增ID

    private String role_name;

    private String role_desc;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return role_name;
    }
}
