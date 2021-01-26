package com.drive.admin.enums;


/**
 * 常用状态枚举
 *
 * @Author JoyoDuan
 * @Date 2021/1/6
 */
public enum StatusEnum {
    ENABLE("1", "正常"),
    DISABLE("2", "停用"),

    NOTDELETE("0", "未删除"),
    ELETE("1", "删除"),

    NOTPUBLISH("0","未发布"),
    PUBLISH("1","发布"),
    SOLD("2","已下架");

    private final String code;
    private final String info;

    StatusEnum(String code, String info) {
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
