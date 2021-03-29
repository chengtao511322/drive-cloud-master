package com.drive.basics.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 流程信息管理
 *
 * @author xiaoguo
 */
@Data
public class DriveFlowEditParam {


    // id
    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private String id;

    // 标题
    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;

    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String context;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 修改时间
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    // 删除状态 0未删除  1 ：删除
    @ApiModelProperty(value = "删除状态 0未删除  1 ：删除")
    private Integer isDelete;

    // 启用状态 0  未启用 1 已经启用
    @ApiModelProperty(value = "启用状态 0  未启用 1 已经启用")
    private Integer status;

    // 栏目ID
    @ApiModelProperty(value = "栏目ID")
    private String channelId;

    // 排序
    @ApiModelProperty(value = "排序")
    private Integer sort;

    // 添加者
    @ApiModelProperty(value = "添加者")
    private String addUser;

    // 运营位置 ，存放省市区编码，多个用逗号隔开
    @ApiModelProperty(value = "运营位置 ，存放省市区编码，多个用逗号隔开")
    private String operatingPosition;

    // 租户ID
    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    // 类型 1 学车流程 2 平台承诺
    private Integer type;


}