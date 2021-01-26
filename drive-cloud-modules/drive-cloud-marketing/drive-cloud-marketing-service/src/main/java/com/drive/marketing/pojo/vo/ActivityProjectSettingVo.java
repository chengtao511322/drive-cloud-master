package com.drive.marketing.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 活动项目设置
 *
 * @author xiaoguo
 */
@Data
public class ActivityProjectSettingVo {


	@Excel(name = "", width = 20)
	private String id;

	// 项目id 班型ID
	@Excel(name = "项目id 班型ID", width = 20)
	private String projectId;

	// 项目名称
	@Excel(name = "项目名称", width = 20)
	private String projectName;

	// 租户ID CacheUtils
	private String tenantId;
	@Excel(name = "运营商", width = 20)
	private String tenantName;

	// 产品价格
	private BigDecimal projectAmount;


	// 活动提成金额
	@Excel(name = "活动提成金额", width = 20)
	private BigDecimal deductAmount;

	// 推广人员 获取佣金
	@Excel(name = "推广人员 获取佣金", width = 20)
	private BigDecimal promotionAmount;

	// 渠道经理获取佣金
	@Excel(name = "渠道经理获取佣金", width = 20)
	private BigDecimal channelManagerAmount;

	/**
	 *  优惠券ID
	 */
	private String couponId;
	/**
	 * 优惠券名称
	 */
	private String couponName;

	// 活动ID
	@Excel(name = "活动ID", width = 20)
	private String activityId;

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
		/*if (StrUtil.isNotEmpty(tenantId)){
			JSONObject jsonObject = JedisConnect.get(Constants.OPERATOR_DEPT_KEY + tenantId);
			if (!jsonObject.isEmpty()){
				this.tenantName = jsonObject.getString("name");
			}
		}*/
	}
}