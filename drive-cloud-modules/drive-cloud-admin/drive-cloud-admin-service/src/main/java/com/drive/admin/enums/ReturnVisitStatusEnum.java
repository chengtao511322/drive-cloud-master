package com.drive.admin.enums;


/**
 * 常用状态枚举
 *
 * @Author JoyoDuan
 * @Date 2021/1/6
 */
public enum ReturnVisitStatusEnum {

    PRE("1", "售前"),

    AFTER("2", "售后")

    ;



    private final String code;
    private final String info;

    ReturnVisitStatusEnum(String code, String info) {
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
