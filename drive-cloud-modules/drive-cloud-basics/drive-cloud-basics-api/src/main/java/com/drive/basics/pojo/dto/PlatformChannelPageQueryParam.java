package com.drive.basics.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 平台渠道表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PlatformChannelPageQueryParam extends BasePageQueryParam {


	// 渠道名称
	private String channelName;

	// 渠道ID
	private String id;

	// 策略执行beanid
	private String strategyBeanId;

	// 状态 0 未启用 1：启用
	private Integer status;

	// 删除状态 0：未删除  1：已删除
	private Integer isDelete;

	// 创建时间
	private LocalDateTime createTime;

	// 渠道key
	private String channelKey;

	// 商户APPID
	private String appId;

	// app私钥
	private String appPrivteKey;

	// 商户公钥
	private String commercialPublicKey;

	// 网关地址
	private String gateway;

	// 异步通知地址
	private String notifyUrl;

	// 同步通知地址
	private String synUrl;

	// 商户号
	private String mchId;

	// 类型 1  ：支付  2 oss上传  3：短信
	private Integer type;

	private String tenantId;

	// 桶名
	private String bucketName;

	private String endPoint;

	private LocalDateTime updateTime;

}