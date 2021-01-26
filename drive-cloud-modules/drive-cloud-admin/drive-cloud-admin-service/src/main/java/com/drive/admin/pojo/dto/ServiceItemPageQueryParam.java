package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 服务项目表
 *
 * @author JoyoDuan
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ServiceItemPageQueryParam extends BasePageQueryParam {


	// 主键id
	private String id;

	// 服务名称
	private String name;

	// 服务类型（报名、科一、科二、科三、科四）
	private String serviceType;

	// 是否删除（1、是，0、否）
	private String isDelete;

	// 状态（1、正常，2、停用）
	private String status;

	// 创建时间
	private LocalDateTime createTime;

	// 更新时间
	private LocalDateTime updateTime;


	// 服务项描述，表述该服务项的具体含义 。 例如：体检面签费是什么？
	private String serviceItemDescribe;

}