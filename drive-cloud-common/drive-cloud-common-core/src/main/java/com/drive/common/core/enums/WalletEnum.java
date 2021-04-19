package com.drive.common.core.enums;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public enum  WalletEnum {

    DRIVER_WALLET_INCOME("1", "收益"),
    DRIVER_WALLET_SUBMIT_CASH ("2", "支出"),


    ;

    private final String code;
    private final String info;

    WalletEnum(String code, String info) {
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
