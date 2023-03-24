package com.studio.image.vo;

/**
 * @Author: BinBin
 * @Date: 2022/10/08/10:51
 * @Description:
 */
public enum ResultCode {

    SUCCESS(200, "成功"),
    PARAM_INVALID(402, "参数输入错误"),
    UNKNOWN_ERROR(500, "未知错误");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
