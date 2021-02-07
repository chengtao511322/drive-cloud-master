package com.drive.admin.enums;


/**
 * 考试报名常用状态枚举
 *
 * @Author JoyoDuan
 * @Date 2021/1/6
 */
public enum ExamEnrollStatusEnum {
    SUBMIT_APPOINTMENT("1", "提交预约"),
    PAY_SUCCESS("2", "支付成功"),

    PAY_FAILL("3", "支付失败"),
    SUBMIT_FAILL("4", "报名失败"),

    APPOINTMENT_SUCCESS("5","预约成功"),
    ENROLL_CANCEL("6","报名取消"),
    EXAM_ACCOMPLISH("7","考试完成"),
    EXAM_PASS("8","考试通过"),
    EXAM_NO_PASS("9","考试不通过"),
    APPLY_LODING("10","申请中"),
    REFUND_LODING("11","退款处理中"),

    REFUND_SUCCESS("12","退款成功");

    private final String code;
    private final String info;

    ExamEnrollStatusEnum(String code, String info) {
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
