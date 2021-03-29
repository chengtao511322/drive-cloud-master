package com.drive.admin.enums;


/**
 * 常用状态枚举
 *
 * @Author JoyoDuan
 * @Date 2021/1/6
 */
public enum TypeEnum {

    COACH("2", "教练"),

    SERVICE("3", "客服"),


    SOLD("2","已下架");

    private final String code;
    private final String info;

    TypeEnum(String code, String info) {
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
