package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 学车一费制定价表
 *
 * @author xiaoguo
 */
@Data
public class OneFeeSystemPriceEditParam {


    // 主键
    private String id;

    // 名称
    @ApiModelProperty(value = "服务包名称")
    private String name;

    // 驾照类型.
    @ApiModelProperty(value = "驾照类型")
    private String driveType;

    // 班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）
    @ApiModelProperty(value = "班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）")
    private String classType;

    // 价格
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    // 科目二教练提成金额
    @ApiModelProperty(value = "科目二教练提成金额")
    private BigDecimal coachSubjectType2;

    // 科目三教练提成金额
    @ApiModelProperty(value = "科目三教练提成金额")
    private BigDecimal coachSubjectType3;

    // 科目二驾校提成金额
    @ApiModelProperty(value = "科目二驾校提成金额")
    private BigDecimal schoolSubjectType2;

    // 科目三驾校提成金额
    @ApiModelProperty(value = "科目三驾校提成金额")
    private BigDecimal schoolSubjectType3;

    // 运营商提成金额
    @ApiModelProperty(value = "运营商提成金额")
    private BigDecimal operatorChangeMoney;

    // 平台提成金额（所有上级运营商提成金）
    @ApiModelProperty(value = "平台提成金额（所有上级运营商提成金）")
    private BigDecimal serviceChangeMoney;

    // 详情介绍
    @ApiModelProperty(value = "详情介绍")
    private String details;

    // 奖金（学员拿证后，教练获得的奖金）
    @ApiModelProperty(value = "奖金（学员拿证后，教练获得的奖金）")
    private BigDecimal bonus;

    // 挂科扣款金额
    @ApiModelProperty(value = "挂科扣款金额")
    private BigDecimal testNotPassWithholdMoney;

    // 是否删除
    private String isDelete;

    // 创建时间

    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 运营商id(数据权限标记)
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;

    // 科目一免费考试次数
    @ApiModelProperty(value = "科目一免费考试次数")
    private Integer subject1CostFreeNumber;

    // 科目二免费考试次数
    @ApiModelProperty(value = "科目二免费考试次数")
    private Integer subject2CostFreeNumber;

    // 科目三免费考试次数
    @ApiModelProperty(value = "科目三免费考试次数")
    private Integer subject3CostFreeNumber;

    // 科目四免费考试次数
    @ApiModelProperty(value = "科目四免费考试次数")
    private Integer subject4CostFreeNumber;

    // 价格介绍
    @ApiModelProperty(value = "价格介绍")
    private String priceIntroduce;

    // 学车流程
    @ApiModelProperty(value = "学车流程")
    private String drivingFlow;

    // 班型等级(用于报名升班控制，只能往大升，不能往小升)
    @ApiModelProperty(value = "班型等级(用于报名升班控制，只能往大升，不能往小升)")
    private Integer classGrade;

    // 原价
    @ApiModelProperty(value = "原价")
    private BigDecimal originalPrice;

    // 是否可升级
    @ApiModelProperty(value = "是否可升级")
    private String isUpgrade;

    // 状态(1-正常，2-停用)
    @ApiModelProperty(value = "状态(1-正常，2-停用)")
    private String status;

    // 服务项价格id
    @ApiModelProperty(value = "服务项价格id")
    private List<ServicePackageDetailEditParam> serviceItemPriceList;

    private String serviceItemPriceId;

    private BigDecimal coachSubject2ExpectIncome;
    private BigDecimal coachSubject3ExpectIncome;
}