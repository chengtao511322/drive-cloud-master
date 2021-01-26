package com.drive.marketing.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;


/**
 * 活动项目设置
 *
 * @author xiaoguo
 */
@Data
public class ActivityProjectSettingEditParam {


    private String id;

    // 优惠
    private String couponId;

    // 项目id 班型ID
    private String projectId;

    // 项目名称
    private String projectName;

    // 产品价格
    private BigDecimal projectAmount;

    // 租户ID
    private String tenantId;

    // 活动提成金额
    private BigDecimal deductAmount;

    // 推广人员 获取佣金
    private BigDecimal promotionAmount;

    // 渠道经理获取佣金
    private BigDecimal channelManagerAmount;

    // 活动ID
    private String activityId;

    // 优惠券名称
    private String couponName;


}