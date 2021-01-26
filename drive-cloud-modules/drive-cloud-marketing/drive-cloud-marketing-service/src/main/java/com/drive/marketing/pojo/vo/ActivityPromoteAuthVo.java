package com.drive.marketing.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;


/**
 * 活动推广权限配置
 *
 * @author xiaoguo
 */
@Data
public class ActivityPromoteAuthVo {


	// 活动ID
	@Excel(name = "活动ID", width = 20)
	private String activityId;

	@Excel(name = "", width = 20)
	private String userId;

	// 栏目ID
	@Excel(name = "栏目ID", width = 20)
	private String channelId;

}