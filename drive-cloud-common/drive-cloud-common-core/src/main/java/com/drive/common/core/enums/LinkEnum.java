package com.drive.common.core.enums;

/**
 * 外链
 *
 * @author xiaoguo
 */
public enum LinkEnum {

    NOT_LINK(1, "否"),
    IS_LINK(0, "是");

    private final Integer code;
    private final String info;

    LinkEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
