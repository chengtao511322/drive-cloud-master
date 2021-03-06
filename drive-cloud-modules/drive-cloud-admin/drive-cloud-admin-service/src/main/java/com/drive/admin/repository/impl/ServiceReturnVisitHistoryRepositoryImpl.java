package com.drive.admin.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.ReturnVisitStatusEnum;
import com.drive.admin.pojo.dto.ServiceReturnVisitHistoryEditParam;
import com.drive.admin.pojo.dto.ServiceReturnVisitHistoryInstallParam;
import com.drive.admin.pojo.dto.ServiceReturnVisitHistoryPageQueryParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.entity.ServiceInfoEntity;
import com.drive.admin.pojo.entity.ServiceReturnVisitHistoryEntity;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.ReturnVisitHistoryEnrollVo;
import com.drive.admin.pojo.vo.ServiceReturnVisitHistoryVo;
import com.drive.admin.repository.ServiceReturnVisitHistoryRepository;
import com.drive.admin.service.*;
import com.drive.admin.service.mapstruct.ServiceReturnVisitHistoryMapStruct;
import com.drive.admin.service.mapstruct.StudentStudyEnrollMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 *
 * ?????????????????? ?????????
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  ServiceReturnVisitHistoryRepositoryImpl extends BaseController<ServiceReturnVisitHistoryPageQueryParam, ServiceReturnVisitHistoryEntity>  implements ServiceReturnVisitHistoryRepository{

    //  ?????????????????? ??????
    @Autowired
    private ServiceReturnVisitHistoryService serviceReturnVisitHistoryService;
    //  ?????????????????? DO-DTO??????
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

    // ??????

    //  ???????????????????????????????????????????????????????????????????????????????????? ??????
    @Autowired
    private DriveSchoolService driveSchoolService;

    @Autowired
    private AreaService areaService;

    //private final Jedis jedis = RedisDS.create().getJedis();

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ?????????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(ServiceReturnVisitHistoryPageQueryParam param) {
        log.info(this.getClass() + "pageList-??????????????????{}",param);
        Page<ServiceReturnVisitHistoryEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        // ????????????
        QueryWrapper queryWrapper = this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);

        //  ????????????
        //queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<ServiceReturnVisitHistoryEntity> pageList = serviceReturnVisitHistoryService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
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
        log.info(this.getClass() + "pageList-??????????????????{}",serviceReturnVisitHistoryVoPage);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),serviceReturnVisitHistoryVoPage);
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ?????????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject findList(ServiceReturnVisitHistoryPageQueryParam param) {
        log.info(this.getClass() + "findList-??????????????????{}",param);
        // ??????????????????????????????
        QueryWrapper queryWrapper= this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);
        // ??? queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServiceReturnVisitHistoryEntity> serviceReturnVisitHistoryList = serviceReturnVisitHistoryService.list(queryWrapper);
        if (serviceReturnVisitHistoryList == null){
            log.error("?????????");
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
        log.info(this.getClass() + "findList-??????????????????{}",serviceReturnVisitHistoryVoList);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),serviceReturnVisitHistoryVoList);
    }

    /**
    * ??????????????????????????????????????????????????????
    * @param param
    * @return
    */
    @Override
    public ResObject getInfo(ServiceReturnVisitHistoryPageQueryParam param) {
        log.info(this.getClass() + "getInfo-??????????????????{}",param);
        if (param == null){
            return R.failure("?????????");
        }
        // ??????????????????????????????
        QueryWrapper queryWrapper = this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = serviceReturnVisitHistoryService.getOne(queryWrapper);
        if (serviceReturnVisitHistory == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceReturnVisitHistory);
        }
        ServiceReturnVisitHistoryVo serviceReturnVisitHistoryVo = BeanConvertUtils.copy(serviceReturnVisitHistory, ServiceReturnVisitHistoryVo.class);
        log.info(this.getClass() + "getInfo-??????????????????{}",serviceReturnVisitHistoryVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),serviceReturnVisitHistoryVo);
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ??????ID?????? ?????????????????? ????????????
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getById-??????????????????{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("?????????");
        }
        // ??????ID ?????? ????????????
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = serviceReturnVisitHistoryService.getById(id);
        if (serviceReturnVisitHistory == null){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceReturnVisitHistory);
        }
        ServiceReturnVisitHistoryVo serviceReturnVisitHistoryVo = BeanConvertUtils.copy(serviceReturnVisitHistory, ServiceReturnVisitHistoryVo.class);
        log.info(this.getClass() + "getById-??????????????????{}",serviceReturnVisitHistoryVo);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_SEARCH_SUCCESS.subMsg(),serviceReturnVisitHistoryVo);
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ???????????????????????? ??????
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject save(ServiceReturnVisitHistoryInstallParam installParam) {
        log.info(this.getClass() + "save??????????????????{}",installParam);
        if (installParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ????????????
        installParam.setReturnVisitTime(LocalDateTime.now());
        // ??????ID
        installParam.setServiceId(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = BeanConvertUtils.copy(installParam, ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.save(serviceReturnVisitHistory);
        log.info(this.getClass() + "save-??????????????????{}",result);
        // ????????????
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_INSTALL_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description  ???????????????????????? ??????
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject update(ServiceReturnVisitHistoryEditParam updateParam) {
        log.info(this.getClass() + "update??????????????????{}",updateParam);
        if (updateParam == null){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = BeanConvertUtils.copy(updateParam, ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.updateById(serviceReturnVisitHistory);
        log.info(this.getClass() + "update-??????????????????{}",result);
        // ????????????
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_UPDATE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
    }

    /*
     *
     *????????????
     * @author xiaoguo
     * @description ?????????????????????????????? ??????
     * @date 2020/2/12 17:09
     * @param  * @param ServiceReturnVisitHistoryPageQueryParam
     * @return
     */
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(serviceReturnVisitHistoryService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *??????id???????????????????????? ??????
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
        Boolean result = serviceReturnVisitHistoryService.removeById(id);
        log.info(this.getClass() + "deleteById-??????????????????{}",result);
        // ????????????
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_DELETE_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_DELETE_FAILL.subCode(),SubResultCode.DATA_DELETE_FAILL.subMsg());
    }

    /**
     * *???????????????????????? ??????
     **/
    @Override
    public ResObject exportXls(ServiceReturnVisitHistoryPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls??????????????????{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(serviceReturnVisitHistoryMapStruct, param);
        List<ServiceReturnVisitHistoryEntity> list = serviceReturnVisitHistoryService.list(queryWrapper);
        List<ServiceReturnVisitHistoryVo>serviceReturnVisitHistoryList = serviceReturnVisitHistoryMapStruct.toVoList(list);
        ExcelUtils.exportExcel(serviceReturnVisitHistoryList, ServiceReturnVisitHistoryVo.class, "", new ExportParams(), response);
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.EXPORT_DATA_SUCCESS.subMsg());
    }

    /**
     * *????????????
     **/
    @Override
    public ResObject changeStatus(ServiceReturnVisitHistoryEditParam param) {
        log.info(this.getClass() + "changeStatus??????????????????{}",param);
        if (StrUtil.isEmpty(param.getId())){
            log.error("ID?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ServiceReturnVisitHistoryEntity ServiceReturnVisitHistoryEntity = new ServiceReturnVisitHistoryEntity();
        ServiceReturnVisitHistoryEntity.setId(param.getId());
        //ServiceReturnVisitHistoryEntity.setStatus(param.getStatus());
        //ServiceReturnVisitHistoryEntity.setUpdateTime()
        Boolean result = serviceReturnVisitHistoryService.updateById(ServiceReturnVisitHistoryEntity);
        log.info(this.getClass() +"changeStatus????????????????????????{}???????????????{}",ServiceReturnVisitHistoryEntity,result);
        // ????????????
        return result ?R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.DATA_STATUS_SUCCESS.subMsg()):R.failure(SubResultCode.DATA_STATUS_FAILL.subCode(),SubResultCode.DATA_STATUS_FAILL.subMsg());
    }

    @Transactional
    @Override
    public ResObject publishReturnVisit(ServiceReturnVisitHistoryEditParam returnVisitHistoryEditParam) {
        log.info(this.getClass() + "publishReturnVisit-?????? ????????????{}",returnVisitHistoryEditParam);
        ServiceReturnVisitHistoryEntity serviceReturnVisitHistory = BeanConvertUtils.copy(returnVisitHistoryEditParam, ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.save(serviceReturnVisitHistory);
        log.info(this.getClass() + "save-" +
                "??????????????????{}",result);
        // ????????????
        if (result) {
            // ??????
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // ??????????????????
        return null;
    }

    @Override
    public ResObject pageListReturnVisitHistory(StudentStudyEnrollPageQueryParam param) {
        log.info(this.getClass() + "pageList-??????????????????{}",param);
        Page<ReturnVisitHistoryEnrollVo> page = new Page<>(param.getPageNum(), param.getPageSize());

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStudentId()),"t1.student_id",param.getStudentId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStudentId()),"t2.enroll_status",param.getEnrollStatus());
        queryWrapper.setEntity(param);
        String sortColumn = param.getSortColumn();
        String underSortColumn = StringUtils.lowerCamelToLowerUnderscore(sortColumn);
        if (param.getIsAsc()) {
            queryWrapper.orderByAsc("t2."+underSortColumn);
        } else {
            queryWrapper.orderByDesc("t2."+underSortColumn);
        }
        // ???????????? ????????????
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueStudyEnrollNoSearch()),"study_enroll_no",param.getVagueStudyEnrollNoSearch());
        // ??????????????????
        queryWrapper.apply(StrUtil.isNotBlank(param.getBeSpeakMeetTimeSearch()),
                "date_format (t2.be_speak_meet_time,'%Y-%m-%d') = date_format('" + param.getBeSpeakMeetTimeSearch() + "','%Y-%m-%d')");
        // ??????????????????
        queryWrapper.apply(StrUtil.isNotBlank(param.getIntentEnrollTimeSearch()),
                "date_format (t2.intent_enroll_time,'%Y-%m-%d') = date_format('" + param.getIntentEnrollTimeSearch() + "','%Y-%m-%d')");

        queryWrapper.apply(StrUtil.isNotBlank(param.getNextReturnVisitTimeSearch()),
                "date_format (t1.next_return_visit_time,'%Y-%m-%d') >= date_format('" + param.getNextReturnVisitTimeSearch() + "','%Y-%m-%d')");


        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }

        IPage<ReturnVisitHistoryEnrollVo> pageList = serviceReturnVisitHistoryService.pageListReturnVisitHistory(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????-------------");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        pageList.getRecords().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId()))item.setOnlineServiceName(serviceInfoService.getById(item.getUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getLineUnderUserId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            if (StrUtil.isNotEmpty(item.getDriveSchoolId()))item.setLineServiceName(serviceInfoService.getById(item.getLineUnderUserId()).getRealName());
            // ?????????
            if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
            if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
            if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());
        });
        log.info(this.getClass() + "pageList-??????????????????{}",pageList);
        return R.success(pageList);
    }

    @Override
    public ResObject aggregationListReturnVisitHistory(String studentId) {
        log.info(this.getClass() + "aggregationListReturnVisitHistory-??????????????????{}",studentId);
        if (StrUtil.isEmpty(studentId)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ???????????????
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("return_visit_status", ReturnVisitStatusEnum.PRE.getCode());
        queryWrapper.orderByDesc("return_visit_time");
        // ??? queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ServiceReturnVisitHistoryEntity> preReturnVisitHistoryList = serviceReturnVisitHistoryService.list(queryWrapper);
        // ??????
        QueryWrapper afterQueryWrapper= new QueryWrapper();
        afterQueryWrapper.eq("student_id",studentId);
        afterQueryWrapper.eq("return_visit_status", ReturnVisitStatusEnum.AFTER.getCode());
        afterQueryWrapper.orderByDesc("return_visit_time");
        List<ServiceReturnVisitHistoryEntity> afterReturnVisitHistoryList = serviceReturnVisitHistoryService.list(afterQueryWrapper);
        // ??????List
        /*List<ServiceReturnVisitHistoryEntity> aggregationList = Stream.concat(preReturnVisitHistoryList.stream(), afterReturnVisitHistoryList.stream())
                .distinct()
                .collect(Collectors.toList());
        if (aggregationList.size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),aggregationList);
        }*/
        // do??????
        List<ServiceReturnVisitHistoryVo> preList= BeanConvertUtils.copyList(preReturnVisitHistoryList,ServiceReturnVisitHistoryVo.class);
        preList.stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getServiceId())){
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getServiceId());
                if (serviceInfo != null)item.setServiceName(serviceInfo.getRealName());
            }
        });
        List<ServiceReturnVisitHistoryVo> afterList= BeanConvertUtils.copyList(afterReturnVisitHistoryList,ServiceReturnVisitHistoryVo.class);
        afterList.stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getServiceId())){
                ServiceInfoEntity serviceInfo = serviceInfoService.getById(item.getServiceId());
                if (serviceInfo != null)item.setServiceName(serviceInfo.getRealName());
            }
        });

        JSONObject json = new JSONObject();
        json.put("pre",preList);
        json.put("after",afterList);
        return R.success(json);
    }

    @Override
    @Transactional
    public ResObject addReturnVisitHistory(ServiceReturnVisitHistoryInstallParam serviceReturnVisitHistoryEditParam) {
        log.info(this.getClass() + "addReturnVisitHistory-??????????????????{}",serviceReturnVisitHistoryEditParam);
        if (serviceReturnVisitHistoryEditParam == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ????????????
        serviceReturnVisitHistoryEditParam.setReturnVisitTime(LocalDateTime.now());
        // ??????ID
        /*ServiceInfoEntity serviceInfo = serviceInfoService.getById(SecurityUtils.getLoginUser().getOperationId());
        log.info("????????????{}",serviceInfo);*/
        serviceReturnVisitHistoryEditParam.setServiceId(SecurityUtils.getLoginUser().getOperationId());
        // ??????????????????
        ServiceReturnVisitHistoryEntity returnVisitHistoryEntity = BeanConvertUtils.copy(serviceReturnVisitHistoryEditParam,ServiceReturnVisitHistoryEntity.class);
        Boolean result = serviceReturnVisitHistoryService.save(returnVisitHistoryEntity);
        if (!result){
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        // ??????????????????
        StudentInfoEntity studentInfo = BeanConvertUtils.copy(serviceReturnVisitHistoryEditParam.getStudentInfoEditParam(),StudentInfoEntity.class);
        log.info("??????????????????{}",studentInfo);
        Boolean studentResult = studentInfoService.updateById(studentInfo);
        if (!studentResult){
            throw new BizException(500,SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
        }
        return R.success("????????????");
    }
}

