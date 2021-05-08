package com.drive.common.core.enums;

/**
 * 状态
 *
 * @author xiaoguo
 */
public enum StatusEnum {
    ENABLE("0", "正常"),
    DISABLE("1", "停用"),
    NO("0", "否"),
    YES("1", "是"),

    ;

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
