package com.studio.gpt.model;

import org.apache.commons.httpclient.HttpMethod;

/**
 * @Author: BinBin
 * @Date: 2023/03/31/09:26
 * @Description: 接收请求返回的信息以及执行结果
 */
public class ExecuteRet {

    /**
     * 操作是否成功
     */
    private final boolean success;

    /**
     * 返回的内容
     */
    private final String respStr;

    /**
     * 请求的地址
     */
    private final HttpMethod method;

    /**
     * statusCode
     */
    private final int statusCode;

    public ExecuteRet(boolean success, String respStr, HttpMethod method, int statusCode) {
        this.success =success;
        this.respStr =respStr;
        this.method =method;
        this.statusCode =statusCode;
    }

    @Override
    public String toString() {
        return String.format("[success:%s,respStr:%s,statusCode:%s]", success, respStr, statusCode);
    }

    /**
     *@returnthe isSuccess
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     *@returnthe !isSuccess
     */
    public boolean isNotSuccess() {
        return !success;
    }

    /**
     *@returnthe respStr
     */
    public String getRespStr() {
        return respStr;
    }

    /**
     *@returnthe statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     *@returnthe method
     */
    public HttpMethod getMethod() {
        return method;
    }
}