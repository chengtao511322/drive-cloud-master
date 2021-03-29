package com.drive.basics.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 菜单 按钮 用户拥有权限管理
 *
 * @author xiaoguo
 */
@Data
public class ChannelAuthEditParam {

    //@NotBlank(message = "菜单ID不能为空")
    @ApiModelProperty(value = "菜单ID")
    private String id;


    // 菜单ID
    @NotBlank(message = "菜单ID不能为空")
    @ApiModelProperty(value = "菜单ID")
    private String channelId;

    // 用户ID
    @NotBlank(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID")
    private String userId;

    // 菜单ID
    @ApiModelProperty(value = "菜单ID")
    private String tenantId;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 修改时间
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    // 创建者
    @ApiModelProperty(value = "创建者")
    private String createUser;

    private String isDelete;

    private String status;

    // 新的用户ID
    private String newUserId;


}