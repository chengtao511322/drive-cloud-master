package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 平台训练场地表
 *
 * @author xiaoguo
 */
@Data
public class CoachingGridEditParam {


    private String id;

    // 场地名称
    @NotBlank(message = "场地名称不能为空")
    @ApiModelProperty(value = "场地名称")
    private String name;

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

    // 详细地址
    @NotBlank(message = "详细地址不能为空")
    @ApiModelProperty(value = "详细地址")
    private String address;

    // 场地详情（图文）
    private String remarks;

    // 图片介绍
    private String imageUrlList;

    // 科目类型
    @NotBlank(message = "科目类型不能为空")
    @ApiModelProperty(value = "科目类型")
    private String subjectType;

    // 场地类型（1-训练场地；2-考试场地）
    @NotBlank(message = "场地类型不能为空")
    @ApiModelProperty(value = "场地类型")
    private String type;

    // 经度
    private String longitude;

    // 纬度
    private String latitude;

    // 场地费用/H
    private BigDecimal price;

    // 驾校id
    private String driveSchoolId;

    // 驾校名称
    private String driveSchooName;

    // 创建者
    private String createUser;

    // 更新着
    private String updateUser;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 考试车价格/H
    private BigDecimal gridTestCarPrice;

    // 联系电话
    @NotBlank(message = "联系电话不能为空")
    @ApiModelProperty(value = "联系电话")
    private String phone;

    // 状态（1-正常，2-停用）
    private String status;

    // 运营商id(数据权限标记)
    private String operatorId;


}