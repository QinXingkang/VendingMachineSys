package com.example.common;

import com.example.common.enums.ResultCode;

/**
 * @author : QXK
 * @date : 2025-06-24 8:18
 * @description :
 */
public class Result<T>{
    private int code;
    private String msg;
    private T data;

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.code();
        this.msg = resultCode.message();
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS,data);
    }

    public static <T> Result<T> fail(ResultCode code) {
        return new Result<>(code, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}