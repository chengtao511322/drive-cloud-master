package com.drive.system.pojo.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 字典类型
 *
 * @author xiaoguo
 */
@Data
public class DictTypeEditParam {


    // 字典主键
    private Long dictTypeId;

    // 字典名称
    @NotBlank(message = "字典名称不能为空")
    private String dictName;

    // 字典编码
    @NotBlank(message = "字典编码不能为空")
    private String dictCode;

    // 状态（0正常 1停用）
    private String status;

    // 备注
    private String remark;

    // 创建人
    private String createBy;

    // 创建时间
    private LocalDateTime createTime;

    // 更新人
    private String updateBy;

    // 更新时间
    private LocalDateTime updateTime;


}