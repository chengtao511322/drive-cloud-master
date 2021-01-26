package com.drive.common.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Entity 基类
 *
 * @author xiaoguo
 */
@Data
public class BaseEntity implements Serializable {

    // 创建人
    @TableField(fill = FieldFill.INSERT,exist = false)
    private String createBy;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 更新人 @TableField(exist = false)
    @TableField(fill = FieldFill.UPDATE,exist = false)
    private String updateBy;

    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    // 请求参数
    @TableField(exist = false)
    private Map<String, Object> params;

}
