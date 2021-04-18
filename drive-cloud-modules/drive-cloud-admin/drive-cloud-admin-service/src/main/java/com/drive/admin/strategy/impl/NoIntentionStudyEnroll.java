package com.drive.admin.strategy.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.service.StudentOrderService;
import com.drive.admin.service.StudentStudyEnrollService;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author 小郭
 * @Description //TODO 无意向报名
 * @Date $ $
 * @Param $
 * @return $
 **/
@Service
@Slf4j
public class NoIntentionStudyEnroll implements StudyEnrollStrategy {

    // 报名状态
    @Autowired
    private StudentStudyEnrollService studentStudyEnrollService;

    // 订单
    @Autowired
    private StudentOrderService studentOrderService;

    @Transactional
    @Override
    public ResObject completeStudyEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
      log.info(this.getClass() + "completeStudyEnroll-方法请求参数{}",studentStudyEnrollEditParam);
      if (studentStudyEnrollEditParam == null){
          return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
      }
      // 1.判断学员是否报名
      // 报名单号
      StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getById(studentStudyEnrollEditParam.getStudyEnrollNo());
      log.error("学员未报名");
      if (studentStudyEnroll == null)return R.failure(SubResultCode.STUDENT_NO_ENROLL.subCode(),SubResultCode.STUDENT_NO_ENROLL.subMsg());

      // 2.判断学员是否有意向

      // 没有意向 直接取消
      // 判断是否已经支付，如果状态不是 提交报名  不允许取消 ENROLL_STATUS_PAY_SUCCESS
        boolean enrollStatus = studentStudyEnroll.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_STAY_SUBMIT.getCode()) || studentStudyEnroll.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_SUBMIT.getCode());
      if (enrollStatus){
          studentStudyEnroll.setEnrollStatus(StudyEnrollEnum.ENROLL_STATUS_CANCEL.getCode());
          studentStudyEnroll.setCancelTime(LocalDateTime.now()); //取消时间
          studentStudyEnroll.setCancelReason("经客服人员确认为无报名意向");
          Boolean studyEnrollResult = studentStudyEnrollService.updateById(studentStudyEnroll);
          // 判断结果
          if (!studyEnrollResult)R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
          // 修改订单
          QueryWrapper queryWrapper = new QueryWrapper();
          queryWrapper.eq("study_enroll_no",studentStudyEnrollEditParam.getStudyEnrollNo());
          StudentOrderEntity studentOrder = studentOrderService.getOne(queryWrapper);
          // 没有查询到订单 失败 回滚
          if (studentOrder == null)throw new BizException(500,SubResultCode.STUDENT_NO_ENROLL.subCode(),SubResultCode.STUDENT_NO_ENROLL.subMsg());
          //修改订单
          studentOrder.setStatus(StudyEnrollEnum.ENROLL_STATUS_CANCEL.getCode());
          studentOrder.setUpdateTime(LocalDateTime.now());
          Boolean orderResult = studentOrderService.updateById(studentOrder);
          if (!orderResult)throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
          return R.success("操作成功");
      }
    return R.failure("该状态下不允许该操作");
    }

    @Override
    public ResObject completeExamEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        return null;
    }
}
