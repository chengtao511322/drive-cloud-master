package com.drive.basics.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * 栏目
 *
 * @author xiaoguo
 */
@Data
public class ChannelEditParam implements java.io.Serializable{


    // id
    private String id;

    // 栏目名称
    private String name;

    // 图标地址
    private String iconPath;

    // 排序
    private Integer sort;

    // 状态：0：未发表  1：已经发表  默认1
    private String status;

    // 删除状态:0:未删除  1：已经删除  默认0
    private String isDelete;

    // 创建时间
    private LocalDateTime createTime;

    // 修改时间
    private LocalDateTime updateTime;

    // 添加者
    private String addUser;

    // 二级栏目
    private String parentId;

    // 点击地址（执行函数）
    private String clickUrl;

    // 租户ID
    private String tenantId;

    // android显示状态：0不显示，1：显示
    private String androidShow;

    // ios显示状态：0不显示，1：显示
    private String iosShow;

    // 描述
    private String description;

    // 活动说明
    private String activityExplain;

    // 规则说明
    private String ruleExplain;

    // 栏目
    private String columnGroup;

    // 口令
    private String command;

    // 权限
    private String auth;

    private String userId;

    // (20)明细用户是否显示（1-明细表的人显示，2-明细表的人不显示），控制明细表中的数据是否显示按钮
    private String itemShow;

    // 排序类型
    private Integer sortType;


}