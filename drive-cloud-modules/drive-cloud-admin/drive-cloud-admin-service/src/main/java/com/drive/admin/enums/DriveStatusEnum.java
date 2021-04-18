package com.drive.admin.enums;


import cn.hutool.core.util.StrUtil;

/**
 * 练车状态
 *
 * @Author
 * @Date 2021/3/17
 */
public enum DriveStatusEnum {



    // 预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）
    STAY_PAY("0","待支付",""),
    MAKE_SUCCESS("1","预约成功",""),
    MAKE_FAIL("2","预约失败","applySuccessStrategy"),
    MAKE_CANCEL("3","预约取消",""),
    DRIVING_LOADING("4","练车中","examPassStrategy"),
    DRIVING_SUCCESS("5","练车完成",""),
    PICK_LOADING("6","接你中","payStayApplyStrategy"),
    YET_GET_ON("7","已上车",""),
    ;

    private final String code;
    private final String info;
    private final String strategyValue;

    DriveStatusEnum(String code, String info, String strategyValue) {
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

        for (DriveStatusEnum enumItem : DriveStatusEnum.values()) {
            if (enumItem.getCode().equals(code)) {
                return enumItem.getStrategyValue();
            }
        }
        return "";

    }
}
