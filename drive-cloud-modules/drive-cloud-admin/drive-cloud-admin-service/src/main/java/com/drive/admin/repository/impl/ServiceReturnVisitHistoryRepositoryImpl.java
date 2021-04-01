package com.drive.admin.repository.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.ReturnVisitStatusEnum;
import com.drive.admin.pojo.entity.ServiceReturnVisitHistoryEntity;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.drive.admin.service.*;
import com.drive.common.core.base.BaseController;
import com.drive.admin.repository.ServiceReturnVisitHistoryRepository;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.service.mapstruct.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import com.drive.common.core.biz.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.drive.common.data.utils.ExcelUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * 客服回访记录 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  ServiceReturnVisitHistoryRepositoryImpl extends BaseController<ServiceReturnVisitHistoryPageQueryParam, ServiceReturnVisitHistoryEntity>  implements ServiceReturnVisitHistoryRepository{

    //  客服回访记录 服务
    @Autowired
    private ServiceReturnVisitHistoryService serviceReturnVisitHistoryService;
    //  客服回访记录 DO-DTO转化
    @Autowired
    private ServiceReturnVisitHistoryMapStruct serviceReturnVisitHistoryMapStruct;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private ServiceInfoService serviceInfoService;

    @Autowired
    private StudentStudyEnrollService studentStudyEnrollService;


    @Autowired
    private StudentStudyEnrollMapStruct studentStudyEnrollMapStruct;

    // 客服

    //  平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 服务
    @Autowired
    private DriveSchoolService driveSchoolService;

    @Autowired
    private AreaService areaService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 客服回访记录 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(ServiceReturnVisitHistoryPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ServiceReturnVisitHistoryEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // 条件查询
        QueryWrapper queryWrapper = this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);

        //  模糊查询
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<ServiceReturnVisitHistoryEntity> pageList = serviceReturnVisitHistoryService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ServiceReturnVisitHistoryVo> serviceReturnVisitHistoryVoPage = serviceReturnVisitHistoryMapStruct.toVoList(pageList);
        serviceReturnVisitHistoryVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getServiceId())){
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getServiceId());
                if (serviceInfo != null){
                    item.setServiceName(serviceInfo.getRealName());
                }
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",serviceReturnVisitHistoryVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),serviceReturnVisitHistoryVoPage);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 客服回访记录 查询列表
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(ServiceReturnVisitHistoryPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServiceReturnVisitHistoryEntity> serviceReturnVisitHistoryList = serviceReturnVisitHistoryService.list(queryWrapper);
        if (serviceReturnVisitHistoryList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceReturnVisitHistoryList);
        }
        List<ServiceReturnVisitHistoryVo> serviceReturnVisitHistoryVoList = serviceReturnVisitHistoryMapStruct.toVoList(serviceReturnVisitHistoryList);
        serviceReturnVisitHistoryVoList.stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getServiceId())){
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getServiceId());
                if (serviceInfo != null){
                    item.setServiceName(serviceInfo.getRealName());
                }
            }
        });
        log.info(this.getClass() + "findList-方法请求结果{}",serviceReturnVisitHistoryVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),serviceReturnVisitHistoryVoList);
    }

    /**
    * 对象条件查询返回单条客服回访记录数据
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(ServiceReturnVisitHistoryPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        // 这里判断条件进行查询
        QueryWrapper queryWrapper = this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = serviceReturnVisitHistoryService.getOne(queryWrapper);
        if (serviceReturnVisitHistory == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceReturnVisitHistory);
        }
        ServiceReturnVisitHistoryVo serviceReturnVisitHistoryVo = BeanConvertUtils.copy(serviceReturnVisitHistory, ServiceReturnVisitHistoryVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",serviceReturnVisitHistoryVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),serviceReturnVisitHistoryVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 通过ID获取 客服回访记录 单条数据
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        // 通过ID 获取 单条数据
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = serviceReturnVisitHistoryService.getById(id);
        if (serviceReturnVisitHistory == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceReturnVisitHistory);
        }
        ServiceReturnVisitHistoryVo serviceReturnVisitHistoryVo = BeanConvertUtils.copy(serviceReturnVisitHistory, ServiceReturnVisitHistoryVo.class);
        log.info(this.getClass() + "getById-方法请求结果{}",serviceReturnVisitHistoryVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),serviceReturnVisitHistoryVo);
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 保存客服回访记录 数据
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject save(ServiceReturnVisitHistoryInstallParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 回访时间
        installParam.setReturnVisitTime(LocalDateTime.now());
        // 客服ID
        installParam.setServiceId(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = BeanConvertUtils.copy(installParam, ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.save(serviceReturnVisitHistory);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description  修改客服回访记录 数据
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject update(ServiceReturnVisitHistoryEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = BeanConvertUtils.copy(updateParam, ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.updateById(serviceReturnVisitHistory);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 数组删除客服回访记录 数据
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(serviceReturnVisitHistoryService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除客服回访记录 信息
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
        Boolean result = serviceReturnVisitHistoryService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *导出客服回访记录 信息
     **/
    @Override
    public ResObject exportXls(ServiceReturnVisitHistoryPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);
        List<ServiceReturnVisitHistoryEntity> list = serviceReturnVisitHistoryService.list(queryWrapper);
        List<ServiceReturnVisitHistoryVo>serviceReturnVisitHistoryList = serviceReturnVisitHistoryMapStruct.toVoList(list);
        ExcelUtils.exportExcel(serviceReturnVisitHistoryList, ServiceReturnVisitHistoryVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ServiceReturnVisitHistoryEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceReturnVisitHistoryEntity ServiceReturnVisitHistoryEntity = new ServiceReturnVisitHistoryEntity();
        ServiceReturnVisitHistoryEntity.setId(param.getId());
        //ServiceReturnVisitHistoryEntity.setStatus(param.getStatus());
        //ServiceReturnVisitHistoryEntity.setUpdateTime()
        Boolean result = serviceReturnVisitHistoryService.updateById(ServiceReturnVisitHistoryEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ServiceReturnVisitHistoryEntity,result);
        // 判断结果
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Transactional
    @Override
    public ResObject publishReturnVisit(ServiceReturnVisitHistoryEditParam returnVisitHistoryEditParam) {
        log.info(this.getClass() + "publishReturnVisit-方法 请求参数{}",returnVisitHistoryEditParam);
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = BeanConvertUtils.copy(returnVisitHistoryEditParam, ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.save(serviceReturnVisitHistory);
        log.info(this.getClass() + "save-" +
                "方法请求结果{}",result);
        // 判断结果
        if (result) {
            // 异常
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // 修改报名状态
        return null;
    }

    @Override
    public ResObject pageListReturnVisitHistory(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ReturnVisitHistoryEnrollVo> page = new Page<>(param.getPageNum(), param.getPageSize());

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(param);
        String sortColumn = param.getSortColumn();
        String underSortColumn = StringUtils.lowerCamelToLowerUnderscore(sortColumn);
        if (param.getIsAsc()) {
            queryWrapper.orderByAsc("t2."+underSortColumn);
        } else {
            queryWrapper.orderByDesc("t2."+underSortColumn);
        }
        // 报名单号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        // 预约见面时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getBeSpeakMeetTimeSearch()),
                "date_format (t2.be_speak_meet_time,'%Y-%m-%d') = date_format('" + param.getBeSpeakMeetTimeSearch() + "','%Y-%m-%d')");
        // 意向报名时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getIntentEnrollTimeSearch()),
                "date_format (t2.intent_enroll_time,'%Y-%m-%d') = date_format('" + param.getIntentEnrollTimeSearch() + "','%Y-%m-%d')");

        queryWrapper.apply(StrUtil.isNotBlank(param.getNextReturnVisitTimeSearch()),
                "date_format (t1.next_return_visit_time,'%Y-%m-%d') >= date_format('" + param.getNextReturnVisitTimeSearch() + "','%Y-%m-%d')");


        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }

        IPage<ReturnVisitHistoryEnrollVo> pageList = serviceReturnVisitHistoryService.pageListReturnVisitHistory(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空-------------");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        pageList.getRecords().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // 省市区
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",pageList);
        return R.success(pageList);
    }

    @Override
    public ResObject aggregationListReturnVisitHistory(String studentId) {
        log.info(this.getClass() + "aggregationListReturnVisitHistory-方法请求参数{}",studentId);
        if (StrUtil.isEmpty(studentId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 先查询售前
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("return_visit_status", ReturnVisitStatusEnum.PRE.getCode());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServiceReturnVisitHistoryEntity> preReturnVisitHistoryList = serviceReturnVisitHistoryService.list(queryWrapper);
        // 售后
        queryWrapper.eq("return_visit_status", ReturnVisitStatusEnum.AFTER.getCode());
        List<ServiceReturnVisitHistoryEntity> afterReturnVisitHistoryList = serviceReturnVisitHistoryService.list(queryWrapper);
        // 聚合List
        /*List<ServiceReturnVisitHistoryEntity> aggregationList = Stream.concat(preReturnVisitHistoryList.stream(), afterReturnVisitHistoryList.stream())
                .distinct()
                .collect(Collectors.toList());
        if (aggregationList.size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),aggregationList);
        }*/
        // do转化
        JSONObject json = new JSONObject();
        json.put("pre",BeanConvertUtils.copyList(preReturnVisitHistoryList,ServiceReturnVisitHistoryVo.class));
        json.put("after",BeanConvertUtils.copyList(afterReturnVisitHistoryList,ServiceReturnVisitHistoryVo.class));
        return R.success(json);
    }

    @Override
    @Transactional
    public ResObject addReturnVisitHistory(ServiceReturnVisitHistoryInstallParam serviceReturnVisitHistoryEditParam) {
        log.info(this.getClass() + "addReturnVisitHistory-方法请求参数{}",serviceReturnVisitHistoryEditParam);
        if (serviceReturnVisitHistoryEditParam == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 回访时间
        serviceReturnVisitHistoryEditParam.setReturnVisitTime(LocalDateTime.now());
        // 客服ID
        serviceReturnVisitHistoryEditParam.setServiceId(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
        // 添加回访记录
        ServiceReturnVisitHistoryEntity returnVisitHistoryEntity = BeanConvertUtils.copy(serviceReturnVisitHistoryEditParam,ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.save(returnVisitHistoryEntity);
        if (!result){
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // 修改学员信息
        StudentInfoEntity studentInfo = BeanConvertUtils.copy(serviceReturnVisitHistoryEditParam.getStudentInfoEditParam(),StudentInfoEntity.class);
        log.info("修改的信息是{}",studentInfo);
        Boolean studentResult = studentInfoService.updateById(studentInfo);
        if (!studentResult){
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success("执行成功");
    }
}

