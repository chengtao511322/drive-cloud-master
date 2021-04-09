package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 学员学车预约表
 *
 * @author xiaoguo
 */
@Data
public class StudentTrainCarApplyEditParam {


    // 预约单号
    @NotBlank(message = "预约单号不能为空")
    @ApiModelProperty(value = "预约单号")
    private String trainApplyNo;

    // 训练类型（1-常规练车；2-考试练车）
    @NotBlank(message = "训练类型（1-常规练车；2-考试练车）不能为空")
    @ApiModelProperty(value = "训练类型（1-常规练车；2-考试练车）")
    private String trainType;

    // 教练课时ID
    @NotBlank(message = "教练课时ID不能为空")
    @ApiModelProperty(value = "教练课时ID")
    private String classId;

    // 学员ID
    @NotBlank(message = "学员ID不能为空")
    @ApiModelProperty(value = "学员ID")
    private String studentId;

    // 驾照类型（c1；c2...）
    @NotBlank(message = "驾照类型（c1；c2...）不能为空")
    @ApiModelProperty(value = "驾照类型（c1；c2...）")
    private String driveType;

    // 科目类型
    @NotBlank(message = "科目类型不能为空")
    @ApiModelProperty(value = "科目类型")
    private String subjectType;

    // 单价
    @NotBlank(message = "单价不能为空")
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    // 总价格
    @NotBlank(message = "总价格不能为空")
    @ApiModelProperty(value = "总价格")
    private BigDecimal sumPrice;

    // 平台服务费用百分比/H
    @NotBlank(message = "平台服务费用百分比/H不能为空")
    @ApiModelProperty(value = "平台服务费用百分比/H")
    private BigDecimal serviceChargePercent;

    // 教练课时费用百分比/H2
    @NotBlank(message = "教练课时费用百分比/H2不能为空")
    @ApiModelProperty(value = "教练课时费用百分比/H2")
    private BigDecimal coachChargePercent;

    // 平台服务费用
    @NotBlank(message = "平台服务费用不能为空")
    @ApiModelProperty(value = "平台服务费用")
    private BigDecimal serviceCharge;

    // 教练课时费用
    @NotBlank(message = "教练课时费用不能为空")
    @ApiModelProperty(value = "教练课时费用")
    private BigDecimal coachCharge;

    // 教练ID
    @NotBlank(message = "教练ID不能为空")
    @ApiModelProperty(value = "教练ID")
    private String coachId;

    // 训练场地ID
    @NotBlank(message = "训练场地ID不能为空")
    @ApiModelProperty(value = "训练场地ID")
    private String coachingGridId;

    // 接你的时间
    @NotBlank(message = "接你的时间不能为空")
    @ApiModelProperty(value = "接你的时间")
    private LocalDateTime takeTime;

    // 上车时间
    @NotBlank(message = "上车时间不能为空")
    @ApiModelProperty(value = "上车时间")
    private LocalDateTime aboardTime;

    // 课程日期
    @NotBlank(message = "课程日期不能为空")
    @ApiModelProperty(value = "课程日期")
    private LocalDate classDate;

    // 开始时间
    @NotBlank(message = "开始时间不能为空")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    // 结束时间
    @NotBlank(message = "结束时间不能为空")
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    // 学时
    @NotBlank(message = "学时不能为空")
    @ApiModelProperty(value = "学时")
    private Integer classHours;

    // 预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）
    @NotBlank(message = "预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）不能为空")
    @ApiModelProperty(value = "预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）")
    private String applyStatus;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @NotBlank(message = "更新时间不能为空")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 学员推荐用户类型（1-学员；2-教练；3-运维）
    @NotBlank(message = "学员推荐用户类型（1-学员；2-教练；3-运维）不能为空")
    @ApiModelProperty(value = "学员推荐用户类型（1-学员；2-教练；3-运维）")
    private String studentRecommendUserType;

    // 学员推荐用户ID
    @NotBlank(message = "学员推荐用户ID不能为空")
    @ApiModelProperty(value = "学员推荐用户ID")
    private String studentRecommendUserId;

    // 学员推荐用户提成百分比
    @NotBlank(message = "学员推荐用户提成百分比不能为空")
    @ApiModelProperty(value = "学员推荐用户提成百分比")
    private BigDecimal studentRecommendUserChargePercent;

    // 学员推荐用户提成费用
    @NotBlank(message = "学员推荐用户提成费用不能为空")
    @ApiModelProperty(value = "学员推荐用户提成费用")
    private BigDecimal studentRecommendUserCharge;

    // 学员常用地址id
    @NotBlank(message = "学员常用地址id不能为空")
    @ApiModelProperty(value = "学员常用地址id")
    private String commonAddressId;

    // 教练推荐用户类型（1-学员；2-教练；3-运营）
    @NotBlank(message = "教练推荐用户类型（1-学员；2-教练；3-运营）不能为空")
    @ApiModelProperty(value = "教练推荐用户类型（1-学员；2-教练；3-运营）")
    private String coachRecommendUserType;

    // 教练推荐用户ID
    @NotBlank(message = "教练推荐用户ID不能为空")
    @ApiModelProperty(value = "教练推荐用户ID")
    private String coachRecommendUserId;

    // 教练推荐用户提成百分比
    @NotBlank(message = "教练推荐用户提成百分比不能为空")
    @ApiModelProperty(value = "教练推荐用户提成百分比")
    private BigDecimal coachRecommendUserChargePercent;

    // 教练推荐用户提成费用
    @NotBlank(message = "教练推荐用户提成费用不能为空")
    @ApiModelProperty(value = "教练推荐用户提成费用")
    private BigDecimal coachRecommendUserCharge;

    // 取消时间
    @NotBlank(message = "取消时间不能为空")
    @ApiModelProperty(value = "取消时间")
    private LocalDateTime cancelTiem;

    // 取消原因
    @NotBlank(message = "取消原因不能为空")
    @ApiModelProperty(value = "取消原因")
    private String cancelReason;

    // 取消扣款百分比
    @NotBlank(message = "取消扣款百分比不能为空")
    @ApiModelProperty(value = "取消扣款百分比")
    private BigDecimal cancelWithholdPercent;

    // 取消扣款金额
    @NotBlank(message = "取消扣款金额不能为空")
    @ApiModelProperty(value = "取消扣款金额")
    private BigDecimal cancelWithholdMoney;

    // 取消退款金额
    @NotBlank(message = "取消退款金额不能为空")
    @ApiModelProperty(value = "取消退款金额")
    private BigDecimal cancelRefundMoney;

    // 考试训练用车类型（1-教练车；2-考试车）
    @NotBlank(message = "考试训练用车类型（1-教练车；2-考试车）不能为空")
    @ApiModelProperty(value = "考试训练用车类型（1-教练车；2-考试车）")
    private String carType;

    // 是否删除（0-否;1-是）
    @NotBlank(message = "是否删除（0-否;1-是）不能为空")
    @ApiModelProperty(value = "是否删除（0-否;1-是）")
    private String isDelete;

    // 实际开始时间
    @NotBlank(message = "实际开始时间不能为空")
    @ApiModelProperty(value = "实际开始时间")
    private LocalDateTime actualStartTime;

    // 实际结束时间
    @NotBlank(message = "实际结束时间不能为空")
    @ApiModelProperty(value = "实际结束时间")
    private LocalDateTime actualEndTime;

    // 课时点评
    @NotBlank(message = "课时点评不能为空")
    @ApiModelProperty(value = "课时点评")
    private String classAppraise;

    // 公车提成费用
    @NotBlank(message = "公车提成费用不能为空")
    @ApiModelProperty(value = "公车提成费用")
    private BigDecimal publicCarPrice;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}