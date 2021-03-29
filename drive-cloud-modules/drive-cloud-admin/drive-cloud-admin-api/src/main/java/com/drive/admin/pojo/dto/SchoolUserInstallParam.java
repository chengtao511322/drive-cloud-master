package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 合作驾校用户
 *
 * @author xiaoguo
 */
@Data
public class SchoolUserInstallParam {


    // id
    @ApiModelProperty(value = "id")
    private String id;

    // 姓名
    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String name;

    // 电话
    @NotBlank(message = "电话不能为空")
    @ApiModelProperty(value = "电话")
    private String phone;

    // 登录密码
    @NotBlank(message = "登录密码不能为空")
    @ApiModelProperty(value = "登录密码")
    private String password;

    // 账户所属驾校
    @NotBlank(message = "账户所属驾校不能为空")
    @ApiModelProperty(value = "账户所属驾校")
    private String schoolId;

    // 状态(1-正常，2-停用)
    @NotBlank(message = "状态(1-正常，2-停用)不能为空")
    @ApiModelProperty(value = "状态(1-正常，2-停用)")
    private String status;

    // 用户类型(1-管理员，2-普通用户)
    @NotBlank(message = "用户类型(1-管理员，2-普通用户)不能为空")
    @ApiModelProperty(value = "用户类型(1-管理员，2-普通用户)")
    private Integer userType;

    // token
    @NotBlank(message = "token不能为空")
    @ApiModelProperty(value = "token")
    private String token;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}