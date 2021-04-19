package com.drive.admin.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.ExamEnrollEnum;
import com.drive.admin.enums.SubjectTypeEnum;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.pojo.entity.StudentTestEnrollEntity;
import com.drive.admin.repository.PlatformWalletRepository;
import com.drive.admin.service.StudentOrderService;
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

    @Autowired
    private StudentOrderService orderService;

    @Autowired
    private PlatformWalletRepository platformWalletRepository;

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
        // 查询出考试信息
        StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getById(studentStudyEnrollEditParam.getTestEnrollNo());
        if (studentTestEnroll == null){
            log.info("数据异常，");
            return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"数据异常");
        }

        StudentTestEnrollEntity studentTestEnrol = new StudentTestEnrollEntity();
        // 预约成功
        studentTestEnrol.setEnrollStatus(ExamEnrollEnum.EXAM_NO_PASS.getCode());
        studentTestEnrol.setTestEnrollNo(studentStudyEnrollEditParam.getTestEnrollNo());
        Boolean result =studentTestEnrollService.updateById(studentTestEnrol);
        if (!result){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }


        if(!SubjectTypeEnum.SUBJECT_FOUR.getCode().equals(studentTestEnroll.getSubjectType())){
            //订单号
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("test_enroll_no",studentTestEnroll.getTestEnrollNo());
            StudentOrderEntity studentOrder  = orderService.getOne(queryWrapper);
            //结算考试费用，记录相关流水
            platformWalletRepository.settlementByOrder(studentOrder.getOrderNo());
        }
        return R.success();
    }
}
