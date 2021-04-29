package com.drive.common.core.enums;

/**
 * 数据权限类型
 *
 * @author xiaoguo
 */
public enum DataScopeEnum {

    ALL("1", "全部数据权限"),
    CUSTOM("2", "自定数据权限"),
    DEPT("3", "本部门数据权限"),
    DEPT_AND_CHILD("4", "本部门及以下数据权限"),
    SELF("5", "仅本人数据权限"),
    CUSTOM_SELF("6", "自定数据权限及仅限本人数据权限"),
    ADMIN_MODULE("admin", "admin系统模块");


    private final String code;
    private final String info;

    DataScopeEnum(String code, String info) {
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
