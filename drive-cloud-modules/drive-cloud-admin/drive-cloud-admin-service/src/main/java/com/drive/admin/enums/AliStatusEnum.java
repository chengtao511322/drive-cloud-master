package com.drive.admin.enums;


import cn.hutool.core.util.StrUtil;

/**
 * 常用状态枚举
 *
 * @Author XIAOGUO
 * @Date 2021/1/6
 */
public enum AliStatusEnum {
    SUCCESS("10000", "接口调用成功",""),
    DISABLE("2", "停用","");



    private final String code;
    private final String info;
    private final String strategyValue;

    AliStatusEnum(String code, String info, String strategyValue) {
        this.code = code;
        this.info = info;
        this.strategyValue = strategyValue;
    }



    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public String getStrategyValue() {
        return strategyValue;
    }

    public static String getStrategyValueByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return "";
        }

        for (AliStatusEnum enumItem : AliStatusEnum.values()) {
            if (enumItem.getCode().equals(code)) {
                return enumItem.getStrategyValue();
            }
        }
        return "";

    }


    public static String getNameByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return "";
        }

        for (AliStatusEnum enumItem : AliStatusEnum.values()) {
            if (enumItem.getCode().equals(code)) {
                return enumItem.getInfo();
            }
        }
        return "";

    }
}
