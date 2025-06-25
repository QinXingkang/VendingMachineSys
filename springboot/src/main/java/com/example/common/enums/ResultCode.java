package com.example.common.enums;

/**
 * @author : QXK
 * @date : 2025-06-24 8:13
 * @description :
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400, "请求参数错误"),
    NOT_FOUND(404, "资源未找到"),
    VALIDATION_FAIL(422, "参数校验失败"),
    ORDER_SAVE_FAIL(430, "订单保存失败"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    PLACE_ORDER_FAILED(600,"下单失败");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
