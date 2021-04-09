package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.DriveStatusEnum;
import com.drive.admin.enums.ExamEnrollEnum;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentTestEnrollEditParam;
import com.drive.admin.pojo.dto.StudentTestEnrollPageQueryParam;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.CoachTeachTimeVo;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.admin.pojo.vo.StudentTestEnrollVo;
import com.drive.admin.pojo.vo.StudentTrainCarApplyVo;
import com.drive.admin.repository.StudentTestEnrollRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.impl.CoachTeachTimeServiceImpl;
import com.drive.admin.service.mapstruct.StudentStudyEnrollMapStruct;
import com.drive.admin.service.mapstruct.StudentTestEnrollMapStruct;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.admin.strategy.context.SpringContextUtil;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * 学员考试报名表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentTestEnrollRepositoryImpl extends BaseController<StudentTestEnrollPageQueryParam, StudentTestEnrollEntity>  implements StudentTestEnrollRepository{

    //  学员考试报名表 服务
    @Autowired
    private StudentTestEnrollService studentTestEnrollService;
    //  学员考试报名表 DO-DTO转化
    @Autowired
    private StudentTestEnrollMapStruct studentTestEnrollMapStruct;

    //  平台训练场地表 服务
    @Autowired
    private CoachingGridService coachingGridService;


    @Autowired
    private AreaService areaService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private ServiceInfoService serviceInfoService;

    @Autowired
    private StudentStudyEnrollService studentStudyEnrollService;

    @Autowired
    private StudentStudyEnrollMapStruct studentStudyEnrollMapStruct;

    @Autowired
    private DriveSchoolService driveSchoolService;

    @Autowired
    private CoachTeachTimeService coachTeachTimeService;

    @Autowired
    private StudentTrainCarApplyService studentTrainCarApplyService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员考试报名表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentTestEnrollPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(StudentTestEnrollPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentTestEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTestEnrollMapStruct, param);
        // 报名单号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTestEnrollNoSearch()),"test_enroll_no",param.getVagueTestEnrollNoSearch());
        // 预约见面时间
        queryWrapper.apply(StrUtil.isNotEmpty(param.getTestActualTimeSearch()),
                "date_format (test_actual_time,'%Y-%m-%d') = date_format('" + param.getTestActualTimeSearch() + "','%Y-%m-%d')");
        // 意向报名时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getTestHopeTimeSearch()),
                "date_format (test_hope_time,'%Y-%m-%d') = date_format('" + param.getTestHopeTimeSearch() + "','%Y-%m-%d')");
        // 报名单号 模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<StudentTestEnrollEntity> pageList = studentTestEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentTestEnrollVo> studentTestEnrollVoPage = studentTestEnrollMapStruct.toVoList(pageList);
        // 循环数据
        studentTestEnrollVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId()))item.setStudentName(studentInfoService.getById(item.getStudentId()).getRealName());
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // 省市区
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
            // 平台训练场地
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }
            // 查询次数
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type",item.getSubjectType());
            String[] arr = {
                                StudyEnrollEnum.PAY_SUCCESS.getCode(),
                                StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                                StudyEnrollEnum.EXAM_PASS.getCode(),
                                StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                                StudyEnrollEnum.APPLY_LOADING.getCode(),
                                StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                                StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
                            };
            // 支付成功
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // 考试次数
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber);
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",studentTestEnrollVoPage);
        return R.success(studentTestEnrollVoPage);
    }

    @Override
    public ResObject findList(StudentTestEnrollPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentTestEnrollMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentTestEnrollEntity> studentTestEnrollList = studentTestEnrollService.list(queryWrapper);
        if (studentTestEnrollList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        List<StudentTestEnrollVo> studentTestEnrollVoList = studentTestEnrollMapStruct.toVoList(studentTestEnrollList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentTestEnrollVoList);
        return R.success(studentTestEnrollVoList);
    }


    @Override
    public ResObject getInfo(StudentTestEnrollPageQueryParam param) {
        log.info(this.getClass()+"getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        return null;
    }

    /**
     * *通过ID获取学员考试报名表 列表
     **/
    @Override
    public ResObject getById(String testEnrollNo) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",testEnrollNo);
        if (StrUtil.isEmpty(testEnrollNo)){
            return R.failure("数据空");
        }
        StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getById(testEnrollNo);
        if (studentTestEnroll == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        StudentTestEnrollVo studentTestEnrollVo = BeanConvertUtils.copy(studentTestEnroll, StudentTestEnrollVo.class);

        if (StrUtil.isNotEmpty(studentTestEnrollVo.getStudentId()))studentTestEnrollVo.setStudentName(studentInfoService.getById(studentTestEnrollVo.getStudentId()).getRealName());
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getUserId()))studentTestEnrollVo.setOnlineServiceName(serviceInfoService.getById(studentTestEnrollVo.getUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getLineUnderUserId()))studentTestEnrollVo.setLineServiceName(serviceInfoService.getById(studentTestEnrollVo.getLineUnderUserId()).getRealName());
        // 省市区
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getProvinceId()))studentTestEnrollVo.setProvinceName(areaService.getByBaCode(studentTestEnrollVo.getProvinceId()).getBaName());
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getCityId()))studentTestEnrollVo.setCityName(areaService.getByBaCode(studentTestEnrollVo.getCityId()).getBaName());
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getAreaId()))studentTestEnrollVo.setAreaName(areaService.getByBaCode(studentTestEnrollVo.getAreaId()).getBaName());
        // 平台训练场地
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getTestActualCoachingGridId())){
            CoachingGridEntity coachingGridEntity =coachingGridService.getById(studentTestEnrollVo.getTestActualCoachingGridId());
            if(coachingGridEntity != null)studentTestEnrollVo.setTestActualCoachingGridName(coachingGridEntity.getName());
        };
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getTestHopeCoachingGridId())){
            CoachingGridEntity coachingGrid = coachingGridService.getById(studentTestEnrollVo.getTestHopeCoachingGridId());
            if(coachingGrid != null)studentTestEnrollVo.setTestHopeCoachingGridName(coachingGrid.getName());
        }
        //studentStudyEnrollService.getById();
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentTestEnrollVo);
        return R.success(studentTestEnrollVo);
    }

    /**
     * *保存学员考试报名表 信息
     **/
    @Override
    public ResObject save(StudentTestEnrollEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity studentTestEnroll = BeanConvertUtils.copy(installParam, StudentTestEnrollEntity.class);
        Boolean result = studentTestEnrollService.save(studentTestEnroll);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改学员考试报名表 信息
     **/
    @Override
    public ResObject update(StudentTestEnrollEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity studentTestEnroll = BeanConvertUtils.copy(updateParam,StudentTestEnrollEntity.class);
        Boolean result = studentTestEnrollService.updateById(studentTestEnroll);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除学员考试报名表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(studentTestEnrollService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员考试报名表 信息
     **/
    @Override
    public ResObject deleteById(String id) {
        log.info(this.getClass() + "deleteById-方法请求参数{}",id);
        if(StrUtil.isEmpty(id)){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        //QueryWrapper<String> queryWrapper = new QueryWrapper<String>();
        //queryWrapper.eq(StrUtil.isNotEmpty(id),"id",id);
        Boolean result = studentTestEnrollService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出学员考试报名表 信息
     **/
    @Override
    public ResObject exportXls(StudentTestEnrollPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTestEnrollMapStruct, param);
        List<StudentTestEnrollEntity> list = studentTestEnrollService.list(queryWrapper);
        List<StudentTestEnrollVo>studentTestEnrollList = studentTestEnrollMapStruct.toVoList(list);
        ExcelUtils.exportExcel(studentTestEnrollList, StudentTestEnrollVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(StudentTestEnrollEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getTestEnrollNo())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity StudentTestEnrollEntity = new StudentTestEnrollEntity();
        StudentTestEnrollEntity.setTestEnrollNo(param.getTestEnrollNo());
        //StudentTestEnrollEntity.setStatus(param.getStatus());
        //StudentTestEnrollEntity.setUpdateTime()
        Boolean result = studentTestEnrollService.updateById(StudentTestEnrollEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",StudentTestEnrollEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject noSubscribeSubjectOneExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-方法请求参数{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // 学车报名单 包名完成
        //2.没有预约过科目一考试

        //3. 考试挂科满10天
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 报名完成
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // 拼接SQL 没有预约过科目一考试
        queryWrapper.le("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_study_enroll.student_id = t_student_test_enroll.student_id)",0);
        // 拼接sql 考试挂科满10天
        //queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) > NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        //
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // 省市区  后续放缓存
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject findPassingList(String studentId) {
        if (StrUtil.isEmpty(studentId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        String[] arr ={
                ExamEnrollEnum.SUBMIT_AWAIT_PAY.getCode(),
                ExamEnrollEnum.PAY_SUCCESS.getCode(),
                ExamEnrollEnum.BOOK_SUCCESS.getCode(),
                ExamEnrollEnum.EXAM_PASS.getCode(),
                ExamEnrollEnum.APPLY_LOADING.getCode(),
        };
        queryWrapper.in("enroll_status",arr);
        // 学员ID
        queryWrapper.eq("student_id",studentId);
        List<StudentTestEnrollEntity> studentTestEnrollList = studentTestEnrollService.list(queryWrapper);
        if (studentTestEnrollList.size() <=0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTestEnrollList);
        }
        List<StudentTestEnrollVo> studentTestEnrollVoList = BeanConvertUtils.copyList(studentTestEnrollList,StudentTestEnrollVo.class);
        studentTestEnrollVoList.stream().forEach((item) ->{
            // 平台训练场地
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }
        });
        return R.success(studentTestEnrollVoList);
    }

    @Override
    public ResObject getDrivingPassing(String studentId) {
        log.info(this.getClass() + "getDrivingPassing-方法请求参数{}",studentId);
        if (StrUtil.isEmpty(studentId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.groupBy("subject_type");
        List<StudentTrainCarApplyEntity> studentTrainCarApplyList = studentTrainCarApplyService.list(queryWrapper);
        if (studentTrainCarApplyList.size() <=0 ){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        List<StudentTrainCarApplyVo> studentTrainCarApplyVos = BeanConvertUtils.copyList(studentTrainCarApplyList, StudentTrainCarApplyVo.class);
        // double sum = studentTrainCarApplyVos.stream().filter((CoachTeachTimeVo coachTeachTime) -> ((StudentTrainCarApplyVo) coachTeachTime).getApplyStatus().equals(DriveStatusEnum.)).mapToDouble(CoachTeachTimeVo::getClassHours).sum();
        // 查询学员总课时 DriveStatusEnum  int sum = personList.stream().mapToInt(person -> person.getAge()).sum();
        //studentTrainCarApplyVos.stream().filter(coachTeachTime -> coachTeachTime.getApplyStatus().equals(DriveStatusEnum.DRIVING_SUCCESS.getCode())).mapToInt(train -> train.getClassHours()).sum();
        studentTrainCarApplyVos.stream().forEach((item) ->{
            // 统计
            if (item.getApplyStatus().equals(DriveStatusEnum.DRIVING_SUCCESS.getCode())){
                item.setClassHourTotal(studentTrainCarApplyVos.stream().mapToInt(train -> train.getClassHours()).sum());
            }
        });
        return R.success(studentTrainCarApplyVos);
    }

    @Override
    public ResObject noSubscribeSubjectTwoExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-方法请求参数{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // 学车报名单 包名完成
        //2.没有预约过科目一考试

        //3. 考试挂科满10天
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 报名完成
        //queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // 拼接SQL 没有预约过科目一考试
        // 拼接sql 考试挂科满10天
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) > NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        //
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // 省市区  后续放缓存
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject noSubscribeSubjectThreeExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-方法请求参数{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // 学车报名单 包名完成
        //2.没有预约过科目一考试

        //3. 考试挂科满10天
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 报名完成
        //queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // 拼接SQL 没有预约过科目一考试
        // 拼接sql 考试挂科满10天
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 30 DAY) > NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        //
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // 省市区  后续放缓存
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject noSubscribeSubjectFourExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-方法请求参数{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // 学车报名单 包名完成
        //2.没有预约过科目一考试

        //3. 考试挂科满10天
        // 条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 报名完成
        //queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // 拼接SQL 没有预约过科目一考试
        // 拼接sql 考试挂科满10天
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND t_student_test_enroll.enroll_status=8 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=2 AND t_student_test_enroll.enroll_status=8 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND t_student_test_enroll.enroll_status=8 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        //
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // 省市区  后续放缓存
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject examLoadingPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        Page<StudentTestEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        //  考试中 考试报名单状态预约成功
        //  考试中 考实际考试时间 今天
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // 考试报名单状态预约成功
        queryWrapper.eq("enroll_status",StudyEnrollEnum.BOOK_SUCCESS.getCode());
        // 科目类型查询
        queryWrapper.eq("subject_type",studentTestEnrollPageQueryParam.getSubjectType());
        // 考实际考试时间 今天
        queryWrapper.apply("date_format (test_hope_time,'%Y-%m-%d') <= date_format('" + LocalDateTime.now() + "','%Y-%m-%d')");
        IPage<StudentTestEnrollEntity> studentTestEnrollPageList = studentTestEnrollService.page(page,queryWrapper);
        if (studentTestEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTestEnrollPageList);
        }
        IPage<StudentTestEnrollVo> studentTestEnrollVoPageList = studentTestEnrollMapStruct.toVoList(studentTestEnrollPageList);
        studentTestEnrollVoPageList.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId()))item.setStudentName(studentInfoService.getById(item.getStudentId()).getRealName());
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // 省市区
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
            // 平台训练场地
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }
        });
        return R.success(studentTestEnrollVoPageList);
    }

    @Override
    public ResObject completeExamEnroll(CompleteStudyEnrollParam completeStudyEnrollParam) {
        log.info(this.getClass() + "completeStudyEnroll-方法请求参数{}",completeStudyEnrollParam);
        if (completeStudyEnrollParam == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        String strategyValue = ExamEnrollEnum.getStrategyValueByCode(completeStudyEnrollParam.getEnrollStatus());
        if (StrUtil.isEmpty(strategyValue)){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL);
        }
        StudyEnrollStrategy studyEnrollStrategy = SpringContextUtil.getBean(strategyValue,StudyEnrollStrategy.class);
        return studyEnrollStrategy.completeExamEnroll(completeStudyEnrollParam);
    }


}

