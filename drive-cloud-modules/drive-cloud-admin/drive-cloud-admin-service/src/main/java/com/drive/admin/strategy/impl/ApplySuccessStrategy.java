package com.drive.admin.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.drive.admin.enums.ExamEnrollEnum;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.entity.StudentTestEnrollEntity;
import com.drive.admin.service.StudentTestEnrollService;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $ 预约成功处理
 * @Param $
 * @return $
 **/
@Service
@Slf4j
public class ApplySuccessStrategy implements StudyEnrollStrategy {

    @Autowired
    private StudentTestEnrollService studentTestEnrollService;

    @Override
    public ResObject completeStudyEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        return null;
    }

    @Transactional
    @Override
    public ResObject completeExamEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        log.info(this.getClass() + "completeExamEnroll-方法请求参数{}",studentStudyEnrollEditParam);
        if (studentStudyEnrollEditParam.getTestHopeTime() == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"希望预约考试时间不能为空");
        }
        if (studentStudyEnrollEditParam.getTestActualTime() == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"实际考试时间");
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getTestHopeCoachingGridId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"希望预约考试场地ID不能为空");
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getTestActualCoachingGridId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"希望预约考试场地ID不能为空");
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getTestEnrollNo())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"考试报名单不能为空");
        }
        // 查询 考试单号
        StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getById(studentStudyEnrollEditParam.getTestEnrollNo());
        if (studentTestEnroll == null){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTestEnroll);
        }
        // 如果 不是 状态2 不可操作
        if (!(studentTestEnroll.getEnrollStatus().equals(ExamEnrollEnum.APPLY_LOADING.getCode()))){
            return R.success(SubResultCode.ORDER_STATUS_NOT_OPERATION.subCode(),SubResultCode.ORDER_STATUS_NOT_OPERATION.subMsg());
        }
        StudentTestEnrollEntity studentTestEnrol = new StudentTestEnrollEntity();
        // 报名单号
        studentTestEnrol.setTestEnrollNo(studentStudyEnrollEditParam.getTestEnrollNo());
        studentTestEnrol.setTestHopeTime(studentStudyEnrollEditParam.getTestHopeTime());
        studentTestEnrol.setTestActualTime(studentStudyEnrollEditParam.getTestActualTime());
        studentTestEnrol.setTestHopeCoachingGridId(studentStudyEnrollEditParam.getTestHopeCoachingGridId());
        studentTestEnrol.setTestActualCoachingGridId(studentStudyEnrollEditParam.getTestActualCoachingGridId());
        // 预约成功
        studentTestEnrol.setEnrollStatus(ExamEnrollEnum.BOOK_SUCCESS.getCode());
        Boolean result =studentTestEnrollService.updateById(studentTestEnrol);
        if (!result){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        return R.success();
    }
}
