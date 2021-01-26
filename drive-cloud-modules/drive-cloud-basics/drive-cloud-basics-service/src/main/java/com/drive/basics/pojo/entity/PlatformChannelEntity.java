package com.drive.basics.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
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
@TableName("platform_channel")
public class PlatformChannelEntity extends BaseEntity {


	// 渠道名称
	private String channelName;

	// 渠道ID
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 策略执行beanid
	private String strategyBeanId;

	// 状态 0 未启用 1：启用
	private Integer status;

	// 删除状态 0：未删除  1：已删除
	@TableLogic
	@TableField(value="is_delete")
	private Integer isDelete;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
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
	@TableField("mchId" )
	private String mchId;

	// 类型 1  ：支付  2 oss上传  3：短信
	private Integer type;

	private String tenantId;

	// 桶名
	private String bucketName;

	private String endPoint;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}