package com.drive.marketing.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.api.RemoteRecommendManagerFeignService;
import com.drive.admin.api.RemoteRecommendUserFeignService;
import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.vo.RecommendManagerVo;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.basics.feign.RemoteChannelAuthFeignService;
import com.drive.basics.feign.RemoteChannelFeignService;
import com.drive.basics.feign.RemoteOperatorFeignService;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.ExclusiveEnum;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.marketing.asyn.ChannelManagerAsync;
import com.drive.marketing.feign.RecommendManagerFeign;
import com.drive.marketing.feign.RecommendUserFeign;
import com.drive.marketing.feign.StudentFeign;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.pojo.entity.CouponEntity;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.vo.ActivityCouponGetVo;
import com.drive.marketing.pojo.vo.ActivityCouponRelationVo;
import com.drive.marketing.pojo.vo.ChannelManagerActivityVo;
import com.drive.marketing.pojo.vo.CouponGetVo;
import com.drive.marketing.repository.ActivityInfoRepository;
import com.drive.marketing.repository.ChannelManagerActivityRepository;
import com.drive.marketing.service.*;
import com.drive.marketing.service.mapstruct.ChannelManagerActivityMapStruct;
import com.drive.marketing.service.mapstruct.CouponGetMapStruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class    ChannelManagerActivityRepositoryImpl implements ChannelManagerActivityRepository {

    @Autowired
    private ChannelManagerActivityService channelManagerActivityService;

    @Autowired
    private ChannelManagerActivityMapStruct channelManagerActivityMapStruct;

    @Autowired
    private ActivityPromotionService activityPromotionService;

    @Autowired
    private ActivityInfoRepository activityInfoRepository;

    @Autowired
    private ActivityPromoteAuthService activityPromoteAuthService;

    @Autowired
    private RemoteChannelAuthFeignService channelAuthFeignService;

    @Autowired
    private RecommendUserFeign recommendUserFeign;
    @Autowired
    private RecommendManagerFeign recommendManagerFeign;
    @Autowired
    private StudentFeign studentFeign;


    //@Autowired
    //private ChannelManagerActivityRepository channelManagerActivityRepository;


    //@Autowired
    //private RecommendManagertRepository recommendManagertRepository;

    @Autowired
    private IActivityInfoService activityInfoService;

    @Autowired
    private RemoteChannelFeignService remoteChannelFeignService;

    @Autowired
    private RemoteRecommendUserFeignService remoteRecommendUserFeignService;

    @Autowired
    private RemoteRecommendManagerFeignService remoteRecommendManagerFeignService;

    @Autowired
    private RemoteOperatorFeignService operatorFeignService;

    @Autowired
    private RemoteStudentFeignService remoteStudentFeignService;


    @Autowired
    private CouponGetService couponGetService;

    @Autowired
    private CouponGetMapStruct couponGetMapStruct;

    @Autowired
    private CouponService couponService;

    @Autowired
    private ChannelManagerAsync channelManagerAsync;

    @Override
    @Transactional
    public ResObject changeChannelManagerStatus(ChannelManagerActivityEditParam channelManagerActivityEditParam) {
        log.info(this.getClass() + "changeChannelManagerStatus-??????????????????{}",channelManagerActivityEditParam);
        if (StrUtil.isEmpty(channelManagerActivityEditParam.getId())){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(channelManagerActivityEditParam.getActivityId())){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ChannelManagerActivityEntity channelManagerActivity = BeanConvertUtils.copy(channelManagerActivityEditParam,ChannelManagerActivityEntity.class);
        // ??????????????????
        Boolean channelManagerResult = channelManagerActivityService.updateById(channelManagerActivity);
        // ?????? ??????
        if (!channelManagerResult){
            throw new BizException("????????????");
        }
        // ????????????????????????????????????????????????
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // ???????????????????????????
        queryWrapper.eq("promotion_type",2);
        // ??????????????????
        queryWrapper.eq("activity_id",channelManagerActivityEditParam.getActivityId());
        // ??????????????????
        queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getChannelManagerId()),"channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getPromotionUserId()),"promotion_user_id",channelManagerActivityEditParam.getPromotionUserId());
        List<ChannelManagerActivityEntity> channelManagerActivityList = channelManagerActivityService.list(queryWrapper);
        // ???????????????????????????
        if (channelManagerActivityList.size() > 0){
            channelManagerActivityList.forEach((item) ->{
                // ???????????????
                item.setStatus(channelManagerActivityEditParam.getStatus());
                //item.setChannelManagerId(channelManagerActivityEditParam.getId());
                item.setActivityId(channelManagerActivityEditParam.getActivityId());
            });
            // ???????????????
            Boolean promotionResult = channelManagerActivityService.updateBatchById(channelManagerActivityList);
            // ?????? ??????
            if (!promotionResult){
                throw  new BizException("????????????");
            }
        }
        return  R.success("????????????");
    }
    @Override
    @Transactional
    public ResObject changePromotionUserStatus(ChannelManagerActivityEditParam channelManagerActivityEditParam) {
        log.info(this.getClass() + "changeChannelManagerStatus-??????????????????{}",channelManagerActivityEditParam);
        if (StrUtil.isEmpty(channelManagerActivityEditParam.getId())){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(channelManagerActivityEditParam.getActivityId())){
            log.error("?????????");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ChannelManagerActivityEntity channelManagerActivity = BeanConvertUtils.copy(channelManagerActivityEditParam,ChannelManagerActivityEntity.class);
        // ??????????????????
        Boolean channelManagerResult = channelManagerActivityService.updateById(channelManagerActivity);
        // ?????? ??????
        if (!channelManagerResult){
            throw  new BizException("????????????");
        }
        return  R.success("????????????");
    }

    @Override
    public ResObject pageChannelManagerList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "pageList ????????????{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        queryWrapper.eq("activity_id",param.getActivityId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        // ??????
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        // ???????????????
        // ??????????????????
        String[] promotionUserIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getPromotionUserId).toArray(String[]::new);
        String[] channelManagerIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getChannelManagerId).toArray(String[]::new);
        Map<String, RecommendUserVo> recommendUserVo = recommendUserFeign.getBatchRecommendUserVo(promotionUserIds);
        Map<String, RecommendManagerVo> channelManagerVo = recommendManagerFeign.getBatchRecommendManagerVo(channelManagerIds);
        channelManagerActivityVoPage.getRecords().stream().forEach((item) ->{
            // ?????????????????? RecommendManagerVo
            if (channelManagerVo != null)item.setChannelManager(channelManagerVo.get(item.getChannelManagerId()));
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // ???????????????
            if (recommendUserVo != null)item.setRecommendUser(recommendUserVo.get(item.getPromotionUserId()));
            // ???????????????
            ActivityCouponRelationEditParam activityCouponRelationEditParam = new ActivityCouponRelationEditParam();
            // ????????????ID
            activityCouponRelationEditParam.setActivityId(item.getActivityId());
            // ????????????ID
            activityCouponRelationEditParam.setProjectId(item.getProjectId());
            // ?????????????????????
            ActivityCouponRelationVo activityCouponRelationVo = activityInfoService.getActivityCouponRelationByCondition(activityCouponRelationEditParam);
            item.setActivityCouponRelationVo(activityCouponRelationVo);
        });
        log.info("channelManagerActivityVoPage{}",channelManagerActivityVoPage);
        return R.success(channelManagerActivityVoPage);
    }

    @Override
    public ResObject findList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "pageList ????????????{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // ??????ID
        queryWrapper.eq("activity_id",param.getActivityId());
        // ??????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        //
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStatus()),"status",param.getStatus());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.groupBy("channel_manager_id");
        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        // ??????????????????
        String[] promotionUserIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getPromotionUserId).toArray(String[]::new);
        String[] channelManagerIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getChannelManagerId).toArray(String[]::new);
        Map<String, RecommendUserVo> recommendUserVo = recommendUserFeign.getBatchRecommendUserVo(promotionUserIds);
        Map<String, RecommendManagerVo> channelManagerVo = recommendManagerFeign.getBatchRecommendManagerVo(channelManagerIds);
        channelManagerActivityVoPage.getRecords().forEach((item) ->{
            if (channelManagerVo != null)item.setChannelManager(channelManagerVo.get(item.getChannelManagerId()));
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // ???????????????
            if (recommendUserVo != null)item.setRecommendUser(recommendUserVo.get(item.getPromotionUserId()));
        });
        log.info(this.getClass() + "findList-??????????????????{}",channelManagerActivityVoPage);
        return R.success(channelManagerActivityVoPage);
    }

    @Override
    public ResObject findPromotionPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findPromotionList ????????????{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // ??????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getActivityId()),"activity_id",param.getActivityId());
        // ??????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.groupBy("promotion_user_id");
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        String[] promotionUserIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getPromotionUserId).toArray(String[]::new);
        String[] channelManagerIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getChannelManagerId).toArray(String[]::new);
        Map<String, RecommendUserVo> recommendUserVo = recommendUserFeign.getBatchRecommendUserVo(promotionUserIds);
        Map<String, RecommendManagerVo> channelManagerVo = recommendManagerFeign.getBatchRecommendManagerVo(channelManagerIds);
        channelManagerActivityVoPage.getRecords().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // ?????????????????? RecommendManagerVo
            if (channelManagerVo != null)item.setChannelManager(channelManagerVo.get(item.getChannelManagerId()));
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // ???????????????
            if (recommendUserVo != null)item.setRecommendUser(recommendUserVo.get(item.getPromotionUserId()));
        });
        log.info(this.getClass() + "findList-??????????????????{}",channelManagerActivityVoPage);
        return R.success(channelManagerActivityVoPage);
    }
    public ResObject findPromotionPageListByManagerId(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findPromotionList ????????????{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // ??????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getActivityId()),"activity_id",param.getActivityId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        // ??????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        // ????????????  ???????????? ?????????  1:???????????? 2 ?????????
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",2);
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        //queryWrapper.groupBy("channel_manager_id");
        //queryWrapper.groupBy("user_id");
        //  ???????????? ???????????????????????????
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        // ?????????
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);

        String[] promotionUserIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getPromotionUserId).toArray(String[]::new);
        String[] channelManagerIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getChannelManagerId).toArray(String[]::new);
        Map<String, RecommendUserVo> recommendUserVo = recommendUserFeign.getBatchRecommendUserVo(promotionUserIds);
        Map<String, RecommendManagerVo> channelManagerVo = recommendManagerFeign.getBatchRecommendManagerVo(channelManagerIds);
        channelManagerActivityVoPage.getRecords().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // ?????????????????? RecommendManagerVo
            if (channelManagerVo != null)item.setChannelManager(channelManagerVo.get(item.getChannelManagerId()));
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // ???????????????
            if (recommendUserVo != null)item.setRecommendUser(recommendUserVo.get(item.getPromotionUserId()));
        });
        log.info(this.getClass() + "findList-??????????????????{}",channelManagerActivityVoPage);
        return R.success(channelManagerActivityVoPage);
    }
    @Override
    public ResObject findChannelManagerOrPromotionPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList ????????????{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityPageQueryParam> queryWrapper = new QueryWrapper<ChannelManagerActivityPageQueryParam>();
        // ??????ID
        queryWrapper.eq("activity_id",param.getActivityId());
        // ??????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"t1.user_id",param.getUserId());
        // ??????????????????
        queryWrapper.eq(param.getPromotionType() != null,"t1.promotion_type",param.getPromotionType());
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"t1.promotion_user_id",param.getPromotionUserId());
        // ??????????????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"t1.channel_manager_id",param.getChannelManagerId());
        // ???x?????????
        queryWrapper.eq(StringUtils.isNotEmpty(param.getProjectId()),"t1.project_id",param.getProjectId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"t1.create_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("t1.create_time");
        queryWrapper.groupBy("t1.promotion_user_id");
        queryWrapper.eq("t1.is_delete", StatusEnum.ENABLE.getCode());
        //queryWrapper.eq("t1.status", StatusEnum.ENABLE.getCode());
        IPage<ChannelManagerActivityVo> pageList = channelManagerActivityService.findPage(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        String[] promotionUserIds = pageList.getRecords().stream().map(ChannelManagerActivityVo::getPromotionUserId).toArray(String[]::new);
        String[] channelManagerIds = pageList.getRecords().stream().map(ChannelManagerActivityVo::getChannelManagerId).toArray(String[]::new);
        Map<String, RecommendUserVo> recommendUserVo = recommendUserFeign.getBatchRecommendUserVo(promotionUserIds);
        Map<String, RecommendManagerVo> channelManagerVo = recommendManagerFeign.getBatchRecommendManagerVo(channelManagerIds);
        // Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        pageList.getRecords().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // ?????????????????? RecommendManagerVo
            if (channelManagerVo != null)item.setChannelManager(channelManagerVo.get(item.getChannelManagerId()));
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // ???????????????
            if (recommendUserVo != null)item.setRecommendUser(recommendUserVo.get(item.getPromotionUserId()));
        });
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList-??????????????????{}",pageList.getRecords());
        return R.success(pageList);
    }

    @Override
    public ResObject findPromotionUserPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findPromotionUserPageList-??????????????????{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(param.getPromotionUserId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper<CouponGetEntity> queryWrapper = new QueryWrapper<CouponGetEntity>();
        // ????????????ID ??????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getActivityId()),"source",param.getActivityId());
        //???????????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promote_user_id",param.getPromotionUserId());
        // ??????ID ??????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        // ??????????????????
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"get_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.groupBy("user_id");
        Page<CouponGetEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<CouponGetEntity> pageList = couponGetService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // ??????
        Page<CouponGetVo> couponGetVoPage = couponGetMapStruct.toVoList(pageList);

        String[] studentIds = couponGetVoPage.getRecords().stream().map(CouponGetVo::getUserId).toArray(String[]::new);
        String[] couponIds = couponGetVoPage.getRecords().stream().map(CouponGetVo::getCouponId).toArray(String[]::new);
        Map<String, StudentInfoRpcVo> studentInfoVo = studentFeign.getBatchStudent(studentIds);
        Map<String, CouponEntity> coupon = couponService.batchCoupon(couponIds);

        couponGetVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId())){
                if (studentInfoVo != null && StrUtil.isNotEmpty(studentInfoVo.get(item.getUserId()).getPhone()))item.setPhone(studentInfoVo.get(item.getUserId()).getPhone());
                if (studentInfoVo != null && StrUtil.isNotEmpty(studentInfoVo.get(item.getUserId()).getUsername()))item.setUserName(studentInfoVo.get(item.getUserId()).getUsername());
                if (coupon != null && StrUtil.isNotEmpty(coupon.get(item.getCouponId()).getName()))item.setCouponName(coupon.get(item.getCouponId()).getName());
            }
        });
        log.info(this.getClass() + "findPromotionUserPageList-??????????????????{}",couponGetVoPage.getRecords());
        return R.success(couponGetVoPage);
    }

    @Override
    public ResObject findChannelManagerPromotionUserPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() +"findChannelManagerPromotionUserPageList-??????????????????{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(param.getChannelManagerId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (param.getPromotionType() == null){
            log.error("???????????????");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // ??????ID
        queryWrapper.eq("activity_id",param.getActivityId());
        // ??????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        // ??????????????????
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        // ??????????????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        // ???x?????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getProjectId()),"project_id",param.getProjectId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.groupBy("promotion_user_id");
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        // ??????????????????
        String[] promotionUserIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getPromotionUserId).toArray(String[]::new);
        String[] channelManagerIds = channelManagerActivityVoPage.getRecords().stream().map(ChannelManagerActivityVo::getChannelManagerId).toArray(String[]::new);
        Map<String, RecommendUserVo> recommendUserVo = recommendUserFeign.getBatchRecommendUserVo(promotionUserIds);
        Map<String, RecommendManagerVo> channelManagerVo = recommendManagerFeign.getBatchRecommendManagerVo(channelManagerIds);
        channelManagerActivityVoPage.getRecords().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // ?????????????????? RecommendManagerVo
            if (channelManagerVo != null)item.setChannelManager(channelManagerVo.get(item.getChannelManagerId()));
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // ???????????????
            if (recommendUserVo != null)item.setRecommendUser(recommendUserVo.get(item.getPromotionUserId()));
        });
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList-??????????????????{}",pageList.getRecords());
        return R.success(channelManagerActivityVoPage);
    }

    @Override
    public ResObject findActivityProjectPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findActivityProjectPageList-??????????????????{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(param.getUserId())){
            log.error("??????ID???");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // ????????????ID ??????
        queryWrapper.eq("activity_id",param.getActivityId());
        //???????????????
        queryWrapper.eq("user_id",param.getUserId());
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("?????????");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // ??????
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findActivityProjectPageList-??????????????????{}",channelManagerActivityVoPage.getRecords());
        return R.success(channelManagerActivityVoPage);
    }

    @Override
    @Transactional
    public ResObject addActivityPromotion(List<ChannelManagerActivityEditParam> channelManagerActivityEditParamList) throws BizException {
        List<ChannelManagerActivityEntity> channelManagerActivity = BeanConvertUtils.copyList(channelManagerActivityEditParamList,ChannelManagerActivityEntity.class);
        List<ChannelManagerActivityEntity> channelManagerActivityList = new ArrayList<>();
        for (ChannelManagerActivityEntity item:
                channelManagerActivity) {
            QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
            queryWrapper.eq("activity_id",item.getActivityId());
            queryWrapper.eq("project_id",item.getProjectId());
            queryWrapper.eq("promotion_type",item.getPromotionType());
            queryWrapper.eq("promotion_user_id",item.getPromotionUserId());
            //queryWrapper.eq("promotion_user_id",item.getPromotionUserId());
            int result = channelManagerActivityService.count(queryWrapper);
            if (result <= 0){
                channelManagerActivityList.add(item);
            }
        };
        //
        if (channelManagerActivityList !=null){
            // ?????????get(0) ????????? ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
            // ????????????
            String channelId = channelManagerActivityEditParamList.get(0).getChannelId();

            String activityId = channelManagerActivityEditParamList.get(0).getActivityId();
            String userId = channelManagerActivityEditParamList.get(0).getUserId();
            String tenantId = channelManagerActivityEditParamList.get(0).getTenantId();
            if (StrUtil.isNotEmpty(channelId)){
                // ????????????
          /*    ChannelEditParam channelEditParam = new ChannelEditParam();
                channelEditParam.setId(channelId);
                //  String[] arrChannel = channelVoRes.getData().getAuth().split(",");
					*//*List<String> arr = new ArrayList<String>();
					Arrays.stream(arrChannel).forEach((item)->{
						arr.add(item);
					});
					arr.add(channelManagerActivityEditParam.get(0).getUserId());
					String auth = Joiner.on(",").join(arr);*//*
                channelEditParam.setAuth(channelManagerActivityEditParamList.get(0).getUserId());
                channelEditParam.setUpdateTime(LocalDateTime.now());
                remoteChannelFeignService.updateChannel(channelEditParam);*/

                // ????????????
                // ??????????????????
               /* ActivityPromoteAuthEntity activityPromoteAuthEntity = new ActivityPromoteAuthEntity();
                // ????????????ID
                activityPromoteAuthEntity.setActivityId(activityId);
                // ??????????????????ID
                activityPromoteAuthEntity.setChannelId(channelId);
                // ????????????ID
                activityPromoteAuthEntity.setUserId(userId);
                // ??????ID
                activityPromoteAuthEntity.setTenantId(tenantId);
                // ??????
                QueryWrapper<ActivityPromoteAuthEntity> authQueryWrapper = new QueryWrapper<ActivityPromoteAuthEntity>();
                authQueryWrapper.eq("activity_id",activityId);
                authQueryWrapper.eq("channel_id",channelId);
                authQueryWrapper.eq("user_id",userId);
                authQueryWrapper.eq("tenant_id",tenantId);
                Boolean delAuth = activityPromoteAuthService.remove(authQueryWrapper);
                log.info("????????????????????????{}",delAuth);
                // ??????
                Boolean saveAuth = activityPromoteAuthService.save(activityPromoteAuthEntity);
                log.info("????????????????????????{}",saveAuth);*/
                // ????????????
                // ????????????  ????????????
                ChannelAuthEditParam channelAuthEditParam = new ChannelAuthEditParam();
                // ??????ID
                channelAuthEditParam.setChannelId(channelId);
                // ??????ID
                channelAuthEditParam.setUserId(userId);
                // ??????ID
                channelAuthEditParam.setTenantId(tenantId);
                ResObject resObject = channelAuthFeignService.updateChannelAuth(channelAuthEditParam);
                log.info(this.getClass() + "????????????{}",resObject);
                log.info(this.getClass() + "????????????{}",resObject);
                if(!(resObject.getCode() .equals(ResCodeEnum.SUCCESS.getCode()))){
                    throw new BizException(500,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
                }

                return R.success(channelManagerActivityService.saveBatch(channelManagerActivityList));
            }
        }
        return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"????????????????????????");
    }

    @Override
    @Transactional
    public ResObject saveBatch(List<ChannelManagerActivityEditParam> channelManagerActivityEditParam) {
        List<ChannelManagerActivityEntity> channelManagerActivity = BeanConvertUtils.copyList(channelManagerActivityEditParam,ChannelManagerActivityEntity.class);
        List<ChannelManagerActivityEntity> channelManagerActivityList = new ArrayList<>();
        for (ChannelManagerActivityEntity item:
                channelManagerActivity) {
            QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
            queryWrapper.eq("activity_id",item.getActivityId());
            queryWrapper.eq("project_id",item.getProjectId());
            queryWrapper.eq("channel_manager_id",item.getChannelManagerId());
            //queryWrapper.eq("promotion_user_id",item.getPromotionUserId());
            int result = channelManagerActivityService.count(queryWrapper);
            if (result <= 0){
                //channelManagerActivity.remove(item);
                //return R.failure("??????????????????");
                channelManagerActivityList.add(item);
            }


            // ??????????????????
			/*RecommendUserPageQueryParam recommendUserPageQueryParam = new RecommendUserPageQueryParam();
			recommendUserPageQueryParam.setChannelManagerId(item.getChannelManagerId());
			// bbdc1bd499b241daa6fe99063e63a193
			recommendUserPageQueryParam.setTenantId(item.getTenantId());
			JSONArray recommendUserList = recommendManagertRepository.recommendUserList(recommendUserPageQueryParam);
			for (Object i:
			recommendUserList) {
				JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(i));
				String id = json.getString("id");
				String studentId = json.getString("studentId");

				QueryWrapper<ChannelManagerActivityEntity> queryChannelManagerResultWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
				queryChannelManagerResultWrapper.eq("activity_id",item.getActivityId());
				queryChannelManagerResultWrapper.eq("project_id",item.getProjectId());
				queryChannelManagerResultWrapper.eq("channel_manager_id",item.getChannelManagerId());
				queryChannelManagerResultWrapper.eq("promotion_user_id",id);
				int channelManagerResult = channelManagerActivityService.count(queryChannelManagerResultWrapper);
				if (channelManagerResult > 0){
					return R.failure("??????????????????");
				}
				ChannelManagerActivityEntity channelManager = new ChannelManagerActivityEntity();
				channelManager.setPromotionUserId(id);
				channelManager.setChannelManagerAmount(item.getChannelManagerAmount());
				channelManager.setChannelManagerId(item.getChannelManagerId());
				channelManager.setProjectName(item.getProjectName());
				channelManager.setProjectId(item.getProjectId());
				channelManager.setDeductAmount(item.getDeductAmount());
				channelManager.setPromotionAmount(item.getPromotionAmount());
				channelManager.setActivityId(item.getActivityId());
				channelManager.setIsDelete("0");
				channelManager.setStatus("0");
				channelManager.setUserId(studentId);
				channelManager.setProjectAmount(item.getProjectAmount());
				channelManagerActivityService.save(channelManager);
			};*/


        };
        if (channelManagerActivityList !=null){
            // ?????????get(0) ????????? ???????????????????????????????????????????????????????????????????????????????????????????????????????????????  ???????????????????????????????????????
            // ????????????
            String channelId = channelManagerActivityEditParam.get(0).getChannelId();
            String activityId = channelManagerActivityEditParam.get(0).getActivityId();
            String userId = channelManagerActivityEditParam.get(0).getUserId();
            String tenantId = channelManagerActivityEditParam.get(0).getTenantId();
            // ?????????????????????
            if (StrUtil.isNotEmpty(channelId)){
                // ???????????????
               /*ChannelEditParam channelEditParam = new ChannelEditParam();
                channelEditParam.setId(channelId);
                channelEditParam.setAuth(channelManagerActivityEditParam.get(0).getUserId());
                channelEditParam.setUpdateTime(LocalDateTime.now());
                remoteChannelFeignService.updateChannel(channelEditParam);*/
                // ????????????
                // ??????????????????
               /* ActivityPromoteAuthEntity activityPromoteAuthEntity = new ActivityPromoteAuthEntity();
                // ????????????ID
                activityPromoteAuthEntity.setActivityId(activityId);
                // ??????????????????ID
                activityPromoteAuthEntity.setChannelId(channelId);
                // ????????????ID
                activityPromoteAuthEntity.setUserId(userId);
                // ??????ID
                activityPromoteAuthEntity.setTenantId(tenantId);
                // ??????
                QueryWrapper<ActivityPromoteAuthEntity> authQueryWrapper = new QueryWrapper<ActivityPromoteAuthEntity>();
                authQueryWrapper.eq("activity_id",activityId);
                authQueryWrapper.eq("channel_id",channelId);
                authQueryWrapper.eq("user_id",userId);
                authQueryWrapper.eq("tenant_id",tenantId);
                Boolean delAuth = activityPromoteAuthService.remove(authQueryWrapper);
                log.info("????????????????????????{}",delAuth);
                // ??????
                Boolean saveAuth = activityPromoteAuthService.save(activityPromoteAuthEntity);
                log.info("????????????????????????{}",saveAuth);*/

                // ????????????
                ChannelAuthEditParam channelAuthEditParam = new ChannelAuthEditParam();
                // ??????ID
                channelAuthEditParam.setChannelId(channelId);
                // ??????ID
                channelAuthEditParam.setUserId(userId);
                // ??????ID
                channelAuthEditParam.setTenantId(tenantId);
                ResObject resObject = channelAuthFeignService.updateChannelAuth(channelAuthEditParam);
                log.info(this.getClass() + "????????????{}",resObject);
                if(!(resObject.getCode() .equals(ResCodeEnum.SUCCESS.getCode()))){
                    throw new BizException(500,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
                }
            }
            return R.success(channelManagerActivityService.saveBatch(channelManagerActivityList));
        }


        return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"????????????????????????");
    }


    /**
     * ??????????????????
     * @param channelManagerActivityEditParam
     * @return
     */
    @Transactional
    public ResObject publishChannelManager(ChannelManagerActivityEditParam channelManagerActivityEditParam) {
        // ?????????
        ChannelManagerActivityEntity channelManagerActivity = BeanConvertUtils.copy(channelManagerActivityEditParam,ChannelManagerActivityEntity.class);
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // ??????iD
        queryWrapper.eq("activity_id",channelManagerActivityEditParam.getActivityId());
        // ???????????????ID
        queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getProjectId()),"project_id",channelManagerActivityEditParam.getProjectId());
        // ????????????ID
        queryWrapper.eq("channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
        // ????????????????????????
        queryWrapper.eq("promotion_type",1);
        //queryWrapper.eq("promotion_user_id",item.getPromotionUserId());
        Boolean result = channelManagerActivityService.remove(queryWrapper);
        log.info("????????????{}",result);
            // channelManagerActivityDo
        Boolean res = channelManagerActivityService.saveOrUpdate(channelManagerActivity);
        // ????????????
        String channelId = channelManagerActivityEditParam.getChannelId();
        String activityId = channelManagerActivityEditParam.getActivityId();
        String userId = channelManagerActivityEditParam.getUserId();
        String tenantId = channelManagerActivityEditParam.getTenantId();
        // ?????????????????????
        if (StrUtil.isNotEmpty(channelId)){
            // ????????????
            ChannelAuthEditParam channelAuthEditParam = new ChannelAuthEditParam();
            // ??????ID
            channelAuthEditParam.setChannelId(channelId);
            // ??????ID
            channelAuthEditParam.setUserId(userId);
            // ??????ID
            channelAuthEditParam.setTenantId(tenantId);
            ResObject resObject = channelAuthFeignService.updateChannelAuth(channelAuthEditParam);
            log.info(this.getClass() + "????????????{}",resObject);
            if(!(resObject.getCode() .equals(ResCodeEnum.SUCCESS.getCode()))){
                throw new BizException(500,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
            }

            // ????????????????????????
            ActivityInfoEntity activityInfo = activityInfoService.getById(activityId);
            if (activityInfo != null && activityInfo.getIsExclusive() != Integer.valueOf(ExclusiveEnum.Exclusive.getCode())){
                // ?????????????????????
                // ??????????????????ID
                ResObject recommendUserRes =  remoteRecommendUserFeignService.getRecommendUserByChannelManagerId(channelManagerActivityEditParam.getChannelManagerId());
                log.info("?????????????????????{}",recommendUserRes);
                List<RecommendUserVo> recommendUserVoList = new ArrayList<>();
                // ???????????????
                if (recommendUserRes.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && recommendUserRes.getData() != null){
                    recommendUserVoList = (List<RecommendUserVo>) recommendUserRes.getData();
                }

                // ?????????????????????
                if (recommendUserVoList.size() > 0){
                    List<ChannelManagerActivityEntity> channelManagerActivityEntityList = new ArrayList<>();
                    recommendUserVoList.stream().forEach((item) ->{
                        // ????????????
                        QueryWrapper<ChannelManagerActivityEntity> query = new QueryWrapper<ChannelManagerActivityEntity>();
                        // ??????iD
                        query.eq("activity_id",channelManagerActivityEditParam.getActivityId());
                        // ???????????????ID
                        query.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getProjectId()),"project_id",channelManagerActivityEditParam.getProjectId());
                        // ????????????ID
                        query.eq("channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
                        // ?????????????????????
                        query.eq("promotion_type",2);
                        query.eq("promotion_user_id",item.getId());
                        Boolean delRes = channelManagerActivityService.remove(query);

                        ChannelManagerActivityEntity channelManagerActivityEntity = new ChannelManagerActivityEntity();
                        // ????????????ID
                        channelManagerActivityEntity.setChannelManagerId(item.getManagerId());
                        // ??????ID
                        channelManagerActivityEntity.setUserId(item.getStudentId());
                        // ????????????ID
                        channelManagerActivityEntity.setPromotionUserId(item.getId());
                        // ???????????????
                        channelManagerActivityEntity.setPromotionType(2);
                        // ????????????
                        channelManagerActivityEntity.setActivityId(activityId);
                        channelManagerActivityEntityList.add(channelManagerActivityEntity);

                        // ????????????
                        // ????????????
                        ChannelAuthEditParam channelAuth = new ChannelAuthEditParam();
                        // ??????ID
                        channelAuth.setChannelId(channelId);
                        // ??????ID
                        channelAuth.setUserId(item.getStudentId());
                        // ??????ID
                        channelAuth.setTenantId(tenantId);
                        ResObject rest = channelAuthFeignService.updateChannelAuth(channelAuth);
                        log.info(this.getClass() + "????????????{}",rest);
                        if(!(rest.getCode() .equals(ResCodeEnum.SUCCESS.getCode()))){
                            throw new BizException(500,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
                        }
                    });
                    Boolean managerRes = channelManagerActivityService.saveOrUpdateBatch(channelManagerActivityEntityList);
            }



            }
           return R.success("????????????");
    }

        return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"????????????????????????");
    }

    @Override
    @Transactional
    //@Async
    public ResObject synData(String activityId) throws ExecutionException, InterruptedException {
        log.info(this.getClass() + "synData-??????????????????{}",activityId);
        // ?????????
        if (StrUtil.isEmpty(activityId)){
            log.error("?????????------");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        // ????????????????????????
        //RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // ??????????????????

        //CompletableFuture updateFuture = CompletableFuture.runAsync(() ->{
            //RequestContextHolder.setRequestAttributes(requestAttributes);
            //channelManagerAsync.dataAsyncSuccess(channelManagerActivityList);
            // ??????????????????
            QueryWrapper queryWrapper = new QueryWrapper();
            // ??????iD
            queryWrapper.eq("activity_id",activityId);
            // ??????????????????
            queryWrapper.eq("promotion_type",1);
            List<ChannelManagerActivityEntity> channelManagerActivityList = channelManagerActivityService.list(queryWrapper);
            // ????????? ?????????
            if (channelManagerActivityList.size() <= 0){
                throw  new BizException("???????????????????????????????????????????????????");
            }
        // ????????????????????????
        ActivityInfoEntity activityInfo = activityInfoService.getById(activityId);
        // ?????????
        if (activityInfo != null && activityInfo.getIsExclusive() != Integer.valueOf(ExclusiveEnum.Exclusive.getCode())){
            // ????????????
            channelManagerActivityList.stream().forEach((item) -> {

                // ???????????????
                ResObject recommendUserRes = remoteRecommendUserFeignService.getRecommendUserByChannelManagerId(item.getChannelManagerId());
                log.info("?????????????????????{}", recommendUserRes);
                List<RecommendUserVo> recommendUserVoList = new ArrayList<>();
                // ???????????????
                if (recommendUserRes.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && recommendUserRes.getData() != null) {
                    recommendUserVoList = (List<RecommendUserVo>) recommendUserRes.getData();
                }

                // ????????? ?????????
                if (recommendUserVoList.size() <= 0) {
                    return;
                    //throw new BizException("???????????????????????????????????????????????????");
                }
                List<ChannelManagerActivityEntity> channelManagerActivityEntityList = new ArrayList<>();
                recommendUserVoList.stream().forEach((recommendUser) -> {
                    // ?????????????????? ??????
                    if (recommendUser.getStudentId().equals(item.getUserId())) {
                        return;
                    }
                    // ????????????
                    QueryWrapper<ChannelManagerActivityEntity> query = new QueryWrapper<ChannelManagerActivityEntity>();
                    // ??????iD
                    query.eq("activity_id", item.getActivityId());
                    // ???????????????ID
                    //query.eq(StrUtil.isNotEmpty(item.getProjectId()),"project_id",item.getProjectId());
                    // ????????????ID
                    query.eq("channel_manager_id", item.getChannelManagerId());
                    // ?????????????????????
                    query.eq("promotion_type", 2);
                    query.eq("promotion_user_id", recommendUser.getId());
                    //Boolean delRes = channelManagerActivityService.remove(query);
                    int count = channelManagerActivityService.count(query);
                    if (count > 0) {
                        return;
                    }
                    // ????????????????????????
                   /* if (channelManagerActivityEntity == null){
                        channelManagerActivityEntity = new ChannelManagerActivityEntity();
                    }*/
                    ChannelManagerActivityEntity channelManagerActivityEntity = new ChannelManagerActivityEntity();
                    // ????????????ID
                    channelManagerActivityEntity.setChannelManagerId(recommendUser.getManagerId());
                    // ??????ID
                    channelManagerActivityEntity.setUserId(recommendUser.getStudentId());
                    // ????????????ID
                    channelManagerActivityEntity.setPromotionUserId(recommendUser.getId());
                    // ???????????????
                    channelManagerActivityEntity.setPromotionType(2);
                    // ????????????
                    channelManagerActivityEntity.setActivityId(item.getActivityId());
                    //
                    // ???????????????2021-03-05 ??????????????????
                    channelManagerActivityEntity.setDeductAmount(item.getDeductAmount());
                    channelManagerActivityEntity.setProjectAmount(item.getProjectAmount());
                    channelManagerActivityEntity.setPromotionAmount(item.getPromotionAmount());
                    channelManagerActivityEntity.setChannelManagerAmount(item.getChannelManagerAmount());
                    channelManagerActivityEntity.setProjectName(item.getProjectName());
                    channelManagerActivityEntityList.add(channelManagerActivityEntity);
                    // ????????????
                    // ????????????
                    ChannelAuthEditParam channelAuth = new ChannelAuthEditParam();
                    // ??????ID
                    channelAuth.setTenantId(recommendUser.getOperatorId());
                    // ??????????????????ID
                    channelAuth.setUserId(item.getUserId());
                    // ???????????????ID
                    channelAuth.setNewUserId(recommendUser.getStudentId());
                    ResObject rest = channelAuthFeignService.copyChannelAuth(channelAuth);
                    log.info(this.getClass() + "????????????{}", rest);
                    if (!(rest.getCode().equals(ResCodeEnum.SUCCESS.getCode()))) {
                        throw new BizException(500, SubResultCode.SYSTEM_FAILL.subCode(), SubResultCode.SYSTEM_FAILL.subMsg());
                    }
                });
                Boolean managerRes = channelManagerActivityService.saveBatch(channelManagerActivityEntityList);
                log.info("??????{}", managerRes);
            });
        }
        //});

        //CompletableFuture.allOf(updateFuture).get();
        return R.success();
    }


    /**
     * ?????? ??????????????????
     * @param param
     * @return
     * @throws IOException
     */
    @SneakyThrows
    @Override
    public List<ChannelManagerActivityVo> exportXls(ChannelManagerActivityPageQueryParam param) throws IOException {
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList ????????????{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("??????ID???");
            return null;
            //return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        // ??????ID
        queryWrapper.eq("activity_id",param.getActivityId());
        // ??????ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        // ??????????????????
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        // ??????????????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        // ???x?????????
        queryWrapper.eq(StrUtil.isNotEmpty(param.getProjectId()),"project_id",param.getProjectId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.groupBy("promotion_user_id");
        List<ChannelManagerActivityEntity> pageList = channelManagerActivityService.list(queryWrapper);
        List<ChannelManagerActivityVo> listVo = BeanConvertUtils.copyList(pageList,ChannelManagerActivityVo.class);
        // Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        // ??????????????????
        String[] promotionUserIds = listVo.stream().map(ChannelManagerActivityVo::getPromotionUserId).toArray(String[]::new);
        String[] channelManagerIds = listVo.stream().map(ChannelManagerActivityVo::getChannelManagerId).toArray(String[]::new);
        Map<String, RecommendUserVo> recommendUserVo = recommendUserFeign.getBatchRecommendUserVo(promotionUserIds);
        Map<String, RecommendManagerVo> channelManagerVo = recommendManagerFeign.getBatchRecommendManagerVo(channelManagerIds);
        listVo.stream().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // ?????????????????? RecommendManagerVo
            if (channelManagerVo != null)item.setChannelManager(channelManagerVo.get(item.getChannelManagerId()));
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // ???????????????
            if (recommendUserVo != null)item.setRecommendUser(recommendUserVo.get(item.getPromotionUserId()));
        });
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList-??????????????????{}",listVo);
        return listVo;
    }

    
    public ResObject publishRecommendUser(List<RecommendUserVo> recommendUserList){
        // ?????????????????????
        if (recommendUserList.size() > 0){
            List<ChannelManagerActivityEntity> channelManagerActivityEntityList = new ArrayList<>();
            recommendUserList.stream().forEach((item) ->{
                ChannelManagerActivityEntity channelManagerActivityEntity = new ChannelManagerActivityEntity();
                // ????????????ID
                channelManagerActivityEntity.setChannelManagerId(item.getManagerId());
                // ??????ID
                channelManagerActivityEntity.setUserId(item.getStudentId());
                // ??????????????? 1 :???????????? 2 ?????????
                channelManagerActivityEntity.setPromotionType(2);
                channelManagerActivityEntityList.add(channelManagerActivityEntity);
            });

            // ???????????? ??????

        }
        return R.failure("??????");
    }


}
