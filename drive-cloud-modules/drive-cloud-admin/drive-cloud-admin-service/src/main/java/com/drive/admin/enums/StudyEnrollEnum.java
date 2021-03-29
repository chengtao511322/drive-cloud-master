package com.drive.admin.enums;


import cn.hutool.core.util.StrUtil;

/**
 * 报名状态
 *
 * @Author
 * @Date 2021/3/17
 */
public enum StudyEnrollEnum {
    /*是否还在平台学习（0-否，1-是）*/
    NO_INTENTION("0", "否","0"),
    HAVE_INTENTION("1", "是","1"),

    ENROLL_STATUS_SUBMIT ("1", "提交报名",""),
    ENROLL_STATUS_STAY_SUBMIT ("2", "已联系待支付",""),
    ENROLL_STATUS_PAY_SUCCESS  ("3", "已支付待面签",""),

    ENROLL_STATUS_ENROLL_COMPLETE ("6", "报名完成","enrollSuccessStrategy"),
    ENROLL_STATUS_CANCEL ("7", "报名取消","noIntentionStudyEnroll"),
    ENROLL_STATUS_PREPARE_STAY_EXAMINE ("12", "已面签待审核","enrollStatusPrepareStayExamineStrategy"),
    ENROLL_STATUS_PASSWORD_EXAMINE ("13", "密码已提交待审核","enrollStatusPasswordExamineStrategy"),

    /*钱包明细交易状态: 收益*/
    DRIVER_WALLET_INCOME ("1", "收益",""),
    /*钱包明细交易状态: 支出*/
    DRIVER_WALLET_SUBMIT_CASH ("2", "支出",""),

    // vip报名-绑定状态-2-已绑定(登录渠道绑定状态共享此值)
    BIND_STATUS_ALREADY_BIND("2","已绑定",""),


    ENROLL_TYPE_AUTONOMOUSLY("1","自主预约",""),


    PLATFORM_WALLET_OPERATOR("34","运营商收入",""),
    PLATFORM_WALLET_OPERATOR_PAY("35","运营商支出",""),

    INCOME_USER_TYPE_TOPERATOR("7","运营商",""),

    AFD_SCHOOL_ENROLL_COST("1","学车报名驾校提成",""),
    INCOME_USER_TYPE_SCHOOL("6","驾校",""),

    AFD_SYSTEM_COUPON_PAY("20","优惠券支出",""),

    ;

    private final String code;
    private final String info;
    private final String strategyValue;

    StudyEnrollEnum(String code, String info,String strategyValue) {
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

        for (StudyEnrollEnum enumItem : StudyEnrollEnum.values()) {
            if (enumItem.getCode().equals(code)) {
                return enumItem.getStrategyValue();
            }
        }
        return "";

    }
}
