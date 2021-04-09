package com.drive.admin.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.repository.PlatformWalletRepository;
import com.drive.admin.service.PlatformWalletService;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.StudentOrderService;
import com.drive.admin.service.StudentStudyEnrollService;
import com.drive.common.core.biz.R;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author 小郭
 * @Description //已面签待审核 逻辑策略
 * @Date $ $
 * @Param $
 * @return $
 **/
@Service
@Slf4j
public class EnrollStatusPrepareStayExamineStrategy implements StudyEnrollStrategy {

    // 报名状态
    @Autowired
    private StudentStudyEnrollService studentStudyEnrollService;

    // 订单
    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private PlatformWalletRepository platformWalletRepository;

    @Transactional
    @Override
    public ResObject completeStudyEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        log.info(this.getClass() + "completeStudyEnroll-方法请求参数{}",studentStudyEnrollEditParam);
        // 数据验证
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getProvinceId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"请选择所在省");
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getCityId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"请选择所在市");
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getAreaId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"请选择所在区");
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getRealName())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"真实姓名不能为空");
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getIdCard())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"身份证号不能为空");
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getEnrollAccountNo())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"交管账号不能为空");
        }
        if (StrUtil.isEmpty(studentStudyEnrollEditParam.getAddress())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"地址不能为空");
        }
        if (studentStudyEnrollEditParam.getActualEnrollTime() == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"实际报名时间不能为空");
        }
        // 查询该报名单的状态
        StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getById(studentStudyEnrollEditParam.getStudyEnrollNo());
        log.error("学员未报名");
        if (studentStudyEnroll == null)return R.failure(SubResultCode.STUDENT_NO_ENROLL.subCode(),SubResultCode.STUDENT_NO_ENROLL.subMsg());
        if (!(studentStudyEnroll.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_PAY_SUCCESS.getCode())))return R.failure(SubResultCode.STATUS_NO_OPERATION.subCode(),SubResultCode.STATUS_NO_OPERATION.subMsg());
        // 修改报名信息
        StudentStudyEnrollEntity studyEnroll = BeanConvertUtils.copy(studentStudyEnrollEditParam, StudentStudyEnrollEntity.class);
        // 修改状态 已面签待审核
        studyEnroll.setEnrollStatus(StudyEnrollEnum.ENROLL_STATUS_PREPARE_STAY_EXAMINE.getCode());
        Boolean studyEnrollResult = studentStudyEnrollService.updateById(studyEnroll);
        // 判断结果
        if (!studyEnrollResult)return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        // 查询订单信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("study_enroll_no",studentStudyEnrollEditParam.getStudyEnrollNo());
        StudentOrderEntity studentOrder = studentOrderService.getOne(queryWrapper);
        // 没有查询到订单 失败 回滚
        if (studentOrder == null)throw new BizException(500,SubResultCode.STUDENT_NO_ENROLL.subCode(),SubResultCode.STUDENT_NO_ENROLL.subMsg());
        //完善老报名单信息
        while (StrUtil.isNotEmpty(studentOrder.getNewOrderNo())) {
            //查询原订单号
            StudentOrderEntity originalStudentOrder = studentOrderService.getById(studentOrder.getNewOrderNo());
            if(originalStudentOrder != null){
                // 通过老报名单号查询老报名单
                StudentStudyEnrollEntity originalStudentStudyEnroll =  studentStudyEnrollService.getById(originalStudentOrder.getStudyEnrollNo());
                //更新报名表
                originalStudentStudyEnroll.setProvinceId(studentStudyEnrollEditParam.getProvinceId()); //省-学员所在地的省
                originalStudentStudyEnroll.setCityId(studentStudyEnrollEditParam.getCityId());//市-学员所在地的市
                originalStudentStudyEnroll.setAreaId(studentStudyEnrollEditParam.getAreaId());//区-学员所在地的区
                originalStudentStudyEnroll.setRealName(studentStudyEnrollEditParam.getRealName());//真实姓名
                originalStudentStudyEnroll.setIdCard(studentStudyEnrollEditParam.getIdCard());//身份证号
                originalStudentStudyEnroll.setDriveSchoolId(studentStudyEnrollEditParam.getDriveSchoolId()); //驾校id
                originalStudentStudyEnroll.setAddress(studentStudyEnrollEditParam.getAddress()); //联系地址
                originalStudentStudyEnroll.setEnrollAccountNo(studentStudyEnrollEditParam.getEnrollAccountNo());//交管网上报名账号
                originalStudentStudyEnroll.setEnrollPassword(studentStudyEnrollEditParam.getEnrollPassword()); //交管网上报名密码
                originalStudentStudyEnroll.setActualEnrollTime(LocalDateTime.now()); //驾校实际报名时间  yyyy-MM-dd HH:mm
                Boolean originalStudentStudyEnrollResult = studentStudyEnrollService.updateById(originalStudentStudyEnroll);
                // 异常
                if (!originalStudentStudyEnrollResult) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
                studentOrder = originalStudentOrder;
            }
        }
        //更新学员信息
        StudentInfoEntity studentInfo = studentInfoService.getById(studentStudyEnroll.getStudentId());
        studentInfo.setIdCard(studentStudyEnrollEditParam.getIdCard()); //身份证号
        studentInfo.setRealName(studentStudyEnrollEditParam.getRealName()); //真实姓名
        Boolean studnetResult = studentInfoService.updateById(studentInfo);
        // 异常
        if (!studnetResult) throw new BizException(500,SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());

        // 钱包结算
        ResObject res= platformWalletRepository.walletSettle(studentOrder.getOrderNo());
        // 判断钱包是否结算成功
        Boolean isWalletSettle=res.getCode() == ResCodeEnum.SUCCESS.getCode() && res.getSubCode().equals(SubResultCode.WALLET_SETTLE_SUCCESS.subCode());
        log.info("请求接口{}",res);
        /*if (!isWalletSettle){
            throw new BizException(500,res.getSubCode(),res.getSubMsg());
        }*/
        return R.success("执行成功");
    }

    @Override
    public ResObject completeExamEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        return null;
    }
}
