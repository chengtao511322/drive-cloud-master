package com.drive.system.pojo.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 字典数据
 *
 * @author xiaoguo
 */
@Data
public class DictItemEditParam {


    // 字典编码
    private Long dictItemId;

    // 字典类型
    private String dictCode;

    // 字典项名称
    @NotBlank(message = "字典项名称不能为空")
    private String itemName;

    // 字典项键值
    private String itemValue;

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