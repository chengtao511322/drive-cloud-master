package com.drive.marketing.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 渠道经理设置
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class RecommendManagerPageQueryParam extends BasePageQueryParam {

    // 状态
    private String status;

    // 渠道经理ID
    private String managerId;

    private String tenantId;
}
