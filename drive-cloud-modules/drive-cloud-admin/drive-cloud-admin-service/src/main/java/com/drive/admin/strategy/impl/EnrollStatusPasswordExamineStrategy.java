package com.drive.admin.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.service.StudentOrderService;
import com.drive.admin.service.StudentStudyEnrollService;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 小郭
 * @Description //密码已提交待审核
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@Service
public class EnrollStatusPasswordExamineStrategy implements StudyEnrollStrategy {

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
        if (studentStudyEnrollEditParam.getEnrollPassword() == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),"交管密码不能为空");
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
        if (!(studentStudyEnroll.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_PREPARE_STAY_EXAMINE.getCode())))return R.failure(SubResultCode.STATUS_NO_OPERATION.subCode(),SubResultCode.STATUS_NO_OPERATION.subMsg());
        // 修改报名信息
        StudentStudyEnrollEntity studyEnroll = BeanConvertUtils.copy(studentStudyEnrollEditParam, StudentStudyEnrollEntity.class);
        // 修改状态 密码已提交待审核
        studyEnroll.setEnrollStatus(StudyEnrollEnum.ENROLL_STATUS_PASSWORD_EXAMINE.getCode());
        Boolean studyEnrollResult = studentStudyEnrollService.updateById(studyEnroll);
        // 判断结果
        if (!studyEnrollResult)return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        return R.success("执行成功");
    }
}
