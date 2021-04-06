package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


/**
 * 客服回访记录
 *
 * @author xiaoguo
 */
@Data
public class ServiceReturnVisitHistoryInstallParam {


    // 主键
    @ApiModelProperty(value = "主键")
    private String id;

    // 回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
    //@NotBlank(message = "回访类型不能为空")
    @ApiModelProperty(value = "回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）")
    private String returnVisitType;

    // 订单明细单号
    //@NotBlank(message = "订单明细单号不能为空")
    @ApiModelProperty(value = "订单明细单号")
    private String orderDetailNo;

    // 学员id
    @NotBlank(message = "学员id不能为空")
    @ApiModelProperty(value = "学员id")
    private String studentId;

    // 回访时间
    @ApiModelProperty(value = "回访时间")
    private LocalDateTime returnVisitTime;

    // 回访状态  1：售前回访 2 售后回访
    private String returnVisitStatus;

    // 是否有意向 0 否 1 是
    private String isIntention;
    // 回访类型明细
    private String returnVisitItem;

    // 回访内容
    @NotBlank(message = "回访内容不能为空")
    @ApiModelProperty(value = "回访内容")
    private String returnVisitContent;

    // 预计下次回访时间
    //@NotNull(message = "回访时间不能为空")
    @ApiModelProperty(value = "预计下次回访时间")
    private LocalDateTime nextReturnVisitTime;

    // 客服id
    // @NotBlank(message = "客服id不能为空")
    @ApiModelProperty(value = "客服id")
    private String serviceId;

    // 是否完结（是，否）
    @NotBlank(message = "是否完结不能为空")
    @ApiModelProperty(value = "是否完结（是，否）")
    private String isEnd;

    // 科目类型(1-科目一，2-科目三，3-科目三，4-科目四)
    @ApiModelProperty(value = "科目类型(1-科目一，2-科目三，3-科目三，4-科目四)")
    private String subjectType;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;

    // 用户信息
    private StudentInfoEditParam studentInfoEditParam;


}