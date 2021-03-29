package com.drive.basics.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 菜单 按钮 用户拥有权限管理
 *
 * @author xiaoguo
 */
@Data
public class ChannelAuthEditParam {

    private String id;


    // 菜单ID
    @NotBlank(message = "菜单ID不能为空")
    private String channelId;

    // 用户ID
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    // 菜单ID
    @NotBlank(message = "菜单ID不能为空")
    private String tenantId;

    // 创建时间
    private LocalDateTime createTime;

    // 修改时间
    private LocalDateTime updateTime;

    // 创建者
    private String createUser;

    private String isDelete;

    private String status;

    // 新用户ID
    private String newUserId;


}