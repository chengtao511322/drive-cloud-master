package com.drive.marketing.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.api.RemoteRecommendManagerFeignService;
import com.drive.admin.api.RemoteRecommendUserFeignService;
import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.dto.RecommendUserPageQueryParam;
import com.drive.admin.pojo.vo.RecommendManagerVo;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.basics.feign.RemoteChannelAuthFeignService;
import com.drive.basics.feign.RemoteChannelFeignService;
import com.drive.basics.feign.RemoteOperatorFeignService;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.ExclusiveEnum;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.marketing.asyn.ChannelManagerAsync;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.pojo.entity.CouponEntity;
import com.drive.marketing.pojo.entity.CouponGetEntity;
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
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ChannelManagerActivityRepositoryImpl implements ChannelManagerActivityRepository {

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
        log.info(this.getClass() + "changeChannelManagerStatus-方法请求参数{}",channelManagerActivityEditParam);
        if (StrUtil.isEmpty(channelManagerActivityEditParam.getId())){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(channelManagerActivityEditParam.getActivityId())){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ChannelManagerActivityEntity channelManagerActivity = BeanConvertUtils.copy(channelManagerActivityEditParam,ChannelManagerActivityEntity.class);
        // 更新渠道经理
        Boolean channelManagerResult = channelManagerActivityService.updateById(channelManagerActivity);
        // 出错 回滚
        if (!channelManagerResult){
            throw new BizException("执行出错");
        }
        // 查询出该渠道经理下面的所有推广商
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // 这里默认查询推广商
        queryWrapper.eq("promotion_type",2);
        // 通过活动查询
        queryWrapper.eq("activity_id",channelManagerActivityEditParam.getActivityId());
        // 渠道经理查询
        queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getChannelManagerId()),"channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getPromotionUserId()),"promotion_user_id",channelManagerActivityEditParam.getPromotionUserId());
        List<ChannelManagerActivityEntity> channelManagerActivityList = channelManagerActivityService.list(queryWrapper);
        // 有数据执行下面操作
        if (channelManagerActivityList.size() > 0){
            channelManagerActivityList.forEach((item) ->{
                // 状态跟着变
                item.setStatus(channelManagerActivityEditParam.getStatus());
                //item.setChannelManagerId(channelManagerActivityEditParam.getId());
                item.setActivityId(channelManagerActivityEditParam.getActivityId());
            });
            // 推广商结果
            Boolean promotionResult = channelManagerActivityService.updateBatchById(channelManagerActivityList);
            // 出错 回滚
            if (!promotionResult){
                throw  new BizException("执行出错");
            }
        }
        return  R.success("执行成功");
    }
    @Override
    @Transactional
    public ResObject changePromotionUserStatus(ChannelManagerActivityEditParam channelManagerActivityEditParam) {
        log.info(this.getClass() + "changeChannelManagerStatus-方法请求参数{}",channelManagerActivityEditParam);
        if (StrUtil.isEmpty(channelManagerActivityEditParam.getId())){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(channelManagerActivityEditParam.getActivityId())){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ChannelManagerActivityEntity channelManagerActivity = BeanConvertUtils.copy(channelManagerActivityEditParam,ChannelManagerActivityEntity.class);
        // 更新渠道经理
        Boolean channelManagerResult = channelManagerActivityService.updateById(channelManagerActivity);
        // 出错 回滚
        if (!channelManagerResult){
            throw  new BizException("执行出错");
        }
        return  R.success("执行成功");
    }

    @Override
    public ResObject pageChannelManagerList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "pageList 请求方法{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("活动ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        queryWrapper.eq("activity_id",param.getActivityId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        // 循环
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        // 循环取数据
        channelManagerActivityVoPage.getRecords().stream().forEach((item) ->{
            // 查询渠道经理 RecommendManagerVo
            ResObject<RecommendManagerVo> recommendManagerVo = remoteRecommendManagerFeignService.get(item.getChannelManagerId());
            if (recommendManagerVo.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setChannelManager(recommendManagerVo.getData());
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // 查询推广商
            ResObject<RecommendUserVo> recommendUser = remoteRecommendUserFeignService.get(item.getPromotionUserId());
            if (recommendUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setRecommendUser(recommendUser.getData());
            // 查询优惠券
            ActivityCouponRelationEditParam activityCouponRelationEditParam = new ActivityCouponRelationEditParam();
            // 设置活动ID
            activityCouponRelationEditParam.setActivityId(item.getActivityId());
            // 设置产品ID
            activityCouponRelationEditParam.setProjectId(item.getProjectId());
            // 查询活动优惠券
            ActivityCouponRelationVo activityCouponRelationVo = activityInfoService.getActivityCouponRelationByCondition(activityCouponRelationEditParam);
            item.setActivityCouponRelationVo(activityCouponRelationVo);
        });
        log.info("channelManagerActivityVoPage{}",channelManagerActivityVoPage);
        return R.success(channelManagerActivityVoPage);
    }

    @Override
    public ResObject findList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "pageList 请求方法{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("活动ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // 活动ID
        queryWrapper.eq("activity_id",param.getActivityId());
        // 用户ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        //
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getStatus()),"status",param.getStatus());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.groupBy("channel_manager_id");
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        channelManagerActivityVoPage.getRecords().forEach((item) ->{
// 查询渠道经理 RecommendManagerVo

            ResObject<RecommendManagerVo> recommendManagerVo = remoteRecommendManagerFeignService.get(item.getChannelManagerId());
            if (recommendManagerVo.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setChannelManager(recommendManagerVo.getData());
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // 查询推广商
            ResObject<RecommendUserVo> recommendUser = remoteRecommendUserFeignService.get(item.getPromotionUserId());
            if (recommendUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setRecommendUser(recommendUser.getData());
        });
        log.info(this.getClass() + "findList-方法请求结果{}",channelManagerActivityVoPage);
        return R.success(channelManagerActivityVoPage);
    }

    @Override
    public ResObject findPromotionPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findPromotionList 请求方法{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("活动ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // 活动ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getActivityId()),"activity_id",param.getActivityId());
        // 用户ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.groupBy("promotion_user_id");
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        channelManagerActivityVoPage.getRecords().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // 查询渠道经理 RecommendManagerVo
            ResObject<RecommendManagerVo> recommendManagerVo = remoteRecommendManagerFeignService.get(item.getChannelManagerId());
            if (recommendManagerVo.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setChannelManager(recommendManagerVo.getData());
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // 查询推广商
            ResObject<RecommendUserVo> recommendUser = remoteRecommendUserFeignService.get(item.getPromotionUserId());
            if (recommendUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setRecommendUser(recommendUser.getData());
        });
        log.info(this.getClass() + "findList-方法请求结果{}",channelManagerActivityVoPage);
        return R.success(channelManagerActivityVoPage);
    }
    public ResObject findPromotionPageListByManagerId(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findPromotionList 请求方法{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("活动ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // 活动ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getActivityId()),"activity_id",param.getActivityId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        // 用户ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        // 推广类型  默认查询 推广商  1:渠道经理 2 推广商
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",2);
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        //queryWrapper.groupBy("channel_manager_id");
        //queryWrapper.groupBy("user_id");
        //  开始时间 结束时间都有才进入
        if (StrUtil.isNotEmpty(param.getBeginTime()) && StrUtil.isNotEmpty(param.getEndTime())){
            queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        }
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        // 空判断
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        channelManagerActivityVoPage.getRecords().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // 查询渠道经理 RecommendManagerVo
            ResObject<RecommendManagerVo> recommendManagerVo = remoteRecommendManagerFeignService.get(item.getChannelManagerId());
            if (recommendManagerVo.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setChannelManager(recommendManagerVo.getData());
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // 查询推广商
            ResObject<RecommendUserVo> recommendUser = remoteRecommendUserFeignService.get(item.getPromotionUserId());
            if (recommendUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setRecommendUser(recommendUser.getData());
        });
        log.info(this.getClass() + "findList-方法请求结果{}",channelManagerActivityVoPage);
        return R.success(channelManagerActivityVoPage);
    }

    @Override
    public ResObject findChannelManagerOrPromotionPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList 请求方法{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("活动ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityPageQueryParam> queryWrapper = new QueryWrapper<ChannelManagerActivityPageQueryParam>();
        // 活动ID
        queryWrapper.eq("activity_id",param.getActivityId());
        // 用户ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"t1.user_id",param.getUserId());
        // 推广类型查询
        queryWrapper.eq(param.getPromotionType() != null,"t1.promotion_type",param.getPromotionType());
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"t1.promotion_user_id",param.getPromotionUserId());
        // 渠道经理查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"t1.channel_manager_id",param.getChannelManagerId());
        // 版x型查询
        queryWrapper.eq(StringUtils.isNotEmpty(param.getProjectId()),"t1.project_id",param.getProjectId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"t1.create_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("t1.create_time");
        queryWrapper.groupBy("t1.promotion_user_id");
        queryWrapper.eq("t1.is_delete", StatusEnum.ENABLE.getCode());
        //queryWrapper.eq("t1.status", StatusEnum.ENABLE.getCode());
        IPage<ChannelManagerActivityVo> pageList = channelManagerActivityService.findPage(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        pageList.getRecords().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // 查询渠道经理 RecommendManagerVo
            ResObject<RecommendManagerVo> recommendManagerVo = remoteRecommendManagerFeignService.get(item.getChannelManagerId());
            if (recommendManagerVo.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setChannelManager(recommendManagerVo.getData());
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // 查询推广商
            ResObject<RecommendUserVo> recommendUser = remoteRecommendUserFeignService.get(item.getPromotionUserId());
            if (recommendUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setRecommendUser(recommendUser.getData());
        });
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList-方法请求结果{}",pageList.getRecords());
        return R.success(pageList);
    }

    @Override
    public ResObject findPromotionUserPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findPromotionUserPageList-方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("活动ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(param.getPromotionUserId())){
            log.error("推广ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper<CouponGetEntity> queryWrapper = new QueryWrapper<CouponGetEntity>();
        // 来源活动ID 查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getActivityId()),"source",param.getActivityId());
        //推广商查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promote_user_id",param.getPromotionUserId());
        // 用户ID 查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        // 时间区域查询
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"get_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.groupBy("user_id");
        Page<CouponGetEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<CouponGetEntity> pageList = couponGetService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // 循环
        Page<CouponGetVo> couponGetVoPage = couponGetMapStruct.toVoList(pageList);
        couponGetVoPage.getRecords().stream().forEach((item) ->{
            if (StrUtil.isNotEmpty(item.getUserId())){
                ResObject<StudentInfoVo> studentInfoVoResObject = remoteStudentFeignService.get(item.getUserId());
                StudentInfoVo studentInfoVo = studentInfoVoResObject.getData();
                if (studentInfoVo != null)item.setPhone(studentInfoVo.getPhone());
                if (studentInfoVo != null)item.setUserName(studentInfoVo.getUsername());
                CouponEntity couponEntity = couponService.getById(item.getCouponId());
                if (couponEntity != null)item.setCouponName(couponEntity.getName());
            }
        });
        log.info(this.getClass() + "findPromotionUserPageList-方法请求结果{}",couponGetVoPage.getRecords());
        return R.success(couponGetVoPage);
    }

    @Override
    public ResObject findChannelManagerPromotionUserPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() +"findChannelManagerPromotionUserPageList-方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("活动ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(param.getChannelManagerId())){
            log.error("渠道ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (param.getPromotionType() == null){
            log.error("渠道类型空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // 活动ID
        queryWrapper.eq("activity_id",param.getActivityId());
        // 用户ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        // 推广类型查询
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        // 渠道经理查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        // 版x型查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getProjectId()),"project_id",param.getProjectId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.groupBy("promotion_user_id");
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        channelManagerActivityVoPage.getRecords().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // 查询渠道经理 RecommendManagerVo
            ResObject<RecommendManagerVo> recommendManagerVo = remoteRecommendManagerFeignService.get(item.getChannelManagerId());
            if (recommendManagerVo.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setChannelManager(recommendManagerVo.getData());
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // 查询推广商
            ResObject<RecommendUserVo> recommendUser = remoteRecommendUserFeignService.get(item.getPromotionUserId());
            if (recommendUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setRecommendUser(recommendUser.getData());
        });
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList-方法请求结果{}",pageList.getRecords());
        return R.success(channelManagerActivityVoPage);
    }

    @Override
    public ResObject findActivityProjectPageList(ChannelManagerActivityPageQueryParam param) {
        log.info(this.getClass() + "findActivityProjectPageList-方法请求参数{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("活动ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(param.getUserId())){
            log.error("用户ID空");
            R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // 来源活动ID 查询
        queryWrapper.eq("activity_id",param.getActivityId());
        //推广商查询
        queryWrapper.eq("user_id",param.getUserId());
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        // 循环
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findActivityProjectPageList-方法请求结果{}",channelManagerActivityVoPage.getRecords());
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
            // 之所以get(0) 是因为 封装的这几个都是固定的，不会存在这几个值不一样的情况，不一样的是版型不一样
            // 设置权限
            String channelId = channelManagerActivityEditParamList.get(0).getChannelId();

            String activityId = channelManagerActivityEditParamList.get(0).getActivityId();
            String userId = channelManagerActivityEditParamList.get(0).getUserId();
            String tenantId = channelManagerActivityEditParamList.get(0).getTenantId();
            if (StrUtil.isNotEmpty(channelId)){
                // 第一版本
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

                // 第二版本
                // 设置活动权限
               /* ActivityPromoteAuthEntity activityPromoteAuthEntity = new ActivityPromoteAuthEntity();
                // 设置活动ID
                activityPromoteAuthEntity.setActivityId(activityId);
                // 设置栏目菜单ID
                activityPromoteAuthEntity.setChannelId(channelId);
                // 设置用户ID
                activityPromoteAuthEntity.setUserId(userId);
                // 租户ID
                activityPromoteAuthEntity.setTenantId(tenantId);
                // 删除
                QueryWrapper<ActivityPromoteAuthEntity> authQueryWrapper = new QueryWrapper<ActivityPromoteAuthEntity>();
                authQueryWrapper.eq("activity_id",activityId);
                authQueryWrapper.eq("channel_id",channelId);
                authQueryWrapper.eq("user_id",userId);
                authQueryWrapper.eq("tenant_id",tenantId);
                Boolean delAuth = activityPromoteAuthService.remove(authQueryWrapper);
                log.info("删除权限关联数据{}",delAuth);
                // 保存
                Boolean saveAuth = activityPromoteAuthService.save(activityPromoteAuthEntity);
                log.info("保存权限关联数据{}",saveAuth);*/
                // 第三版本
                // 第三版本  保存权限
                ChannelAuthEditParam channelAuthEditParam = new ChannelAuthEditParam();
                // 菜单ID
                channelAuthEditParam.setChannelId(channelId);
                // 用户ID
                channelAuthEditParam.setUserId(userId);
                // 租户ID
                channelAuthEditParam.setTenantId(tenantId);
                ResObject resObject = channelAuthFeignService.updateChannelAuth(channelAuthEditParam);
                log.info(this.getClass() + "请求结果{}",resObject);
                log.info(this.getClass() + "请求结果{}",resObject);
                if(!(resObject.getCode() .equals(ResCodeEnum.SUCCESS.getCode()))){
                    throw new BizException(500,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
                }

                return R.success(channelManagerActivityService.saveBatch(channelManagerActivityList));
            }
        }
        return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"数据不可重复提交");
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
                //return R.failure("不可重复添加");
                channelManagerActivityList.add(item);
            }


            // 查询出推广商
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
					return R.failure("不可重复添加");
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
            // 之所以get(0) 是因为 封装的这几个都是固定的，不会存在这几个值不一样的情况，不一样的是版型不一样  这样避免循环数据的性能消耗
            // 设置权限
            String channelId = channelManagerActivityEditParam.get(0).getChannelId();
            String activityId = channelManagerActivityEditParam.get(0).getActivityId();
            String userId = channelManagerActivityEditParam.get(0).getUserId();
            String tenantId = channelManagerActivityEditParam.get(0).getTenantId();
            // 选了按钮才进行
            if (StrUtil.isNotEmpty(channelId)){
                // 第一个版本
               /*ChannelEditParam channelEditParam = new ChannelEditParam();
                channelEditParam.setId(channelId);
                channelEditParam.setAuth(channelManagerActivityEditParam.get(0).getUserId());
                channelEditParam.setUpdateTime(LocalDateTime.now());
                remoteChannelFeignService.updateChannel(channelEditParam);*/
                // 第二版本
                // 设置活动权限
               /* ActivityPromoteAuthEntity activityPromoteAuthEntity = new ActivityPromoteAuthEntity();
                // 设置活动ID
                activityPromoteAuthEntity.setActivityId(activityId);
                // 设置栏目菜单ID
                activityPromoteAuthEntity.setChannelId(channelId);
                // 设置用户ID
                activityPromoteAuthEntity.setUserId(userId);
                // 租户ID
                activityPromoteAuthEntity.setTenantId(tenantId);
                // 删除
                QueryWrapper<ActivityPromoteAuthEntity> authQueryWrapper = new QueryWrapper<ActivityPromoteAuthEntity>();
                authQueryWrapper.eq("activity_id",activityId);
                authQueryWrapper.eq("channel_id",channelId);
                authQueryWrapper.eq("user_id",userId);
                authQueryWrapper.eq("tenant_id",tenantId);
                Boolean delAuth = activityPromoteAuthService.remove(authQueryWrapper);
                log.info("删除权限关联数据{}",delAuth);
                // 保存
                Boolean saveAuth = activityPromoteAuthService.save(activityPromoteAuthEntity);
                log.info("保存权限关联数据{}",saveAuth);*/

                // 第三版本
                ChannelAuthEditParam channelAuthEditParam = new ChannelAuthEditParam();
                // 菜单ID
                channelAuthEditParam.setChannelId(channelId);
                // 用户ID
                channelAuthEditParam.setUserId(userId);
                // 租户ID
                channelAuthEditParam.setTenantId(tenantId);
                ResObject resObject = channelAuthFeignService.updateChannelAuth(channelAuthEditParam);
                log.info(this.getClass() + "请求结果{}",resObject);
                if(!(resObject.getCode() .equals(ResCodeEnum.SUCCESS.getCode()))){
                    throw new BizException(500,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
                }
            }
            return R.success(channelManagerActivityService.saveBatch(channelManagerActivityList));
        }


        return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"数据不可重复提交");
    }


    /**
     * 发布渠道经理
     * @param channelManagerActivityEditParam
     * @return
     */
    @Transactional
    public ResObject publishChannelManager(ChannelManagerActivityEditParam channelManagerActivityEditParam) {
        // 空判断
        ChannelManagerActivityEntity channelManagerActivity = BeanConvertUtils.copy(channelManagerActivityEditParam,ChannelManagerActivityEntity.class);
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        // 活动iD
        queryWrapper.eq("activity_id",channelManagerActivityEditParam.getActivityId());
        // 之前的版型ID
        queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getProjectId()),"project_id",channelManagerActivityEditParam.getProjectId());
        // 渠道经理ID
        queryWrapper.eq("channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
        // 默认都是渠道经理
        queryWrapper.eq("promotion_type",1);
        //queryWrapper.eq("promotion_user_id",item.getPromotionUserId());
        Boolean result = channelManagerActivityService.remove(queryWrapper);
        log.info("删除结果{}",result);
            // channelManagerActivityDo
        Boolean res = channelManagerActivityService.saveOrUpdate(channelManagerActivity);
        // 设置权限
        String channelId = channelManagerActivityEditParam.getChannelId();
        String activityId = channelManagerActivityEditParam.getActivityId();
        String userId = channelManagerActivityEditParam.getUserId();
        String tenantId = channelManagerActivityEditParam.getTenantId();
        // 选了按钮才进行
        if (StrUtil.isNotEmpty(channelId)){
            // 第三版本
            ChannelAuthEditParam channelAuthEditParam = new ChannelAuthEditParam();
            // 菜单ID
            channelAuthEditParam.setChannelId(channelId);
            // 用户ID
            channelAuthEditParam.setUserId(userId);
            // 租户ID
            channelAuthEditParam.setTenantId(tenantId);
            ResObject resObject = channelAuthFeignService.updateChannelAuth(channelAuthEditParam);
            log.info(this.getClass() + "请求结果{}",resObject);
            if(!(resObject.getCode() .equals(ResCodeEnum.SUCCESS.getCode()))){
                throw new BizException(500,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
            }

            // 判断活动是否独享
            ActivityInfoEntity activityInfo = activityInfoService.getById(activityId);
            if (activityInfo != null && activityInfo.getIsExclusive() == Integer.valueOf(ExclusiveEnum.NOTExclusive.getCode())){
                // 设置推广商权限
                // 设置渠道经理ID
                ResObject recommendUserRes =  remoteRecommendUserFeignService.getRecommendUserByChannelManagerId(channelManagerActivityEditParam.getChannelManagerId());
                log.info("请求推广商数据{}",recommendUserRes);
                List<RecommendUserVo> recommendUserVoList = new ArrayList<>();
                // 请求到数据
                if (recommendUserRes.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && recommendUserRes.getData() != null){
                    recommendUserVoList = (List<RecommendUserVo>) recommendUserRes.getData();
                }

                // 判断是否有数据
                if (recommendUserVoList.size() > 0){
                    List<ChannelManagerActivityEntity> channelManagerActivityEntityList = new ArrayList<>();
                    recommendUserVoList.stream().forEach((item) ->{
                        // 删除数据
                        QueryWrapper<ChannelManagerActivityEntity> query = new QueryWrapper<ChannelManagerActivityEntity>();
                        // 活动iD
                        query.eq("activity_id",channelManagerActivityEditParam.getActivityId());
                        // 之前的版型ID
                        query.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getProjectId()),"project_id",channelManagerActivityEditParam.getProjectId());
                        // 渠道经理ID
                        query.eq("channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
                        // 默认都是推广商
                        query.eq("promotion_type",2);
                        query.eq("promotion_user_id",item.getId());
                        Boolean delRes = channelManagerActivityService.remove(query);

                        ChannelManagerActivityEntity channelManagerActivityEntity = new ChannelManagerActivityEntity();
                        // 渠道经理ID
                        channelManagerActivityEntity.setChannelManagerId(item.getManagerId());
                        // 用户ID
                        channelManagerActivityEntity.setUserId(item.getStudentId());
                        // 推广用户ID
                        channelManagerActivityEntity.setPromotionUserId(item.getId());
                        // 设置推广商
                        channelManagerActivityEntity.setPromotionType(2);
                        // 设置活动
                        channelManagerActivityEntity.setActivityId(activityId);
                        channelManagerActivityEntityList.add(channelManagerActivityEntity);

                        // 添加权限
                        // 第三版本
                        ChannelAuthEditParam channelAuth = new ChannelAuthEditParam();
                        // 菜单ID
                        channelAuth.setChannelId(channelId);
                        // 用户ID
                        channelAuth.setUserId(item.getStudentId());
                        // 租户ID
                        channelAuth.setTenantId(tenantId);
                        ResObject rest = channelAuthFeignService.updateChannelAuth(channelAuth);
                        log.info(this.getClass() + "请求结果{}",rest);
                        if(!(rest.getCode() .equals(ResCodeEnum.SUCCESS.getCode()))){
                            throw new BizException(500,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
                        }
                    });
                    Boolean managerRes = channelManagerActivityService.saveOrUpdateBatch(channelManagerActivityEntityList);
            }



            }
           return R.success("发布成功");
    }

        return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"数据不可重复提交");
    }

    @Override
    @Transactional
    //@Async
    public ResObject synData(String activityId) throws ExecutionException, InterruptedException {
        log.info(this.getClass() + "synData-方法请求参数{}",activityId);
        // 空判断
        if (StrUtil.isEmpty(activityId)){
            log.error("数据空------");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        // 设置主线程的信息
        //RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 异步数据处理

        //CompletableFuture updateFuture = CompletableFuture.runAsync(() ->{
            //RequestContextHolder.setRequestAttributes(requestAttributes);
            //channelManagerAsync.dataAsyncSuccess(channelManagerActivityList);
            // 查询渠道经理
            QueryWrapper queryWrapper = new QueryWrapper();
            // 活动iD
            queryWrapper.eq("activity_id",activityId);
            // 查询渠道经理
            queryWrapper.eq("promotion_type",1);
            List<ChannelManagerActivityEntity> channelManagerActivityList = channelManagerActivityService.list(queryWrapper);
            // 数据空 不出来
            if (channelManagerActivityList.size() <= 0){
                throw  new BizException("数据同步失败，该活动下没有渠道经理");
            }
        // 判断活动是否独享
        ActivityInfoEntity activityInfo = activityInfoService.getById(activityId);
        // 非独享
        if (activityInfo != null && activityInfo.getIsExclusive() == Integer.valueOf(ExclusiveEnum.NOTExclusive.getCode())){
            // 循环处理
            channelManagerActivityList.stream().forEach((item) -> {

                // 查询推广商
                ResObject recommendUserRes = remoteRecommendUserFeignService.getRecommendUserByChannelManagerId(item.getChannelManagerId());
                log.info("请求推广商数据{}", recommendUserRes);
                List<RecommendUserVo> recommendUserVoList = new ArrayList<>();
                // 请求到数据
                if (recommendUserRes.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && recommendUserRes.getData() != null) {
                    recommendUserVoList = (List<RecommendUserVo>) recommendUserRes.getData();
                }

                // 数据空 不出来
                if (recommendUserVoList.size() <= 0) {
                    throw new BizException("数据同步失败，该活动下没有渠道经理");
                }
                List<ChannelManagerActivityEntity> channelManagerActivityEntityList = new ArrayList<>();
                recommendUserVoList.stream().forEach((recommendUser) -> {
                    // 渠道经理本人 退出
                    if (recommendUser.getStudentId().equals(item.getUserId())) {
                        return;
                    }
                    // 删除数据
                    QueryWrapper<ChannelManagerActivityEntity> query = new QueryWrapper<ChannelManagerActivityEntity>();
                    // 活动iD
                    query.eq("activity_id", item.getActivityId());
                    // 之前的版型ID
                    //query.eq(StrUtil.isNotEmpty(item.getProjectId()),"project_id",item.getProjectId());
                    // 渠道经理ID
                    query.eq("channel_manager_id", item.getChannelManagerId());
                    // 默认都是推广商
                    query.eq("promotion_type", 2);
                    query.eq("promotion_user_id", recommendUser.getId());
                    //Boolean delRes = channelManagerActivityService.remove(query);
                    int count = channelManagerActivityService.count(query);
                    if (count > 0) {
                        return;
                    }
                    // 解决重复数据问题
                   /* if (channelManagerActivityEntity == null){
                        channelManagerActivityEntity = new ChannelManagerActivityEntity();
                    }*/
                    ChannelManagerActivityEntity channelManagerActivityEntity = new ChannelManagerActivityEntity();
                    // 渠道经理ID
                    channelManagerActivityEntity.setChannelManagerId(recommendUser.getManagerId());
                    // 用户ID
                    channelManagerActivityEntity.setUserId(recommendUser.getStudentId());
                    // 推广用户ID
                    channelManagerActivityEntity.setPromotionUserId(recommendUser.getId());
                    // 设置推广商
                    channelManagerActivityEntity.setPromotionType(2);
                    // 设置活动
                    channelManagerActivityEntity.setActivityId(item.getActivityId());
                    //
                    // 这段代码在2021-03-05 需要注释作废
                    channelManagerActivityEntity.setDeductAmount(item.getDeductAmount());
                    channelManagerActivityEntity.setProjectAmount(item.getProjectAmount());
                    channelManagerActivityEntity.setPromotionAmount(item.getPromotionAmount());
                    channelManagerActivityEntity.setChannelManagerAmount(item.getChannelManagerAmount());
                    channelManagerActivityEntity.setProjectName(item.getProjectName());
                    channelManagerActivityEntityList.add(channelManagerActivityEntity);
                    // 添加权限
                    // 第三版本
                    ChannelAuthEditParam channelAuth = new ChannelAuthEditParam();
                    // 菜单ID
                    channelAuth.setTenantId(recommendUser.getOperatorId());
                    // 用户渠道经理ID
                    channelAuth.setUserId(item.getUserId());
                    // 用户推广商ID
                    channelAuth.setNewUserId(recommendUser.getStudentId());
                    ResObject rest = channelAuthFeignService.copyChannelAuth(channelAuth);
                    log.info(this.getClass() + "请求结果{}", rest);
                    if (!(rest.getCode().equals(ResCodeEnum.SUCCESS.getCode()))) {
                        throw new BizException(500, SubResultCode.SYSTEM_FAILL.subCode(), SubResultCode.SYSTEM_FAILL.subMsg());
                    }
                });
                Boolean managerRes = channelManagerActivityService.saveBatch(channelManagerActivityEntityList);
                log.info("结果{}", managerRes);
            });
        }
        //});

        //CompletableFuture.allOf(updateFuture).get();
        return R.success();
    }


    /**
     * 导出 渠道经理数据
     * @param param
     * @return
     * @throws IOException
     */
    @SneakyThrows
    @Override
    public List<ChannelManagerActivityVo> exportXls(ChannelManagerActivityPageQueryParam param) throws IOException {
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList 请求方法{}",param);
        if (StrUtil.isEmpty(param.getActivityId())){
            log.error("活动ID空");
            return null;
            //return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        // 活动ID
        queryWrapper.eq("activity_id",param.getActivityId());
        // 用户ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        // 推广类型查询
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        // 渠道经理查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        // 版x型查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getProjectId()),"project_id",param.getProjectId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"create_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.groupBy("promotion_user_id");
        List<ChannelManagerActivityEntity> pageList = channelManagerActivityService.list(queryWrapper);
        List<ChannelManagerActivityVo> listVo = BeanConvertUtils.copyList(pageList,ChannelManagerActivityVo.class);
        // Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        listVo.stream().forEach((item) ->{
            //JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
            // 查询渠道经理 RecommendManagerVo
            ResObject<RecommendManagerVo> recommendManagerVo = remoteRecommendManagerFeignService.get(item.getChannelManagerId());
            if (recommendManagerVo.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setChannelManager(recommendManagerVo.getData());
            //JSONObject recommendUser = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
            // 查询推广商
            ResObject<RecommendUserVo> recommendUser = remoteRecommendUserFeignService.get(item.getPromotionUserId());
            if (recommendUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()))item.setRecommendUser(recommendUser.getData());
        });
        log.info(this.getClass() + "findChannelManagerOrPromotionPageList-方法请求结果{}",listVo);
        return listVo;
    }

    
    public ResObject publishRecommendUser(List<RecommendUserVo> recommendUserList){
        // 判断是否有数据
        if (recommendUserList.size() > 0){
            List<ChannelManagerActivityEntity> channelManagerActivityEntityList = new ArrayList<>();
            recommendUserList.stream().forEach((item) ->{
                ChannelManagerActivityEntity channelManagerActivityEntity = new ChannelManagerActivityEntity();
                // 渠道经理ID
                channelManagerActivityEntity.setChannelManagerId(item.getManagerId());
                // 用户ID
                channelManagerActivityEntity.setUserId(item.getStudentId());
                // 设置推广商 1 :渠道经理 2 推广商
                channelManagerActivityEntity.setPromotionType(2);
                channelManagerActivityEntityList.add(channelManagerActivityEntity);
            });

            // 渠道经理 数据

        }
        return R.failure("出错");
    }
}
