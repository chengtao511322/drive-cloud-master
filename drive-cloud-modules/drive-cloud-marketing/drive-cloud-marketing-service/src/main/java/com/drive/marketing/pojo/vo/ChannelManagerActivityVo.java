package com.drive.marketing.pojo.vo;

import com.drive.admin.pojo.vo.RecommendManagerVo;
import com.drive.admin.pojo.vo.RecommendUserVo;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 渠道经理 可推广表配置
 *
 * @author xiaoguo
 */
@Data
public class ChannelManagerActivityVo {


	private String channelManagerId;

	// 活动ID
	private String activityId;

	// 冗余 活动名称
	private String activityName;
	// 用户ID
	private String userId;
	// 创建时间
	private String createTime;
	// 版型ID
	private String projectId;
	// 活动投入金额
	private BigDecimal deductAmount;
	// 推广商所得
	private BigDecimal promotionAmount;
	// 渠道经理所得
	private BigDecimal channelManagerAmount;

	// 班型名称
	private String projectName;
	// 状态  0 正常 1 禁言
	private String status;
	private String statusName;
	// 删除状态 0正常 1已经删除
	private String isDelete;
	private String isDeleteName;

	private String id;

	private String promotionUserId;
	// 推广用户总数
	private String promotionUserTotal;

	private RecommendManagerVo channelManager;
	private RecommendUserVo recommendUser;


	// 推广类型 1：渠道经理 2：推广商
	private Integer promotionType;

	private String promotionTypeName;


	private ActivityCouponRelationVo activityCouponRelationVo;


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

	public void setPromotionType(Integer promotionType) {
		this.promotionType = promotionType;
		if (isDelete.equals("1")){
			this.promotionTypeName = "渠道经理";
		}
		if (isDelete.equals("2")){
			this.promotionTypeName = "推广商";
		}
	}
}