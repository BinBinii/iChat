package com.studio.common.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2023/03/28/14:53
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MqMessageVo<T> implements Serializable {
    private String title;

    private T data;
}
