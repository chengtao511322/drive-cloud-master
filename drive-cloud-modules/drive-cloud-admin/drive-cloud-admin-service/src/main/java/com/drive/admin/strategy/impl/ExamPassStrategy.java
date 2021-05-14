package com.drive.admin.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.ExamEnrollEnum;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.enums.SubjectTypeEnum;
import com.drive.admin.pojo.dto.AccountFlowDetailEditParam;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentTestEnrollEditParam;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.pojo.entity.StudentStudyProgressHistoryEntity;
import com.drive.admin.pojo.entity.StudentTestEnrollEntity;
import com.drive.admin.pojo.vo.AccountFlowDetailVo;
import com.drive.admin.repository.AccountFlowRepository;
import com.drive.admin.repository.PlatformWalletRepository;
import com.drive.admin.repository.StudentStudyProgressHistoryRepository;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.StudentOrderService;
import com.drive.admin.service.StudentStudyProgressHistoryService;
import com.drive.admin.service.StudentTestEnrollService;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 小郭
 * @Description //TODO 考试通过
 * @Date $ $
 * @Param $
 * @return $
 **/
@Service
@Slf4j
public class ExamPassStrategy implements StudyEnrollStrategy {

    @Autowired
    private StudentTestEnrollService studentTestEnrollService;

    @Autowired
    private StudentStudyProgressHistoryRepository studentStudyProgressHistoryRepository;

    @Autowired
    private StudentStudyProgressHistoryService studentStudyProgressHistoryService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private PlatformWalletRepository platformWalletRepository;

    @Autowired
    private StudentOrderService orderService;

    @Autowired
    private AccountFlowRepository accountFlowRepository;



    @Override
    public ResObject completeStudyEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        return null;
    }

    @Transactional
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
        //判断实际考试时间 是否小于 当前时间
        long time = System.currentTimeMillis() - DateUtils.asDate(studentTestEnroll.getTestActualTime()).getTime();
        if(time<0){
            return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"请在学员考试结束后在进行操作");
        }

        studentTestEnroll.setEnrollStatus(ExamEnrollEnum.EXAM_PASS.getCode());
        StudentTestEnrollEntity studentTestEnrol = new StudentTestEnrollEntity();
        // 预约成功
        studentTestEnrol.setEnrollStatus(ExamEnrollEnum.EXAM_PASS.getCode());
        studentTestEnrol.setTestEnrollNo(studentStudyEnrollEditParam.getTestEnrollNo());
        Boolean result =studentTestEnrollService.updateById(studentTestEnrol);
        if (!result){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }

        //考试通过，更新学员科目一考试是否通过信息
        StudentInfoEntity tStudentInfo = new StudentInfoEntity();
        tStudentInfo.setId(studentTestEnroll.getStudentId());
        //科目一考试通过
        if(SubjectTypeEnum.SUBJECT_ONE.getCode().equals(studentTestEnrol.getSubjectType())){
            tStudentInfo.setSubject1TestResultType(StatusEnum.ENABLE.getCode());
        }
        //科目二考试通过
        if(SubjectTypeEnum.SUBJECT_TWO.equals(studentTestEnrol.getSubjectType())){
            tStudentInfo.setSubject2TestResultType(StatusEnum.ENABLE.getCode());
        }
        //科目三考试通过
        if(SubjectTypeEnum.SUBJECT_THREE.getCode().equals(studentTestEnrol.getSubjectType())){
            tStudentInfo.setSubject3TestResultType(StatusEnum.ENABLE.getCode());
        }
        //科目四考试通过
        if(SubjectTypeEnum.SUBJECT_FOUR.getCode().equals(studentTestEnrol.getSubjectType())){
            tStudentInfo.setSubject4TestResultType(StatusEnum.ENABLE.getCode());
        }
        Boolean studentRes = studentInfoService.updateById(tStudentInfo);
        if (!studentRes){
            throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }

        // 考试通过，创建账务流明细，结算教练收入费用
        if(SubjectTypeEnum.SUBJECT_TWO.getCode().equals(studentTestEnroll.getSubjectType())
                ||
        SubjectTypeEnum.SUBJECT_THREE.getCode().equals(studentTestEnrol.getSubjectType())){
            // 创建账务流水明细
             ResObject<List<AccountFlowDetailVo>> accountFlowRes = accountFlowRepository.createTestPassVIPCoachFlowDetail(BeanConvertUtils.copy(studentTestEnroll, StudentTestEnrollEditParam.class));
            // 结算费用到钱包
            if (accountFlowRes.getCode().equals(ResCodeEnum.SUCCESS.getCode())){
                List<AccountFlowDetailVo> accountFlowDetailVoList = accountFlowRes.getData();
                log.info("转化后的对象{}",accountFlowDetailVoList);
                accountFlowRepository.settlementByFlowDetailList(BeanConvertUtils.copyList(accountFlowDetailVoList,AccountFlowDetailEditParam.class));
            }

        }

//				this.updateWithholdingExamine(tStudentTestEnroll,WebConstant.YES);

        if(!SubjectTypeEnum.SUBJECT_FOUR.getCode().equals(studentTestEnroll.getSubjectType())){
            //订单号
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("test_enroll_no",studentTestEnroll.getTestEnrollNo());
            StudentOrderEntity studentOrder  = orderService.getOne(queryWrapper);
            //结算考试费用，记录相关流水
            platformWalletRepository.settlementByOrder(studentOrder.getOrderNo());
        }

        // 插入历史
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("student_id",studentTestEnroll.getStudentId());
        queryWrapper.eq("test_enroll_subject",studentTestEnroll.getSubjectType());
        queryWrapper.eq("test_coaching_grid_id",studentTestEnroll.getTestActualCoachingGridId());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.last("limit 1");
        StudentStudyProgressHistoryEntity studentStudyProgressHistory = studentStudyProgressHistoryService.getOne(queryWrapper);
        if(studentStudyProgressHistory != null){
            // 考试完成
            studentStudyProgressHistory.setTestResultType(ExamEnrollEnum.PAY_SUCCESS.getCode());
            studentStudyProgressHistoryService.updateById(studentStudyProgressHistory);
        }

        Boolean testResu= studentTestEnrollService.updateById(studentTestEnroll);
        if (!testResu){
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success();
    }
}
