package com.drive.admin.enums;


/**
 * 常用状态枚举
 *
 * @Author JoyoDuan
 * @Date 2021/1/6
 */
public enum OperationTypeEnum {
    BACK_SERVICE("1", "后台）客服取消"),
    STUDENT("2", "学员取消 "),


    SOLD("2","已下架");

    private final String code;
    private final String info;

    OperationTypeEnum(String code, String info) {
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
