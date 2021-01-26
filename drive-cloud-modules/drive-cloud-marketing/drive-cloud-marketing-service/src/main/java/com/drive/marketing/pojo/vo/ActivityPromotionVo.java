package com.drive.marketing.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 
 *
 * @author xiaoguo
 */
@Data
public class ActivityPromotionVo {


	// 推广人员用户ID
	@Excel(name = "推广人员用户ID", width = 20)
	private String promotionUserId;

	// 推广人员类型
	@Excel(name = "推广人员类型", width = 20)
	private Integer promotionType;

	// 报名百分比
	@Excel(name = "报名百分比", width = 20)
	private BigDecimal applyPre;

	// 推广手机号
	@Excel(name = "推广手机号", width = 20)
	private String promotionPhone;

	// 推广人员姓名
	@Excel(name = "推广人员姓名", width = 20)
	private String promotionUserName;

	// 活动ID
	@Excel(name = "活动ID", width = 20)
	private String activityId;

	// 推广商获得金额
	@Excel(name = "推广商获得金额", width = 20)
	private BigDecimal promotionAmount;

	@Excel(name = "", width = 20)
	private String projectId;

	private JSONObject jsonObject;

	// 用户ID
	private String userId;
	// 投入金额
	private BigDecimal deductAmount;
	// 渠道经理获得金额
	private BigDecimal channelManagerAmount;
	// 创建时间
	private String createTime;
	// 修改时间
	private String updateTime;

	// 班型名称
	private String projectName;
	// 状态  0 正常 1 禁言
	private String status;
	private String statusName;
	// 删除状态 0正常 1已经删除
	private String isDelete;
	private String isDeleteName;

	private String id;

	// 渠道经理ID
	private String channelManagerId;

	public void setStatus(String status) {
		this.status = status;
		if (status.equals("0")){
			this.statusName = "正常";
		}else{
			this.statusName = "停用";
		}
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
		if (isDelete.equals("0")){
			this.isDeleteName = "正常";
		}else{
			this.isDeleteName = "已经删除";
		}
	}


}