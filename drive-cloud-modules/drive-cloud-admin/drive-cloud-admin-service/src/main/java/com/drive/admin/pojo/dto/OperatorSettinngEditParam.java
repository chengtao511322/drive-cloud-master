package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 运营商参数设置表
 *
 * @author xiaoguo
 */
@Data
public class OperatorSettinngEditParam {


    // 主键
    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "主键")
    private String id;

    // 编号(使用 00000 的形式来添加)
    @ApiModelProperty(value = "编号(使用 00000 的形式来添加)")
    private String number;

    // 编号对应值
    @ApiModelProperty(value = "编号对应值")
    private String numberValue;

    // 编号中文描述
    @ApiModelProperty(value = "编号中文描述")
    private String numberDescribe;

    // 运营商id(数据权限标记)
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;

    // 状态(1-正常，2-停用)
    @ApiModelProperty(value = "状态(1-正常，2-停用)")
    private String status;

    // 是否删除（0-否，1-是）
    @ApiModelProperty(value = "是否删除（0-否，1-是）")
    private String isDelete;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}