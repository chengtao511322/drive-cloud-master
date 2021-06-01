package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 平台题目选项
 *
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class QuestionOptionsPageQueryParam extends BasePageQueryParam {


	@ApiModelProperty(value = "")
	private String id;
	// 模糊查询字段

	// 题库ID
	@ApiModelProperty(value = "题库ID")
	private String questionBankId;
	// 模糊查询字段

	// 题目选项
	@ApiModelProperty(value = "题目选项")
	private String questionOption;
	// 模糊查询字段

	// 选项内容
	@ApiModelProperty(value = "选项内容")
	private String optionContent;
	// 模糊查询字段

	// 是否正确
	@ApiModelProperty(value = "是否正确")
	private String isRight;
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
	//private String vagueNameSearch

	//模糊题号
	private String vagueQuestionBankIdSearch;

	//模糊选项
	private String vagueOptionContentSearch;

}