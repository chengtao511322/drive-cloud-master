package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 平台应用版本表
 *
 * @author xiaoguo
 */
@Data
public class AppVersionEditParam {


    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String id;

    // 应用类型（1：学员；2：教练，3-运维）
    @NotBlank(message = "应用类型（1：学员；2：教练，3-运维）不能为空")
    @ApiModelProperty(value = "应用类型（1：学员；2：教练，3-运维）")
    private String appType;

    // 版本类型(ios,android)
    @NotBlank(message = "版本类型(ios,android)不能为空")
    @ApiModelProperty(value = "版本类型(ios,android)")
    private String versionType;

    // 版本名称
    @NotBlank(message = "版本名称不能为空")
    @ApiModelProperty(value = "版本名称")
    private String versionName;

    // 版本号
    @NotBlank(message = "版本号不能为空")
    @ApiModelProperty(value = "版本号")
    private String version;

    // 是否强制更新
    @NotBlank(message = "是否强制更新不能为空")
    @ApiModelProperty(value = "是否强制更新")
    private String isMustUpdate;

    // 下载地址
    @NotBlank(message = "下载地址不能为空")
    @ApiModelProperty(value = "下载地址")
    private String downloadLocation;

    // 平台密钥
    @NotBlank(message = "平台密钥不能为空")
    @ApiModelProperty(value = "平台密钥")
    private String appKey;

    // 版本说明
    @NotBlank(message = "版本说明不能为空")
    @ApiModelProperty(value = "版本说明")
    private String versionExplain;

    // 下载次数
    @NotBlank(message = "下载次数不能为空")
    @ApiModelProperty(value = "下载次数")
    private String downloadCount;

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


}