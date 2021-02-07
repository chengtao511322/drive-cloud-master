package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 运营商教练课时设置表
 *
 * @author xiaoguo
 */
@Data
public class CoachHourSettingDetailEditParam {


    @ApiModelProperty(value = "ID")
    private String id;

    // 时间段(1-上午;2-下午;3-晚上)
    @ApiModelProperty(value = "时间段(1-上午;2-下午;3-晚上)")
    private String timeSection;

    // 开始时间(只存时分，所以使用字符串)
    @NotBlank(message = "开始时间(只存时分，所以使用字符串)不能为空")
    @ApiModelProperty(value = "开始时间(只存时分，所以使用字符串)")
    private String startTime;

    // 结束时间(只存时分，所以使用字符串)
    @NotBlank(message = "结束时间(只存时分，所以使用字符串)不能为空")
    @ApiModelProperty(value = "结束时间(只存时分，所以使用字符串)")
    private String endTime;

    // 运营商ID
    @NotBlank(message = "运营商ID不能为空")
    @ApiModelProperty(value = "运营商ID")
    private String operatorId;

    @NotBlank(message = "发课主表ID")
    @ApiModelProperty(value = "发课主表ID")
    private String hourSettingId;

    // 创建者
    @ApiModelProperty(value = "创建者")
    private String createUser;

    // 创建时间  系统自动创建
    @ApiModelProperty(value = "创建时间  系统自动创建")
    private LocalDateTime createTime;

    // 修改时间
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    // 是否删除
    @ApiModelProperty(value = "是否删除")
    private String isDelete;





}