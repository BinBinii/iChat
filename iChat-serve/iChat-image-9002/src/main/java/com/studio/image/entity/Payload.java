package com.studio.image.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/15:42
 * @Description:
 */
@Data
public class Payload<T> {
    private String id;
    private T userInfo;
    private Date expiration;
}
