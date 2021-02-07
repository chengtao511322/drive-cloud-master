package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 教练发课设置
 *
 * @author xiaoguo
 */
@Data
public class CoachHourSettingEditParam {


    // 主键
    @ApiModelProperty(value = "主键")
    private String id;

    // 生效时间
    @ApiModelProperty(value = "生效时间")
    private LocalDateTime effectiveTime;

    // 状态(1-正常，2-停用)
    @ApiModelProperty(value = "状态(1-正常，2-停用)")
    private String status;

    // 是否删除
    @ApiModelProperty(value = "是否删除")
    private String isDelete;

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

    //
    private List<CoachHourSettingDetailEditParam> coachHourSettingDetailList;


}