package com.drive.admin.enums;


/**
 * 常用状态枚举
 *
 * @Author xiaoguo
 * @Date 2021/1/6
 */
public enum SubjectTypeEnum {

    SUBJECT_ONE("1", "科目一"),
    SUBJECT_TWO("2", "科目二"),
    SUBJECT_THREE("3","科目三"),
    SUBJECT_FOUR("4","科目四");

    private final String code;
    private final String info;

    SubjectTypeEnum(String code, String info) {
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
