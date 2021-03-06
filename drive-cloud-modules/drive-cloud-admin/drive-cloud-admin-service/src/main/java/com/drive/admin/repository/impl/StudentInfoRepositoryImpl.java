package com.drive.admin.repository.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.enums.ReturnVisitStatusEnum;
import com.drive.admin.pojo.dto.StudentInfoEditParam;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.ServiceInfoEntity;
import com.drive.admin.pojo.entity.ServiceReturnVisitHistoryEntity;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.admin.repository.StudentInfoRepository;
import com.drive.admin.service.AreaService;
import com.drive.admin.service.ServiceInfoService;
import com.drive.admin.service.ServiceReturnVisitHistoryService;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.mapstruct.StudentInfoMapStruct;
import com.drive.admin.util.AdminCacheUtil;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 *
 * 学员信息表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  StudentInfoRepositoryImpl extends BaseController<StudentInfoPageQueryParam, StudentInfoEntity>  implements StudentInfoRepository{

    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private StudentInfoMapStruct studentInfoMapStruct;
    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private ServiceReturnVisitHistoryService serviceReturnVisitHistoryService;


    @Override
    public ResObject<StudentInfoRpcVo> getByIdInfo(String id) {
        log.info("-getByIdInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentInfoEntity studentInfo = studentInfoService.getById(id);
        if (studentInfo == null)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        StudentInfoVo studentInfoVo = BeanConvertUtils.copy(studentInfo,StudentInfoVo.class);
        StudentInfoRpcVo studentInfoRpcVo = BeanConvertUtils.copy(studentInfoVo,StudentInfoRpcVo.class);
        return R.success(studentInfoRpcVo);
    }

    /**
     * List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
     *         System.out.println(users);
     * @param ids
     * @return
     */
    @Override
    public ResObject<Map<String, StudentInfoRpcVo>> batchStudent(String[] ids) {
        log.info("-getByIdInfo-方法请求参数{}",ids);
        if (ids.length <=0){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        List<StudentInfoEntity> studentInfoList = studentInfoService.listByIds(Arrays.asList(ids));
        if (studentInfoList.isEmpty())return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        List<StudentInfoVo> studentInfoVoList = BeanConvertUtils.copyList(studentInfoList,StudentInfoVo.class);
        List<StudentInfoRpcVo> studentInfoRpcVoList = BeanConvertUtils.copyList(studentInfoVoList,StudentInfoRpcVo.class);
        Map<String, StudentInfoRpcVo> appleMap = studentInfoRpcVoList.stream().collect(Collectors.toMap(StudentInfoRpcVo::getId, a -> a,(k1, k2)->k1));
        return R.success(appleMap);
    }

    @Transactional
    @Override
    public ResObject reduceInventoryRollback() {
        log.info(this.getClass() + "reduceInventoryRollback-方法请求参数{}");
        StudentInfoEntity activityInfoEntity = new StudentInfoEntity();
        activityInfoEntity.setId("000f431303a543eb95a9");
        activityInfoEntity.setUsername("测试回滚");
        studentInfoService.updateById(activityInfoEntity);
        //模拟异常
        int i = 1 / 0;
        return R.success();
    }

    @Override
    public ResObject newStudentPageList(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "newStudentPageList-方法请求参数{}",param);
        Page<StudentInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.isNull("tsrvh.id");
        queryWrapper.isNull("tsse.study_enroll_no");
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"tsi.phone",param.getVaguePhoneSearch());
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"tsi.operator_id",param.getOperatorId());
        // 渠道查询
        queryWrapper.eq(param.getChannel() != null,"tsi.channel",param.getChannel());
        // 手机号码查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"tsi.phone",param.getPhone());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"tsi.phone",param.getVaguePhoneSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueUserNameSearch()),"tsi.username",param.getVagueUserNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"tsi.real_name",param.getVagueRealNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueEmailSearch()),"tsi.email",param.getVagueEmailSearch());
        /*新用户回访*/
        // queryWrapper.apply(" (SELECT COUNT(1) FROM t_service_return_visit_history t3 WHERE t3.student_id =tsi.id) <=0");
        // 登录时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchLoginDate()),
                "date_format (login_time,'%Y-%m-%d') = date_format('" + param.getSearchLoginDate() + "','%Y-%m-%d')");
        // 推荐时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchRecommendDate()),
                "date_format (recommend_date,'%Y-%m-%d') <= date_format('" + LocalDate.now().toString() + "','%Y-%m-%d')");

        // 省市区
        if (StrUtil.isNotEmpty(param.getCityArr())){
            String[] splitParam = param.getCityArr().split(",");
            if (splitParam.length >=1 && StrUtil.isNotEmpty(splitParam[0]))queryWrapper.eq(StrUtil.isNotEmpty(splitParam[0]),"tsi.province_id",splitParam[0]);
            if (splitParam.length >=2 && StrUtil.isNotEmpty(splitParam[1]))queryWrapper.eq(StrUtil.isNotEmpty(splitParam[1]),"tsi.city_id",splitParam[1]);
            if (splitParam.length >=3 && StrUtil.isNotEmpty(splitParam[2]))queryWrapper.eq(StrUtil.isNotEmpty(splitParam[2]),"tsi.area_id",splitParam[2]);
        }
        if (StrUtil.isNotEmpty(param.getCreateTimeSearch())){
            String[] arr = param.getCreateTimeSearch().split(",");
            // queryWrapper.between("date_format (tsi.create_time,'%Y-%m-%d') = date_format('" + arr[0] + "','%Y-%m-%d')","date_format (tsi.create_time,'%Y-%m-%d') = date_format('" + arr[1] + "','%Y-%m-%d')");
            queryWrapper.between("tsi.create_time",arr[0],arr[1]);
        }
        if (param.getHasPreOnlineServicer() !=null && param.getHasPreOnlineServicer().equals(0)){
            queryWrapper.isNull("tsi.service_id");
        }
        if (param.getHasPreOnlineServicer() !=null && param.getHasPreOnlineServicer().equals(1)){
            queryWrapper.isNotNull("tsi.service_id");
        }

        if (StrUtil.isNotEmpty(param.getVagueServiceNameSearch())){
            List<ServiceInfoEntity> serviceInfoList = null;
            QueryWrapper serviceQueryWrapper = new QueryWrapper();
            serviceQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueServiceNameSearch()),"real_name",param.getVagueServiceNameSearch());
            serviceQueryWrapper.like(StrUtil.isNotEmpty(param.getVaguePreSalesServiceNameSearch()),"real_name",param.getVaguePreSalesServiceNameSearch());
            serviceInfoList  = serviceInfoService.list(serviceQueryWrapper);
            if (serviceInfoList.size() <= 0)R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceInfoList);
            queryWrapper.in("tsi.service_id",serviceInfoList.stream().map(ServiceInfoEntity::getId).collect(Collectors.toList()));
        }

        String sortColumn = param.getSortColumn();
        String underSortColumn = StringUtils.lowerCamelToLowerUnderscore(sortColumn);
        if (param.getIsAsc()) {
            queryWrapper.orderByAsc("tsi."+underSortColumn);
        } else {
            queryWrapper.orderByDesc("tsi."+underSortColumn);
        }
        IPage<StudentInfoVo> studentInfoVoPage = studentInfoService.newStudentPageList(page,queryWrapper);
        if (studentInfoVoPage.getRecords().size() <= 0){
            log.error(this.getClass() +"数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoVoPage);
        }

        studentInfoVoPage.getRecords().stream().forEach((item) ->{
            QueryWrapper returnVisitHistoryQueryWrapper = new QueryWrapper();
            /*if (StrUtil.isNotEmpty(item.getId())){
                returnVisitHistoryQueryWrapper.eq("student_id",item.getId());
                int countReturnVisitHistory =serviceReturnVisitHistoryService.count(returnVisitHistoryQueryWrapper);
                if (countReturnVisitHistory > 0){
                    item.setReturnVisitHistory(true);
                }
            }*/

            if (StrUtil.isNotEmpty(item.getServiceId())){
                ServiceInfoEntity serviceInfo =serviceInfoService.getById(item.getServiceId());
                if (serviceInfo != null){
                    item.setServiceName(serviceInfo.getRealName());
                }
            }
            // 回访阶段
            if (StrUtil.isNotEmpty(item.getStudyEnrollNo())){
                returnVisitHistoryQueryWrapper.eq("student_id",item.getId());
                returnVisitHistoryQueryWrapper.eq("study_enroll_no",item.getStudyEnrollNo());
                ServiceReturnVisitHistoryEntity returnVisitHistory =serviceReturnVisitHistoryService.getOne(returnVisitHistoryQueryWrapper);
                if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitStatus()))item.setReturnVisitStatus(returnVisitHistory.getReturnVisitStatus());
                if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitItem()))item.setReturnVisitItem(returnVisitHistory.getReturnVisitItem());
            }
        });
/*
        if (param.isReturnVisitHistory()){
            // 根据条件查询回访
            List<StudentInfoVo> studentInfoVoList = studentInfoVoPage.getRecords().stream().filter((StudentInfoVo student)->student.isReturnVisitHistory() == param.isReturnVisitHistory())
                    .collect(Collectors.toList());
            studentInfoVoPage.setRecords(studentInfoVoList);
            studentInfoVoPage.setTotal(studentInfoVoList.size());
        }*/
        return R.success(studentInfoVoPage);
    }

    @Override
    public ResObject newStudentReturnVisitPageList(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "newStudentPageList-方法请求参数{}",param);
        Page<StudentInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        // 客服模糊
        List<ServiceInfoEntity> serviceInfoList = new ArrayList<>();
        if (StrUtil.isNotEmpty(param.getVagueServiceNameSearch()) || StrUtil.isNotEmpty(param.getVagueServicePhoneSearch())){
            QueryWrapper studentQueryWrapper = new QueryWrapper();
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueServiceNameSearch()),"real_name",param.getVagueServiceNameSearch());
            studentQueryWrapper.like(StrUtil.isNotEmpty(param.getVagueServicePhoneSearch()),"phone",param.getVagueServicePhoneSearch());
            serviceInfoList = serviceInfoService.list(studentQueryWrapper);
            if(serviceInfoList.size() <= 0)return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),serviceInfoList);
        }
        queryWrapper.in(serviceInfoList.size() > 0,"tsi.service_id",serviceInfoList.stream().map(ServiceInfoEntity::getId).collect(Collectors.toList()));
        queryWrapper.isNull("tsse.study_enroll_no");
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"tsi.operator_id",param.getOperatorId());
        // 渠道查询
        queryWrapper.eq(param.getChannel() != null,"tsi.channel",param.getChannel());
        // AND tsrvh.return_visit_status=1 AND return_visit_item=0
        // 真实姓名
        queryWrapper.like(StrUtil.isNotEmpty(param.getRealName()),"tsse.real_name", param.getRealName());
        queryWrapper.eq("tsrvh.return_visit_status", ReturnVisitStatusEnum.PRE.getCode());
        //
        // queryWrapper.gt("(SELECT COUNT(1) FROM t_service_return_visit_history WHERE t1.id = t_service_return_visit_history.student_id AND t_service_return_visit_history.return_visit_status="+ReturnVisitStatusEnum.PRE.getCode()+" AND t_service_return_visit_history.return_visit_item = "+param.getChannel()+")",0);
        // 是否有意向
        queryWrapper.eq(param.getIsIntentionSearch() !=null,"tsrvh.is_intention", param.getIsIntentionSearch());
        // 线上
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOfflineServiceId()),"tsse.user_id", param.getOfflineServiceId());
        // queryWrapper.eq("tsrvh.return_visit_item", ReturnVisitStatusEnum.NEW_USER.getCode());
        // 手机号码查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"tsi.phone",param.getPhone());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"tsi.phone",param.getVaguePhoneSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueUserNameSearch()),"tsi.username",param.getVagueUserNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"tsi.real_name",param.getVagueRealNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueEmailSearch()),"tsi.email",param.getVagueEmailSearch());
        // queryWrapper.apply(" (SELECT COUNT(1) FROM t_service_return_visit_history t3 WHERE t3.student_id =tsi.id) <=0");
        /*if (param.isReturnVisitHistory()){
            queryWrapper.apply(" (SELECT COUNT(1) FROM t_service_return_visit_history t3 WHERE t3.student_id =tsi.id) >0");
        }*/

        // tsrvh
        queryWrapper.apply(StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch()),
                "date_format (tsrvh.next_return_visit_time,'%Y-%m-%d') = date_format('" +param.getNextReturnVisitTimeSearch()  + "','%Y-%m-%d')");

        // 登录时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchLoginDate()),
                "date_format (login_time,'%Y-%m-%d') = date_format('" + param.getSearchLoginDate() + "','%Y-%m-%d')");
        // 推荐时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchRecommendDate()),
                "date_format (recommend_date,'%Y-%m-%d') <= date_format('" + LocalDate.now().toString() + "','%Y-%m-%d')");

        // 省市区
        if (StrUtil.isNotEmpty(param.getCityArr())){
            String[] splitParam = param.getCityArr().split(",");
            if (splitParam.length >=1 && StrUtil.isNotEmpty(splitParam[0]))queryWrapper.eq(StrUtil.isNotEmpty(splitParam[0]),"tsi.province_id",splitParam[0]);
            if (splitParam.length >=2 && StrUtil.isNotEmpty(splitParam[1]))queryWrapper.eq(StrUtil.isNotEmpty(splitParam[1]),"tsi.city_id",splitParam[1]);
            if (splitParam.length >=3 && StrUtil.isNotEmpty(splitParam[2]))queryWrapper.eq(StrUtil.isNotEmpty(splitParam[2]),"tsi.area_id",splitParam[2]);
        }
        if (StrUtil.isNotEmpty(param.getCreateTimeSearch())){
            String[] arr = param.getCreateTimeSearch().split(",");
            // queryWrapper.between("date_format (tsi.create_time,'%Y-%m-%d') = date_format('" + arr[0] + "','%Y-%m-%d')","date_format (tsi.create_time,'%Y-%m-%d') = date_format('" + arr[1] + "','%Y-%m-%d')");
            queryWrapper.between("tsi.create_time",arr[0],arr[1]);
        }
        // 回访时间范围
        if (param.getDateTimeSearchArr() != null && param.getDateTimeSearchArr().length > 0){
            queryWrapper.between("date_format (tsrvh.return_visit_time,'%Y-%m-%d')",param.getDateTimeSearchArr()[0],param.getDateTimeSearchArr()[1]);
        }
        // 注册时间范围
        if (param.getRegDateTimeSearchArr() != null && param.getRegDateTimeSearchArr().length > 0){
            queryWrapper.between("date_format (tsi.create_time,'%Y-%m-%d')",param.getRegDateTimeSearchArr()[0],param.getRegDateTimeSearchArr()[1]);
        }
        // 时间范围
        if (param.getAllocationDateTimeSearchArr() != null && param.getAllocationDateTimeSearchArr().length > 0){
            queryWrapper.between("date_format (tsi.operation_time,'%Y-%m-%d')",param.getAllocationDateTimeSearchArr()[0],param.getAllocationDateTimeSearchArr()[1]);
        }

        //
        queryWrapper.groupBy("tsi.id");
        String sortColumn = param.getSortColumn();
        String underSortColumn = StringUtils.lowerCamelToLowerUnderscore(sortColumn);
        queryWrapper.orderByDesc("tsrvh.return_visit_time");
        IPage<StudentInfoVo> studentInfoVoPage = studentInfoService.newStudentReturnVisitPageList(page,queryWrapper);
        if (studentInfoVoPage.getRecords().size() <= 0){
            log.error(this.getClass() +"数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoVoPage);
        }

        studentInfoVoPage.getRecords().stream().forEach((item) ->{
            QueryWrapper returnVisitHistoryQueryWrapper = new QueryWrapper();
            /*if (StrUtil.isNotEmpty(item.getId())){
                returnVisitHistoryQueryWrapper.eq("student_id",item.getId());
                int countReturnVisitHistory =serviceReturnVisitHistoryService.count(returnVisitHistoryQueryWrapper);
                if (countReturnVisitHistory > 0){
                    item.setReturnVisitHistory(true);
                }
            }*/
            if (StrUtil.isNotEmpty(item.getServiceId())){
                ServiceInfoEntity serviceInfo =serviceInfoService.getById(item.getServiceId());
                if (serviceInfo != null){
                    item.setServiceName(serviceInfo.getRealName());
                }
            }
            // 回访阶段 return_visit_time
            returnVisitHistoryQueryWrapper.eq("student_id",item.getId());
            returnVisitHistoryQueryWrapper.orderByDesc("return_visit_time");
            returnVisitHistoryQueryWrapper.last("limit 1");
            ServiceReturnVisitHistoryEntity returnVisitHistory =serviceReturnVisitHistoryService.getOne(returnVisitHistoryQueryWrapper);
            if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitStatus()))item.setReturnVisitStatus(returnVisitHistory.getReturnVisitStatus());
            if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitItem()))item.setReturnVisitItem(returnVisitHistory.getReturnVisitItem());
            if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitItem()))item.setReturnVisitTime(returnVisitHistory.getReturnVisitTime());
            if (returnVisitHistory != null && returnVisitHistory.getNextReturnVisitTime() != null)item.setNextReturnVisitTime(returnVisitHistory.getNextReturnVisitTime());
            if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitItem()))item.setIsIntention(returnVisitHistory.getIsIntention());
            QueryWrapper returnVisitHistoryCountQueryWrapper = new QueryWrapper();
            returnVisitHistoryCountQueryWrapper.eq("student_id",item.getId());
            int returnVisitHistoryCount = serviceReturnVisitHistoryService.count(returnVisitHistoryCountQueryWrapper);
            // 回访次数
            item.setReturnVisitHistoryCount(returnVisitHistoryCount);
        });
/*
        if (param.isReturnVisitHistory()){
            // 根据条件查询回访
            List<StudentInfoVo> studentInfoVoList = studentInfoVoPage.getRecords().stream().filter((StudentInfoVo student)->student.isReturnVisitHistory() == param.isReturnVisitHistory())
                    .collect(Collectors.toList());
            studentInfoVoPage.setRecords(studentInfoVoList);
            studentInfoVoPage.setTotal(studentInfoVoList.size());
        }*/
        return R.success(studentInfoVoPage);
    }
    @Override
    public ResObject oneNewStudentReturnVisitPageList(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "newStudentPageList-方法请求参数{}",param);
        Page<StudentInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.isNull("tsse.study_enroll_no");
        // 运营商查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOperatorId()),"tsi.operator_id",param.getOperatorId());
        // 渠道查询
        queryWrapper.eq(param.getChannel() != null,"tsi.channel",param.getChannel());
        // AND tsrvh.return_visit_status=1 AND return_visit_item=0
        // 真实姓名
        queryWrapper.like(StrUtil.isNotEmpty(param.getRealName()),"tsse.real_name", param.getRealName());
        // queryWrapper.eq("tsrvh.return_visit_status", ReturnVisitStatusEnum.PRE.getCode());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOnLineServiceId()),"tsi.service_id", param.getOnLineServiceId());
        // 是否有意向
        queryWrapper.eq(param.getIsIntentionSearch() !=null,"tsrvh.is_intention", param.getIsIntentionSearch());
        // 线上
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getOfflineServiceId()),"tsse.user_id", param.getOfflineServiceId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getOnLineServiceId()),"tsi.service_id", param.getOnLineServiceId());
        queryWrapper.eq("tsrvh.return_visit_item", ReturnVisitStatusEnum.NEW_USER.getCode());
        // 手机号码查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"tsi.phone",param.getPhone());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"tsi.phone",param.getVaguePhoneSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueUserNameSearch()),"tsi.username",param.getVagueUserNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"tsi.real_name",param.getVagueRealNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueEmailSearch()),"tsi.email",param.getVagueEmailSearch());
        // queryWrapper.apply(" (SELECT COUNT(1) FROM t_service_return_visit_history t3 WHERE t3.student_id =tsi.id) <=0");
        /*if (param.isReturnVisitHistory()){
            queryWrapper.apply(" (SELECT COUNT(1) FROM t_service_return_visit_history t3 WHERE t3.student_id =tsi.id) >0");
        }*/

        // tsrvh
        queryWrapper.apply(StrUtil.isNotEmpty(param.getNextReturnVisitTimeSearch()),
                "date_format (tsrvh.next_return_visit_time,'%Y-%m-%d') = date_format('" +param.getNextReturnVisitTimeSearch()  + "','%Y-%m-%d')");

        // 登录时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchLoginDate()),
                "date_format (login_time,'%Y-%m-%d') = date_format('" + param.getSearchLoginDate() + "','%Y-%m-%d')");
        // 推荐时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchRecommendDate()),
                "date_format (recommend_date,'%Y-%m-%d') <= date_format('" + LocalDate.now().toString() + "','%Y-%m-%d')");

        // 省市区
        if (StrUtil.isNotEmpty(param.getCityArr())){
            String[] splitParam = param.getCityArr().split(",");
            if (splitParam.length >=1 && StrUtil.isNotEmpty(splitParam[0]))queryWrapper.eq(StrUtil.isNotEmpty(splitParam[0]),"tsi.province_id",splitParam[0]);
            if (splitParam.length >=2 && StrUtil.isNotEmpty(splitParam[1]))queryWrapper.eq(StrUtil.isNotEmpty(splitParam[1]),"tsi.city_id",splitParam[1]);
            if (splitParam.length >=3 && StrUtil.isNotEmpty(splitParam[2]))queryWrapper.eq(StrUtil.isNotEmpty(splitParam[2]),"tsi.area_id",splitParam[2]);
        }
        if (StrUtil.isNotEmpty(param.getCreateTimeSearch())){
            String[] arr = param.getCreateTimeSearch().split(",");
            // queryWrapper.between("date_format (tsi.create_time,'%Y-%m-%d') = date_format('" + arr[0] + "','%Y-%m-%d')","date_format (tsi.create_time,'%Y-%m-%d') = date_format('" + arr[1] + "','%Y-%m-%d')");
            queryWrapper.between("tsi.create_time",arr[0],arr[1]);
        }
        //
        queryWrapper.groupBy("tsi.id");
        String sortColumn = param.getSortColumn();
        String underSortColumn = StringUtils.lowerCamelToLowerUnderscore(sortColumn);
        if (param.getIsAsc()) {
            queryWrapper.orderByAsc("tsi."+underSortColumn);
        } else {
            queryWrapper.orderByDesc("tsi."+underSortColumn);
        }
        IPage<StudentInfoVo> studentInfoVoPage = studentInfoService.oneNewStudentReturnVisitPageList(page,queryWrapper);
        if (studentInfoVoPage.getRecords().size() <= 0){
            log.error(this.getClass() +"数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoVoPage);
        }

        studentInfoVoPage.getRecords().stream().forEach((item) ->{
            QueryWrapper returnVisitHistoryQueryWrapper = new QueryWrapper();
            /*if (StrUtil.isNotEmpty(item.getId())){
                returnVisitHistoryQueryWrapper.eq("student_id",item.getId());
                int countReturnVisitHistory =serviceReturnVisitHistoryService.count(returnVisitHistoryQueryWrapper);
                if (countReturnVisitHistory > 0){
                    item.setReturnVisitHistory(true);
                }
            }*/
            if (StrUtil.isNotEmpty(item.getServiceId())){
                ServiceInfoEntity serviceInfo =serviceInfoService.getById(item.getServiceId());
                if (serviceInfo != null){
                    item.setServiceName(serviceInfo.getRealName());
                }
            }
            // 回访阶段
            returnVisitHistoryQueryWrapper.eq("student_id",item.getId());
            returnVisitHistoryQueryWrapper.orderByDesc("return_visit_time");
            returnVisitHistoryQueryWrapper.last("limit 1");
            ServiceReturnVisitHistoryEntity returnVisitHistory =serviceReturnVisitHistoryService.getOne(returnVisitHistoryQueryWrapper);
            if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitStatus()))item.setReturnVisitStatus(returnVisitHistory.getReturnVisitStatus());
            if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitItem()))item.setReturnVisitItem(returnVisitHistory.getReturnVisitItem());
            if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitItem()))item.setReturnVisitTime(returnVisitHistory.getReturnVisitTime());
            if (returnVisitHistory != null && returnVisitHistory.getNextReturnVisitTime() != null)item.setNextReturnVisitTime(returnVisitHistory.getNextReturnVisitTime());
            if (returnVisitHistory != null && StrUtil.isNotEmpty(returnVisitHistory.getReturnVisitItem()))item.setIsIntention(returnVisitHistory.getIsIntention());
        });
/*
        if (param.isReturnVisitHistory()){
            // 根据条件查询回访
            List<StudentInfoVo> studentInfoVoList = studentInfoVoPage.getRecords().stream().filter((StudentInfoVo student)->student.isReturnVisitHistory() == param.isReturnVisitHistory())
                    .collect(Collectors.toList());
            studentInfoVoPage.setRecords(studentInfoVoList);
            studentInfoVoPage.setTotal(studentInfoVoList.size());
        }*/
        return R.success(studentInfoVoPage);
    }

    @Override
    public ResObject updateBatch(List<StudentInfoEditParam> studentInfoList) {
        log.info(this.getClass() + "updateBatch-方法请求参数{}",studentInfoList);
        if(studentInfoList.size() <= 0){
            return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        String serviceId = SecurityUtils.getLoginUser().getOperationId();
        // do dto
        List<StudentInfoEntity> studentInfoEntityList = BeanConvertUtils.copyList(studentInfoList,StudentInfoEntity.class);
        studentInfoEntityList.stream().forEach((item) -> {
            // 操作者
            item.setOperationUser(AdminCacheUtil.getServiceRealName(serviceId));
            item.setOperationTime(LocalDateTime.now());
        });
        Boolean result = studentInfoService.updateBatchById(studentInfoEntityList);
        if (!result){
            return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }
        return R.success(result);
    }

    /**
    * *学员信息表 分页列表
    **/
    @Override
    public ResObject pageList(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<StudentInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(studentInfoMapStruct, param);
        queryWrapper.like(StrUtil.isNotEmpty(param.getVaguePhoneSearch()),"phone",param.getVaguePhoneSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueUserNameSearch()),"username",param.getVagueUserNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueRealNameSearch()),"real_name",param.getVagueRealNameSearch());
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueEmailSearch()),"email",param.getVagueEmailSearch());
        // 登录时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchLoginDate()),
                "date_format (login_time,'%Y-%m-%d') = date_format('" + param.getSearchLoginDate() + "','%Y-%m-%d')");
        // 推荐时间
        queryWrapper.apply(StrUtil.isNotBlank(param.getSearchRecommendDate()),
                "date_format (recommend_date,'%Y-%m-%d') <= date_format('" + LocalDate.now().toString() + "','%Y-%m-%d')");
                /*.apply(StrUtil.isNotBlank(end_date),
                        "date_format (optime,'%Y-%m-%d') <= date_format('" + end_date + "','%Y-%m-%d')")*/
        // 没有开始时间 默认查询
        if (StrUtil.isEmpty(param.getBeginTime())){
            queryWrapper.apply(StrUtil.isNotBlank(param.getSearchRecommendDate()),
                    "date_format (" +
                            ",'%Y-%m-%d') <= date_format('" + LocalDate.now().toString() + "','%Y-%m-%d')");
        }
        // 没有结束时间
        if (StrUtil.isEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),LocalDate.now().toString());
        }
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }

        IPage<StudentInfoEntity> pageList = studentInfoService.page(page,queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error(this.getClass() +"数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<StudentInfoVo> studentInfoVoPage = studentInfoMapStruct.toVoList(pageList);
        // List<Problem> problemList = problemByExample.stream().filter(problem -> "空调制冷".equals(problem.getProTitle()) || "李一一的难题1".equals(problem.getProTitle())).collect(Collectors.toList());
        studentInfoVoPage.getRecords().stream().forEach(item ->{
          /* if (StrUtil.isNotEmpty(item.getProvinceId()))item.setProvinceName(areaService.getByBaCode(item.getProvinceId()).getBaName());
           if (StrUtil.isNotEmpty(item.getCityId()))item.setCityName(areaService.getByBaCode(item.getCityId()).getBaName());
           if (StrUtil.isNotEmpty(item.getAreaId()))item.setAreaName(areaService.getByBaCode(item.getAreaId()).getBaName());*/
            // 查询推广人员
            if (StrUtil.isNotEmpty(item.getRecommendUserId())){
                StudentInfoEntity studentInfo = studentInfoService.getById(item.getRecommendUserId());
                if (studentInfo != null && StrUtil.isNotEmpty(studentInfo.getRealName()))item.setRecommendUserName(studentInfo.getRealName());
                if (studentInfo != null && StrUtil.isNotEmpty(studentInfo.getPhone()))item.setRecommendUserPhone(studentInfo.getPhone());
            }
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",studentInfoVoPage);
        return R.success(studentInfoVoPage);
    }

    @Override
    public ResObject findList(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(studentInfoMapStruct, param);
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueSearch()),"phone",param.getVagueSearch());
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<StudentInfoEntity> pageList = studentInfoService.list(queryWrapper);
        List<StudentInfoVo> studentInfoVoList = studentInfoMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",studentInfoVoList);
        if (studentInfoVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoVoList);
        }
        return R.success(studentInfoVoList);
    }

    /**
     * *通过ID获取学员信息表 列表
     **/
    @Override
    public ResObject<StudentInfoVo> getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        StudentInfoEntity studentInfo = studentInfoService.getById(id);
        if (studentInfo == null){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfo);
        }
        StudentInfoVo studentInfoVo = BeanConvertUtils.copy(studentInfo, StudentInfoVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",studentInfoVo);
        if (studentInfoVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoVo);
        }
        if (StrUtil.isNotEmpty(studentInfoVo.getServiceId())){
            ServiceInfoEntity serviceInfo =serviceInfoService.getById(studentInfoVo.getServiceId());
            if (serviceInfo != null){
                studentInfoVo.setServiceName(serviceInfo.getRealName());
            }
        }
        return R.success(studentInfoVo);
    }

    @Override
    public ResObject<StudentInfoVo> getInfo(StudentInfoPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(studentInfoMapStruct, param);
        StudentInfoEntity studentInfo = studentInfoService.getOne(queryWrapper);
        StudentInfoVo studentInfoVo = BeanConvertUtils.convertBean(studentInfo,StudentInfoVo.class);
        //
        if(studentInfoVo == null){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),studentInfoVo);
        }
        return R.success(studentInfoVo);
    }

    /**
     * *保存学员信息表 信息
     **/
    @Override
    public ResObject save(StudentInfoEditParam installParam) {

        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentInfoEntity studentInfo = BeanConvertUtils.copy(installParam, StudentInfoEntity.class);
        Boolean result = studentInfoService.save(studentInfo);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改学员信息表 信息
     **/
    @Override
    public ResObject update(StudentInfoEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        StudentInfoEntity studentInfo = BeanConvertUtils.copy(updateParam, StudentInfoEntity.class);
        Boolean result = studentInfoService.updateById(studentInfo);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除学员信息表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(studentInfoService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除学员信息表 信息
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
        Boolean result = studentInfoService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出学员信息表 信息
     **/
    @Override
    public ResObject exportXls(StudentInfoPageQueryParam param, HttpServletResponse response) {
        log.info(this.getClass() +"exportXls-方法请求参数");
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(StudentInfoEditParam param) {

        StudentInfoEntity StudentInfoEntity = new StudentInfoEntity();
        StudentInfoEntity.setId(param.getId());
        StudentInfoEntity.setStatus(param.getStatus());
        Boolean result = studentInfoService.updateById(StudentInfoEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",StudentInfoEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }


}

