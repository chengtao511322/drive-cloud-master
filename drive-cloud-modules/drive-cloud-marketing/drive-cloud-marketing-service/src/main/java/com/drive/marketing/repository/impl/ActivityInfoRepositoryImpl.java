package com.drive.marketing.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.api.RemoteRecommendUserFeignService;
import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.basics.feign.RemoteOperatorFeignService;
import com.drive.basics.pojo.vo.OperatorVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.dto.ActivityInfoPageQueryParam;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.vo.*;
import com.drive.marketing.repository.ActivityInfoRepository;
import com.drive.marketing.repository.RecommendManagertRepository;
import com.drive.marketing.service.CouponGetService;
import com.drive.marketing.service.IActivityInfoService;
import com.drive.marketing.service.mapstruct.ActivityMapStruct;
import com.drive.marketing.service.mapstruct.CouponGetMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityInfoRepositoryImpl implements ActivityInfoRepository {

    @Autowired
    private IActivityInfoService activityInfoService;

    @Autowired
    private ActivityMapStruct activityMapStruct;
    @Autowired
    private CouponGetMapStruct couponGetMapStruct;

    @Autowired
    @Lazy
    private RemoteStudentFeignService remoteStudentFeignService;

    @Autowired
    private CouponGetService couponGetService;

    @Autowired
    @Lazy
    private RemoteOperatorFeignService remoteOperatorFeignService;

    @Autowired
    @Lazy
    private RemoteRecommendUserFeignService remoteRecommendUserFeignService;

    @Autowired
    @Lazy
    private RecommendManagertRepository recommendManagertRepository;

    private Lock lock = new ReentrantLock(true);

    @Override
    public ResObject reduceInventoryRollback() {
        log.info(this.getClass() + "reduceInventoryRollback-??????????????????{}");
        ActivityInfoEntity activityInfoEntity = new ActivityInfoEntity();
        activityInfoEntity.setId("1215185452315754496");
        activityInfoEntity.setDescription("????????????");
        activityInfoService.updateById(activityInfoEntity);
        //????????????
        int i = 1 / 0;
        return R.success();
    }

    @Override
    public ResObject getActivityCouponRelationByCondition(ActivityCouponRelationEditParam activityCouponRelationEditParam) {
        log.info(this.getClass() + "getActivityCouponRelationByCondition-??????????????????{}",activityCouponRelationEditParam);
        if (StrUtil.isEmpty(activityCouponRelationEditParam.getActivityId())){
            log.error("???????????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ActivityCouponRelationVo activityCouponRelationVo = activityInfoService.getActivityCouponRelationByCondition(activityCouponRelationEditParam);
        if (activityCouponRelationVo == null) {
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(activityCouponRelationVo);
    }

    @Override
    public ResObject getCouponByActivityId(String activityId) {
        log.info(this.getClass() + "getCouponByActivityId-??????????????????{}",activityId);
        if (StrUtil.isEmpty(activityId)){
            log.error("??????ID-?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        List<ActivityCouponRelationVo> activityCouponRelationVos = activityInfoService.getActivityCouponRelation(activityId);
        if (activityCouponRelationVos == null){
            log.error("???????????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(activityCouponRelationVos);
    }

    @Override
    public ResObject findCouponPageListByActivityId(CouponGetPageQueryParam activityEditParam) {
        log.info(this.getClass() + "findCouponPageListByActivityId-??????????????????{}",activityEditParam);
        if (StrUtil.isEmpty(activityEditParam.getActivityId())){
            log.error("??????ID-?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ??????????????????
        QueryWrapper<CouponGetPageQueryParam> wrapper = new QueryWrapper<CouponGetPageQueryParam>();
        // ????????????
        Page<ActivityCouponGetVo> page = new Page<ActivityCouponGetVo>(activityEditParam.getPageNum(), activityEditParam.getPageSize());
        // ????????????
        wrapper.eq("t2.is_delete", StatusEnum.ENABLE.getCode());
        // ???????????????
        wrapper.eq("t2.status",1);
        // ??????ID ??????
        wrapper.eq("t1.source",activityEditParam.getActivityId());
        // ?????????????????????  1 ????????? 2 ????????? 3???????????? 4 ??????
        wrapper.eq(StrUtil.isNotEmpty(activityEditParam.getStatus()),"t1.status",activityEditParam.getStatus());
        // ???????????????
        wrapper.like(StrUtil.isNotEmpty(activityEditParam.getCouponName()),"t2.name",activityEditParam.getCouponName());
        //?????????????????????
        wrapper.like(StrUtil.isNotEmpty(activityEditParam.getUserName()),"t1.phone",activityEditParam.getPhone());
        //????????????????????????
        if(StrUtil.isNotEmpty(activityEditParam.getPromoteUserPhone())){
            ResObject<RecommendUserVo> res = remoteRecommendUserFeignService.getRecommendUserByPhone(activityEditParam.getPromoteUserPhone());
            if(res.getSubCode().equalsIgnoreCase("success") && res.getCode() == 200 &&res.getData() != null){
                RecommendUserVo rcVo = res.getData();
                wrapper.eq("promote_user_id",rcVo.getId());
            }else {
                //??????????????????
                return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
            }
        }
        // ?????????????????????
        wrapper.between(StrUtil.isNotEmpty(activityEditParam.getBeginTime()),"get_time",activityEditParam.getBeginTime(),activityEditParam.getEndTime());
        wrapper.eq(StrUtil.isNotEmpty(activityEditParam.getTenantId()),"t1.tenant_id",activityEditParam.getTenantId());
        //wrapper.eq(StrUtil.isNotEmpty(activityEditParam.getPromoteUserId()),"promote_user_id",activityEditParam.getPromoteUserId());
        //???????????????????????????
        wrapper.ge(StrUtil.isNotEmpty(activityEditParam.getUseBeginTime()),"t2.start_time",activityEditParam.getUseBeginTime());
        wrapper.le(StrUtil.isNotEmpty(activityEditParam.getUseEndTime()),"t2.end_time",activityEditParam.getUseEndTime());
        // ????????????
        wrapper.orderByDesc("t1.create_time");
        IPage<ActivityCouponGetVo> pageList = couponGetService.findCouponPageListByActivityId(page, wrapper);
        if (pageList.getRecords().size() <= 0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        /*ResObject res = remoteRecommendUserFeignService.getByIdInfo("83eaf614fc8c4632b99d");
        log.info("??????????????????{}",res);*/
        String[] studentIds = pageList.getRecords().stream().map(ActivityCouponGetVo::getUserId).toArray(String[]::new);
        String[] promoteUserIds = pageList.getRecords().stream().map(ActivityCouponGetVo::getPromoteUserId).toArray(String[]::new);

        Map<String, StudentInfoRpcVo> studentInfoVo = this.getBatchStudent(studentIds);
        log.info("????????????{}",studentInfoVo);

        Map<String, RecommendUserVo> recommendUserVo = this.getRecommendUserVo(promoteUserIds);
        log.info("???????????????{}",recommendUserVo);
        // ????????????
        pageList.getRecords().forEach((item) ->{
            if (studentInfoVo != null && studentInfoVo.get(item.getUserId()) != null)item.setUserName(studentInfoVo.get(item.getUserId()).getUsername());
            if (studentInfoVo != null && studentInfoVo.get(item.getUserId()) != null)item.setPhone(studentInfoVo.get(item.getUserId()).getPhone());
            if (recommendUserVo != null && recommendUserVo.get(item.getPromoteUserId()) != null)item.setPromoteUser(recommendUserVo.get(item.getPromoteUserId()));
        });
        return R.success(pageList);
    }

    @Override
    public ResObject pageList(ActivityInfoPageQueryParam param) {
        log.info(this.getClass() + "pageList-??????????????????{}",param);
        QueryWrapper<ActivityInfoEntity> queryWrapper = new QueryWrapper<ActivityInfoEntity>();
        queryWrapper.like(StrUtil.isNotEmpty(param.getZoneName()),"zone_name",param.getZoneName());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getTenantId()),"tenant_id",param.getTenantId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStatus()),"status",param.getStatus());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getType()),"type",param.getType());
        Page<ActivityInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        queryWrapper.orderByDesc("create_time");
        IPage<ActivityInfoEntity> pageList = activityInfoService.page(page, queryWrapper);
        Page<ActivityInfoVo> deptVoPage = activityMapStruct.toVoList(pageList);
       /* deptVoPage.getRecords().stream().forEach((item) ->{
            OperatorVo operatorVo = null;
            // ???????????????
            ResObject<OperatorVo> operatorVoResObject =remoteOperatorFeignService.get(item.getTenantId());
            log.info("???????????????????????????{}",operatorVoResObject);
            if (operatorVoResObject.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && operatorVoResObject.getData() != null){
                operatorVo = operatorVoResObject.getData();
            }
            log.info("???????????????????????????{}",operatorVo);
            if (operatorVo!= null && StrUtil.isNotEmpty(operatorVo.getName()))item.setTenantName(operatorVo.getName());
        });*/
        log.info(this.getClass() + "??????????????????{}",deptVoPage.getRecords());
        return R.success(deptVoPage);
    }

    /**
     * ???????????????
     * @param ids
     * @return
     */
    @Async
    Map<String, StudentInfoRpcVo> getBatchStudent(String[] ids){
        Map<String, StudentInfoRpcVo> studentInfoVos = null;
        ResObject<Map<String, StudentInfoRpcVo>> resObject = remoteStudentFeignService.batchStudent(ids);

       log.info("??????????????????{}",resObject);
       // ??????????????????????????????
       if (resObject.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && resObject.getData() != null){
           studentInfoVos =  resObject.getData();
       }
       return studentInfoVos;

    }

    /**
     * ??????????????????
     * @param tenantId
     * @return
     */
    @Async
    OperatorVo getOperatorVo(String tenantId){
        OperatorVo operatorVo = null;
        // ???????????????
        ResObject<OperatorVo> operatorVoResObject =remoteOperatorFeignService.get(tenantId);
        log.info("???????????????????????????{}",operatorVoResObject);
        if (operatorVoResObject.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && operatorVoResObject.getData() != null){
            operatorVo = operatorVoResObject.getData();
        }
        log.info("???????????????????????????{}",operatorVo);
        return operatorVo;
    }

    /**
     * ???????????????
     * @param promoteUserId
     * @return
     */
    @Async
    Map<String, RecommendUserVo> getRecommendUserVo(String[] promoteUserId){
        Map<String, RecommendUserVo> recommendUserVo = null;
        // ???????????????
        ResObject<Map<String, RecommendUserVo>>  promoteUser = remoteRecommendUserFeignService.batchRecommendUserVo(promoteUserId);
        if (promoteUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && promoteUser.getData() != null){
            recommendUserVo = promoteUser.getData();
        }
        log.info("???????????????????????????{}",recommendUserVo);
        return recommendUserVo;
    }

    /**
     * ????????????????????????
     * @param ids ??????id
     * @return
     */
    @Override
    public ResObject<Map<String, ActivityInfoVo>> batchActivityInfo(String[] ids) {
        log.info("-getByIdInfo-??????????????????{}",ids);
        if(ids.length <= 0){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        List<ActivityInfoEntity> activityInfoList= activityInfoService.listByIds(Arrays.asList(ids));
        if (activityInfoList.isEmpty())return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        List<ActivityInfoVo> activityInfoVos = BeanConvertUtils.copyList(activityInfoList,ActivityInfoVo.class);
        Map<String, ActivityInfoVo> appleMap = activityInfoVos.stream().collect(Collectors.toMap(ActivityInfoVo::getId, a -> a,(k1, k2)->k1));
        return R.success(appleMap);
    }
}
