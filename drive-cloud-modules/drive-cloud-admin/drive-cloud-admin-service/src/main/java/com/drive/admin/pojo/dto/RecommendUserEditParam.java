package com.drive.admin.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * 推广人员信息表
 *
 * @author xiaoguo
 */
@Data
public class RecommendUserEditParam {


    // id
    private String id;

    // 渠道经理表id
    private String managerId;

    // 推广商类型（1-个人，2-商铺）
    private String userType;

    // 学员id
    private String studentId;

    // 名称(商店，个人，组织)
    private String name;

    // 备注
    private String remarks;

    // 状态(1-待审核，2-通过，3-驳回)
    private String status;

    // 经度
    private String longitude;

    // 纬度
    private String latitude;

    // 详细地址
    private String address;

    // 是否删除(0-否，1-是)
    private String isDelete;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 课时提成百分比
    private BigDecimal classTiemPercent;

    // 运营商id(数据权限标记)
    private String operatorId;

    // 报名分成金额
    private BigDecimal applyDivideAmount;

    // VIP报名分成金额
    private BigDecimal vipApplyDivideAmount;

    // VIP课时提成百分比
    private BigDecimal vipClassTiemPercent;


}