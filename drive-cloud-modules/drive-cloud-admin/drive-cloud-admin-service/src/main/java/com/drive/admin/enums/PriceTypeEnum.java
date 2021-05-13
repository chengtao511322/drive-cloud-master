package com.drive.admin.enums;


/**
 * 常用状态枚举
 *
 * @Author xiaoguo
 * @Date 2021/1/6
 */
public enum PriceTypeEnum {
    CLASS_DEDUCT_PERCENT("6", "推广商课时提成百分比"),
    CLASS_DEDUCT_AMOUNT("5", "推广商推荐报名提成金额"),

    SOLD("2","已下架");

    private final String code;
    private final String info;

    PriceTypeEnum(String code, String info) {
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
