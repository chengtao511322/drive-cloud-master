package com.drive.marketing.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 活动推广权限配置
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("activity_promote_auth")
public class ActivityPromoteAuthEntity extends BaseEntity {


	// 活动ID
	private String activityId;

	private String userId;

	// 栏目ID
	private String channelId;
	// 租户ID
	private String tenantId;

}