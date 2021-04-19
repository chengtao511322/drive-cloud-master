package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.DateUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * 学员学车报名单 服务类
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

    // 客服
    @Autowired
    private ServiceInfoService serviceInfoService;

    //  平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 服务
    @Autowired
    private DriveSchoolService driveSchoolService;

    @Autowired
    private AreaService areaService;

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

    private  final Jedis jedis = RedisDS.create().getJedis();

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




    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 学员学车报名单 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param StudentStudyEnrollPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentStudyEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());

       /* if (StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch())){
            return serviceReturnVisitHistoryRepository.pageListReturnVisitHistory(param);
        }*/

        // 订单

        List<StudentOrderEntity> studentOrderList = null;
        QueryWrapper orderQueryWrapper = new QueryWrapper();
        if (StrUtil.isNotEmpty(param.getStudentOrderNo())){
            orderQueryWrapper.like("order_no",param.getStudentOrderNo());
            studentOrderList  = studentOrderService.list(orderQueryWrapper);
        }

        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        // 报名单号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        // 真实姓名模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());

        if (param.getIsReturnVisit()!= null && param.getIsReturnVisit() == 1){
            queryWrapper.gt("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id)",0);
        }
        if (param.getIsReturnVisit()!= null && param.getIsReturnVisit() == 0){
            queryWrapper.eq("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id)",0);
        }

        if (StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch())){
            queryWrapper.gt("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t_service_return_visit_history.student_id = t_student_study_enroll.student_id AND date_format(t_service_return_visit_history.next_return_visit_time,'%Y-%m-%d')= DATE_FORMAT('"+param.getNextReturnVisitTimeSearch()+"','%Y-%m-%d'))",0);
        }
        // 预约见面时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getBeSpeakMeetTimeSearch()),
        "date_format (be_speak_meet_time,'%Y-%m-%d') = date_format('" + param.getBeSpeakMeetTimeSearch() + "','%Y-%m-%d')");
        // 意向报名时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getIntentEnrollTimeSearch()),
        "date_format (intent_enroll_time,'%Y-%m-%d') = date_format('" + param.getIntentEnrollTimeSearch() + "','%Y-%m-%d')");

        if (studentOrderList != null && studentOrderList.size() > 0){
            queryWrapper.in("study_enroll_no",studentOrderList.stream().map(StudentOrderEntity::getStudyEnrollNo).collect(Collectors.toList()));
        }
        // 报名状态
        if (StrUtil.isNotEmpty(param.getEnrollStatusArr())){
            queryWrapper.in("enroll_status",param.getEnrollStatusArr().split(","));
        }

        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        if (StrUtil.isNotEmpty(param.getCreateTimeSearch())){
            String[] arr = param.getCreateTimeSearch().split(",");
            queryWrapper.between("create_time",arr[0],arr[1]);
        }

        IPage<StudentStudyEnrollEntity> pageList = studentStudyEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空-------------");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // 是否全部匹配,还是 局部包含匹配
        Boolean globalMatch = Boolean.FALSE;
        Page<StudentStudyEnrollVo> studentStudyEnrollVoPage = studentStudyEnrollMapStruct.toVoList(pageList);
        studentStudyEnrollVoPage.getRecords().forEach((item) ->{
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
            // 订单
            QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
            studentOrderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
            if (studentOrder != null){
                String classRes = jedis.get(CacheConstants.REDIS_CACHE_CLASS_KEY+studentOrder.getProductId());
                JSONObject jsonObject = (JSONObject) JSONObject.parse(classRes);
                if (jsonObject != null)item.setClassName(jsonObject.getString("name"));
                item.setStudentOrderVo(BeanConvertUtils.copy(studentOrder, StudentOrderVo.class));
            }

            // 状态是3
            if (item.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_PAY_SUCCESS.getCode())){
                // examine
                long examineDay= DateUtil.between(new Date(),DateUtils.asDate(studentOrder.getPayTime()), DateUnit.DAY);//两个时间间隔几
                if (examineDay >= 1) {
                    item.setExamine(true);
                }
            }
            //12 13 >=1
            Boolean prepareStayExamine =item.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_PREPARE_STAY_EXAMINE.getCode());
            Boolean passwordExamine =item.getEnrollStatus().equals(StudyEnrollEnum.ENROLL_STATUS_PASSWORD_EXAMINE.getCode());
            if (prepareStayExamine || passwordExamine){
                // examine
                long examineDay= DateUtil.between(new Date(), DateUtils.asDate(item.getUpdateTime()), DateUnit.DAY);//两个时间间隔几
                if (examineDay >= 7) {
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
                log.info("教练绑定数据{}",feeSystemCoachStudent);
                String cacheRes = jedis.get(CacheConstants.REDIS_CACHE_COACH_KEY+feeSystemCoachStudent.getCoachId());
                JSONObject jsonObject = (JSONObject) JSONObject.parse(cacheRes);
                if (jsonObject != null)item.setCoachName(jsonObject.getString("realName"));
            }
            // 学员
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
            // 取消次数
            //IntSummaryStatistics sumcc = studentOrderVos.stream().collect(Collectors.summarizingInt(e->Integer.valueOf(String.valueOf(e.getOrderStatus()=="5"))));
            // 取消订单次数
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
        // 订单号 模糊查询
   /*     if (StrUtil.isNotEmpty(param.getStudentOrderNo())){
            studentStudyEnrollVoPage.getRecords().stream().filter(userz -> globalMatch?userz.getStudentOrderVo().getOrderNo().equals(param.getStudentOrderNo()):
                    userz.getStudentOrderVo().getOrderNo().contains(param.getStudentOrderNo())).
                    collect(Collectors.toList());
        }*/
        // 真实姓名 模糊查询
       /* if (StrUtil.isNotEmpty(param.getVagueRealNameSearch())){
            studentStudyEnrollVoPage.getRecords().stream().filter(userz -> globalMatch?userz.getStudentVo().getRealName().equals(param.getVagueRealNameSearch()):
                    userz.getStudentVo().getRealName().contains(param.getVagueRealNameSearch())).
                    collect(Collectors.toList());
        }*/
        if(param.isReturnVisitHistory()){
            List<StudentStudyEnrollVo> studentStudyEnrollVoList = studentStudyEnrollVoPage.getRecords().stream().filter((StudentStudyEnrollVo student)->student.isReturnVisitHistory() == param.isReturnVisitHistory()) //筛选出大于150的
                    .collect(Collectors.toList());
            // 根据条件查询回访
            studentStudyEnrollVoPage.setRecords(studentStudyEnrollVoList);
            log.info(this.getClass() + "pageList-方法请求结果{}",studentStudyEnrollVoPage);
            studentStudyEnrollVoPage.setTotal(studentStudyEnrollVoList.size());
            return R.success(studentStudyEnrollVoPage);
        }
        return R.success(studentStudyEnrollVoPage);
    }

    @Override
    public ResObject findList(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentStudyEnrollEntity> pageList = studentStudyEnrollService.list(queryWrapper);
        List<StudentStudyEnrollVo> studentStudyEnrollVoList = studentStudyEnrollMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentStudyEnrollVoList);
        if (studentStudyEnrollVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(studentStudyEnrollVoList);
    }


    @Override
    public ResObject getInfo(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        queryWrapper.in(param.getOrderStatusArr().length > 0,"enroll_status",param.getOrderStatusArr());
        StudentStudyEnrollEntity studentStudyEnrollEntity = studentStudyEnrollService.getOne(queryWrapper);
        if (studentStudyEnrollEntity == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudyEnrollEntity);
        }
        StudentStudyEnrollVo studentStudyEnrollVo = BeanConvertUtils.copy(studentStudyEnrollEntity,StudentStudyEnrollVo.class);
        log.info(this.getClass() + "findList-方法请求结果{}",studentStudyEnrollVo);
        // 数据回显
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getUserId()))studentStudyEnrollVo.setOnlineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getLineUnderUserId()))studentStudyEnrollVo.setLineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getLineUnderUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getDriveSchoolId())){
            DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(studentStudyEnrollVo.getLineUnderUserId());
            if (driveSchoolEntity != null)studentStudyEnrollVo.setLineServiceName(driveSchoolEntity.getSchoolName());
        }
        // 省市区
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getProvinceId()))studentStudyEnrollVo.setProvinceName(areaService.getByBaCode(studentStudyEnrollVo.getProvinceId()).getBaName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getCityId()))studentStudyEnrollVo.setCityName(areaService.getByBaCode(studentStudyEnrollVo.getCityId()).getBaName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getAreaId()))studentStudyEnrollVo.setAreaName(areaService.getByBaCode(studentStudyEnrollVo.getAreaId()).getBaName());
        QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
        studentOrderQueryWrapper.eq("study_enroll_no",studentStudyEnrollVo.getStudyEnrollNo());
        StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
        if (studentOrder != null){
            String classRes = jedis.get(CacheConstants.REDIS_CACHE_CLASS_KEY+studentOrder.getProductId());
            JSONObject jsonObject = (JSONObject) JSONObject.parse(classRes);
            if (jsonObject != null)studentStudyEnrollVo.setClassName(jsonObject.getString("name"));
        }

        return R.success(studentStudyEnrollVo);
    }

    /**
     * *通过ID获取学员学车报名单 列表
     **/
    @Override
    public ResObject getById(String studyEnrollNo) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",studyEnrollNo);
        if (StrUtil.isEmpty(studyEnrollNo)){
            return R.failure("数据空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("study_enroll_no",studyEnrollNo);
        StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(queryWrapper);
        if (studentStudyEnroll == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        StudentStudyEnrollVo studentStudyEnrollVo = BeanConvertUtils.copy(studentStudyEnroll, StudentStudyEnrollVo.class);
        // 数据回显
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getUserId()))studentStudyEnrollVo.setOnlineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getLineUnderUserId()))studentStudyEnrollVo.setLineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getLineUnderUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getDriveSchoolId())){
            DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(studentStudyEnrollVo.getDriveSchoolId());
            if (driveSchoolEntity != null)studentStudyEnrollVo.setDriveSchoolName(driveSchoolEntity.getSchoolName());
        }
        // 省市区
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getProvinceId()))studentStudyEnrollVo.setProvinceName(areaService.getByBaCode(studentStudyEnrollVo.getProvinceId()).getBaName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getCityId()))studentStudyEnrollVo.setCityName(areaService.getByBaCode(studentStudyEnrollVo.getCityId()).getBaName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getAreaId()))studentStudyEnrollVo.setAreaName(areaService.getByBaCode(studentStudyEnrollVo.getAreaId()).getBaName());
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentStudyEnrollVo);
        return R.success(studentStudyEnrollVo);
    }

    /**
     * *保存学员学车报名单 信息
     **/
    @Override
    public ResObject save(StudentStudyEnrollEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentStudyEnrollEntity studentStudyEnroll = BeanConvertUtils.copy(installParam, StudentStudyEnrollEntity.class);
        Boolean result = studentStudyEnrollService.save(studentStudyEnroll);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改学员学车报名单 信息
     **/
    @Override
    public ResObject update(StudentStudyEnrollEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentStudyEnrollEntity studentStudyEnroll = BeanConvertUtils.copy(updateParam, StudentStudyEnrollEntity.class);
        Boolean result = studentStudyEnrollService.updateById(studentStudyEnroll);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除学员学车报名单 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(studentStudyEnrollService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员学车报名单 信息
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
        Boolean result = studentStudyEnrollService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出学员学车报名单 信息
     **/
    @Override
    public ResObject exportXls(StudentStudyEnrollPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        List<StudentStudyEnrollEntity> list = studentStudyEnrollService.list(queryWrapper);
        List<StudentStudyEnrollVo>studentStudyEnrollList = studentStudyEnrollMapStruct.toVoList(list);
        ExcelUtils.exportExcel(studentStudyEnrollList, StudentStudyEnrollVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(StudentStudyEnrollEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentStudyEnrollEntity StudentStudyEnrollEntity = new StudentStudyEnrollEntity();
        StudentStudyEnrollEntity.setStudyEnrollNo(param.getStudyEnrollNo());
        //StudentStudyEnrollEntity.setStatus(param.getStatus());
        //StudentStudyEnrollEntity.setUpdateTime()
        Boolean result = studentStudyEnrollService.updateById(StudentStudyEnrollEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",StudentStudyEnrollEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject studyEnrollPageList(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "studyEnrollPageList-方法请求参数{}",param);
        Page<StudentStudyEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());

        if (StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch())){
            return serviceReturnVisitHistoryRepository.pageListReturnVisitHistory(param);
        }

        // 订单

        List<StudentOrderEntity> studentOrderList = new ArrayList<>();
        QueryWrapper orderQueryWrapper = new QueryWrapper();
        if (StrUtil.isNotEmpty(param.getStudentOrderNo())){
            orderQueryWrapper.like("order_no",param.getStudentOrderNo());
            studentOrderList  = studentOrderService.list(orderQueryWrapper);
        }

        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyEnrollMapStruct, param);
        // 报名单号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        // 真实姓名模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
        // 预约见面时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getBeSpeakMeetTimeSearch()),
                "date_format (be_speak_meet_time,'%Y-%m-%d') = date_format('" + param.getBeSpeakMeetTimeSearch() + "','%Y-%m-%d')");
        // 意向报名时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getIntentEnrollTimeSearch()),
                "date_format (intent_enroll_time,'%Y-%m-%d') = date_format('" + param.getIntentEnrollTimeSearch() + "','%Y-%m-%d')");

        if (studentOrderList != null && studentOrderList.size() > 0){
            queryWrapper.in("study_enroll_no",studentOrderList.stream().map(StudentOrderEntity::getStudyEnrollNo).collect(Collectors.toList()));
        }
        // 报名状态
        if (StrUtil.isNotEmpty(param.getEnrollStatusArr())){
            queryWrapper.in("enroll_status",param.getEnrollStatusArr().split(","));
        }

        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        if (StrUtil.isNotEmpty(param.getCreateTimeSearch())){
            String[] arr = param.getCreateTimeSearch().split(",");
            queryWrapper.between("create_time",arr[0],arr[1]);
        }

        IPage<StudentStudyEnrollEntity> pageList = studentStudyEnrollService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空-------------");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // 是否全部匹配,还是 局部包含匹配
        Boolean globalMatch = Boolean.FALSE;
        Page<StudentStudyEnrollVo> studentStudyEnrollVoPage = studentStudyEnrollMapStruct.toVoList(pageList);
        studentStudyEnrollVoPage.getRecords().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // 省市区
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
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
            // 订单
            QueryWrapper studentOrderQueryWrapper = new QueryWrapper();
            studentOrderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder  = studentOrderService.getOne(studentOrderQueryWrapper);
            if (studentOrder != null){
                item.setStudentOrderVo(BeanConvertUtils.copy(studentOrder, StudentOrderVo.class));
            }

            // 学员
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
            // 取消次数
            //IntSummaryStatistics sumcc = studentOrderVos.stream().collect(Collectors.summarizingInt(e->Integer.valueOf(String.valueOf(e.getOrderStatus()=="5"))));
            // 取消订单次数
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
        log.info(this.getClass() + "getStudentStudyEnrollInfo-请求参数{}",studentStudyEnrollPageQueryParam);
        if (StrUtil.isEmpty(studentStudyEnrollPageQueryParam.getStudentId())){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper queryWrapper = this.getQueryWrapper(studentStudyEnrollMapStruct, studentStudyEnrollPageQueryParam);
        // 状态值
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
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentStudy);
        }
        StudentStudyEnrollVo studentStudyEnrollVo = BeanConvertUtils.copy(studentStudy, StudentStudyEnrollVo.class);
        // 数据回显
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getUserId()))studentStudyEnrollVo.setOnlineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getLineUnderUserId()))studentStudyEnrollVo.setLineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getLineUnderUserId()).getRealName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getDriveSchoolId()))studentStudyEnrollVo.setLineServiceName(serviceInfoService.getById(studentStudyEnrollVo.getLineUnderUserId()).getRealName());
        // 省市区
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getProvinceId()))studentStudyEnrollVo.setProvinceName(areaService.getByBaCode(studentStudyEnrollVo.getProvinceId()).getBaName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getCityId()))studentStudyEnrollVo.setCityName(areaService.getByBaCode(studentStudyEnrollVo.getCityId()).getBaName());
        if (StrUtil.isNotEmpty(studentStudyEnrollVo.getAreaId()))studentStudyEnrollVo.setAreaName(areaService.getByBaCode(studentStudyEnrollVo.getAreaId()).getBaName());
        log.info(this.getClass() + "getStudentStudyEnrollInfo-请求结果{}",studentStudy);
        return R.success(studentStudyEnrollVo);
    }

    @Override
    public ResObject completeStudyEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam) {
        log.info(this.getClass() + "completeStudyEnroll-方法请求参数{}",studentStudyEnrollEditParam);
        if (studentStudyEnrollEditParam == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        String strategyValue = StudyEnrollEnum.getStrategyValueByCode(studentStudyEnrollEditParam.getEnrollStatus());
        StudyEnrollStrategy studyEnrollStrategy = SpringContextUtil.getBean(strategyValue,StudyEnrollStrategy.class);
        return studyEnrollStrategy.completeStudyEnroll(studentStudyEnrollEditParam);
    }

    @Override
    public ResObject stayPayChangePageList(@Valid StudentStudyEnrollPageQueryParam param) throws BizException {
        log.info(this.getClass() + "stayPayChangePageList-方法请求参数{}",param);
        if (param == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<StudentStudyEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOrderStatusSearch()),"t1.status",param.getOrderStatusSearch());
        // 转化类型
        queryWrapper.eq(StrUtil.isNotEmpty(param.getConversionType()),"t2.conversion_type",param.getConversionType());
        //
        queryWrapper.eq(StrUtil.isNotEmpty(param.getLineUnderUserId()),"t2.line_under_user_id",param.getLineUnderUserId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"t2.user_id",param.getUserId());
        // 报名单号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"t2.study_enroll_no",param.getVagueStudyEnrollNoSearch());
        // 订单号查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getStudentOrderNo()),"t1.order_no",param.getStudentOrderNo());
        // 真实姓名模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"t2.real_name",param.getVagueRealNameSearch());
        // 预约见面时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getBeSpeakMeetTimeSearch()),
                "date_format (t2.be_speak_meet_time,'%Y-%m-%d') = date_format('" + param.getBeSpeakMeetTimeSearch() + "','%Y-%m-%d')");
        // 意向报名时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getIntentEnrollTimeSearch()),
                "date_format (t2.intent_enroll_time,'%Y-%m-%d') = date_format('" + param.getIntentEnrollTimeSearch() + "','%Y-%m-%d')");

        // 报名状态
        if (param.getOrderStatusArr().length > 0){
            queryWrapper.in("t2.enroll_status",param.getOrderStatusArr());
        }

        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        if (StrUtil.isNotEmpty(param.getPayTimeSearch())){
            String[] arr = param.getPayTimeSearch().split(",");
            queryWrapper.between("t1.pay_time",arr[0],arr[1]);
        }

        IPage<StudentStudyEnrollVo> pageList = studentStudyEnrollService.studyEnrollPageList(page,queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error(this.getClass() +"数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // 循环
        pageList.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId())){
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getUserId());
                log.info("线上客服{}",serviceInfo);
                if (serviceInfo != null)item.setOnlineServiceName(serviceInfo.getRealName());
            }
            if (StrUtil.isNotEmpty(item.getLineUnderUserId())){
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getLineUnderUserId());
                log.info("线下客服{}",serviceInfo);
                if (serviceInfo != null)item.setLineServiceName(serviceInfo.getRealName());
            }
            if (StrUtil.isNotEmpty(item.getDriveSchoolId())){
                DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(item.getLineUnderUserId());
                if (driveSchoolEntity != null)item.setLineServiceName(driveSchoolEntity.getSchoolName());
            }
            // 冗余数据
            StudentOrderVo studentOrderVo = new StudentOrderVo();
            studentOrderVo.setOrderNo(item.getStudentOrderNo());
            studentOrderVo.setStatus(item.getOrderStatus());
            //studentOrderVo.setPayTime(DateUtil.parseTime(item.getPayTime()));
            item.setStudentOrderVo(studentOrderVo);
            // 省市区
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
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
                item.setServiceReturnVisitHistory(BeanConvertUtils.copy(serviceReturnVisitHistoryEntity, ServiceReturnVisitHistoryVo.class));
            }

            QueryWrapper queryWrapperCount = new QueryWrapper();
            queryWrapperCount.eq("student_id",item.getStudentId());
            queryWrapperCount.eq("status", StudyEnrollEnum.CANCEL_ORDER.getCode());
            int cancelNum= studentOrderService.count(queryWrapperCount);
            //studentOrderVos.stream().mapToDouble(StudentStudyEnrollVo::getOrderStatus).sum()
            // 取消次数
            //IntSummaryStatistics sumcc = studentOrderVos.stream().collect(Collectors.summarizingInt(e->Integer.valueOf(String.valueOf(e.getOrderStatus()=="5"))));
            // 取消订单次数
            item.setCancelNum(cancelNum);
        });
        return R.success(pageList);
    }

    @Override
    public ResObject statisticsStudentDataPageList(@Valid StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "statisticsStudentData-方法请求参数{}",param);
        Page<StudentStudyEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());

       /* if (StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch())){
            return serviceReturnVisitHistoryRepository.pageListReturnVisitHistory(param);
        }*/
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"t1.operator_id",param.getOperatorId());
        // 学员ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStudentId()),"t1.student_id",param.getStudentId());
        // 推荐人ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getRecommendUserId()),"t3.recommend_user_id",param.getRecommendUserId());

        if (StrUtil.isNotEmpty(param.getSubjectType()) && StrUtil.isNotEmpty(param.getEnrollStatus())){
            queryWrapper.gt("(SELECT COUNT(1) FROM t_student_test_enroll WHERE t1.student_id = t_student_test_enroll.student_id AND t_student_test_enroll.subject_type="+param.getSubjectType()+" AND t_student_test_enroll.enroll_status = "+param.getEnrollStatus()+")",0);
        }
        queryWrapper.orderByDesc("t1.create_time");
        IPage<StatisticsStudentDataVo> pageList = studentStudyEnrollService.statisticsStudentDataPageList(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空-------------");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }

        // 循环取数据
        pageList.getRecords().stream().forEach((item) ->{
            // 查询科目一
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

            // 查询科目二
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

            // 查询科目三
            QueryWrapper subjectThreeQueryWrapper = new QueryWrapper();
            subjectThreeQueryWrapper.eq("subject_type", SubjectTypeEnum.SUBJECT_THREE.getCode());
            subjectThreeQueryWrapper.eq("student_id",item.getStudentId());
            subjectThreeQueryWrapper.orderByDesc("create_time");
            subjectThreeQueryWrapper.last("limit 1");
            StudentTestEnrollEntity studentTestEnrollThree = studentTestEnrollService.getOne(subjectThreeQueryWrapper);
            if (studentTestEnrollThree != null){
                item.setSubjectThreeStatus(studentTestEnrollThree.getEnrollStatus());
                item.setSubjectThreeStatusName(ExamEnrollEnum.getNameByCode(studentTestEnrollThree.getEnrollStatus()));
                item.setSubjectOneExamTime(studentTestEnrollThree.getTestActualTime());
            }

            // 查询科目四
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
            // 统计课时
            Integer subjectTwoClassHour = studentTrainCarApplyService.classHoursSum(item.getStudentId(),SubjectTypeEnum.SUBJECT_TWO.getCode());
            if (subjectTwoClassHour != null)item.setSubjectTwoHour(subjectTwoClassHour);
            Integer subjectThreeClassHour = studentTrainCarApplyService.classHoursSum(item.getStudentId(),SubjectTypeEnum.SUBJECT_TWO.getCode());
            if (subjectThreeClassHour != null)item.setSubjectThreeHour(subjectThreeClassHour);

            //
            QueryWrapper systemCoachStudentQueryWrapper = new QueryWrapper();
            // 学员ID
            systemCoachStudentQueryWrapper.eq("student_id",item.getStudentId());
            systemCoachStudentQueryWrapper.eq("bind_status",StatusEnum.NORMAL.getCode());
            OneFeeSystemCoachStudentEntity systemCoachStudent = oneFeeSystemCoachStudentService.getOne(systemCoachStudentQueryWrapper);
            if (systemCoachStudent != null){
                String coachRedis = jedis.get(CacheConstants.REDIS_CACHE_COACH_KEY + systemCoachStudent.getCoachId());
                JSONObject jsonObject = JSON.parseObject(coachRedis);
                if (jsonObject != null)item.setBindCoach(jsonObject.getString("realName"));
            }

            // 客服
            ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getOnLineServiceId());
            if (serviceInfo != null)item.setOnLineService(serviceInfo.getRealName());
            // 推广商
            if (StrUtil.isNotEmpty(item.getReferrerId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getReferrerId());
                if (studentInfo != null)item.setReferrerName(studentInfo.getRealName());
            }

            // 驾校
            if (StrUtil.isNotEmpty(item.getApplySchoolId())){
                DriveSchoolEntity driveSchool = driveSchoolService.getById(item.getApplySchoolId());
                if (driveSchool != null)item.setDriveSchoolName(driveSchool.getSchoolName());
            }

            // 订单查询状态
            QueryWrapper orderQueryWrapper = new QueryWrapper();
            // 学员ID
            orderQueryWrapper.eq("student_id",item.getStudentId());
            // 报名单号
            orderQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
            StudentOrderEntity studentOrder = studentOrderService.getOne(orderQueryWrapper);
            if (studentOrder == null){
                item.setStudentType("新用户");
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
            int isIndex = item.getEnrollStatus().indexOf(StringUtils.join(arr, ","));
            if (studentOrder != null && isIndex== -1){
                item.setStudentType("学员");
            }else{
                item.setStudentType("待支付");
            }

        });

        return R.success(pageList);
    }

    @Override
    public ResObject drivingStudentDataPageList(@Valid StudentStudyEnrollPageQueryParam param) {
        log.info("drivingStudentDataPageList-方法请求参数{}",param);
        /**
         * 用于管理学员-学车业务，方便客服查看跟踪学员的  练车状态，考试状态。
         *
         * 条件：
         * 1.科目一考试通过
         * 2.科目二，科目三.考试未通 （未预约考试。考试挂科）
         */
        Page<CoachTeachTimeEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        IPage<StudyCarScheduleVo> pageList =  studentStudyEnrollService.studyCarSchedulePageList(page,queryWrapper);
        return R.success(pageList);



//        Page<CoachTeachTimeEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
//        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
//        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"operator_id",param.getOperatorId());
//        // 学员ID
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
//        // 科目类型
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
//        // 处理数据
//        coachTeachTimeVoPage.getRecords().stream().forEach((item) ->{
//            // 学员
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
//            // 客服
//            QueryWrapper studentStudyQueryWrapper = new QueryWrapper();
//            // 报名状态
//            studentStudyQueryWrapper.in("enroll_status",arrStatus);
//            // 学员ID
//            studentStudyQueryWrapper.eq("student_id",item.getStudentId());
//            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studentStudyQueryWrapper);
//            if (studentStudyEnroll != null){
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(studentStudyEnroll.getUserId()).getRealName());
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(studentStudyEnroll.getLineUnderUserId()).getRealName());
//
//                // 省市区  后续放缓存
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getProvinceId()))item.setProvinceName(areaService.getByBaCode(studentStudyEnroll.getProvinceId()).getBaName());
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getCityId()))item.setCityName(areaService.getByBaCode(studentStudyEnroll.getCityId()).getBaName());
//                if (StrUtil.isNotEmpty(studentStudyEnroll.getAreaId()))item.setAreaName(areaService.getByBaCode(studentStudyEnroll.getAreaId()).getBaName());
//            }
//
//            // 教练信息
//            CoachInfoEntity coachInfo = coachInfoService.getById(item.getCoachId());
//            if (coachInfo != null) {
//                item.setCoachName(coachInfo.getRealName());
//            }
//
//            // 统计课时
//            Integer subjectTwoClassHour = studentTrainCarApplyService.classHoursSum(item.getStudentId(),item.getSubjectType());
//            if (subjectTwoClassHour != null)item.setTotalHour(subjectTwoClassHour);
//
//            // 考试
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
        log.info("drivingStudentDataPageList-方法请求参数{}",param);
        Page<StudentOrderEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"operator_id",param.getOperatorId());
        // 学员ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStudentId()),"student_id",param.getStudentId());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueOrderNoSearch()),"order_no",param.getVagueOrderNoSearch());
        //
        // 科目类型
        queryWrapper.in("order_type",3,4);

        queryWrapper.orderByDesc("create_time");
        IPage<StudentOrderEntity> pageList = studentOrderService.page(page,queryWrapper);
        Page<StudentOrderVo> studentOrderVoPage = studentOrderMapStruct.toVoList(pageList);
        if (studentOrderVoPage.getRecords().size() <= 0 ){
            return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_NULL.subMsg(),studentOrderVoPage);
        }
        // 处理数据
        studentOrderVoPage.getRecords().stream().forEach((item) ->{
            // 学员
            StudentInfoEntity student  = studentInfoService.getById(item.getStudentId());
            if (student != null){
                item.setStudentVo(BeanConvertUtils.copy(student, StudentInfoVo.class));
            }
            // 客服
            QueryWrapper studentStudyQueryWrapper = new QueryWrapper();
            // 报名状态
            studentStudyQueryWrapper.eq("enroll_status",StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
            // 学员ID
            studentStudyQueryWrapper.eq("student_id",item.getStudentId());
            StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getOne(studentStudyQueryWrapper);
            if (studentStudyEnroll != null){
                if (StrUtil.isNotEmpty(studentStudyEnroll.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(studentStudyEnroll.getUserId()).getRealName());
                if (StrUtil.isNotEmpty(studentStudyEnroll.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(studentStudyEnroll.getLineUnderUserId()).getRealName());
                item.setStudentStudyEnrollVo(BeanConvertUtils.copy(studentStudyEnroll,StudentStudyEnrollVo.class));
                // 省市区  后续放缓存
                if (StrUtil.isNotEmpty(studentStudyEnroll.getProvinceId()))item.setProvinceName(areaService.getByBaCode(studentStudyEnroll.getProvinceId()).getBaName());
                if (StrUtil.isNotEmpty(studentStudyEnroll.getCityId()))item.setCityName(areaService.getByBaCode(studentStudyEnroll.getCityId()).getBaName());
                if (StrUtil.isNotEmpty(studentStudyEnroll.getAreaId()))item.setAreaName(areaService.getByBaCode(studentStudyEnroll.getAreaId()).getBaName());
            }
            // 教练信息
            StudentTrainCarApplyEntity studentTrainCarApply = studentTrainCarApplyService.getById(item.getTrainApplyNo());
            if (studentTrainCarApply != null){
                item.setStudentTrainCarApplyVo(BeanConvertUtils.copy(studentTrainCarApply,StudentTrainCarApplyVo.class));
                String cacheRes = jedis.get(CacheConstants.REDIS_CACHE_COACH_KEY+studentTrainCarApply.getCoachId());
                JSONObject jsonObject = (JSONObject) JSONObject.parse(cacheRes);
                if (jsonObject != null)item.setCoachName(jsonObject.getString("realName"));
            }
        });
        return R.success(studentOrderVoPage);
    }
}

