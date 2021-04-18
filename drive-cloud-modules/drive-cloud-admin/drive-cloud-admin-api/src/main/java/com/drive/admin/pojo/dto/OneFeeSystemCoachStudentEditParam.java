package com.drive.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 一费制学员教练关联表
 *
 * @author xiaoguo
 */
@Data
public class OneFeeSystemCoachStudentEditParam {


    // 主键
    private String id;

    // 教练id
    private String coachId;

    // 学员id
    private String studentId;

    // 订单号
    private String orderNo;

    // 状态（正常，停用）
    private String status;

    // 班型（1-自主预约,2-vip特训，23-vip普通，4-vip包过班
    private String classType;

    // 是否删除
    private String isDelete;

    // 备注(主要存储，停用原因)
    private String remarks;

    // 原班班型ID
    private String originalClassId;

    // 现班班型ID
    private String classId;

    // 运营商id(数据权限标记)
    private String operatorId;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 驾照类型
    private Integer driveType;

    // 绑定时间
    private LocalDateTime bindTime;

    // 绑定状态 (1-待绑定 ,2-已绑定，3-已解绑)
    private Integer bindStatus;

    // 解除绑定时间
    private LocalDateTime relieveBindTime;

    // 新绑定关联表id
    private String newBindId;

    // 科目一免费考试次数
    private Integer subject1CostFreeNumber;

    // 科目二免费考试次数
    private Integer subject2CostFreeNumber;

    // 科目三免费考试次数
    private Integer subject3CostFreeNumber;

    // 科目四免费考试次数
    private Integer subject4CostFreeNumber;

    // 支付状态 (1-待支付, 2-已经支付)
    private Integer payStatus;

    // 科目二教练提成金额
    private BigDecimal coachSubjectType2;

    // 科目二教练预收入金额
    private BigDecimal coachSubject2ExpectIncome;

    // 科目三教练提成金额
    private BigDecimal coachSubjectType3;

    // 科目三教练预收入金额
    private BigDecimal coachSubject3ExpectIncome;

    // 科目二驾校提成金额
    private BigDecimal schoolSubjectType2;

    // 科目三驾校提成金额
    private BigDecimal schoolSubjectType3;

    // 运营商提成金额
    private BigDecimal operatorChangeMoney;

    // 奖金（学员拿证后，教练获得的奖金）
    private BigDecimal bonus;

    // 挂科扣款金额
    private BigDecimal testNotPassWithholdMoney;

    // 平台提成金额（所有上级运营商提成金）
    private BigDecimal serviceChangeMoney;


}