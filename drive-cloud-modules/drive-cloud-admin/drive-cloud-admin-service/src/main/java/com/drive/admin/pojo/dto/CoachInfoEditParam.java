package com.drive.admin.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 教练信息表
 *
 * @author xiaoguo
 */
@Data
public class CoachInfoEditParam {


    private String id;

    // 省
    private String provinceId;

    // 市
    private String cityId;

    // 区
    private String areaId;

    // 电话
    private String phone;

    // 登录密码
    private String password;

    // 真实姓名
    private String realName;

    // 身份证号码
    private String idCard;

    // 身份证图片正面
    private String idCardPathPositive;

    // 身份证图片反面
    private String idCardPathNegative;

    // 邮箱
    private String email;

    // 年龄
    private Integer age;

    // 教龄
    private Integer teachYears;

    // 性别
    private String sex;

    // 教练证照片
    private String teachLicensePath;

    // 教练证号码
    private String teachLicenseNo;

    // 是否为金牌教练
    private String isVip;

    // 状态，0：待审，1：驳回，2：正常：3禁用, 4-未提交5-保险过期预警期
    private String status;

    // 自身推荐码
    private String recommendCodeSelf;

    // 推荐人id
    private String recommendUserId;

    // 推荐类型（1-学员；2-教练；3-运维）
    private String recommendUserType;

    // 推荐时间
    private LocalDateTime recommendDate;

    // 评价分数
    private BigDecimal appraiseGrade;

    // token_key
    private String tokeKey;

    // 45度车辆照片
    private String carPhotoPath;

    // 品牌型号
    private String brandModel;

    // 颜色
    private String color;

    // 车牌号
    private String vehicleNo;

    // 工作证照片路径
    private String workPath;

    // 行驶证照片
    private String drivingLicensePath;

    // 车架号
    private String carFrameNo;

    // 发动机排量
    private String engineDisplace;

    // 发动机号码
    private String engineId;

    // 纬度
    private String latitude;

    // 经度
    private String longitude;

    // 车辆所有人 (与《机动车登记证书》所注明的车辆所有人一致)
    private String ownerName;

    // 车辆注册日期(以行驶证为准)
    private LocalDate certifyDate;

    // 教练证有效期起
    private LocalDate licenseOn;

    // 教练证有效期止
    private LocalDate licenseOff;

    // 审核结果说明
    private String examineResultExplain;

    // 注册时间
    private LocalDateTime createTime;

    // 修改时间
    private LocalDateTime updateTime;

    // 保险单照片
    private String insurancePath;

    // 保险开始时间
    private LocalDate insuranceStartDate;

    // 保险截止时间
    private LocalDate insuranceEndDate;

    // 车型（1-公车，2-私车）
    private String carType;

    // 公车所属驾校id
    private String carSchoolId;

    // 教练提成百分比
    private BigDecimal trainCoachChargePercent;

    // 公车提成百分比
    private BigDecimal carChargePercent;

    // 平台提成百分比
    private BigDecimal serviceChangePercent;

    // 运营商id(数据权限标记)
    private String operatorId;

    // 自动发科 1 是 2否
    private Integer autoSendClass;

    // 绩效归属 1  教练自己 2  驾校
    private Integer performanceAffiliation;

    private Integer topSort;


}