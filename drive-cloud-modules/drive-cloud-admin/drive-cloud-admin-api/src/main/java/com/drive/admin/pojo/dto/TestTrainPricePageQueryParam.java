package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 平台报名考试练车单价表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TestTrainPricePageQueryParam extends BasePageQueryParam {


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

	// 驾照类型（c1；c2...）
	@ApiModelProperty(value = "驾照类型（c1；c2...）")
	private String driveType;
	// 模糊查询字段

	// 科目类型（一；二；三；四）
	@ApiModelProperty(value = "科目类型（一；二；三；四）")
	private String subjectType;
	// 模糊查询字段

	// 价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)
	@ApiModelProperty(value = "价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)")
	private String priceType;
	// 模糊查询字段

	// 单价
	@ApiModelProperty(value = "单价")
	private BigDecimal price;
	// 模糊查询字段

	// 详情（报名所需的费用明细介绍）
	@ApiModelProperty(value = "详情（报名所需的费用明细介绍）")
	private String remarks;
	// 模糊查询字段

	// 创建者
	@ApiModelProperty(value = "创建者")
	private String createUser;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新者
	@ApiModelProperty(value = "更新者")
	private String updateUser;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段
	//private String vagueNameSearch

}