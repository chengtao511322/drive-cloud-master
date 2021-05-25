package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 平台发送短信表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class MessagePageQueryParam extends BasePageQueryParam {


	@ApiModelProperty(value = "")
	private String id;
	// 模糊查询字段

	// 手机号码
	@ApiModelProperty(value = "手机号码")
	private String phone;
	// 模糊查询字段

	// 验证码
	@ApiModelProperty(value = "验证码")
	private String code;
	// 模糊查询字段

	@ApiModelProperty(value = "")
	private LocalDateTime createtime;
	// 模糊查询字段

	// 有效时间
	@ApiModelProperty(value = "有效时间")
	private LocalDateTime expiretime;
	// 模糊查询字段

	// 备注
	@ApiModelProperty(value = "备注")
	private String remarks;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段
	//private String vagueNameSearch

}