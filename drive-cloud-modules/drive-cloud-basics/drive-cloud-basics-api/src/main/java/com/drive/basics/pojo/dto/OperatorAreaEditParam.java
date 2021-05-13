package com.drive.basics.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 运营商代理区域
 *
 * @author xiaoguo
 */
@Data
public class OperatorAreaEditParam {


    // 主键
    @NotBlank(message = "主键不能为空")
    @ApiModelProperty(value = "主键")
    private String id;

    // 代理商id
    @NotBlank(message = "代理商id不能为空")
    @ApiModelProperty(value = "代理商id")
    private String operatorId;

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
    private String isUpSelect;

    // 是否删除
    @NotBlank(message = "是否删除不能为空")
    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @NotBlank(message = "更新时间不能为空")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}