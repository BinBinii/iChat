package com.studio.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2023/03/28/08:33
 * @Description:
 */
@Data
public class RegisterDto implements Serializable {
    private String username;
    private String password;
}
