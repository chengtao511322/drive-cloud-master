package com.drive.marketing.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 活动推广权限配置
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ActivityPromoteAuthPageQueryParam extends BasePageQueryParam {


	// 活动ID
	private String activityId;

	private String userId;

	// 栏目ID
	private String channelId;

}