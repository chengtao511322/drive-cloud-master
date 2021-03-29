package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 学员教练互评表
 *
 * @author xiaoguo
 */
@Data
public class StudentCoachAppraiseInstallParam {


    // 编号
    @ApiModelProperty(value = "编号")
    private String id;

    // 订单号
    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    // 教练课程ID
    @NotBlank(message = "教练课程ID不能为空")
    @ApiModelProperty(value = "教练课程ID")
    private String classId;

    // 报名单号（考试，学车）
    @NotBlank(message = "报名单号（考试，学车）不能为空")
    @ApiModelProperty(value = "报名单号（考试，学车）")
    private String enrollNo;

    // 评价用户id（学员id）
    @NotBlank(message = "评价用户id（学员id）不能为空")
    @ApiModelProperty(value = "评价用户id（学员id）")
    private String studentId;

    // 学员评价内容
    @NotBlank(message = "学员评价内容不能为空")
    @ApiModelProperty(value = "学员评价内容")
    private String appraiseReplyContent;

    // 学员评价分数
    @NotNull(message = "学员评价分数不能为空")
    @ApiModelProperty(value = "学员评价分数")
    private BigDecimal appraiseReplyGrade;

    // 学员评价时间
    @NotNull(message = "学员评价时间不能为空")
    @ApiModelProperty(value = "学员评价时间")
    private LocalDateTime appraiseReplyTime;

    // 回复内容
    @NotBlank(message = "回复内容不能为空")
    @ApiModelProperty(value = "回复内容")
    private String replyContent;

    // 回复时间
    @ApiModelProperty(value = "回复时间")
    private LocalDateTime replyTime;

    // 被评价用户id
    @NotBlank(message = "被评价用户id不能为空")
    @ApiModelProperty(value = "被评价用户id")
    private String replyId;

    // 被评价用户类型（2-教练；3-运维）
    @NotBlank(message = "被评价用户类型（2-教练；3-运维）不能为空")
    @ApiModelProperty(value = "被评价用户类型（2-教练；3-运维）")
    private String replyType;

    // 被评价用户头像
    @ApiModelProperty(value = "被评价用户头像")
    private String replyHeadUrl;

    // 学员头像
    @ApiModelProperty(value = "学员头像")
    private String studentHeadUrl;

    // 学员评价类型（1-评价 2-投诉）
    @NotBlank(message = "学员评价类型（1-评价 2-投诉）不能为空")
    @ApiModelProperty(value = "学员评价类型（1-评价 2-投诉）")
    private String appraiseType;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}