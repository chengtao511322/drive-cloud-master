package com.drive.basics.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * banner 轮播图
 *
 * @author xiaoguo
 */
@Data
public class BannerVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// banner的主题名称
	@Excel(name = "banner的主题名称", width = 20)
	private String name;

	// 发布状态0未发布 1发布
	@Excel(name = "发布状态0未发布 1发布", width = 20)
	private String status;

	// 排序号
	@Excel(name = "排序号", width = 20)
	private Integer sort;

	// banner时间控制
	@Excel(name = "banner时间控制", width = 20)
	private String timer;

	// 删除状态 0 未删除1删除
	@Excel(name = "删除状态 0 未删除1删除", width = 20)
	private String isDelete;

	// 添加者
	@Excel(name = "添加者", width = 20)
	private String addUser;

	// 远程图片路径
	@Excel(name = "远程图片路径", width = 20)
	private String urlPath;

	// banner 所属栏目  如活动
	@Excel(name = "banner 所属栏目  如活动", width = 20)
	private String channel;

	// banner点击地址
	@Excel(name = "banner点击地址", width = 20)
	private String clickUrl;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// banner位置类型 0 ：首页轮播轮播位置 1：学车报名轮播位置 2考试报名轮播位置3常规训练轮播位置4考试训练轮播位置
	@Excel(name = "banner位置类型 0 ：首页轮播轮播位置 1：学车报名轮播位置 2考试报名轮播位置3常规训练轮播位置4考试训练轮播位置", width = 20)
	private String type;

	// 标题
	@Excel(name = "标题", width = 20)
	private String title;

	// 扩展字段
	@Excel(name = "扩展字段", width = 20)
	private String extend;

	// 运营位置 ，存放省市区编码，多个用逗号隔开
	@Excel(name = "运营位置 ，存放省市区编码，多个用逗号隔开", width = 20)
	private String operatingPosition;

	// 租户ID
	@Excel(name = "租户ID", width = 20)
	private String tenantId;

}