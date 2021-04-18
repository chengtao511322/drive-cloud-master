package com.drive.admin.enums;


import cn.hutool.core.util.StrUtil;

/**
 * 报名状态
 *
 * @Author
 * @Date 2021/3/17
 */
public enum ExamEnrollEnum {



    // 报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）
    SUBMIT_AWAIT_PAY("1","提交报名待支付",""),
    PAY_SUCCESS("2","支付成功",""),
    BOOK_SUCCESS("5","预约成功","applySuccessStrategy"),
    EXAM_LOADING("15","考试中",""),
    EXAM_ACCOMPLISH("7","考试完成",""),
    EXAM_PASS("8","考试通过","examPassStrategy"),
    EXAM_NO_PASS("9","考试不通过","examNoPassStrategy"),
    APPLY_LOADING("10","申请中","payStayApplyStrategy"),
    REFUND_DISPOSE_LOADING("11","退款处理中",""),
    EXAM_REFUND_SUCCESS("12","退款成功",""),

    ;

    private final String code;
    private final String info;
    private final String strategyValue;

    ExamEnrollEnum(String code, String info, String strategyValue) {
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

        for (ExamEnrollEnum enumItem : ExamEnrollEnum.values()) {
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

        for (ExamEnrollEnum enumItem : ExamEnrollEnum.values()) {
            if (enumItem.getCode().equals(code)) {
                return enumItem.getInfo();
            }
        }
        return "";

    }
}
