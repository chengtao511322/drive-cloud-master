package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


/**
 * 平台发送短信表
 *
 * @author xiaoguo
 */
@Data
public class MessageEditParam {


    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "")
    private String id;

    // 手机号码
    @ApiModelProperty(value = "手机号码")
    private String phone;

    // 验证码
    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "")
    private LocalDateTime createtime;

    // 有效时间
    @ApiModelProperty(value = "有效时间")
    private LocalDateTime expiretime;

    // 备注
    @ApiModelProperty(value = "备注")
    private String remarks;

    // 运营商id(数据权限标记)
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}