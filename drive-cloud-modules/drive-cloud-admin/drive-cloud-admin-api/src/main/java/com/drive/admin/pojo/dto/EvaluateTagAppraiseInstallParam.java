package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 学员教练评价明细表
 *
 * @author xiaoguo
 */
@Data
public class EvaluateTagAppraiseInstallParam {


    // id
    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private String id;

    // 评价标签id
    @NotBlank(message = "评价标签id不能为空")
    @ApiModelProperty(value = "评价标签id")
    private String evaluateTagId;

    // 学员教练评价表id
    @NotBlank(message = "学员教练评价表id不能为空")
    @ApiModelProperty(value = "学员教练评价表id")
    private String studentCoachAppraiseId;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @NotBlank(message = "更新时间不能为空")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 是否删除
    @NotBlank(message = "是否删除不能为空")
    @ApiModelProperty(value = "是否删除")
    private String isDelete;


}