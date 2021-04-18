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

/**
 * @Author 小郭
 * @Description // 考试不通过
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@Service
public class ExamNoPassStrategy implements StudyEnrollStrategy {

    @Autowired
    private StudentTestEnrollService studentTestEnrollService;

    @Override
    public ResObject completeStudyEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        return null;
    }

    @Override
    public ResObject completeExamEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        log.info(this.getClass() + "completeExamEnroll-方法请求参数{}",studentStudyEnrollEditParam);
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getTestEnrollNo())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getEnrollStatus())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity studentTestEnrol = new StudentTestEnrollEntity();
        // 预约成功
        studentTestEnrol.setEnrollStatus(ExamEnrollEnum.EXAM_NO_PASS.getCode());
        studentTestEnrol.setTestEnrollNo(studentStudyEnrollEditParam.getTestEnrollNo());
        Boolean result =studentTestEnrollService.updateById(studentTestEnrol);
        if (!result){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        return R.success();
    }
}
