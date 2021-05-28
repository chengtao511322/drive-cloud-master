package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 平台题库表
 *
 * @author chentao
 */
@Data
public class QuestionBankEditParam {


    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String id;

    // 题号
    @NotBlank(message = "题号不能为空")
    @ApiModelProperty(value = "题号")
    private Integer questionNo;

    // 科目类型
    @NotBlank(message = "科目类型不能为空")
    @ApiModelProperty(value = "科目类型")
    private String subjectType;

    // 题目章节
    @NotBlank(message = "题目章节不能为空")
    @ApiModelProperty(value = "题目章节")
    private String questionChapter;

    // 题型（知识点类型）
    @NotBlank(message = "题型（知识点类型）不能为空")
    @ApiModelProperty(value = "题型（知识点类型）")
    private String questionType;

    // 媒介路径（图片或者视频）
    @NotBlank(message = "媒介路径（图片或者视频）不能为空")
    @ApiModelProperty(value = "媒介路径（图片或者视频）")
    private String mediaUrl;

    // 题目内容
    @NotBlank(message = "题目内容不能为空")
    @ApiModelProperty(value = "题目内容")
    private String questionContent;

    // 是否多选
    @NotBlank(message = "是否多选不能为空")
    @ApiModelProperty(value = "是否多选")
    private String isMultipleAnswer;

    // 答题限时（单位秒）
    @NotBlank(message = "答题限时（单位秒）不能为空")
    @ApiModelProperty(value = "答题限时（单位秒）")
    private Integer answerTimeLimit;

    // 解答说明
    @NotBlank(message = "解答说明不能为空")
    @ApiModelProperty(value = "解答说明")
    private String answerRemarks;

    // 创建者
    @NotBlank(message = "创建者不能为空")
    @ApiModelProperty(value = "创建者")
    private String createUser;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新者
    @NotBlank(message = "更新者不能为空")
    @ApiModelProperty(value = "更新者")
    private String updateUser;

    // 更新时间
    @NotBlank(message = "更新时间不能为空")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 难度(1,2,3,4,5)
    @NotBlank(message = "难度(1,2,3,4,5)不能为空")
    @ApiModelProperty(value = "难度(1,2,3,4,5)")
    private String difficulty;

    // 媒介类型(1-图片，2-视频)
    @NotBlank(message = "媒介类型(1-图片，2-视频)不能为空")
    @ApiModelProperty(value = "媒介类型(1-图片，2-视频)")
    private String isView;


}