package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 平台报名考试练车单价表
 *
 * @author xiaoguo
 */
@Data
public class TestTrainPriceInstallParam {


    // 主键
    @NotBlank(message = "主键不能为空")
    @ApiModelProperty(value = "主键")
    private String id;

    // 省
    @NotBlank(message = "省不能为空")
    @ApiModelProperty(value = "省")
    private String provinceId;

    // 市
    @NotBlank(message = "市不能为空")
    @ApiModelProperty(value = "市")
    private String cityId;

    // 区
    @NotBlank(message = "区不能为空")
    @ApiModelProperty(value = "区")
    private String areaId;

    // 驾照类型（c1；c2...）
    @NotBlank(message = "驾照类型（c1；c2...）不能为空")
    @ApiModelProperty(value = "驾照类型（c1；c2...）")
    private String driveType;

    // 科目类型（一；二；三；四）
    @NotBlank(message = "科目类型（一；二；三；四）不能为空")
    @ApiModelProperty(value = "科目类型（一；二；三；四）")
    private String subjectType;

    // 价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)
    @NotBlank(message = "价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)不能为空")
    @ApiModelProperty(value = "价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)")
    private String priceType;

    // 单价
    @NotBlank(message = "单价不能为空")
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    // 详情（报名所需的费用明细介绍）
    @NotBlank(message = "详情（报名所需的费用明细介绍）不能为空")
    @ApiModelProperty(value = "详情（报名所需的费用明细介绍）")
    private String remarks;

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

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}