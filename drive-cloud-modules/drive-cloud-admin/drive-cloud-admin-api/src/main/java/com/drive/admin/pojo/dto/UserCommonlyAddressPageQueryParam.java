package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户常用地址关联表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserCommonlyAddressPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 用户id
	@ApiModelProperty(value = "用户id")
	private String userId;

	// 模糊查询字段

	// 用户类型(1-学员 2-教练  3-客服 )
	@ApiModelProperty(value = "用户类型(1-学员 2-教练  3-客服 )")
	private String userType;
	// 模糊查询字段

	// 地址名称
	@ApiModelProperty(value = "地址名称")
	private String address;
	// 模糊查询字段

	// 地址纬度
	@ApiModelProperty(value = "地址纬度")
	private String latitude;
	// 模糊查询字段

	// 地址经度
	@ApiModelProperty(value = "地址经度")
	private String longitude;
	// 模糊查询字段

	// 是否默认为地址（是，否）
	@ApiModelProperty(value = "是否默认为地址（是，否）")
	private String isDefault;
	// 模糊查询字段

	// 状态(正常，停用)
	@ApiModelProperty(value = "状态(正常，停用)")
	private String status;
	// 模糊查询字段

	// 备注
	@ApiModelProperty(value = "备注")
	private String remarks;
	// 模糊查询字段

	// 注册时间
	@ApiModelProperty(value = "注册时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 修改时间
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 场地ID
	@ApiModelProperty(value = "场地ID")
	private String siteId;
	// 模糊查询字段

	// 科目类型
	@ApiModelProperty(value = "科目类型")
	private Integer subjectType;
	// 模糊查询字段
	//private String vagueNameSearch

}