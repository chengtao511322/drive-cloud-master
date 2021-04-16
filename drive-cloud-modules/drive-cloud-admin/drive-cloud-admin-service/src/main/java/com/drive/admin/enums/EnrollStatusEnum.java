package com.drive.admin.enums;

public enum EnrollStatusEnum {

    SUBMIT_ENROLL("1", "提交报名"),
    RELATION_WAIT_PAY("2", "已联系待支付"),

    PAY_WAIT_PUT("3", "已支付待备案"),
    SUBMIT_FAIL("4", "支付失败"),

    PAY_FAIL("5","支付失败"),
    ENROLL_SUCCESS("6","报名完成"),
    ENROLL_CANCEL("7","报名取消"),
    ENROLL_FAIL("8","报名失败"),
    REFUND("9","已退款"),
    AUTO_ENROLL_SUCCESS("10","自动报名完成"),
    AUTO_ENROLL_WAIT_AUDIT("11","自动报名待审核"),
    PUT_WAIT_AUDIT("12","已备案待审核"),
    PASSWORD_SUBMIT_WAIT_AUDIT("13","密码已提交待审核"),
    REFUND_LOADING("14","退款处理中"),
    UPGRADE("15","已升班"),

    UPGRADE_WAIT_PAY("16","已升班待支付");

    private final String code;
    private final String info;

    EnrollStatusEnum(String code, String info) {
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
