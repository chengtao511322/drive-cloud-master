package com.drive.admin.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
public class StatisticsStudentDataVo implements java.io.Serializable{

    // 报名单号
    private String studyEnrollNo;
    // 用户编号
    //private String studentNo;
    private String studentId;
    // 用户昵称
    private String nickName;
    // 用户姓名
    private String realName;
    // 手机号码
    private String phone;
    // 线上客服
    private String onLineServiceId;
    private String onLineService;
    // 报名单价
    private BigDecimal applyAmount;
    // 报名驾校
    private String driveSchoolName;
    private String applySchoolId;
    // 联系地址
    private String address;
    // 用户阶段
    private String studentStage;
    // 报名状态
    private String enrollStatus;
    // 科目一状态
    private String subjectOneStatus;
    private String subjectOneStatusName = "未预约";
    // 科目一考试时间
    private LocalDateTime subjectOneExamTime;
    // 科目二状态
    private String subjectTwoStatus;
    private String subjectTwoStatusName = "未预约";
    // 科目二考试时间
    private LocalDateTime subjectTwoExamTime;
    // 科目三状态
    private String subjectThreeStatus;
    private String subjectThreeStatusName  = "未预约";
    // 科目三考试时间
    private LocalDateTime subjectThreeExamTime;
    // 科目四状态
    private String subjectFourStatus;
    private String subjectFourStatusName = "未预约";
    // 科目四考试时间
    private LocalDateTime subjectFourExamTime;
    //科目二课时
    private int subjectTwoHour;
    //科目三课时
    private int subjectThreeHour;
    // 绑定教练
    private String bindCoach;
    // 教练ID
    private String coachId;
    // 推荐人
    private String referrer;
    private String referrerId;
    private String referrerName;
    // 推荐时间
    private LocalDateTime referrerTime;
    // 运营商ID
    private String operatorId;
    // 学员类型
    private String studentType;
}
