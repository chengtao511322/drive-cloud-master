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
 * 平台题库表
 *
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class QuestionBankPageQueryParam extends BasePageQueryParam {


	@ApiModelProperty(value = "")
	private String id;
	// 模糊查询字段

	// 题号
	@ApiModelProperty(value = "题号")
	private Integer questionNo;
	// 模糊查询字段

	// 科目类型
	@ApiModelProperty(value = "科目类型")
	private String subjectType;
	// 模糊查询字段

	// 题目章节
	@ApiModelProperty(value = "题目章节")
	private String questionChapter;
	// 模糊查询字段

	// 题型（知识点类型）
	@ApiModelProperty(value = "题型（知识点类型）")
	private String questionType;
	// 模糊查询字段

	// 媒介路径（图片或者视频）
	@ApiModelProperty(value = "媒介路径（图片或者视频）")
	private String mediaUrl;
	// 模糊查询字段

	// 题目内容
	@ApiModelProperty(value = "题目内容")
	private String questionContent;
	// 模糊查询字段

	// 是否多选
	@ApiModelProperty(value = "是否多选")
	private String isMultipleAnswer;
	// 模糊查询字段

	// 答题限时（单位秒）
	@ApiModelProperty(value = "答题限时（单位秒）")
	private Integer answerTimeLimit;
	// 模糊查询字段

	// 解答说明
	@ApiModelProperty(value = "解答说明")
	private String answerRemarks;
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

	// 难度(1,2,3,4,5)
	@ApiModelProperty(value = "难度(1,2,3,4,5)")
	private String difficulty;
	// 模糊查询字段

	// 媒介类型(1-图片，2-视频)
	@ApiModelProperty(value = "媒介类型(1-图片，2-视频)")
	private String isView;
	// 模糊查询字段
	//private String vagueNameSearch

	//题号模糊搜索
	@ApiModelProperty(value = "模糊搜索题号")
	private String vagueQuestionNoSearch;

	//内容模糊搜索
	@ApiModelProperty(value = "题目内容模糊搜索")
	private String vagueQuestionContentSearch;

}