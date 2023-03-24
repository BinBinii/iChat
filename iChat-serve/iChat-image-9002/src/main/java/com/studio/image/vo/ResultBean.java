package com.studio.image.vo;
/**
 * @Author: BinBin
 * @Date: 2022/10/08/10:49
 * @Description:
 */
public class ResultBean<T> {

    private Integer code;
    private String message;
    private T data;

    ResultBean() {
    }

    // 返回成功
    public static <T> ResultBean<T> success() {
        ResultBean<T> resultBean = new ResultBean<>();
        resultBean.setCode(ResultCode.SUCCESS.getCode());
        resultBean.setMessage(ResultCode.SUCCESS.getMessage());
        return resultBean;
    }

    // 返回成功
    public static <T> ResultBean<T> success(T result) {
        ResultBean<T> resultBean = ResultBean.success();
        resultBean.setData(result);
        return resultBean;
    }

    // 返回失败
    public static <T> ResultBean<T> error() {
        ResultBean<T> resultBean = new ResultBean<>();
        resultBean.setCode(ResultCode.UNKNOWN_ERROR.getCode());
        resultBean.setMessage(ResultCode.UNKNOWN_ERROR.getMessage());
        return resultBean;
    }

    // 返回失败
    public static <T> ResultBean<T> error(T result) {
        ResultBean<T> resultBean = ResultBean.success();
        resultBean.setData(result);
        return resultBean;
    }

    // 自定义方法，留作扩展
    public static <T> ResultBean<T> defined(Integer code, String message, T result) {
        ResultBean<T> resultBean = new ResultBean<>();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        resultBean.setData(result);
        return resultBean;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
