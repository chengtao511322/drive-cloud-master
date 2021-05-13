package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 运营商加盟申请
 *
 * @author xiaoguo
 */
@Data
public class OperatorRecruitInstallParam {


    // 主键
    @ApiModelProperty(value = "主键")
    private String id;

    // 省
    @ApiModelProperty(value = "省")
    private String provinceId;

    // 市
    @ApiModelProperty(value = "市")
    private String cityId;

    // 区
    @ApiModelProperty(value = "区")
    private String areaId;

    // 姓名
    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String realName;

    // 电话
    @NotBlank(message = "电话不能为空")
    @ApiModelProperty(value = "电话")
    private String phone;

    // 邮箱
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String eMail;

    // 公司名称
    @NotBlank(message = "公司名称不能为空")
    @ApiModelProperty(value = "公司名称")
    private String corporationName;

    // 申请说明
    @NotBlank(message = "申请说明不能为空")
    @ApiModelProperty(value = "申请说明")
    private String applyExplain;

    // 是否删除
    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    // 注册时间
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime createTime;

    // 修改时间
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}