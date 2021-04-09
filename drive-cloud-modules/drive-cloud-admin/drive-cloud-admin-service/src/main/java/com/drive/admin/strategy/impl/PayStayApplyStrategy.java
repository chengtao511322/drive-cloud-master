package com.drive.admin.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.drive.admin.enums.ExamEnrollEnum;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.entity.StudentTestEnrollEntity;
import com.drive.admin.pojo.vo.StudentTestEnrollVo;
import com.drive.admin.repository.StudentTestEnrollRepository;
import com.drive.admin.service.StudentTestEnrollService;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import com.drive.common.core.biz.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $ 已支付待申请
 * @Param $
 * @return $
 **/
@Service
@Slf4j
public class PayStayApplyStrategy implements StudyEnrollStrategy {

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
        // 判断期望考试地点
        if (studentStudyEnrollEditParam.getTestHopeTime() == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getTestEnrollNo())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getTestHopeCoachingGridId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询 考试单号
        StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getById(studentStudyEnrollEditParam.getTestEnrollNo());
        if (studentTestEnroll == null){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTestEnroll);
        }
        // 如果 不是 状态2 不可操作
        if (!(studentTestEnroll.getEnrollStatus().equals(ExamEnrollEnum.PAY_SUCCESS.getCode()))){
            return R.success(SubResultCode.ORDER_STATUS_NOT_OPERATION.subCode(),SubResultCode.ORDER_STATUS_NOT_OPERATION.subMsg());
        }
        StudentTestEnrollEntity studentTestEnrol = new StudentTestEnrollEntity();
        // 报名单号
        studentTestEnrol.setTestEnrollNo(studentStudyEnrollEditParam.getTestEnrollNo());
        studentTestEnrol.setTestHopeTime(studentStudyEnrollEditParam.getTestHopeTime());
        studentTestEnrol.setTestActualCoachingGridId(studentStudyEnrollEditParam.getTestActualCoachingGridId());
        studentTestEnrol.setEnrollStatus(ExamEnrollEnum.APPLY_LOADING.getCode());
        Boolean result =studentTestEnrollService.updateById(studentTestEnrol);
        if (!result){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        return R.success();
    }
}
