package com.drive.basics.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 平台渠道表
 *
 * @author xiaoguo
 */
@Data
public class PlatformChannelVo {


	// 渠道名称
	@Excel(name = "渠道名称", width = 20)
	private String channelName;

	// 渠道ID
	@Excel(name = "渠道ID", width = 20)
	private String id;

	// 策略执行beanid
	@Excel(name = "策略执行beanid", width = 20)
	private String strategyBeanId;

	// 状态 0 未启用 1：启用
	@Excel(name = "状态 0 未启用 1：启用", width = 20)
	private Integer status;

	// 删除状态 0：未删除  1：已删除
	@Excel(name = "删除状态 0：未删除  1：已删除", width = 20)
	private Integer isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 渠道key
	@Excel(name = "渠道key", width = 20)
	private String channelKey;

	// 商户APPID
	@Excel(name = "商户APPID", width = 20)
	private String appId;

	// app私钥
	@Excel(name = "app私钥", width = 20)
	private String appPrivteKey;

	// 商户公钥
	@Excel(name = "商户公钥", width = 20)
	private String commercialPublicKey;

	// 网关地址
	@Excel(name = "网关地址", width = 20)
	private String gateway;

	// 异步通知地址
	@Excel(name = "异步通知地址", width = 20)
	private String notifyUrl;

	// 同步通知地址
	@Excel(name = "同步通知地址", width = 20)
	private String synUrl;

	// 商户号
	@Excel(name = "商户号", width = 20)
	private String mchId;

	// 类型 1  ：支付  2 oss上传  3：短信
	@Excel(name = "类型 1  ：支付  2 oss上传  3：短信", width = 20)
	private Integer type;

	@Excel(name = "", width = 20)
	private String tenantId;

	// 桶名
	@Excel(name = "桶名", width = 20)
	private String bucketName;

	@Excel(name = "", width = 20)
	private String endPoint;

	@Excel(name = "", width = 20)
	private LocalDateTime updateTime;

}