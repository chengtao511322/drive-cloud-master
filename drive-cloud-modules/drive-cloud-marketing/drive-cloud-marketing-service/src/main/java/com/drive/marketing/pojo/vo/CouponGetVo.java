package com.drive.marketing.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * 
 *
 * @author xiaoguo
 */
@Data
public class CouponGetVo implements java.io.Serializable{


	// id
	private String id;

	// 用户名称
	@Excel(name = "用户名称", width = 20)
	private String userName;

	// 用户手机号
	@Excel(name = "用户手机号", width = 20)
	private String phone;

	// 用户ID
	private String userId;

	// 优惠券ID
	private String couponId;

	// 优惠券使用状态:1 未领取  2 已经领取 3 已经使用 4：优惠券过期  
	private String status;
	@Excel(name = "优惠券使用状态", width = 20)
	private String statusName;

	// 领取时间
	@Excel(name = "领取时间", width = 20)
	private LocalDateTime getTime;

	// 修改时间
	private LocalDateTime updateTime;

	// 优惠券码
	@Excel(name = "优惠券码", width = 20)
	private String couponCode;

	// 获取类型：0->后台赠送；1->主动获取
	private String getType;
	@Excel(name = "获取类型 ", width = 20)
	private String getTypeName;

	// 使用时间
	@Excel(name = "使用时间", width = 20)
	private LocalDateTime useTime;

	// 有效期开始日
	@Excel(name = "有效期开始日", width = 20)
	private LocalDateTime periodTimeStart;

	// 有效期内  结束
	@Excel(name = "有效期内  结束", width = 20)
	private LocalDateTime periodTimeEnd;

	@Excel(name = "", width = 20)
	private String operatingPosition;

	// 来源 ID  （主要用于来源活动ID）
	private String source;

	// 租户ID
	private String tenantId;

	private LocalDateTime createTime;

	// 推广ID
	private String promoteUserId;

	@Excel(name = "来源活动", width = 20)
	private String activity;
	@Excel(name = "推广人员", width = 20)
	private String promoteUserName;

	@Excel(name = "推广人员手机号", width = 20)
	private String promoteUserPhone;

	private String couponName;


	public void setStatus(String status) {
		this.status = status;
		switch(status){
			case "1" :
				//语句
				this.statusName = "未领取";
				break; //可选
			case "2" :
				//语句
				this.statusName = "已领取";
				break; //可选
			//你可以有任意数量的case语句
			case "3" :
				//语句
				this.statusName = "已使用";
				break; //可选
			//你可以有任意数量的case语句
			case "4" :
				//语句
				this.statusName = "已失效";
				break; //可选
			//你可以有任意数量的case语句
		}
	}

	public void setGetType(String getType) {
		this.getType = getType;
		if (getType.equals("1")){
			this.getTypeName = "主动获取";
		}else{
			this.getTypeName = "后台赠送";
		}
	}
}