package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 教练授课区域表
 *
 * @author xiaoguo
 */
@Data
public class CoachGiveAreaEditParam {


    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String id;

    // 教练ID
    @NotBlank(message = "教练ID不能为空")
    @ApiModelProperty(value = "教练ID")
    private String coachId;

    // 省级编码
    @NotBlank(message = "省级编码不能为空")
    @ApiModelProperty(value = "省级编码")
    private String provinceId;

    // 市级编码
    @NotBlank(message = "市级编码不能为空")
    @ApiModelProperty(value = "市级编码")
    private String cityId;

    // 区(县)级编码
    @NotBlank(message = "区(县)级编码不能为空")
    @ApiModelProperty(value = "区(县)级编码")
    private String areaId;

    // 是否向上查询（价格,教练在当前区划查询不到时）
    @NotBlank(message = "是否向上查询（价格,教练在当前区划查询不到时）不能为空")
    @ApiModelProperty(value = "是否向上查询（价格,教练在当前区划查询不到时）")
    private Integer isUpSelect;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 修改时间
    @NotBlank(message = "修改时间不能为空")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}