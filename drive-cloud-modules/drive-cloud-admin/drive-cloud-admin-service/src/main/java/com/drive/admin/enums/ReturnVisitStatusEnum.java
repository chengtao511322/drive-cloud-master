package com.drive.admin.enums;


/**
 * 常用状态枚举
 *
 * @Author xiaoguo
 * @Date 2021/1/6
 */
public enum ReturnVisitStatusEnum {

    PRE("1", "售前"),

    AFTER("2", "售后"),
    NEW_USER("1", "新用户待回访")

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
