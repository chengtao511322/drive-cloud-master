package com.drive.basics.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * banner 轮播图
 *
 * @author xiaoguo
 */
@Data
public class BannerEditParam {


    // id
    private String id;

    // banner的主题名称
    private String name;

    // 发布状态0未发布 1发布
    private String status;

    // 排序号
    private Integer sort;

    // banner时间控制
    private String timer;

    // 删除状态 0 未删除1删除
    private String isDelete;

    // 添加者
    private String addUser;

    // 远程图片路径
    private String urlPath;

    // banner 所属栏目  如活动
    private String channel;

    // banner点击地址
    private String clickUrl;

    // 创建时间
    private LocalDateTime createTime;

    // 修改时间
    private LocalDateTime updateTime;

    // banner位置类型 0 ：首页轮播轮播位置 1：学车报名轮播位置 2考试报名轮播位置3常规训练轮播位置4考试训练轮播位置
    private String type;

    // 标题
    private String title;

    // 扩展字段
    private String extend;

    // 运营位置 ，存放省市区编码，多个用逗号隔开
    private String operatingPosition;

    // 租户ID
    private String tenantId;


}