package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.*;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentOrderPageQueryParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollEditParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.repository.ServiceReturnVisitHistoryRepository;
import com.drive.admin.repository.StudentStudyEnrollRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.mapstruct.CoachTeachTimeMapStruct;
import com.drive.admin.service.mapstruct.StudentOrderMapStruct;
import com.drive.admin.service.mapstruct.StudentStudyEnrollMapStruct;
import com.drive.admin.strategy.StudyEnrollStrategy;
import com.drive.admin.strategy.context.SpringContextUtil;
import com.drive.admin.util.AdminCacheUtil;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.DateUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
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
public class  StudentStudyEnrollRepositoryImpl extends BaseController<StudentStudyEnrollPageQueryParam, StudentStudyEnrollEntity>  implements StudentStudyEnrollRepository{

    @Autowired
    private StudentStudyEnrollService studentStudyEnrollService;
    @Autowired
    private StudentStudyEnrollMapStruct studentStudyEnrollMapStruct;

    // ??????
    @Autowired
    private ServiceInfoService serviceInfoService;

    //  ???????????????????????????????????????????????????????????????????????????????????? ??????
    @Autowired
    private DriveSchoolService driveSchoolService;

    @Autowired
    private ServiceReturnVisitHistoryService serviceReturnVisitHistoryService;
    @Autowired
    private ServiceReturnVisitHistoryRepository serviceReturnVisitHistoryRepository;

    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private OneFeeSystemCoachStudentService oneFeeSystemCoachStudentService;


    @Autowired
    private StudentTestEnrollService studentTestEnrollService;

    @Autowired
    private StudentTrainCarApplyService studentTrainCarApplyService;

    @Autowired
    private CoachTeachTimeService coachTeachTimeService;
    @Autowired
    private CoachTeachTimeMapStruct coachTeachTimeMapStruct;

    @Autowired
    private CoachInfoService coachInfoService;

    @Autowired
    private StudentOrderMapStruct studentOrderMapStruct;

    @Autowired
    private OneFeeSystemPriceService oneFeeSystemPriceService;





    /*
     *
     *????????????
     * @author xiaoguo
     * @description ????????????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param StudentStudyEnrollPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "pageList-??????????????????{}",param);
        Page<StudentStudyEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());

       /* if (StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch())){
            return serviceReturnVisitHistoryRepository.pageListReturnVisitHistory(param);
        }*/

        // ??????
        //log.info("????????????{}",SecurityUtils.getAuthentication());
        List<StudentOrderEntity> studentOrderList = null;
        QueryWrapper orderQueryWrapper = new QueryWrapper();
        if (StrUtil.isNotEmpty(param.getStudentOrderNo())){
            orderQueryWrapper.like("order_no",param.getStudentOrderNo());
            studentOrderList  = studentOrderService.list(orderQueryWrapper);
            if (studentOrderList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentOrderList);
        }

        QueryWrapper<StudentStudyEnrollEntity> queryWrapper = this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        // ???????????? ????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        // ????????????????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
        // ????????????????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"telephone",param.getVaguePhoneSearch());

        if (param.getIsReturnVisit()!= null && param.getIsReturnVisit() == 1){
            queryWrapper.gt("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id AND t_service_return_visit_history.order_detail_no = t_student_study_enroll.study_enroll_no AND t_service_return_visit_history.return_visit_item IN(2,3))",0);
        }
        if (param.getIsReturnVisit()!= null && param.getIsReturnVisit() == 0){
            //queryWrapper.eq("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id )",0);
            //queryWrapper.gt("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id AND t_service_return_visit_history.return_visit_item IN(1,4))",0);

            // ????????? ???????????????????????????
            QueryWrapper returnVisitQueryWrapper = new QueryWrapper();
            returnVisitQueryWrapper.orderByDesc("return_visit_time");
            returnVisitQueryWrapper.last("limit 1");
            serviceReturnVisitHistoryService.getOne(returnVisitQueryWrapper);

            queryWrapper.and(wrapper ->{
                wrapper.and(nameAgeQueryWrapper ->{
                    nameAgeQueryWrapper.or(itemWrapper ->{
                        itemWrapper.eq("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id AND t_service_return_visit_history.order_detail_no = t_student_study_enroll.study_enroll_no)",0);
                    });
                    nameAgeQueryWrapper.or(itemWrapper ->{
                        itemWrapper.gt("(SELECT count(1) FROM (select  t.* from (SELECT * FROM t_service_return_visit_history HAVING 1 ORDER BY create_time DESC) t GROUP BY t.student_id) t1 WHERE t1.student_id = t_student_study_enroll.student_id AND t1.order_detail_no = t_student_study_enroll.study_enroll_no AND t1.return_visit_item IN (1, 4))",0);
                    });
                });
            });
        }
        // ??????????????????????????????
        if (StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch())){
            queryWrapper.gt("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id AND t_service_return_visit_history.order_detail_no = t_student_study_enroll.study_enroll_no AND date_format(t_service_return_visit_history.next_return_visit_time,'%Y-%m-%d')= DATE_FORMAT('"+param.getNextReturnVisitTimeSearch()+"','%Y-%m-%d'))",0);
        }
        // ?????????????????????
        if (param.getIsIntentionSearch() != null){
            queryWrapper.gt("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id AND t_service_return_visit_history.order_detail_no = t_student_study_enroll.study_enroll_no AND is_intention="+param.getIsIntentionSearch()+")",0);
        }
        // ??????????????????
        if (StrUtil.isNotEmpty(param.getVagueServiceNameSearch())){
            List<ServiceInfoEntity> serviceInfoList = null;
            QueryWrapper serviceQueryWrapper = new QueryWrapper();
            serviceQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueServiceNameSearch()),"real_name",param.getVagueServiceNameSearch());
            serviceInfoList  = serviceInfoService.list(serviceQueryWrapper);
            if (serviceInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceInfoList);
            queryWrapper.in("user_id",serviceInfoList.stream().map(ServiceInfoEntity::getId).collect(Collectors.toList()));
        }
        // ????????????????????????
        if (StrUtil.isNotEmpty(param.getVagueOnlineServiceNameSearch())){
            List<ServiceInfoEntity> serviceInfoList = null;
            QueryWrapper serviceQueryWrapper = new QueryWrapper();
            serviceQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueOnlineServiceNameSearch()),"real_name",param.getVagueOnlineServiceNameSearch());
            serviceInfoList  = serviceInfoService.list(serviceQueryWrapper);
            if (serviceInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceInfoList);
            queryWrapper.in("user_id",serviceInfoList.stream().map(ServiceInfoEntity::getId).collect(Collectors.toList()));
        }
        //??????????????????
        if (StrUtil.isNotEmpty(param.getVaguePreSalesServiceNameSearch())){
            List<ServiceInfoEntity> serviceInfoList = null;
            QueryWrapper serviceQueryWrapper = new QueryWrapper();
            serviceQueryWrapper.like(StrUtil.isNotEmpty(param.getVaguePreSalesServiceNameSearch()),"real_name",param.getVaguePreSalesServiceNameSearch());
            serviceInfoList  = serviceInfoService.list(serviceQueryWrapper);
            if (serviceInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceInfoList);
            queryWrapper.in("pre_sales_service_id",serviceInfoList.stream().map(ServiceInfoEntity::getId).collect(Collectors.toList()));
        }
        // ??????????????????
        queryWrapper.apply(StrUtil.isNotBlank(param.getBeSpeakMeetTimeSearch()),
        "date_format (be_speak_meet_time,'%Y-%m-%d') = date_format('" + param.getBeSpeakMeetTimeSearch() + "','%Y-%m-%d')");
        // ??????????????????
        queryWrapper.apply(StrUtil.isNotBlank(param.getIntentEnrollTimeSearch()),
        "date_format (intent_enroll_time,'%Y-%m-%d') = date_format('" + param.getIntentEnrollTimeSearch() + "','%Y-%m-%d')");

        if (studentOrderList != null && studentOrderList.size() > 0){
            queryWrapper.in("study_enroll_no",studentOrderList.stream().map(StudentOrderEntity::getStudyEnrollNo).collect(Collectors.toList()));
        }
        // ????????????
        if (StrUtil.isNotEmpty(param.getEnrollStatusArr())){
            queryWrapper.in("enroll_status",param.getEnrollStatusArr().split(","));
        }

        if (param.getHasPreOnlineServicer() !=null && param.getHasPreOnlineServicer().equals(0)){
            queryWrapper.isNull("pre_sales_service_id");
        }
        if (param.getHasPreOnlineServicer() !=null && param.getHasPreOnlineServicer().equals(1)){
            queryWrapper.isNotNull("pre_sales_service_id");
        }

        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        if (StrUtil.isNotEmpty(param.getCreateTimeSearch())){
            String[] arr = param.getCreateTimeSearch().split(",");
            queryWrapper.between("create_time",arr[0],arr[1]);
        }

        IPage<StudentStudyEnrollEntity> pageList = studentStudyEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????-------------");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // ??????????????????,?????? ??????????????????
        Boolean globalMatch = Boolean.FALSE;
        Page<StudentStudyEnrollVo> studentStudyEnrollVoPage = studentStudyEnrollMapStruct.toVoList(pageList);
        studentStudyEnrollVoPage.getRecords().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(AdminCacheUtil.getServiceRealName(item.getUserId()));
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(AdminCacheUtil.getServiceRealName(item.getLineUnderUserId()));
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // ?????????  ???????????????
            QueryWrapper serviceQueryWrapper = new QueryWrapper();
            serviceQueryWrapper.eq("order_detail_no",item.getStudyEnrollNo());
            serviceQueryWrapper.eq("student_id",item.getStudentId());
            serviceQueryWrapper.orderByDesc("create_time");
            serviceQueryWrapper.last("limit 1");
            ServiceReturnVisitHistoryEntity serviceReturnVisitHistoryEntity  = serviceReturnVisitHistoryService.getOne(serviceQueryWrapper);
            if (serviceReturnVisitHistoryEntity != null){
                item.setReturnVisitTime(serviceReturnVisitHistoryEntity.getReturnVisitTime());
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(serviceReturnVisitHistoryEntity.getServiceId());
                item.setReturnVisitServiceName(AdminCacheUtil.getServiceRealName(serviceReturnVisitHistoryEntity.getServiceId()));
                item.setReturnVisitContent(serviceReturnVisitHistoryEntity.getReturnVisitContent());
            }
            // ??????
            QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
            studentOrderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
            if (studentOrder != null){
                String className = AdminCacheUtil.getClassName(studentOrder.getProductId());
                if(StrUtil.isNotEmpty(className))item.setClassName(className);
                item.setStudentOrderVo(BeanConvertUtils.copy(studentOrder, StudentOrderVo.class));
                // ????????????
                item.setOrderAmount(studentOrder.getOrderAmount());
                // ????????????
                item.setPayAmount(studentOrder.getPayableAmount());
            }

            // ?????????3
            if (item.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_PAY_SUCCESS.getCode())){
                // examine
                long examineDay= DateUtil.betweenDay(new Date(),DateUtils.asDate(studentOrder.getPayTime()), true);//?????????????????????
                if ((examineDay) >= 1) {
                    item.setExamine(true);
                }
            }
            //12 13 >=1  ?????? ??????
            Boolean prepareStayExamine =item.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_PREPARE_STAY_EXAMINE.getCode());
            Boolean passwordExamine =item.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_PASSWORD_EXAMINE.getCode());
            if (prepareStayExamine || passwordExamine){
                // examine
                long examineDay= DateUtil.betweenDay(new Date(), DateUtils.asDate(item.getUpdateTime()), true);//?????????????????????
                if ((examineDay) >= 7) {
                    item.setExamine(true);
                }
            }

            QueryWrapper oneFeeSystemCoachStudentQueryWrapper = new QueryWrapper();
            oneFeeSystemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            //
            oneFeeSystemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            oneFeeSystemCoachStudentQueryWrapper.eq("status",StatusEnum.ENABLE.getCode());
            OneFeeSystemCoachStudentEntity oneFeeSystemCoachStudent = oneFeeSystemCoachStudentService.getOne(oneFeeSystemCoachStudentQueryWrapper);
            if (oneFeeSystemCoachStudent != null) {
                OneFeeSystemCoachStudentVo feeSystemCoachStudent = BeanConvertUtils.copy(oneFeeSystemCoachStudent,OneFeeSystemCoachStudentVo.class);
                log.info("??????????????????{}",feeSystemCoachStudent);

                String coachName = AdminCacheUtil.getCoachName(feeSystemCoachStudent.getCoachId());
                if(StrUtil.isNotEmpty(coachName))item.setCoachName(coachName);
                // ??????
                String className = AdminCacheUtil.getClassName(oneFeeSystemCoachStudent.getClassId());
                if (StrUtil.isNotEmpty(className))item.setClassName(className);
            }
            // ??????
            StudentInfoEntity student = studentInfoService.getById(item.getStudentId());
            if (student != null){
                item.setStudentVo(BeanConvertUtils.copy(student, StudentInfoVo.class));
            }
            //
            QueryWrapper returnQueryWrapper = new QueryWrapper();
            //returnQueryWrapper.eq("order_detail_no",item.getStudyEnrollNo());
            returnQueryWrapper.eq("student_id",item.getStudentId());
            returnQueryWrapper.orderByDesc("create_time");
            returnQueryWrapper.last("limit 1");
            ServiceReturnVisitHistoryEntity serviceReturnVisitHistory  = serviceReturnVisitHistoryService.getOne(returnQueryWrapper);
            if (serviceReturnVisitHistory != null){
                item.setReturnVisitTime(serviceReturnVisitHistory.getReturnVisitTime());
                item.setReturnVisitServiceName(AdminCacheUtil.getServiceRealName(serviceReturnVisitHistory.getServiceId()));
                item.setReturnVisitContent(serviceReturnVisitHistory.getReturnVisitContent());
                item.setServiceReturnVisitHistory(BeanConvertUtils.copy(serviceReturnVisitHistory, ServiceReturnVisitHistoryVo.class));

            }
            QueryWrapper startReturnQueryWrapper = new QueryWrapper();
            //returnQueryWrapper.eq("order_detail_no",item.getStudyEnrollNo());
            startReturnQueryWrapper.eq("student_id",item.getStudentId());
            startReturnQueryWrapper.orderByAsc("create_time");
            startReturnQueryWrapper.last("limit 1");
            ServiceReturnVisitHistoryEntity startServiceReturnVisitHistory  = serviceReturnVisitHistoryService.getOne(startReturnQueryWrapper);
            if (startServiceReturnVisitHistory != null){
                item.setStartReturnVisitTime(startServiceReturnVisitHistory.getReturnVisitTime());
            }
            QueryWrapper queryWrapperCount = new QueryWrapper();
            queryWrapperCount.eq("student_id",item.getStudentId());
            queryWrapperCount.eq("status", StudyEnrollEnum.CANCEL_ORDER.getCode());
            int cancelNum= studentOrderService.count(queryWrapperCount);
            //studentOrderVos.stream().mapToDouble(StudentStudyEnrollVo::getOrderStatus).sum()
            // ????????????
            //IntSummaryStatistics sumcc = studentOrderVos.stream().collect(Collectors.summarizingInt(e->Integer.valueOf(String.valueOf(e.getOrderStatus()=="5"))));
            // ??????????????????
            item.setCancelNum(cancelNum);
            if (StrUtil.isNotEmpty(item.getStudentId())){
                QueryWrapper returnVisitHistoryQueryWrapper = new QueryWrapper();
                returnVisitHistoryQueryWrapper.eq("student_id",item.getStudentId());
                returnVisitHistoryQueryWrapper.in("return_visit_item",2,3);
                int countReturnVisitHistory =serviceReturnVisitHistoryService.count(returnVisitHistoryQueryWrapper);
                if (countReturnVisitHistory > 0){
                    item.setReturnVisitHistory(true);
                }
            }
        });
        // ????????? ????????????
   /*     if (StrUtil.isNotEmpty(param.getStudentOrderNo())){
            studentStudyEnrollVoPage.getRecords().stream().filter(userz -> globalMatch?userz.getStudentOrderVo().getOrderNo().equals(param.getStudentOrderNo()):
                    userz.getStudentOrderVo().getOrderNo().contains(param.getStudentOrderNo())).
                    collect(Collectors.toList());
        }*/
        // ???????????? ????????????
       /* if (StrUtil.isNotEmpty(param.getVagueRealNameSearch())){
            studentStudyEnrollVoPage.getRecords().stream().filter(userz -> globalMatch?userz.getStudentVo().getRealName().equals(param.getVagueRealNameSearch()):
                    userz.getStudentVo().getRealName().contains(param.getVagueRealNameSearch())).
                    collect(Collectors.toList());
        }*/
        if(param.isReturnVisitHistory()){
            List<StudentStudyEnrollVo> studentStudyEnrollVoList = studentStudyEnrollVoPage.getRecords().stream().filter((StudentStudyEnrollVo student)->student.isReturnVisitHistory() == param.isReturnVisitHistory()) //???????????????150???
                    .collect(Collectors.toList());
            // ????????????????????????
            studentStudyEnrollVoPage.setRecords(studentStudyEnrollVoList);
            log.info(this.getClass() + "pageList-??????????????????{}",studentStudyEnrollVoPage);
            studentStudyEnrollVoPage.setTotal(studentStudyEnrollVoList.size());
            return R.success(studentStudyEnrollVoPage);
        }
        return R.success(studentStudyEnrollVoPage);
    }

    @Override
    public ResObject findList(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "findList-??????????????????{}",param);
        // ??????????????????????????????
        QueryWrapper queryWrapper= this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        // ??? queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentStudyEnrollEntity> pageList = studentStudyEnrollService.list(queryWrapper);
        List<StudentStudyEnrollVo> studentStudyEnrollVoList = studentStudyEnrollMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-??????????????????{}",studentStudyEnrollVoList);
        if (studentStudyEnrollVoList == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(studentStudyEnrollVoList);
    }


    @Override
    public ResObject getInfo(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "findList-??????????????????{}",param);
        // ??????????????????????????????
        QueryWrapper queryWrapper= this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        // ??? queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        queryWrapper.in(param.getOrderStatusArr().length > 0,"enroll_status",param.getOrderStatusArr());
        StudentStudyEnrollEntity studentStudyEnrollEntity = studentStudyEnrollService.getOne(queryWrapper);
        if (studentStudyEnrollEntity == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollEntity);
        }
        StudentStudyEnrollVo studentStudyEnrollVo = BeanConvertUtils.copy(studentStudyEnrollEntity,StudentStudyEnrollVo.class);
        log.info(this.getClass() + "findList-??????????????????{}",studentStudyEnrollVo);
        // ????????????
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getUserId()))studentStudyEnrollVo.setOnlineServiceName(AdminCacheUtil.getServiceRealName(studentStudyEnrollVo.getUserId()));
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getLineUnderUserId()))studentStudyEnrollVo.setLineServiceName(AdminCacheUtil.getServiceRealName(studentStudyEnrollVo.getLineUnderUserId()));
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getDriveSchoolId())){
            DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(studentStudyEnrollVo.getLineUnderUserId());
            if (driveSchoolEntity != null)studentStudyEnrollVo.setLineServiceName(driveSchoolEntity.getSchoolName());
        }
        // ?????????
        QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
        studentOrderQueryWrapper.eq("study_enroll_no",studentStudyEnrollVo.getStudyEnrollNo());
        StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
        if (studentOrder != null){
            String className = AdminCacheUtil.getClassName(studentOrder.getProductId());
            if(StrUtil.isNotEmpty(className))studentStudyEnrollVo.setCoachName(className);
        }

        return R.success(studentStudyEnrollVo);
    }

    /**
     * *??????ID??????????????????????????? ??????
     **/
    @Override
    public ResObject getById(String studyEnrollNo) {
        log.info(this.getClass() + "getInfo-??????????????????{}",studyEnrollNo);
        if (StrUtil.isEmpty(studyEnrollNo)){
            return R.failure("?????????");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("study_enroll_no",studyEnrollNo);
        StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(queryWrapper);
        if (studentStudyEnroll == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        StudentStudyEnrollVo studentStudyEnrollVo = BeanConvertUtils.copy(studentStudyEnroll, StudentStudyEnrollVo.class);
        // ????????????
        //if (StrUtil.isNotEmpty(studentStudyEnrollVo.getUserId()))studentStudyEnrollVo.setOnlineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getUserId()).getRealName());
        //if (StrUtil.isNotEmpty(studentStudyEnrollVo.getLineUnderUserId()))studentStudyEnrollVo.setLineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getLineUnderUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getDriveSchoolId())){
            DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(studentStudyEnrollVo.getDriveSchoolId());
            if (driveSchoolEntity != null)studentStudyEnrollVo.setDriveSchoolName(driveSchoolEntity.getSchoolName());
        }
        log.info(this.getClass() + "getInfo-??????????????????{}",studentStudyEnrollVo);
        return R.success(studentStudyEnrollVo);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject save(StudentStudyEnrollEditParam installParam) {
        log.info(this.getClass() + "save??????????????????{}",installParam);
        if (installParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentStudyEnrollEntity studentStudyEnroll = BeanConvertUtils.copy(installParam, StudentStudyEnrollEntity.class);
        Boolean result = studentStudyEnrollService.save(studentStudyEnroll);
        log.info(this.getClass() + "save-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject update(StudentStudyEnrollEditParam updateParam) {
        log.info(this.getClass() + "update??????????????????{}",updateParam);
        if (updateParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentStudyEnrollEntity studentStudyEnroll = BeanConvertUtils.copy(updateParam, StudentStudyEnrollEntity.class);
        studentStudyEnroll.setUpdateTime(LocalDateTime.now());
        Boolean result = studentStudyEnrollService.updateById(studentStudyEnroll);
        log.info(this.getClass() + "update-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }
    @Override
    public ResObject updateBatchById(List<StudentStudyEnrollEditParam> updateParam) {
        log.info(this.getClass() + "update??????????????????{}",updateParam);
        if (updateParam.size() <= 0){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        List<StudentStudyEnrollEntity> studentStudyEnroll = BeanConvertUtils.copyList(updateParam, StudentStudyEnrollEntity.class);
        Boolean result = studentStudyEnrollService.updateBatchById(studentStudyEnroll);
        log.info(this.getClass() + "update-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject operationStudent(StudentStudyEnrollEditParam studyEnrollEditParam) {
        if (StrUtil.isEmpty(studyEnrollEditParam.getStudentId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(studyEnrollEditParam.getIsInStudy())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        //??????????????????????????????
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("student_id",studyEnrollEditParam.getStudentId());
        List<StudentStudyEnrollEntity> studentStudyEnrollList = studentStudyEnrollService.list(queryWrapper);
        if (studentStudyEnrollList.size() <= 0){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        studentStudyEnrollList.stream().forEach((item) ->{
            item.setIsInStudy(studyEnrollEditParam.getIsInStudy());
        }) ;
        // ?????? ????????????
        Boolean result = studentStudyEnrollService.updateBatchById(studentStudyEnrollList);
        if (!result)return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        return R.success(result);
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
        return R.toRes(studentStudyEnrollService.removeByIds(Arrays.asList(ids)));
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
        Boolean result = studentStudyEnrollService.removeById(id);
        log.info(this.getClass() + "deleteById-??????????????????{}",result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *??????????????????????????? ??????
     **/
    @Override
    public ResObject exportXls(StudentStudyEnrollPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls??????????????????{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        List<StudentStudyEnrollEntity> list = studentStudyEnrollService.list(queryWrapper);
        List<StudentStudyEnrollVo>studentStudyEnrollList = studentStudyEnrollMapStruct.toVoList(list);
        ExcelUtils.exportExcel(studentStudyEnrollList, StudentStudyEnrollVo.class, "", new ExportParams(), response);
        return R.success("????????????");
    }

    /**
     * *????????????
     **/
    @Override
    public ResObject changeStatus(StudentStudyEnrollEditParam param) {
        log.info(this.getClass() + "changeStatus??????????????????{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentStudyEnrollEntity StudentStudyEnrollEntity = new StudentStudyEnrollEntity();
        StudentStudyEnrollEntity.setStudyEnrollNo(param.getStudyEnrollNo());
        //StudentStudyEnrollEntity.setStatus(param.getStatus());
        //StudentStudyEnrollEntity.setUpdateTime()
        Boolean result = studentStudyEnrollService.updateById(StudentStudyEnrollEntity);
        log.info(this.getClass() +"changeStatus????????????????????????{}???????????????{}",StudentStudyEnrollEntity,result);
        // ????????????
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject studyEnrollPageList(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "studyEnrollPageList-??????????????????{}",param);
        Page<StudentStudyEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());

        if (StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch())){
            return serviceReturnVisitHistoryRepository.pageListReturnVisitHistory(param);
        }

        // ??????

        List<StudentOrderEntity> studentOrderList = new ArrayList<>();
        QueryWrapper orderQueryWrapper = new QueryWrapper();
        if (StrUtil.isNotEmpty(param.getStudentOrderNo())){
            orderQueryWrapper.like("order_no",param.getStudentOrderNo());
            studentOrderList  = studentOrderService.list(orderQueryWrapper);
            if (studentOrderList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentOrderList);
        }

        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        // ???????????? ????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        // ????????????????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
        // ??????????????????
        queryWrapper.apply(StrUtil.isNotBlank(param.getBeSpeakMeetTimeSearch()),
                "date_format (be_speak_meet_time,'%Y-%m-%d') = date_format('" + param.getBeSpeakMeetTimeSearch() + "','%Y-%m-%d')");
        // ??????????????????
        queryWrapper.apply(StrUtil.isNotBlank(param.getIntentEnrollTimeSearch()),
                "date_format (intent_enroll_time,'%Y-%m-%d') = date_format('" + param.getIntentEnrollTimeSearch() + "','%Y-%m-%d')");
        // ????????????
        if (studentOrderList != null && studentOrderList.size() > 0){
            queryWrapper.in("study_enroll_no",studentOrderList.stream().map(StudentOrderEntity::getStudyEnrollNo).collect(Collectors.toList()));
        }
        // ????????????
        if (StrUtil.isNotEmpty(param.getEnrollStatusArr())){
            queryWrapper.in("enroll_status",param.getEnrollStatusArr().split(","));
        }

        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        if (StrUtil.isNotEmpty(param.getCreateTimeSearch())){
            String[] arr = param.getCreateTimeSearch().split(",");
            queryWrapper.between("create_time",arr[0],arr[1]);
        }

        IPage<StudentStudyEnrollEntity> pageList = studentStudyEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????-------------");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // ??????????????????,?????? ??????????????????
        Boolean globalMatch = Boolean.FALSE;
        Page<StudentStudyEnrollVo> studentStudyEnrollVoPage = studentStudyEnrollMapStruct.toVoList(pageList);
        studentStudyEnrollVoPage.getRecords().forEach((item) ->{
            //if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            //if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // ?????????
            QueryWrapper serviceQueryWrapper = new QueryWrapper();
            serviceQueryWrapper.eq("order_detail_no",item.getStudyEnrollNo());
            serviceQueryWrapper.eq("student_id",item.getStudentId());
            serviceQueryWrapper.orderByDesc("create_time");
            serviceQueryWrapper.last("limit 1");
            ServiceReturnVisitHistoryEntity serviceReturnVisitHistoryEntity  = serviceReturnVisitHistoryService.getOne(serviceQueryWrapper);
            if (serviceReturnVisitHistoryEntity != null){
                item.setReturnVisitTime(serviceReturnVisitHistoryEntity.getReturnVisitTime());
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(serviceReturnVisitHistoryEntity.getServiceId());
                if (serviceInfo != null)item.setReturnVisitServiceName(serviceInfo.getRealName());
                item.setReturnVisitContent(serviceReturnVisitHistoryEntity.getReturnVisitContent());
            }
            // ??????
            QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
            studentOrderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
            if (studentOrder != null){
                item.setStudentOrderVo(BeanConvertUtils.copy(studentOrder, StudentOrderVo.class));
            }

            // ??????
            StudentInfoEntity student  = studentInfoService.getById(item.getStudentId());
            if (student != null){
                item.setStudentVo(BeanConvertUtils.copy(student, StudentInfoVo.class));
            }

            QueryWrapper returnQueryWrapper = new QueryWrapper();
            //returnQueryWrapper.eq("order_detail_no",item.getStudyEnrollNo());
            returnQueryWrapper.eq("student_id",item.getStudentId());
            returnQueryWrapper.orderByDesc("create_time");
            returnQueryWrapper.last("limit 1");
            ServiceReturnVisitHistoryEntity serviceReturnVisitHistory  = serviceReturnVisitHistoryService.getOne(returnQueryWrapper);
            if (serviceReturnVisitHistory != null){
                item.setReturnVisitTime(serviceReturnVisitHistory.getReturnVisitTime());
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(serviceReturnVisitHistory.getServiceId());
                if (serviceInfo != null)item.setReturnVisitServiceName(serviceInfo.getRealName());
                item.setReturnVisitContent(serviceReturnVisitHistory.getReturnVisitContent());
                item.setServiceReturnVisitHistory(BeanConvertUtils.copy(serviceReturnVisitHistory, ServiceReturnVisitHistoryVo.class));
            }



            QueryWrapper queryWrapperCount = new QueryWrapper();
            queryWrapperCount.eq("student_id",item.getStudentId());
            queryWrapperCount.eq("status", StudyEnrollEnum.CANCEL_ORDER.getCode());
            int cancelNum= studentOrderService.count(queryWrapperCount);
            //studentOrderVos.stream().mapToDouble(StudentStudyEnrollVo::getOrderStatus).sum()
            // ????????????
            //IntSummaryStatistics sumcc = studentOrderVos.stream().collect(Collectors.summarizingInt(e->Integer.valueOf(String.valueOf(e.getOrderStatus()=="5"))));
            // ??????????????????
            item.setCancelNum(cancelNum);

            if (StrUtil.isNotEmpty(item.getStudentId())){
                QueryWrapper returnVisitHistoryQueryWrapper = new QueryWrapper();
                returnVisitHistoryQueryWrapper.eq("student_id",item.getStudentId());
                int countReturnVisitHistory =serviceReturnVisitHistoryService.count(returnVisitHistoryQueryWrapper);
                if (countReturnVisitHistory > 0){
                    item.setReturnVisitHistory(true);
                }
            }
        });

        return R.success(studentStudyEnrollVoPage);
    }

    @Override
    public ResObject getStudentStudyEnrollInfo(StudentStudyEnrollPageQueryParam studentStudyEnrollPageQueryParam) {
        log.info(this.getClass() + "getStudentStudyEnrollInfo-????????????{}",studentStudyEnrollPageQueryParam);
        if (StrUtil.isEmpty(studentStudyEnrollPageQueryParam.getStudentId())){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyEnrollMapStruct, studentStudyEnrollPageQueryParam);
        // ?????????
        String[] arr = new String[]
                {
                        EnrollStatusEnum.RELATION_WAIT_PAY.getCode(),
                        EnrollStatusEnum.PAY_WAIT_PUT.getCode(),
                        EnrollStatusEnum.ENROLL_SUCCESS.getCode(),
                        EnrollStatusEnum.AUTO_ENROLL_SUCCESS.getCode(),
                        EnrollStatusEnum.PUT_WAIT_AUDIT.getCode(),
                        EnrollStatusEnum.PASSWORD_SUBMIT_WAIT_AUDIT.getCode()
                };
        queryWrapper.in("enroll_status",arr);
        StudentStudyEnrollEntity studentStudy = studentStudyEnrollService.getOne(queryWrapper);
        if (studentStudy == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudy);
        }
        StudentStudyEnrollVo studentStudyEnrollVo = BeanConvertUtils.copy(studentStudy, StudentStudyEnrollVo.class);
        // ????????????
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getUserId()))studentStudyEnrollVo.setOnlineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getLineUnderUserId()))studentStudyEnrollVo.setLineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getLineUnderUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getDriveSchoolId()))studentStudyEnrollVo.setLineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getLineUnderUserId()).getRealName());
        // ?????????
        log.info(this.getClass() + "getStudentStudyEnrollInfo-????????????{}",studentStudy);
        return R.success(studentStudyEnrollVo);
    }

    @Override
    public ResObject completeStudyEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        log.info(this.getClass() + "completeStudyEnroll-??????????????????{}",studentStudyEnrollEditParam);
        if (studentStudyEnrollEditParam == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        String strategyValue = StudyEnrollEnum.getStrategyValueByCode(studentStudyEnrollEditParam.getEnrollStatus());
        StudyEnrollStrategy studyEnrollStrategy = SpringContextUtil.getBean(strategyValue,StudyEnrollStrategy.class);
        return studyEnrollStrategy.completeStudyEnroll(studentStudyEnrollEditParam);
    }

    @Override
    public ResObject stayPayChangePageList(@Valid StudentStudyEnrollPageQueryParam param) throws BizException {
        log.info(this.getClass() + "stayPayChangePageList-??????????????????{}",param);
        if (param == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<StudentStudyEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        List<ServiceInfoEntity> serviceInfoList = null;
        QueryWrapper serviceQueryWrapper = new QueryWrapper();
        if (StrUtil.isNotEmpty(param.getVaguePreSalesServiceNameSearch())){
            serviceQueryWrapper.like("real_name",param.getVaguePreSalesServiceNameSearch());
            serviceInfoList  = serviceInfoService.list(serviceQueryWrapper);
            if (serviceInfoList.size()<=0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),new Page<>());
        }
        if (serviceInfoList != null && serviceInfoList.size() > 0){
            queryWrapper.in("t2.pre_sales_service_id",serviceInfoList.stream().map(ServiceInfoEntity::getId).collect(Collectors.toList()));
        }
        // ??????????????????
        List<ServiceInfoEntity> onlineServiceInfoList = null;
        QueryWrapper onlineServiceQueryWrapper = new QueryWrapper();
        if (StrUtil.isNotEmpty(param.getVagueOnlineServiceNameSearch())){
            onlineServiceQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueOnlineServiceNameSearch()),"real_name",param.getVagueOnlineServiceNameSearch());
            onlineServiceInfoList  = serviceInfoService.list(onlineServiceQueryWrapper);
            if (onlineServiceInfoList.size()<=0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),new Page<>());
        }
        if (onlineServiceInfoList != null && onlineServiceInfoList.size() > 0){
            queryWrapper.in("t2.user_id",onlineServiceInfoList.stream().map(ServiceInfoEntity::getId).collect(Collectors.toList()));
        }
        // ??????????????????
        if (StrUtil.isNotEmpty(param.getVagueServiceNameSearch())){
            List<ServiceInfoEntity> serviceLineInfoList = null;
            QueryWrapper serviceNameQueryWrapper = new QueryWrapper();
            serviceNameQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueServiceNameSearch()),"real_name",param.getVagueServiceNameSearch());
            serviceLineInfoList  = serviceInfoService.list(serviceNameQueryWrapper);
            if (serviceLineInfoList.size()<=0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),new Page<>());
            queryWrapper.in("t2.user_id",serviceLineInfoList.stream().map(ServiceInfoEntity::getId).collect(Collectors.toList()));
        }

        queryWrapper.eq(StrUtil.isNotEmpty(param.getOrderStatusSearch()),"t1.status",param.getOrderStatusSearch());
        // ?????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"t1.operator_id",param.getOperatorId());
        // ????????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getConversionType()),"t2.conversion_type",param.getConversionType());
        //
        queryWrapper.eq(StrUtil.isNotEmpty(param.getLineUnderUserId()),"t2.line_under_user_id",param.getLineUnderUserId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"t2.user_id",param.getUserId());
        // ???????????? ????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"t2.study_enroll_no",param.getVagueStudyEnrollNoSearch());
        // ???????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueOrderNoSearch()),"t1.order_no",param.getVagueOrderNoSearch());
        // ????????????????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"t2.real_name",param.getVagueRealNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"t2.telephone",param.getVaguePhoneSearch());
        // ??????????????????
        queryWrapper.apply(StrUtil.isNotBlank(param.getBeSpeakMeetTimeSearch()),
                "date_format (t2.be_speak_meet_time,'%Y-%m-%d') = date_format('" + param.getBeSpeakMeetTimeSearch() + "','%Y-%m-%d')");
        // ??????????????????
        queryWrapper.apply(StrUtil.isNotBlank(param.getIntentEnrollTimeSearch()),
                "date_format (t2.intent_enroll_time,'%Y-%m-%d') = date_format('" + param.getIntentEnrollTimeSearch() + "','%Y-%m-%d')");

        // ????????????
        if (param.getOrderStatusArr().length > 0){
            queryWrapper.in("t2.enroll_status",param.getOrderStatusArr());
        }

        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"t1.create_time",param.getBeginTime(),param.getEndTime());
        }
        if (StrUtil.isNotEmpty(param.getPayTimeSearch())){
            String[] arr = param.getPayTimeSearch().split(",");
            queryWrapper.between("t1.pay_time",arr[0],arr[1]);
        }
       /* if (param.getPayTimeArr() != null && param.getPayTimeArr().length > 0){
            queryWrapper.between("date_format (t1.pay_time,'%Y-%m-%d')",param.getPayTimeArr()[0],param.getPayTimeArr()[1]);
        }*/
        // ????????????
        queryWrapper.orderByDesc("t1.create_time");
        IPage<StudentStudyEnrollVo> pageList = studentStudyEnrollService.studyEnrollPageList(page,queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error(this.getClass() +"?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // ??????
        pageList.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId())){
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getUserId());
                log.info("????????????{}",serviceInfo);
                if (serviceInfo != null)item.setOnlineServiceName(serviceInfo.getRealName());
            }
            if (StrUtil.isNotEmpty(item.getLineUnderUserId())){
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getLineUnderUserId());
                log.info("????????????{}",serviceInfo);
                if (serviceInfo != null)item.setLineServiceName(serviceInfo.getRealName());
            }
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // ????????????
            StudentOrderVo studentOrderVo = new StudentOrderVo();
            studentOrderVo.setOrderNo(item.getStudentOrderNo());
            studentOrderVo.setStatus(item.getOrderStatus());
            //studentOrderVo.setPayTime(DateUtil.parseTime(item.getPayTime()));
            item.setStudentOrderVo(studentOrderVo);
            item.setClassName(AdminCacheUtil.getClassName(item.getClassId()));
            // ?????????
            QueryWrapper nextServiceQueryWrapper = new QueryWrapper();
            //serviceQueryWrapper.eq("order_detail_no",item.getStudyEnrollNo());
            nextServiceQueryWrapper.eq("student_id",item.getStudentId());
            nextServiceQueryWrapper.orderByDesc("create_time");
            nextServiceQueryWrapper.last("limit 1");
            ServiceReturnVisitHistoryEntity serviceReturnVisitHistoryEntity  = serviceReturnVisitHistoryService.getOne(nextServiceQueryWrapper);
            if (serviceReturnVisitHistoryEntity != null){
                item.setReturnVisitTime(serviceReturnVisitHistoryEntity.getReturnVisitTime());
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(serviceReturnVisitHistoryEntity.getServiceId());
                if (serviceInfo != null)item.setReturnVisitServiceName(serviceInfo.getRealName());
                item.setReturnVisitContent(serviceReturnVisitHistoryEntity.getReturnVisitContent());
                item.setServiceReturnVisitHistory(BeanConvertUtils.copy(serviceReturnVisitHistoryEntity, ServiceReturnVisitHistoryVo.class));
            }
            // ??????????????????
            QueryWrapper startReturnQueryWrapper = new QueryWrapper();
            //returnQueryWrapper.eq("order_detail_no",item.getStudyEnrollNo());
            startReturnQueryWrapper.eq("student_id",item.getStudentId());
            startReturnQueryWrapper.orderByAsc("create_time");
            startReturnQueryWrapper.last("limit 1");
            ServiceReturnVisitHistoryEntity startServiceReturnVisitHistory  = serviceReturnVisitHistoryService.getOne(startReturnQueryWrapper);
            if (startServiceReturnVisitHistory != null){
                item.setStartReturnVisitTime(startServiceReturnVisitHistory.getReturnVisitTime());
            }

            QueryWrapper queryWrapperCount = new QueryWrapper();
            queryWrapperCount.eq("student_id",item.getStudentId());
            queryWrapperCount.eq("status", StudyEnrollEnum.CANCEL_ORDER.getCode());
            int cancelNum= studentOrderService.count(queryWrapperCount);
            //studentOrderVos.stream().mapToDouble(StudentStudyEnrollVo::getOrderStatus).sum()
            // ????????????
            //IntSummaryStatistics sumcc = studentOrderVos.stream().collect(Collectors.summarizingInt(e->Integer.valueOf(String.valueOf(e.getOrderStatus()=="5"))));
            // ??????????????????
            item.setCancelNum(cancelNum);
        });
        return R.success(pageList);
    }

    @Override
    public ResObject statisticsStudentDataPageList(@Valid StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "statisticsStudentData-??????????????????{}",param);
        Page<StudentStudyEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());

       /* if (StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch())){
            return serviceReturnVisitHistoryRepository.pageListReturnVisitHistory(param);
        }*/
        QueryWrapper queryWrapper = new QueryWrapper();
        // ???????????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"t3.operator_id",param.getOperatorId());
        // ??????ID
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getStudentId()),"t1.student_id",param.getStudentId());
        // ?????????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getRecommendUserId()),"t3.recommend_user_id",param.getRecommendUserId());

        // ????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"t3.phone",param.getVaguePhoneSearch());
        // ?????? ?????? ????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"t3.real_name",param.getVagueRealNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePromoterName()),"t3.real_name",param.getVaguePromoterName());
        if (StrUtil.isNotEmpty(param.getSubjectType()) && StrUtil.isNotEmpty(param.getEnrollStatus())){
            queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t3.id = t_student_test_enroll.student_id AND t_student_test_enroll.subject_type="+param.getSubjectType()+" AND t_student_test_enroll.enroll_status = "+param.getEnrollStatus()+")",0);
        }
        queryWrapper.orderByDesc("t3.create_time");
        IPage<StatisticsStudentDataVo> pageList = studentStudyEnrollService.newStatisticsStudentDataPageList(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????-------------");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }

        // ???????????????
        pageList.getRecords().stream().forEach((item) ->{
            String[] statusArr =
            {
                EnrollStatusEnum.RELATION_WAIT_PAY.getCode(),
                EnrollStatusEnum.PAY_WAIT_PUT.getCode(),
                EnrollStatusEnum.ENROLL_SUCCESS.getCode(),
                EnrollStatusEnum.AUTO_ENROLL_SUCCESS.getCode(),
                EnrollStatusEnum.AUTO_ENROLL_WAIT_AUDIT.getCode(),
                EnrollStatusEnum.PUT_WAIT_AUDIT.getCode(),
                EnrollStatusEnum.PASSWORD_SUBMIT_WAIT_AUDIT.getCode(),
                EnrollStatusEnum.REFUND_LOADING.getCode(),
                EnrollStatusEnum.UPGRADE.getCode(),
            };
            QueryWrapper studentStudyEnrollQueryWrapper = new QueryWrapper();
            // ??????id
            studentStudyEnrollQueryWrapper.eq("student_id",item.getStudentId());
            studentStudyEnrollQueryWrapper.in("enroll_status",statusArr);
            studentStudyEnrollQueryWrapper.last("limit 1");
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studentStudyEnrollQueryWrapper);
            if (studentStudyEnroll != null){
                item.setOnLineServiceId(studentStudyEnroll.getUserId());
                item.setStudyEnrollNo(studentStudyEnroll.getStudyEnrollNo());
                item.setApplyAmount(studentStudyEnroll.getPrice());
                item.setApplySchoolId(studentStudyEnroll.getDriveSchoolId());
                item.setAddress(studentStudyEnroll.getAddress());
                item.setEnrollStatus(studentStudyEnroll.getEnrollStatus());
            }
            // ???????????????
            QueryWrapper subjectOneQueryWrapper = new QueryWrapper();
            subjectOneQueryWrapper.eq("subject_type", SubjectTypeEnum.SUBJECT_ONE.getCode());
            subjectOneQueryWrapper.eq("student_id",item.getStudentId());
            subjectOneQueryWrapper.orderByDesc("create_time");
            subjectOneQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(subjectOneQueryWrapper);
            if (studentTestEnroll != null){
                item.setSubjectOneStatus(studentTestEnroll.getEnrollStatus());
                item.setSubjectOneStatusName(ExamEnrollEnum.getNameByCode(studentTestEnroll.getEnrollStatus()));
                item.setSubjectOneExamTime(studentTestEnroll.getTestActualTime());
            }

            // ???????????????
            QueryWrapper subjectTwoQueryWrapper = new QueryWrapper();
            subjectTwoQueryWrapper.eq("subject_type", SubjectTypeEnum.SUBJECT_TWO.getCode());
            subjectTwoQueryWrapper.eq("student_id",item.getStudentId());
            subjectTwoQueryWrapper.orderByDesc("create_time");
            subjectTwoQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnrollTwo = studentTestEnrollService.getOne(subjectTwoQueryWrapper);
            if (studentTestEnrollTwo != null){
                item.setSubjectTwoStatus(studentTestEnrollTwo.getEnrollStatus());
                item.setSubjectTwoStatusName(ExamEnrollEnum.getNameByCode(studentTestEnrollTwo.getEnrollStatus()));
                item.setSubjectTwoExamTime(studentTestEnrollTwo.getTestActualTime());
            }

            // ???????????????
            QueryWrapper subjectThreeQueryWrapper = new QueryWrapper();
            subjectThreeQueryWrapper.eq("subject_type", SubjectTypeEnum.SUBJECT_THREE.getCode());
            subjectThreeQueryWrapper.eq("student_id",item.getStudentId());
            subjectThreeQueryWrapper.orderByDesc("create_time");
            subjectThreeQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnrollThree = studentTestEnrollService.getOne(subjectThreeQueryWrapper);
            if (studentTestEnrollThree != null){
                item.setSubjectThreeStatus(studentTestEnrollThree.getEnrollStatus());
                item.setSubjectThreeStatusName(ExamEnrollEnum.getNameByCode(studentTestEnrollThree.getEnrollStatus()));
                item.setSubjectThreeExamTime(studentTestEnrollThree.getTestActualTime());
            }

            // ???????????????
            QueryWrapper subjectFourQueryWrapper = new QueryWrapper();
            subjectFourQueryWrapper.eq("subject_type", SubjectTypeEnum.SUBJECT_FOUR.getCode());
            subjectFourQueryWrapper.eq("student_id",item.getStudentId());
            subjectFourQueryWrapper.orderByDesc("create_time");
            subjectFourQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnrollFour = studentTestEnrollService.getOne(subjectFourQueryWrapper);
            if (studentTestEnrollFour != null){
                item.setSubjectFourStatus(studentTestEnrollFour.getEnrollStatus());
                item.setSubjectFourStatusName(ExamEnrollEnum.getNameByCode(studentTestEnrollFour.getEnrollStatus()));
                item.setSubjectFourExamTime(studentTestEnrollFour.getTestActualTime());
            }
            // ????????????
            Integer subjectTwoClassHour = studentTrainCarApplyService.classHoursSum(item.getStudentId(),SubjectTypeEnum.SUBJECT_TWO.getCode());
            if (subjectTwoClassHour != null)item.setSubjectTwoHour(subjectTwoClassHour);
            Integer subjectThreeClassHour = studentTrainCarApplyService.classHoursSum(item.getStudentId(),SubjectTypeEnum.SUBJECT_TWO.getCode());
            if (subjectThreeClassHour != null)item.setSubjectThreeHour(subjectThreeClassHour);

            //
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
            ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getOnLineServiceId());
            if (serviceInfo != null)item.setOnLineService(serviceInfo.getRealName());
            // ?????????
            if (StrUtil.isNotEmpty(item.getReferrerId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getReferrerId());
                if (studentInfo != null)item.setReferrerName(studentInfo.getRealName());
            }

            // ??????
            if (StrUtil.isNotEmpty(item.getApplySchoolId())){
                DriveSchoolEntity driveSchool = driveSchoolService.getById(item.getApplySchoolId());
                if (driveSchool != null)item.setDriveSchoolName(driveSchool.getSchoolName());
            }

            // ??????????????????
            QueryWrapper orderQueryWrapper = new QueryWrapper();
            // ??????ID
            orderQueryWrapper.eq("student_id",item.getStudentId());
            // ????????????
            orderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder = studentOrderService.getOne(orderQueryWrapper);
            if (studentOrder == null){
                item.setStudentType("?????????");
            }
            String[] arr = {
                    EnrollStatusEnum.PAY_WAIT_PUT.getCode(),
                    EnrollStatusEnum.ENROLL_SUCCESS.getCode(),
                    EnrollStatusEnum.REFUND.getCode(),
                    EnrollStatusEnum.AUTO_ENROLL_SUCCESS.getCode(),
                    EnrollStatusEnum.AUTO_ENROLL_WAIT_AUDIT.getCode(),
                    EnrollStatusEnum.PUT_WAIT_AUDIT.getCode(),
                    EnrollStatusEnum.PASSWORD_SUBMIT_WAIT_AUDIT.getCode(),
                    EnrollStatusEnum.REFUND_LOADING.getCode(),
                    EnrollStatusEnum.UPGRADE.getCode(),
                    EnrollStatusEnum.UPGRADE_WAIT_PAY.getCode(),
            };
            if (StrUtil.isNotEmpty(item.getEnrollStatus())){
                int isIndex = item.getEnrollStatus().indexOf(StringUtils.join(arr, ","));
                if (studentOrder != null && isIndex== -1){
                    item.setStudentType("??????");
                }else{
                    item.setStudentType("?????????");
                }
            }

        });

        return R.success(pageList);
    }

    @Override
    public ResObject drivingStudentDataPageList(@Valid StudentStudyEnrollPageQueryParam param) {
        log.info("drivingStudentDataPageList-??????????????????{}",param);
        /**
         * ??????????????????-????????????????????????????????????????????????  ??????????????????????????????
         *
         * ?????????
         * 1.?????????????????????
         * 2.?????????????????????.???????????? ????????????????????????????????????
         */
        Page<CoachTeachTimeEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        // ??????????????????
        queryWrapper.eq("tsse.enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
        List<StudentInfoEntity> studentInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(param.getVaguePhoneSearch()) ||
                StrUtil.isNotEmpty(param.getVagueRealNameSearch())){
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            // ?????????
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
            // ????????????
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());

            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
        }
        // ??????ID
        queryWrapper.in(studentInfoList.size() > 0,"tsse.student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));
        queryWrapper.orderByDesc("tsse.create_time");
        IPage<StudyCarScheduleVo> pageList =  studentStudyEnrollService.studyCarSchedulePageList(page,queryWrapper);
        return R.success(pageList);



//        Page<CoachTeachTimeEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
//        QueryWrapper queryWrapper = new QueryWrapper();
        // ???????????????
//        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"operator_id",param.getOperatorId());
//        // ??????ID
//        queryWrapper.eq(StrUtil.isNotEmpty(param.getStudentId()),"student_id",param.getStudentId());
//        //
//        queryWrapper.eq(StrUtil.isNotEmpty(param.getClassType()),"class_type",param.getClassType());
//        if (StrUtil.isNotEmpty(param.getDrivingStatus())){
//            queryWrapper.eq("status",param.getDrivingStatus());
//        }else{
//            //
//            String[] arr = {
//                    StudyEnrollEnum.YET_APPOINTMENT.getCode(),
//                    StudyEnrollEnum.TEACHING_LOADING.getCode(),
//                    StudyEnrollEnum.TEACHING_SUCCESS.getCode(),
//                    StudyEnrollEnum.PICK_SOMEBODY_UP.getCode(),
//                    StudyEnrollEnum.YET_GET_ON.getCode(),
//            };
//            queryWrapper.in("status",arr);
//        }
//        // ????????????
//        queryWrapper.eq(StrUtil.isNotEmpty(param.getSubjectType()),"subject_type",param.getSubjectType());
//
//        if (StrUtil.isNotEmpty(param.getSubjectType()) && StrUtil.isNotEmpty(param.getAboutStatus())){
//            queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t_coach_teach_time.student_id = t_student_test_enroll.student_id AND t_student_test_enroll.subject_type="+param.getSubjectType()+" AND t_student_test_enroll.enroll_status = "+param.getAboutStatus()+")",0);
//        }
//        queryWrapper.groupBy("student_id,subject_type");
//        queryWrapper.orderByDesc("create_time");
//        IPage<CoachTeachTimeEntity> pageList = coachTeachTimeService.page(page,queryWrapper);
//        Page<CoachTeachTimeVo> coachTeachTimeVoPage = coachTeachTimeMapStruct.toVoList(pageList);
//        if (coachTeachTimeVoPage.getRecords().size() <= 0 ){
//            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),coachTeachTimeVoPage);
//        }
//        // ????????????
//        coachTeachTimeVoPage.getRecords().stream().forEach((item) ->{
//            // ??????
//            StudentInfoEntity student  = studentInfoService.getById(item.getStudentId());
//            if (student != null){
//                item.setStudentVo(BeanConvertUtils.copy(student, StudentInfoVo.class));
//            }
//
//            String[] arrStatus = {
//                    EnrollStatusEnum.ENROLL_SUCCESS.getCode(),
//                    EnrollStatusEnum.AUTO_ENROLL_SUCCESS.getCode(),
//                    EnrollStatusEnum.AUTO_ENROLL_WAIT_AUDIT.getCode(),
//            };
//            // ??????
//            QueryWrapper studentStudyQueryWrapper = new QueryWrapper();
//            // ????????????
//            studentStudyQueryWrapper.in("enroll_status",arrStatus);
//            // ??????ID
//            studentStudyQueryWrapper.eq("student_id",item.getStudentId());
//            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studentStudyQueryWrapper);
//            if (studentStudyEnroll != null){
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(studentStudyEnroll.getUserId()).getRealName());
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(studentStudyEnroll.getLineUnderUserId()).getRealName());
//
//                // ?????????  ???????????????
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getProvinceId()))item.setProvinceName(areaService.getByBaCode(studentStudyEnroll.getProvinceId()).getBaName());
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getCityId()))item.setCityName(areaService.getByBaCode(studentStudyEnroll.getCityId()).getBaName());
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getAreaId()))item.setAreaName(areaService.getByBaCode(studentStudyEnroll.getAreaId()).getBaName());
//            }
//
//            // ????????????
//            CoachInfoEntity coachInfo = coachInfoService.getById(item.getCoachId());
//            if (coachInfo != null) {
//                item.setCoachName(coachInfo.getRealName());
//            }
//
//            // ????????????
//            Integer subjectTwoClassHour = studentTrainCarApplyService.classHoursSum(item.getStudentId(),item.getSubjectType());
//            if (subjectTwoClassHour != null)item.setTotalHour(subjectTwoClassHour);
//
//            // ??????
//            QueryWrapper testQueryWrapper = new QueryWrapper();
//            //testQueryWrapper.eq(studentStudyEnroll != null,"order_detail_no",studentStudyEnroll.getStudyEnrollNo());
//            testQueryWrapper.eq("student_id",item.getStudentId());
//            testQueryWrapper.eq(StrUtil.isNotEmpty(param.getAboutStatus()),"enroll_status",param.getAboutStatus());
//            testQueryWrapper.orderByDesc("create_time");
//            testQueryWrapper.last("limit 1");
//            StudentTestEnrollEntity studentTestEnroll = studentTestEnrollService.getOne(testQueryWrapper);
//            if (studentTestEnroll != null){
//                item.setStudentTestEnrollVo(BeanConvertUtils.copy(studentTestEnroll,StudentTestEnrollVo.class));
//            }
//        });
//        return R.success();
    }

    @Override
    public ResObject drillStudentDataPageList(@Valid StudentOrderPageQueryParam param) {
        log.info("drivingStudentDataPageList-??????????????????{}",param);
        Page<StudentOrderEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        List<StudentInfoEntity> studentInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(param.getVaguePhoneSearch()) || StrUtil.isNotEmpty(param.getVagueRealNameSearch())){
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            // ?????????
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
            // ????????????
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());

            studentInfoList = studentInfoService.list(studentQueryWrapper);
            if(studentInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoList);
        }
        // ??????ID
        queryWrapper.in(studentInfoList.size() > 0,"student_id",studentInfoList.stream().map(StudentInfoEntity::getId).collect(Collectors.toList()));
        // ???????????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"operator_id",param.getOperatorId());
        // ??????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStudentId()),"student_id",param.getStudentId());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueOrderNoSearch()),"order_no",param.getVagueOrderNoSearch());
        //
        // ????????????
        queryWrapper.in("order_type",SubjectTypeEnum.SUBJECT_THREE.getCode(),SubjectTypeEnum.SUBJECT_FOUR.getCode());

        queryWrapper.orderByDesc("create_time");
        IPage<StudentOrderEntity> pageList = studentOrderService.page(page,queryWrapper);
        Page<StudentOrderVo> studentOrderVoPage = studentOrderMapStruct.toVoList(pageList);
        if (studentOrderVoPage.getRecords().size() <= 0 ){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentOrderVoPage);
        }
        // ????????????
        studentOrderVoPage.getRecords().stream().forEach((item) ->{
            // ??????
            StudentInfoEntity student  = studentInfoService.getById(item.getStudentId());
            if (student != null){
                item.setStudentVo(BeanConvertUtils.copy(student, StudentInfoVo.class));
            }
            // ??????
            QueryWrapper studentStudyQueryWrapper = new QueryWrapper();
            // ????????????
            studentStudyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            // ??????ID
            studentStudyQueryWrapper.eq("student_id",item.getStudentId());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studentStudyQueryWrapper);
            if (studentStudyEnroll != null){
                if (StrUtil.isNotEmpty(studentStudyEnroll.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(studentStudyEnroll.getUserId()).getRealName());
                if (StrUtil.isNotEmpty(studentStudyEnroll.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(studentStudyEnroll.getLineUnderUserId()).getRealName());
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
                // ?????????  ???????????????
            }
            // ????????????
            StudentTrainCarApplyEntity studentTrainCarApply = studentTrainCarApplyService.getById(item.getTrainApplyNo());
            if (studentTrainCarApply != null){
                item.setStudentTrainCarApplyVo(BeanConvertUtils.copy(studentTrainCarApply,StudentTrainCarApplyVo.class));
                String coachName = AdminCacheUtil.getCoachName(studentTrainCarApply.getCoachId());
                if(StrUtil.isNotEmpty(coachName))item.setCoachName(coachName);
            }
        });
        return R.success(studentOrderVoPage);
    }
}

