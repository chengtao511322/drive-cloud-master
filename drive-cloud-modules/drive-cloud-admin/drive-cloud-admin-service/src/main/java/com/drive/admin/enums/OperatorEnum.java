package com.drive.admin.enums;


/**
 * 常用状态枚举
 *
 * @Author JoyoDuan
 * @Date 2021/1/6
 */
public enum OperatorEnum {
    DAY("2", "天数"),
    BINDING("2", "绑定"),
    STATUS_RELIEVE_BIND("3", "已解绑"),
    STATUS_NOT_BIND("1", "待解绑"),

    STAY_PAY("1","待支付"),
    YET_PAY_SUCCESS("2","已支付"),

    C1 ("1","C1"),


    FLOW_TYPE_PAY("1","支付流水"),
    AFD_COACH_TIEM_ROYALTY_COST("3","课时教练提成费用"),
    INCOME_USER_TYPE_COACH("2","教练"),
    INCOME_USER_TYPE_TOPERATOR("7","运营商"),
    INCOME_USER_TYPE_SCHOOL("6","驾校"),
    DRIVERPRINCE_OPERATORID("8d26f5f6cae74532bf0baf37fc8ccd0f","学车小王子"),
    PUBLIC_CAR_ROYALTY_COST("22","驾校公车提成费用"),
    PLATFORM_WALLET_OPERATOR_PAY("35","运营商支出"),
    AFD_SCHOOL_ENROLL_COST ("1","学车报名驾校提成"),
    PLATFORM_WALLET_OPERATOR ("34","运营商收入"),



    ;

    private final String code;
    private final String info;

    OperatorEnum(String code, String info) {
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
