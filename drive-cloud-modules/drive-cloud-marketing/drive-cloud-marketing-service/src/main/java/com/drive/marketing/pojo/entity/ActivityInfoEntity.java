package com.drive.marketing.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("activity_info")
public class ActivityInfoEntity extends BaseEntity implements java.io.Serializable{

    @TableId(type= IdType.ID_WORKER)
    private String id;

    private String zoneName;

    /**
     * 专区封面图路径
     */
    private String zoneImgPath;

    /**
     * 标题
     */
    private String zoneTitle;

    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     * */
    private LocalDateTime endTime;



    /**
     * 状态启用，0：否，1是
     */
    private String status;

    /**
     * 删除状态：0 没有删除，1：已经删除
     */
    @TableLogic
    private String isDelete;

    /**
     * 添加者
     */
    private String addUser;

    /**
     * 模板
     */
    private String template;

    /**
     * 热门级别
     */
    private Integer hot;

    /**
     * 优惠卷类型；0->全场活动；活动类型  1 学车报名  2考试报名 3常规训练 4 考试训练  5大礼包活动
     */
    private String type;

    private String ruleId;

    /**
     * 活动价格
     */
    private BigDecimal activityPrice;

    /**
     * 原价
     */
    private BigDecimal originalCost;

    private String strategyBeanId;

    /**
     * 运营位置 ，存放省市区编码，多个用逗号隔开
     */
    private String operatingPosition;


    /**
     * 是否首选  0  否 1  是
     */
    private String isFirst;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 专区描述
     */
    private String description;

    /**
     * 静态化地址
     */
    private String staticLink;

    //推广说明
    private String promotionExplain;

    // 推广地址
    private String promotionUrl;



}