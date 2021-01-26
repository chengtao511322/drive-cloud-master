package com.drive.marketing.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class RecommendUserPageQueryParam extends BasePageQueryParam {

    // 状态
    private String status;

    // 推广人员ID
    private String recommendId;
    // 渠道经理
    private String channelManagerId;

    private String tenantId;
}
