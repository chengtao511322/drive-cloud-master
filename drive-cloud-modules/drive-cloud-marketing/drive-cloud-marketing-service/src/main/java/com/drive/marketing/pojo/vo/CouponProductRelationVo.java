package com.drive.marketing.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;


/**
 * 
 *
 * @author xiaoguo
 */
@Data
public class CouponProductRelationVo {


	@Excel(name = "", width = 20)
	private String id;

	// 优惠券ID
	@Excel(name = "优惠券ID", width = 20)
	private String couponId;

	// 产品ID
	@Excel(name = "产品ID", width = 20)
	private String productId;

	// 类型
	@Excel(name = "类型", width = 20)
	private Long type;

	private String typeName;
	private String productName;

}