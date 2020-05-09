package com.ryb.core.resultenum;

/**
 * @author 常坤
 */

public enum ResultEnum {
    SUCCESS("200", "成功"),
    ERROR("500", "失败"),
    WAIT("202", "正在处理结果");

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
