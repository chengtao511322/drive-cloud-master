package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 平台报名考试练车单价表
 *
 * @author xiaoguo
 */
@Data
public class TestTrainPriceVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 省
	@Excel(name = "省", width = 20)
	private String provinceId;

	// 市
	@Excel(name = "市", width = 20)
	private String cityId;

	// 区
	@Excel(name = "区", width = 20)
	private String areaId;

	// 驾照类型（c1；c2...）
	@Excel(name = "驾照类型（c1；c2...）", width = 20)
	private String driveType;

	// 科目类型（一；二；三；四）
	@Excel(name = "科目类型（一；二；三；四）", width = 20)
	private String subjectType;

	// 价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)
	@Excel(name = "价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)", width = 20)
	private String priceType;

	// 单价
	@Excel(name = "单价", width = 20)
	private BigDecimal price;

	// 详情（报名所需的费用明细介绍）
	@Excel(name = "详情（报名所需的费用明细介绍）", width = 20)
	private String remarks;

	// 创建者
	@Excel(name = "创建者", width = 20)
	private String createUser;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新者
	@Excel(name = "更新者", width = 20)
	private String updateUser;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}