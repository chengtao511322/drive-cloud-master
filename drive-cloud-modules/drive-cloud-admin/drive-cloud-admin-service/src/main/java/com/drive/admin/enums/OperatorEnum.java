package com.drive.admin.enums;


/**
 * 常用状态枚举
 *
 * @Author JoyoDuan
 * @Date 2021/1/6
 */
public enum OperatorEnum {
    DAY("2", "天数"),

    ;

    private final String code;
    private final String info;

    OperatorEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
