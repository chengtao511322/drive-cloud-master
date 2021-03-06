package com.drive.admin.enums;


import cn.hutool.core.util.StrUtil;

/**
 * 常用状态枚举
 *
 * @Author JoyoDuan
 * @Date 2021/1/6
 */
public enum StatusEnum {
    ENABLE("1", "正常",""),
    DISABLE("2", "停用",""),

    NOTDELETE("0", "未删除",""),
    ELETE("1", "删除",""),

    NOTPUBLISH("0","未发布",""),
    PUBLISH("1","发布",""),

    NORMAL("2", "正常",""),
    STAY_EXAMINE("0", "待审核","passAuditStatusState"),
    LIQUIDATION_STATUS_WAIT("1", "待清算",""),
    LIQUIDATION_STATUS_SUCCESS("2", "清算成功",""),
    LIQUIDATION_STATUS_FAIL("3", "清算失败",""),
    LIQUIDATION_STATUS_ILLEGAL_OPERATION("4", "非法操作",""),
    TRANSFER_SUCCESS("TRANSFER_SUCCESS", "转账成功",""),
    TRANSFER_ERROR("TRANSFER_ERROR", "转账失败",""),
    ACCOUNT_ERROR("ACCOUNT_ERROR", "支付账户密码错误",""),
    TRADE_TYPE_SUCCESS("1", "成功",""),
    TRADE_TYPE_FAIL("2", "失败",""),
    TRADE_TYPE_HAVE_IN_HAND("3", "处理中",""),
    SOLD("2","已下架","");

    private final String code;
    private final String info;
    private final String strategyValue;

    StatusEnum(String code, String info, String strategyValue) {
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

        for (StatusEnum enumItem : StatusEnum.values()) {
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

        for (StatusEnum enumItem : StatusEnum.values()) {
            if (enumItem.getCode().equals(code)) {
                return enumItem.getInfo();
            }
        }
        return "";

    }
}
