package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.*;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentTestEnrollEditParam;
import com.drive.admin.pojo.dto.StudentTestEnrollPageQueryParam;
import com.drive.admin.pojo.dto.StudentTrainCarApplyPageQueryParam;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.OneFeeSystemCoachStudentVo;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.admin.pojo.vo.StudentTestEnrollVo;
import com.drive.admin.pojo.vo.StudentTrainCarApplyVo;
import com.drive.admin.repository.StudentTestEnrollRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.mapstruct.StudentStudyEnrollMapStruct;
import com.drive.admin.service.mapstruct.StudentTestEnrollMapStruct;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.admin.strategy.context.SpringContextUtil;
import com.drive.admin.util.AdminCacheUtil;
import com.drive.admin.util.MyDateUtil;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.DateUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * ????????????????????? ?????????
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentTestEnrollRepositoryImpl extends BaseController<StudentTestEnrollPageQueryParam, StudentTestEnrollEntity>  implements StudentTestEnrollRepository{

    //  ????????????????????? ??????
    @Autowired
    private StudentTestEnrollService studentTestEnrollService;
    //  ????????????????????? DO-DTO??????
    @Autowired
    private StudentTestEnrollMapStruct studentTestEnrollMapStruct;

    //  ????????????????????? ??????
    @Autowired
    private CoachingGridService coachingGridService;



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

    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private OneFeeSystemCoachStudentService oneFeeSystemCoachStudentService;


    @Autowired
    private OneFeeSystemPriceService oneFeeSystemPriceService;

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ????????????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param StudentTestEnrollPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(StudentTestEnrollPageQueryParam param) {
        log.info(this.getClass() + "pageList-??????????????????{}",param);
        Page<StudentTestEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // ????????????
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTestEnrollMapStruct, param);
        // ???????????? ????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTestEnrollNoSearch()),"test_enroll_no",param.getVagueTestEnrollNoSearch());
        List<StudentInfoEntity> studentInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(param.getVagueRealNameSearch()) || StrUtil.isNotEmpty(param.getVaguePhoneSearch())){
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
        }
        if (StrUtil.isNotEmpty(param.getExamStatus()) && param.getExamStatus().equals(ExamEnrollEnum.EXAM_LOADING.getCode())){
            queryWrapper.eq("enroll_status",ExamEnrollEnum.BOOK_SUCCESS.getCode());
            queryWrapper.apply("date_format(test_actual_time,'%Y-%m-%d') <= date_format(now(),'%Y-%m-%d')");
        }

        // ??????????????????
        if (StrUtil.isNotEmpty(param.getEnrollStatus()) && param.getEnrollStatus().equals(ExamEnrollEnum.BOOK_SUCCESS.getCode())){
            queryWrapper.apply("date_format (test_actual_time,'%Y-%m-%d') > date_format(now(),'%Y-%m-%d')");
        }else{
            queryWrapper.apply(StrUtil.isNotEmpty(param.getTestActualTimeSearch()),
                    "date_format (test_actual_time,'%Y-%m-%d') = date_format('" + param.getTestActualTimeSearch() + "','%Y-%m-%d')");
        }

        // ?????????????????? testActualTime
        queryWrapper.apply(StrUtil.isNotBlank(param.getTestHopeTimeSearch()),
                "date_format (test_hope_time,'%Y-%m-%d') = date_format('" + param.getTestHopeTimeSearch() + "','%Y-%m-%d')");
        // ???????????? ????????????
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        queryWrapper.in(studentInfoList.size() > 0,"student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));

        if (param.getTestHopeTimeArr() .length >0){
            queryWrapper.between("test_hope_time",param.getTestHopeTimeArr()[0],param.getTestHopeTimeArr()[1]);
        }
        if (param.getTestActualTimeArr() .length >0){
            queryWrapper.between("test_actual_time",param.getTestActualTimeArr()[0],param.getTestActualTimeArr()[1]);
        }
        // ????????????
        if (StrUtil.isNotEmpty(param.getEnrollStatusArr())){
            queryWrapper.in("enroll_status",param.getEnrollStatusArr().split(","));
        }
        // queryWrapper.orderByDesc("create_time");
        IPage<StudentTestEnrollEntity> pageList = studentTestEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentTestEnrollVo> studentTestEnrollVoPage = studentTestEnrollMapStruct.toVoList(pageList);
        List<StudentTestEnrollVo> newStudentTestEnrollVoList = new ArrayList<>();
        // ????????????
        studentTestEnrollVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getStudentId());
                if (studentInfo != null)item.setStudentName(studentInfo.getRealName());
                if (studentInfo != null)item.setPhone(studentInfo.getPhone());
            }
            //if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            //if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // ??????????????????
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }
            QueryWrapper trainCarApplyQueryWrapper = new QueryWrapper();
            trainCarApplyQueryWrapper.eq("student_id",item.getStudentId());
            // studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            trainCarApplyQueryWrapper.orderByDesc("create_time");
            trainCarApplyQueryWrapper.last("limit 1");
            StudentTrainCarApplyEntity studentTrainCarApply = studentTrainCarApplyService.getOne(trainCarApplyQueryWrapper);
            if (studentTrainCarApply != null){
                CoachingGridEntity coachingGrid = coachingGridService.getById(studentTrainCarApply.getCoachingGridId());
                if(coachingGrid != null)item.setCoachingGridName(coachingGrid.getName());
            }
            // ????????????
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type",item.getSubjectType());
            String[] arr = {
                                StudyEnrollEnum.PAY_SUCCESS.getCode(),
                                StudyEnrollEnum.BOOK_SUCCESS.getCode(),
                                StudyEnrollEnum.EXAM_PASS.getCode(),
                                StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                                StudyEnrollEnum.APPLY_LOADING.getCode(),
                            };
            // ????????????
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // ????????????
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber);


            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // ??????ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                String coachName = AdminCacheUtil.getCoachName(systemCoachStudent.getCoachId());
                if(StrUtil.isNotEmpty(coachName))item.setBindCoach(coachName);
            }

            // ?????????????????? ??????????????????
            StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam = new StudentTrainCarApplyPageQueryParam();
            studentTrainCarApplyPageQueryParam.setStudentId(item.getStudentId());
            studentTrainCarApplyPageQueryParam.setSubjectType(item.getSubjectType());
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.CONVENTION.getCode());
            int conventionSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.EXAM.getCode());
            int examSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            item.setConventionSum(conventionSum);
            item.setExamSum(examSum);
            // ??????
            QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
            studentOrderQueryWrapper.eq("test_enroll_no",item.getTestEnrollNo());
            StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
            if (studentOrder != null){
                String className = AdminCacheUtil.getClassName(studentOrder.getProductId());
                if(StrUtil.isNotEmpty(className))item.setClassName(className);
                // ????????????
                item.setOrderAmount(studentOrder.getOrderAmount());
                // ????????????
                item.setPayAmount(studentOrder.getPayableAmount());
                item.setPayTime(studentOrder.getPayTime());
            }

            //12 13 >=1  ?????? ??????
            Boolean isBookSuccess =item.getEnrollStatus().equals(ExamEnrollEnum.BOOK_SUCCESS.getCode());
            Boolean isPaySuccess =item.getEnrollStatus().equals(ExamEnrollEnum.PAY_SUCCESS.getCode());
            // ???????????? ????????? ??????????????????????????????
            if (isBookSuccess){
                // examine
                //long examineDay= DateUtil.betweenDay(new Date(),DateUtils.asDate(item.getTestActualTime()),true);//?????????????????????
                long time = System.currentTimeMillis() - DateUtils.asDate(item.getTestActualTime()).getTime();
                //int examineDay = (int) (time / (1000 * 60 * 60 * 24));
                Date date1 = new Date();
                Date date2 = DateUtils.asDate(item.getTestActualTime());
                long examineDay = (date2.getTime() - date1.getTime()) / (1000L*3600L*12L);
                if ((examineDay) == 0) {
                    item.setExamine(true);
                }
            }
            // ?????????????????? ????????? ??????????????????????????????
            if (isPaySuccess){
                // examine
                //long examineDay= DateUtil.betweenDay(new Date(), DateUtils.asDate(item.getTestHopeTime()), true);//?????????????????????
                // ????????????
                Date date1 = new Date();
                Date date2 = DateUtils.asDate(item.getTestHopeTime());

                long examineDay = (date2.getTime() - date1.getTime()) / (1000L*3600L*12L);
                if ((examineDay) == 0) {
                    item.setExamine(true);
                }
            }

            // ??????????????????
            QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studyQueryWrapper);
            if (studentStudyEnroll != null){
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
                if (studentStudyEnroll.getIsInStudy().equals(StatusEnum.NOTDELETE.getCode())){
                    return;
                }
            }
            newStudentTestEnrollVoList.add(item);
        });
        if (newStudentTestEnrollVoList.size() <= 0){
            studentTestEnrollVoPage.setTotal(0);
        }
        log.info(this.getClass() + "pageList-??????????????????{}",studentTestEnrollVoPage.setRecords(newStudentTestEnrollVoList));
        return R.success(studentTestEnrollVoPage);
    }

    @Override
    public ResObject findList(StudentTestEnrollPageQueryParam param) {
        log.info(this.getClass() + "findList-??????????????????{}",param);
        // ??????????????????????????????
        QueryWrapper queryWrapper= this.getQueryWrapper(studentTestEnrollMapStruct, param);
        // ??? queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentTestEnrollEntity> studentTestEnrollList = studentTestEnrollService.list(queryWrapper);
        if (studentTestEnrollList == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        List<StudentTestEnrollVo> studentTestEnrollVoList = studentTestEnrollMapStruct.toVoList(studentTestEnrollList);
        log.info(this.getClass() + "findList-??????????????????{}",studentTestEnrollVoList);
        return R.success(studentTestEnrollVoList);
    }


    @Override
    public ResObject getInfo(StudentTestEnrollPageQueryParam param) {
        log.info(this.getClass()+"getInfo-??????????????????{}",param);
        if (param == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        return null;
    }

    /**
     * *??????ID??????????????????????????? ??????
     **/
    @Override
    public ResObject getById(String testEnrollNo) {
        log.info(this.getClass() + "getInfo-??????????????????{}",testEnrollNo);
        if (StrUtil.isEmpty(testEnrollNo)){
            return R.failure("?????????");
        }
        StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getById(testEnrollNo);
        if (studentTestEnroll == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        StudentTestEnrollVo studentTestEnrollVo = BeanConvertUtils.copy(studentTestEnroll, StudentTestEnrollVo.class);

        if (StrUtil.isNotEmpty(studentTestEnrollVo.getStudentId()))studentTestEnrollVo.setStudentName(studentInfoService.getById(studentTestEnrollVo.getStudentId()).getRealName());
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getUserId()))studentTestEnrollVo.setOnlineServiceName(serviceInfoService.getById(studentTestEnrollVo.getUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getLineUnderUserId()))studentTestEnrollVo.setLineServiceName(serviceInfoService.getById(studentTestEnrollVo.getLineUnderUserId()).getRealName());
        // ??????????????????
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getTestActualCoachingGridId())){
            CoachingGridEntity coachingGridEntity =coachingGridService.getById(studentTestEnrollVo.getTestActualCoachingGridId());
            if(coachingGridEntity != null)studentTestEnrollVo.setTestActualCoachingGridName(coachingGridEntity.getName());
        };
        if (StrUtil.isNotEmpty(studentTestEnrollVo.getTestHopeCoachingGridId())){
            CoachingGridEntity coachingGrid = coachingGridService.getById(studentTestEnrollVo.getTestHopeCoachingGridId());
            if(coachingGrid != null)studentTestEnrollVo.setTestHopeCoachingGridName(coachingGrid.getName());
        }
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        queryWrapper.eq("student_id",studentTestEnrollVo.getStudentId());
        StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(queryWrapper);
        if (studentStudyEnroll != null){
            studentTestEnrollVo.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
        }
        // ?????????
        log.info(this.getClass() + "getInfo-??????????????????{}",studentTestEnrollVo);
        return R.success(studentTestEnrollVo);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject save(StudentTestEnrollEditParam installParam) {
        log.info(this.getClass() + "save??????????????????{}",installParam);
        if (installParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity studentTestEnroll = BeanConvertUtils.copy(installParam, StudentTestEnrollEntity.class);
        Boolean result = studentTestEnrollService.save(studentTestEnroll);
        log.info(this.getClass() + "save-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject update(StudentTestEnrollEditParam updateParam) {
        log.info(this.getClass() + "update??????????????????{}",updateParam);
        if (updateParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity studentTestEnroll = BeanConvertUtils.copy(updateParam,StudentTestEnrollEntity.class);
        studentTestEnroll.setUpdateTime(LocalDateTime.now());
        Boolean result = studentTestEnrollService.updateById(studentTestEnroll);
        log.info(this.getClass() + "update-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(studentTestEnrollService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *??????id??????????????????????????? ??????
     **/
    @Override
    public ResObject deleteById(String id) {
        log.info(this.getClass() + "deleteById-??????????????????{}",id);
        if(StrUtil.isEmpty(id)){
            log.error("ID?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        //QueryWrapper<String> queryWrapper = new QueryWrapper<String>();
        //queryWrapper.eq(StrUtil.isNotEmpty(id),"id",id);
        Boolean result = studentTestEnrollService.removeById(id);
        log.info(this.getClass() + "deleteById-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject exportXls(StudentTestEnrollPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls??????????????????{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTestEnrollMapStruct, param);
        List<StudentTestEnrollEntity> list = studentTestEnrollService.list(queryWrapper);
        List<StudentTestEnrollVo>studentTestEnrollList = studentTestEnrollMapStruct.toVoList(list);
        ExcelUtils.exportExcel(studentTestEnrollList, StudentTestEnrollVo.class, "", new ExportParams(), response);
        return R.success("????????????");
    }

    /**
     * *????????????
     **/
    @Override
    public ResObject changeStatus(StudentTestEnrollEditParam param) {
        log.info(this.getClass() + "changeStatus??????????????????{}",param);
        if (StrUtil.isEmpty(param.getTestEnrollNo())){
            log.error("ID?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentTestEnrollEntity StudentTestEnrollEntity = new StudentTestEnrollEntity();
        StudentTestEnrollEntity.setTestEnrollNo(param.getTestEnrollNo());
        //StudentTestEnrollEntity.setStatus(param.getStatus());
        //StudentTestEnrollEntity.setUpdateTime()
        Boolean result = studentTestEnrollService.updateById(StudentTestEnrollEntity);
        log.info(this.getClass() +"changeStatus????????????????????????{}???????????????{}",StudentTestEnrollEntity,result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject noSubscribeSubjectOneExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-??????????????????{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // ??????????????? ????????????
        //2.??????????????????????????????

        // ???????????? ????????????

        //3. ???????????????10???
        // ????????????
        QueryWrapper<StudentStudyEnrollEntity> queryWrapper = new QueryWrapper();
        // ???????????????????????????0-??????1-??????
        queryWrapper.eq("is_in_study",StatusEnum.ENABLE.getCode());
        List<StudentInfoEntity> studentInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVagueRealNameSearch()) || StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVaguePhoneSearch())){
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVagueRealNameSearch()),"real_name",studentTestEnrollPageQueryParam.getVagueRealNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVaguePhoneSearch()),"phone",studentTestEnrollPageQueryParam.getVaguePhoneSearch());
            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
        }
        queryWrapper.in(studentInfoList.size() > 0,"student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));
        // ???????????????
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // ????????????
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // ??????SQL ??????????????????????????????
        //queryWrapper.le("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_study_enroll.student_id = t_student_test_enroll.student_id)",0);
        // ??????sql ???????????????10???
        /**
         * LambdaQueryWrapper<Task> queryWrapper2 = new QueryWrapper<Task>().lambda();
         *  queryWrapper2
         *   .eq(Task::getUserId, "15")
         *   .and(wrapper -> wrapper.eq(Task::getStatus, 2).or().eq(Task::getFileSize, 3251544304L));
         */

        queryWrapper.and(wrapper ->{
            wrapper.and(nameAgeQueryWrapper ->{
             /*   nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.eq("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id )",0);
                });*/
                // ???????????????10
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.student_id = t_student_study_enroll.student_id AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) <= NOW() AND t_student_test_enroll.enroll_status=9) <=0");
                });
                // ??????????????????????????????
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.student_id = t_student_study_enroll.student_id and t_student_test_enroll.subject_type=1) <=0");
                });
            });
        });
        // ????????????
        queryWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE  t_student_test_enroll.student_id = t_student_study_enroll.student_id and t_student_test_enroll.enroll_status in(1,2,5,7,8,10)) <= 0");
        //queryWrapper.le("(SELECT COUNT(1) FROM t_student_test_enroll WHERE DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) > NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        // queryWrapper .eq(???name???,????????????).or().eq(???sim???,???2???);
        queryWrapper.orderByDesc("create_time");
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        List<StudentStudyEnrollVo> newStudentStudyEnrollVos = new ArrayList<>();
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{

            // ?????????????????? ??????????????????
            StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam = new StudentTrainCarApplyPageQueryParam();
            studentTrainCarApplyPageQueryParam.setStudentId(item.getStudentId());
            studentTrainCarApplyPageQueryParam.setSubjectType(item.getSubjectType());
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.CONVENTION.getCode());
            int conventionSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.EXAM.getCode());
            int examSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            item.setConventionSum(conventionSum);
            item.setExamSum(examSum);
            // ??????
            QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
            studentOrderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
            if (studentOrder != null){
                String className = AdminCacheUtil.getClassName(studentOrder.getProductId());
                if(StrUtil.isNotEmpty(className))item.setClassName(className);
                // ????????????
                item.setOrderAmount(studentOrder.getOrderAmount());
                // ????????????
                item.setPayAmount(studentOrder.getPayableAmount());
                item.setPayTime(studentOrder.getPayTime());
            }

            item.setSubjectType(SubjectTypeEnum.SUBJECT_ONE.getCode());
            // ??????????????????
           QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_ONE.getCode());
            // studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            studyQueryWrapper.orderByDesc("test_actual_time");
            studyQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(studyQueryWrapper);
            if (studentTestEnroll != null){
                // ????????????
                item.setTestEnrollNo(studentTestEnroll.getTestEnrollNo());
                item.setExamStatus(studentTestEnroll.getEnrollStatus());
                // ??????
            }
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }


            // ????????????
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type","1");
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                    StudyEnrollEnum.EXAM_PASS.getCode(),
                    StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                    StudyEnrollEnum.APPLY_LOADING.getCode(),
                    StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                    StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
            };
            // ????????????
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // ????????????
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber + 1);


            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // ??????ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                String coachName = AdminCacheUtil.getCoachName(systemCoachStudent.getCoachId());
                if(StrUtil.isNotEmpty(coachName))item.setBindCoach(coachName);
            }
            //newStudentStudyEnrollVos.add(item);
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject findPassingList(String studentId) {
        if (StrUtil.isEmpty(studentId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        String[] arr ={
                ExamEnrollEnum.SUBMIT_AWAIT_PAY.getCode(),
                ExamEnrollEnum.PAY_SUCCESS.getCode(),
                ExamEnrollEnum.BOOK_SUCCESS.getCode(),
                ExamEnrollEnum.EXAM_PASS.getCode(),
                ExamEnrollEnum.APPLY_LOADING.getCode(),
        };
        queryWrapper.in("enroll_status",arr);
        // ??????ID
        queryWrapper.eq("student_id",studentId);
        List<StudentTestEnrollEntity> studentTestEnrollList = studentTestEnrollService.list(queryWrapper);
        if (studentTestEnrollList.size() <=0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTestEnrollList);
        }
        List<StudentTestEnrollVo> studentTestEnrollVoList = BeanConvertUtils.copyList(studentTestEnrollList,StudentTestEnrollVo.class);
        studentTestEnrollVoList.stream().forEach((item) ->{
            // ??????????????????
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
        log.info(this.getClass() + "getDrivingPassing-??????????????????{}",studentId);
        if (StrUtil.isEmpty(studentId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.groupBy("train_type,subject_type");
        List<StudentTrainCarApplyEntity> studentTrainCarApplyList = studentTrainCarApplyService.list(queryWrapper);
        if (studentTrainCarApplyList.size() <=0 ){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTrainCarApplyList);
        }
        List<StudentTrainCarApplyVo> studentTrainCarApplyVos = BeanConvertUtils.copyList(studentTrainCarApplyList, StudentTrainCarApplyVo.class);
        // double sum = studentTrainCarApplyVos.stream().filter((CoachTeachTimeVo coachTeachTime) -> ((StudentTrainCarApplyVo) coachTeachTime).getApplyStatus().equals(DriveStatusEnum.)).mapToDouble(CoachTeachTimeVo::getClassHours).sum();
        // ????????????????????? DriveStatusEnum  int sum = personList.stream().mapToInt(person -> person.getAge()).sum();
        //studentTrainCarApplyVos.stream().filter(coachTeachTime -> coachTeachTime.getApplyStatus().equals(DriveStatusEnum.DRIVING_SUCCESS.getCode())).mapToInt(train -> train.getClassHours()).sum();
        studentTrainCarApplyVos.stream().forEach((item) ->{
            // ??????
            if (item.getApplyStatus().equals(DriveStatusEnum.DRIVING_SUCCESS.getCode())){
                item.setClassHourTotal(studentTrainCarApplyVos.stream().mapToInt(train -> train.getClassHours()).sum());
            }
        });
        return R.success(studentTrainCarApplyVos);
    }

    @Override
    public ResObject noSubscribeSubjectTwoExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-??????????????????{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // ????????????????????????
        // 1.?????????????????????10????????????
        //2.????????????????????????10????????????
        //3. ?????????????????????
        // ????????????
        QueryWrapper<StudentStudyEnrollEntity> queryWrapper = new QueryWrapper();
        // ???????????????????????????0-??????1-??????
        queryWrapper.eq("is_in_study",StatusEnum.ENABLE.getCode());
        List<StudentInfoEntity> studentInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVagueRealNameSearch()) || StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVaguePhoneSearch())){
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVagueRealNameSearch()),"real_name",studentTestEnrollPageQueryParam.getVagueRealNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVaguePhoneSearch()),"phone",studentTestEnrollPageQueryParam.getVaguePhoneSearch());
            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
        }
        queryWrapper.in(studentInfoList.size() > 0,"student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));
        // ???????????????
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // ????????????
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        //queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // ??????sql ?????????????????????10????????????
        // ????????????????????????10????????????

        queryWrapper.and(wrapper ->{
            wrapper.and(nameAgeQueryWrapper ->{
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) <= NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=8)",0);
                });
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=2 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) <= NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=9) <=0");

                });
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type = 2 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status = 6) > 0");

                });
            });
        });

        // ?????????????????????
        // queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=2 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=6)",0);
        queryWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=2 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status in(1,2,5,7,8,10)) <= 0");
        //
        queryWrapper.orderByDesc("create_time");
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            item.setSubjectType(SubjectTypeEnum.SUBJECT_TWO.getCode());
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // ????????????
            QueryWrapper examQueryWrapper = new QueryWrapper();
            examQueryWrapper.eq("student_id",item.getStudentId());
            examQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_TWO.getCode());
            // studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            examQueryWrapper.orderByDesc("test_actual_time");
            examQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(examQueryWrapper);
            if (studentTestEnroll != null){
                // ????????????
                item.setTestEnrollNo(studentTestEnroll.getTestEnrollNo());
                item.setStudentTestEnrollVo(BeanConvertUtils.copy(studentTestEnroll,StudentTestEnrollVo.class));
                item.setExamStatus(studentTestEnroll.getEnrollStatus());
                // ??????
            }

            // ????????????
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_TWO.getCode());
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                    StudyEnrollEnum.EXAM_PASS.getCode(),
                    StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                    StudyEnrollEnum.APPLY_LOADING.getCode(),
                    StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                    StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
            };
            // ????????????
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // ????????????
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber);
            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // ??????ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                String coachName = AdminCacheUtil.getCoachName(systemCoachStudent.getCoachId());
                if(StrUtil.isNotEmpty(coachName))item.setBindCoach(coachName);
            }

            // ??????
            QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
            studentOrderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
            if (studentOrder != null){
                String className = AdminCacheUtil.getClassName(studentOrder.getProductId());
                if(StrUtil.isNotEmpty(className))item.setClassName(className);
                // ????????????
                item.setOrderAmount(studentOrder.getOrderAmount());
                // ????????????
                item.setPayAmount(studentOrder.getPayableAmount());
                item.setPayTime(studentOrder.getPayTime());
            }

            // ?????????????????? ??????????????????
            StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam = new StudentTrainCarApplyPageQueryParam();
            studentTrainCarApplyPageQueryParam.setStudentId(item.getStudentId());
            studentTrainCarApplyPageQueryParam.setSubjectType(item.getSubjectType());
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.CONVENTION.getCode());
            int conventionSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.EXAM.getCode());
            int examSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            item.setConventionSum(conventionSum);
            item.setExamSum(examSum);
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject noSubscribeSubjectThreeExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-??????????????????{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // ??????????????? ????????????
        //1.??????????????????30???
        // ???????????????????????????10
        //????????????????????????
        QueryWrapper<StudentStudyEnrollEntity> queryWrapper = new QueryWrapper();
        // ???????????????????????????0-??????1-??????
        queryWrapper.eq("is_in_study",StatusEnum.ENABLE.getCode());
        List<StudentInfoEntity> studentInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVagueRealNameSearch()) || StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVaguePhoneSearch())){
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVagueRealNameSearch()),"real_name",studentTestEnrollPageQueryParam.getVagueRealNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVaguePhoneSearch()),"phone",studentTestEnrollPageQueryParam.getVaguePhoneSearch());
            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
        }
        queryWrapper.in(studentInfoList.size() > 0,"student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));
        // ???????????????
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // ????????????
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // ??????SQL ??????????????????????????????
        // ??????sql ???????????????10???
        //queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 30 DAY) > NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        //

        queryWrapper.and(wrapper ->{
            wrapper.and(nameAgeQueryWrapper ->{
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 30 DAY) <= NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=8)",0);
                });
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND DATE_ADD(t_student_test_enroll.test_actual_time,INTERVAL 10 DAY) <= NOW() AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=9) <=0");

                });
                nameAgeQueryWrapper.or(itemWrapper ->{
                    itemWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=6)>0");

                });
            });
        });
        // ?????????????????????
        //queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status=6)",0);
        queryWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status in(1,2,5,7,8,10)) <= 0");
        queryWrapper.orderByDesc("create_time");
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            item.setSubjectType(SubjectTypeEnum.SUBJECT_THREE.getCode());
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }

            // ??????
            QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
            studentOrderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
            if (studentOrder != null){
                String className = AdminCacheUtil.getClassName(studentOrder.getProductId());
                if(StrUtil.isNotEmpty(className))item.setClassName(className);
                // ????????????
                item.setOrderAmount(studentOrder.getOrderAmount());
                // ????????????
                item.setPayAmount(studentOrder.getPayableAmount());
                item.setPayTime(studentOrder.getPayTime());
            }

            // ??????????????????
  /*          QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studyQueryWrapper);
            if (studentStudyEnroll != null){
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
            }*/

            // ????????????
            QueryWrapper examQueryWrapper = new QueryWrapper();
            examQueryWrapper.eq("student_id",item.getStudentId());
            examQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_THREE.getCode());
            // studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            examQueryWrapper.orderByDesc("test_actual_time");
            examQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(examQueryWrapper);
            if (studentTestEnroll != null){
                // ????????????
                item.setTestEnrollNo(studentTestEnroll.getTestEnrollNo());
                item.setStudentTestEnrollVo(BeanConvertUtils.copy(studentTestEnroll,StudentTestEnrollVo.class));
                item.setExamStatus(studentTestEnroll.getEnrollStatus());
                // ??????
            }

            // ????????????
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_THREE.getCode());
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                    StudyEnrollEnum.EXAM_PASS.getCode(),
                    StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                    StudyEnrollEnum.APPLY_LOADING.getCode(),
                    StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                    StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
            };
            // ????????????
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // ????????????
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber);
            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // ??????ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                String coachName = AdminCacheUtil.getCoachName(systemCoachStudent.getCoachId());
                if(StrUtil.isNotEmpty(coachName))item.setBindCoach(coachName);
            }

            // ?????????????????? ??????????????????
            StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam = new StudentTrainCarApplyPageQueryParam();
            studentTrainCarApplyPageQueryParam.setStudentId(item.getStudentId());
            studentTrainCarApplyPageQueryParam.setSubjectType(item.getSubjectType());
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.CONVENTION.getCode());
            int conventionSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.EXAM.getCode());
            int examSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            item.setConventionSum(conventionSum);
            item.setExamSum(examSum);
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject examPassStatisticsPageList(StudentTestEnrollPageQueryParam param) {
        log.info("examPassStatistics-??????????????????{}",param);
        Page<StudentTestEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // ????????????
        QueryWrapper queryWrapper = this.getQueryWrapper(studentTestEnrollMapStruct, param);
        List<StudentInfoEntity> studentInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(param.getVagueRealNameSearch()) || StrUtil.isNotEmpty(param.getVaguePhoneSearch())){
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
        }
        queryWrapper.in(studentInfoList.size() > 0,"student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));
        // ???????????? ????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueTestEnrollNoSearch()),"test_enroll_no",param.getVagueTestEnrollNoSearch());
        // ???????????? ?????????
        queryWrapper.eq("subject_type", SubjectTypeEnum.SUBJECT_FOUR.getCode());
        // ??????
        queryWrapper.eq("enroll_status",StudyEnrollEnum.EXAM_PASS.getCode());

        // ?????????????????? testActualTime
        queryWrapper.apply(StrUtil.isNotEmpty(param.getTestHopeTimeSearch()),
                "date_format (test_hope_time,'%Y-%m-%d') = date_format('" + param.getTestHopeTimeSearch() + "','%Y-%m-%d')");
        // ???????????? ????????????
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        if (param.getExamPassTimeArr() != null && param.getExamPassTimeArr().length > 0){
            queryWrapper.between("test_actual_time",param.getExamPassTimeArr()[0],param.getExamPassTimeArr()[1]);
        }
        IPage<StudentTestEnrollEntity> pageList = studentTestEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentTestEnrollVo> studentTestEnrollVoPage = studentTestEnrollMapStruct.toVoList(pageList);
        // ????????????
        studentTestEnrollVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getStudentId());
                if (studentInfo != null)item.setStudentName(studentInfo.getRealName());
                if (studentInfo != null)item.setPhone(studentInfo.getPhone());
            }
           // if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            //if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // ??????????????????
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }

            // ??????
            QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
            studentOrderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
            if (studentOrder != null){
                String className = AdminCacheUtil.getClassName(studentOrder.getProductId());
                if(StrUtil.isNotEmpty(className))item.setClassName(className);
                // ????????????
                item.setOrderAmount(studentOrder.getOrderAmount());
                // ????????????
                item.setPayAmount(studentOrder.getPayableAmount());
                item.setPayTime(studentOrder.getPayTime());
            }

            // ??????????????????
            QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studyQueryWrapper);
            if (studentStudyEnroll != null){
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
            }
            QueryWrapper feeSystemCoachStudentQueryWrapper = new QueryWrapper();
            // ??????ID
            feeSystemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            feeSystemCoachStudentQueryWrapper.eq("bind_status", StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = oneFeeSystemCoachStudentService.getOne(feeSystemCoachStudentQueryWrapper);
            if (oneFeeSystemCoachStudent != null) {
                OneFeeSystemCoachStudentVo feeSystemCoachStudent = BeanConvertUtils.copy(oneFeeSystemCoachStudent,OneFeeSystemCoachStudentVo.class);
                log.info("??????????????????{}",feeSystemCoachStudent);
                String coachName = AdminCacheUtil.getCoachName(feeSystemCoachStudent.getCoachId());
                if(StrUtil.isNotEmpty(coachName))item.setCoachName(coachName);
                // ??????
                String className = AdminCacheUtil.getClassName(feeSystemCoachStudent.getClassId());
                if(StrUtil.isNotEmpty(className))item.setClassName(className);
            }
            // ??????
            QueryWrapper orderQueryWrapper = new QueryWrapper();
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.STAT_EVALUATE.getCode(),
                    StudyEnrollEnum.EVALUATE_SUCCESS.getCode(),
            };
            orderQueryWrapper.eq("student_id",item.getStudentId());
            orderQueryWrapper.in("status",arr);
            List<StudentOrderEntity> studentOrderList = studentOrderService.list(orderQueryWrapper);

            //??????????????????
            BigDecimal totalAmount = studentOrderList.stream().map(StudentOrderEntity::getPayableAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            item.setTotalAmount(totalAmount);

            // ?????????????????? ??????????????????
            StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam = new StudentTrainCarApplyPageQueryParam();
            studentTrainCarApplyPageQueryParam.setStudentId(item.getStudentId());
            studentTrainCarApplyPageQueryParam.setSubjectType(item.getSubjectType());
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.CONVENTION.getCode());
            int conventionSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            studentTrainCarApplyPageQueryParam.setTrainType(SubjectTypeEnum.EXAM.getCode());
            int examSum =studentTrainCarApplyService.getClassHoursSum(studentTrainCarApplyPageQueryParam);
            item.setConventionSum(conventionSum);
            item.setExamSum(examSum);
        });
        log.info(this.getClass() + "pageList-??????????????????{}",studentTestEnrollVoPage);
        return R.success(studentTestEnrollVoPage);
    }

    @Override
    public ResObject noSubscribeSubjectFourExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        log.info(this.getClass()+"noSubscribeSubjectOneExamPageList-??????????????????{}",studentTestEnrollPageQueryParam);
        Page<StudentStudyEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        // ??????????????? ????????????
        //2.?????????????????????

        //3.?????????????????????
        // ????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        // ???????????????????????????0-??????1-??????
        queryWrapper.eq("is_in_study",StatusEnum.ENABLE.getCode());
        List<StudentInfoEntity> studentInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVagueRealNameSearch()) || StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVaguePhoneSearch())){
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVagueRealNameSearch()),"real_name",studentTestEnrollPageQueryParam.getVagueRealNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getVaguePhoneSearch()),"phone",studentTestEnrollPageQueryParam.getVaguePhoneSearch());
            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
        }
        queryWrapper.in(studentInfoList.size() > 0,"student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));
        // ???????????????
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // ????????????
        queryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        // ??????SQL ??????????????????????????????
        // ??????sql ???????????????10???
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=1 AND t_student_test_enroll.enroll_status=8 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=2 AND t_student_test_enroll.enroll_status=8 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=3 AND t_student_test_enroll.enroll_status=8 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id)",0);
        //
        queryWrapper.apply("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_student_test_enroll.subject_type=4 AND t_student_test_enroll.student_id = t_student_study_enroll.student_id AND t_student_test_enroll.enroll_status in(1,2,5,7,8,10)) <= 0");
        queryWrapper.orderByDesc("create_time");
        IPage<StudentStudyEnrollEntity> studentStudyEnrollPageList = studentStudyEnrollService.page(page,queryWrapper);
        if (studentStudyEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollPageList);
        }
        IPage<StudentStudyEnrollVo> studentStudyEnrollVos = studentStudyEnrollMapStruct.toVoList(studentStudyEnrollPageList);
        studentStudyEnrollVos.getRecords().stream().forEach((item) ->{
            item.setSubjectType(SubjectTypeEnum.SUBJECT_FOUR.getCode());
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }

            // ????????????
            QueryWrapper examQueryWrapper = new QueryWrapper();
            examQueryWrapper.eq("student_id",item.getStudentId());
            examQueryWrapper.eq("subject_type",SubjectTypeEnum.SUBJECT_FOUR.getCode());
            // studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            examQueryWrapper.orderByDesc("test_actual_time");
            examQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(examQueryWrapper);
            if (studentTestEnroll != null){
                item.setStudentTestEnrollVo(BeanConvertUtils.copy(studentTestEnroll,StudentTestEnrollVo.class));
                // ????????????
                item.setTestEnrollNo(studentTestEnroll.getTestEnrollNo());
                item.setExamStatus(studentTestEnroll.getEnrollStatus());
                // ??????
            }

            // ????????????
            QueryWrapper testQueryWrapper = new QueryWrapper();
            testQueryWrapper.eq("student_id",item.getStudentId());
            testQueryWrapper.eq("subject_type","1");
            String[] arr = {
                    StudyEnrollEnum.PAY_SUCCESS.getCode(),
                    StudyEnrollEnum.EXAM_ACCOMPLISH.getCode(),
                    StudyEnrollEnum.EXAM_PASS.getCode(),
                    StudyEnrollEnum.EXAM_NO_PASS.getCode(),
                    StudyEnrollEnum.APPLY_LOADING.getCode(),
                    StudyEnrollEnum.REFUND_DISPOSE_LOADING.getCode(),
                    StudyEnrollEnum.EXAM_REFUND_SUCCESS.getCode()
            };
            // ????????????
            testQueryWrapper.in("enroll_status", arr);
            testQueryWrapper.eq("operator_id", item.getOperatorId());
            // ????????????
            int examNumber = studentTestEnrollService.count(testQueryWrapper);
            item.setExamNumber(examNumber);
            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // ??????ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                String coachName = AdminCacheUtil.getCoachName(systemCoachStudent.getCoachId());
                if(StrUtil.isNotEmpty(coachName))item.setBindCoach(coachName);
            }
        });
        return R.success(studentStudyEnrollVos);
    }

    @Override
    public ResObject examLoadingPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam) {
        Page<StudentTestEnrollEntity> page = new Page<>(studentTestEnrollPageQueryParam.getPageNum(), studentTestEnrollPageQueryParam.getPageSize());
        //  ????????? ?????????????????????????????????
        //  ????????? ????????????????????? ??????
        QueryWrapper queryWrapper = new QueryWrapper();
        // ???????????????
        queryWrapper.eq(StrUtil.isNotEmpty(studentTestEnrollPageQueryParam.getOperatorId()),"operator_id",studentTestEnrollPageQueryParam.getOperatorId());
        // ?????????????????????????????????
        queryWrapper.eq("enroll_status",StudyEnrollEnum.BOOK_SUCCESS.getCode());
        // ??????????????????
        queryWrapper.eq("subject_type",studentTestEnrollPageQueryParam.getSubjectType());
        // ????????????????????? ??????
        queryWrapper.apply("date_format (test_hope_time,'%Y-%m-%d') <= date_format(now(),'%Y-%m-%d')");
        IPage<StudentTestEnrollEntity> studentTestEnrollPageList = studentTestEnrollService.page(page,queryWrapper);
        if (studentTestEnrollPageList.getRecords().size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentTestEnrollPageList);
        }
        IPage<StudentTestEnrollVo> studentTestEnrollVoPageList = studentTestEnrollMapStruct.toVoList(studentTestEnrollPageList);
        studentTestEnrollVoPageList.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getStudentId()))item.setStudentName(studentInfoService.getById(item.getStudentId()).getRealName());
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // ?????????
            // ??????????????????
            if (StrUtil.isNotEmpty(item.getTestActualCoachingGridId())){
                CoachingGridEntity coachingGridEntity =coachingGridService.getById(item.getTestActualCoachingGridId());
                if(coachingGridEntity != null)item.setTestActualCoachingGridName(coachingGridEntity.getName());
            };
            if (StrUtil.isNotEmpty(item.getTestHopeCoachingGridId())){
                CoachingGridEntity coachingGrid = coachingGridService.getById(item.getTestHopeCoachingGridId());
                if(coachingGrid != null)item.setTestHopeCoachingGridName(coachingGrid.getName());
            }

            // ??????????????????
            QueryWrapper studyQueryWrapper = new QueryWrapper();
            studyQueryWrapper.eq("student_id",item.getStudentId());
            studyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studyQueryWrapper);
            if (studentStudyEnroll != null){
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
            }

            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // ??????ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                String coachName = AdminCacheUtil.getCoachName(systemCoachStudent.getCoachId());
                if(StrUtil.isNotEmpty(coachName))item.setBindCoach(coachName);
            }

        });
        return R.success(studentTestEnrollVoPageList);
    }

    @Override
    public ResObject completeExamEnroll(CompleteStudyEnrollParam completeStudyEnrollParam) {
        log.info(this.getClass() + "completeStudyEnroll-??????????????????{}",completeStudyEnrollParam);
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

