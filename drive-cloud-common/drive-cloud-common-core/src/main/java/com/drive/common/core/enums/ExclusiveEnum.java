package com.drive.common.core.enums;

public enum  ExclusiveEnum {

    Exclusive("1", "独享"),
    NOTExclusive("2", "非独享");

    private final String code;
    private final String info;

    ExclusiveEnum(String code, String info) {
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
