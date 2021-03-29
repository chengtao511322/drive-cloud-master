package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 评价标签表
 *
 * @author xiaoguo
 */
@Data
public class EvaluateTagEditParam {


    // id
    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private String id;

    // 标签名称
    @NotBlank(message = "标签名称不能为空")
    @ApiModelProperty(value = "标签名称")
    private String name;

    // 状态(1-正常，2-停用)
    @NotBlank(message = "状态(1-正常，2-停用)不能为空")
    @ApiModelProperty(value = "状态(1-正常，2-停用)")
    private String status;

    // 订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
    @NotBlank(message = "订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）不能为空")
    @ApiModelProperty(value = "订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）")
    private String orderType;

    // 评价分段（1-好评（45星），2-中评（3星），3-差评（12星））
    @NotBlank(message = "评价分段（1-好评（45星），2-中评（3星），3-差评（12星））不能为空")
    @ApiModelProperty(value = "评价分段（1-好评（45星），2-中评（3星），3-差评（12星））")
    private String fractionParagraph;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 修改时间
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    // 是否删除(0否；1：是)
    @ApiModelProperty(value = "是否删除(0否；1：是)")
    private String isDelete;


}