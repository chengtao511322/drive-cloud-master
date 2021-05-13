package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 运营商加盟申请
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OperatorRecruitPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 省
	@ApiModelProperty(value = "省")
	private String provinceId;
	// 模糊查询字段

	// 市
	@ApiModelProperty(value = "市")
	private String cityId;
	// 模糊查询字段

	// 区
	@ApiModelProperty(value = "区")
	private String areaId;
	// 模糊查询字段

	// 姓名
	@ApiModelProperty(value = "姓名")
	private String realName;
	// 模糊查询字段

	// 电话
	@ApiModelProperty(value = "电话")
	private String phone;
	// 模糊查询字段

	// 邮箱
	@ApiModelProperty(value = "邮箱")
	private String eMail;
	// 模糊查询字段

	// 公司名称
	@ApiModelProperty(value = "公司名称")
	private String corporationName;
	// 模糊查询字段

	// 申请说明
	@ApiModelProperty(value = "申请说明")
	private String applyExplain;
	// 模糊查询字段

	// 是否删除
	@ApiModelProperty(value = "是否删除")
	private String isDelete;
	// 模糊查询字段

	// 注册时间
	@ApiModelProperty(value = "注册时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 修改时间
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	// 模糊查询字段
	@ApiModelProperty(value = "公司名称模糊查询")
	private String vagueCorporationNameSearch;


	private String[] createDateTimeSearchArr = new String[2];

}