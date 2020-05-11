package com.ryb.core.resultenum;

/**
 * @author 常坤
 */

public enum ResultEnum {
    SUCCESS("200", "成功"),
    ERROR("500", "系统错误"),
    WAIT("202", "正在处理结果"),
    IS_REGISTER("50001", "用户已注册"),
    IS_NOT_REGISTER("50002", "用户尚未注册"),
    SAVE_FAIL("50003", "保存失败"),
    USER_AUTH_FAIL("50004", "用户验证失败,请检查账号密码");

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
