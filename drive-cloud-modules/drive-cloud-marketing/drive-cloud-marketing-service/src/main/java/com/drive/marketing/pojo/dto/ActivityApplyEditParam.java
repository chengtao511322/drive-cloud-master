package com.drive.marketing.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * 活动参加记录表
 *
 * @author xiaoguo
 */
@Data
public class ActivityApplyEditParam {


    private String id;

    // 用户名称
    private String userName;

    // 手机号
    private String phone;

    // 活动名称
    private String activityName;

    private String userId;

    // 活动ID
    private String activityId;

    private LocalDateTime applyTime;

    // 创建者
    private String addUser;

    private LocalDateTime createTime;

    // 修改时间
    private LocalDateTime updateTime;

    // 启用状态 0  未启用  1 已经启用
    private Integer status;

    // 删除状态 0 未删除1删除
    private Integer isDelete;

    // 运营位置 ，存放省市区编码，多个用逗号隔开
    private String operatingPosition;

    private String operator;

    // 租户ID
    private String tenantId;

    private String promoteUserId;

    private String projectId;


}