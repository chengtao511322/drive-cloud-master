package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.EnrollStatusEnum;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollEditParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.admin.pojo.vo.StudentOrderVo;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.admin.repository.ServiceReturnVisitHistoryRepository;
import com.drive.admin.repository.StudentStudyEnrollRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.mapstruct.StudentStudyEnrollMapStruct;
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
import java.util.Arrays;
import java.util.Comparator;
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

        if (StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch())){
            return serviceReturnVisitHistoryRepository.pageListReturnVisitHistory(param);
        }

        // 订单
        QueryWrapper orderQueryWrapper = new QueryWrapper();
        orderQueryWrapper.like(StrUtil.isNotEmpty(param.getStudentOrderNo()),"order_no",param.getStudentOrderNo());
        List<StudentOrderEntity> studentOrderList  = studentOrderService.list(orderQueryWrapper);
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

        if (studentOrderList.size() > 0){
            queryWrapper.in("study_enroll_no",studentOrderList.stream().map(StudentOrderEntity::getStudyEnrollNo).collect(Collectors.toList()));
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
        });
        // 订单号 模糊查询
        if (StrUtil.isNotEmpty(param.getStudentOrderNo())){
            studentStudyEnrollVoPage.getRecords().stream().filter(userz -> globalMatch?userz.getStudentOrderVo().getOrderNo().equals(param.getStudentOrderNo()):
                    userz.getStudentOrderVo().getOrderNo().contains(param.getStudentOrderNo())).
                    collect(Collectors.toList());
        }
        // 真实姓名 模糊查询
       /* if (StrUtil.isNotEmpty(param.getVagueRealNameSearch())){
            studentStudyEnrollVoPage.getRecords().stream().filter(userz -> globalMatch?userz.getStudentVo().getRealName().equals(param.getVagueRealNameSearch()):
                    userz.getStudentVo().getRealName().contains(param.getVagueRealNameSearch())).
                    collect(Collectors.toList());
        }*/
        log.info(this.getClass() + "pageList-方法请求结果{}",studentStudyEnrollVoPage);
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
        return null;
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
            DriveSchoolEntity driveSchoolEntity =driveSchoolService.getById(studentStudyEnrollVo.getLineUnderUserId());
            if (driveSchoolEntity != null)studentStudyEnrollVo.setLineServiceName(driveSchoolEntity.getSchoolName());
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
}

