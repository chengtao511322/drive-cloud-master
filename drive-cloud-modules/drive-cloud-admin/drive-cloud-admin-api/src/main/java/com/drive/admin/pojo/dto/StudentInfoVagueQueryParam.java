package com.drive.admin.pojo.dto;

import lombok.Data;

@Data
public class StudentInfoVagueQueryParam {

    private String vagueId;

    // 省
    private String vagueProvinceId;

    // 市
    private String vagueCityId;

    // 区
    private String vagueAreaId;

    // 手机
    private String vaguePhone;

    // 昵称
    private String vagueUsername;

    // 密码
    private String vaguePassword;

    // 身份证号
    private String vagueIdCard;

    // 真实姓名
    private String vagueRealName;

    // 年龄
    private Integer vagueAge;

    // 性别（男；女）
    private String vagueSex;

    // 邮箱
    private String vagueEmail;

    // 自身推荐码
    private String vagueRecommendCodeSelf;

    // 推荐人id
    private String vagueRecommendUserId;

    // 推荐类型（1-学员；2-教练；3-运维）
    private String vagueRecommendUserType;

    // 推荐新用户类型（1-收入现金，2-收入学车币）
    private String vagueRecommendIncomeType;


}
