package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


/**
 * 用户常用地址关联表
 *
 * @author xiaoguo
 */
@Data
public class UserCommonlyAddressInstallParam {


    // 主键
    @ApiModelProperty(value = "主键")
    private String id;

    // 用户id
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private String userId;

    // 用户类型(1-学员 2-教练  3-客服 )
    @NotNull(message = "用户类型(1-学员 2-教练  3-客服 )不能为空")
    @ApiModelProperty(value = "用户类型(1-学员 2-教练  3-客服 )")
    private String userType;

    // 地址名称
    @NotNull(message = "地址名称不能为空")
    @ApiModelProperty(value = "地址名称")
    private String address;

    // 地址纬度
    @ApiModelProperty(value = "地址纬度")
    private String latitude;

    // 地址经度
    @ApiModelProperty(value = "地址经度")
    private String longitude;

    // 是否默认为地址（是，否）
    @ApiModelProperty(value = "是否默认为地址（是，否）")
    private String isDefault;

    // 状态(正常，停用)
    @ApiModelProperty(value = "状态(正常，停用)")
    private String status;

    // 备注
    @ApiModelProperty(value = "备注")
    private String remarks;

    // 注册时间
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime createTime;

    // 修改时间
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    // 场地ID
    @NotNull(message = "场地ID不能为空")
    @ApiModelProperty(value = "场地ID")
    private String siteId;

    // 科目类型
    @ApiModelProperty(value = "科目类型")
    private Integer subjectType;


}