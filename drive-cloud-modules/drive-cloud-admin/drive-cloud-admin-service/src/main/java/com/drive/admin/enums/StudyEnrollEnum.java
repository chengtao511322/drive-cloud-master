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
    YET_ENROLL_UPGRADE_CLASS("15","已升班",""),
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
    TRADE_FLOW_STATUS_PAY("1","支付流水 ",""),
    TRADE_FLOW_REFUND("1","退款流水 ",""),
    /******-----------------------订单相关-----------------------------------*/
    // 待支付
    STAT_PAY("1","待支付",""),
    PAY_SUCCESS("2","支付成功",""),
    PAY_LOADING("3","支付处理中",""),
    PAY_ERROR("4","支付失败",""),
    CANCEL_ORDER("5","已取消",""),
    STAT_EVALUATE("6","待评价",""),
    EVALUATE_SUCCESS("7","已评价",""),
    REFUND_SUCCESS("8","退款成功",""),
    REFUND_LOADING("9","退款处理中",""),
    FLOW_TYPE_REFUND_IN_HAND("5","退款处理中",""),

    FLOW_TYPE_REFUND("2","退款流水",""),
    ENROLL_STATUS_REFUND("9","已退款",""),
    TEST_ENROLL_REFUND_SUCCESS("12","退款成功",""),
    FLOW_TYPE_REFUND_SUCCESS("6","退款成功",""),
    ORDER_TYPE_REFUND_SUCCESS("8","退款成功",""),

    ORDER_TYPE_STUDY_ENROLL("1","学车报名",""),
    ORDER_TYPE_TEST_ENROLL("2","考试报名",""),
    ORDER_TYPE_COMMONLY_TRAIN("3","常规训练",""),
    ORDER_TYPE_TEST_TRAIN("4","考试训练",""),
    CLASS_TYPE_COMMONLY_TRAIN("1","普通训练",""),
    CLASS_TYPE_TEST_TRAIN("2","考试训练",""),

    REFUND_TYPE_WHOLE("0","全额退款 ",""),
    PAY_TYPE_ALI("1","支付宝",""),
    PAY_TYPE_WECHAT ("2","微信",""),
    PAY_TYPE_COUPON  ("3","优惠卷支付",""),
    PAY_TYPE_VIP("4","一费制vip支付",""),
    PAY_TYPE_WECHAT_PUBLIC("6","微信公众号",""),
    APPLY_STATUS_CANCEL("3","预约取消",""),





    /*----------------------教学相关--------------------*/
    //
    BOOK_SUCCESS("5","预约成功",""),
    EXAM_ACCOMPLISH("7","考试完成",""),
    EXAM_PASS("8","考试通过",""),
    EXAM_NO_PASS("9","考试不通过",""),
    APPLY_LOADING("10","申请中",""),
    REFUND_DISPOSE_LOADING("11","退款处理中",""),
    EXAM_REFUND_SUCCESS("12","退款成功",""),


    NO_APPOINTMENT("1","未预约",""),
    YET_APPOINTMENT("2","已预约",""),
    TEACHING_LOADING("3","教学中",""),
    TEACHING_SUCCESS("4","已预约",""),
    CLASS_STATUS_CANCEL("5","已取消",""),
    PICK_SOMEBODY_UP("6","接人中",""),
    YET_GET_ON("7","已上车",""),





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
