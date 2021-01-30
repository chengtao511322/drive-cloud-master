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
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.basics.feign.RemoteChannelFeignService;
import com.drive.basics.feign.RemoteOperatorFeignService;
import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityPromoteAuthEntity;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ResObject changeChannelManagerStatus(ChannelManagerActivityEditParam channelManagerActivityEditParam) {
        log.info(this.getClass() + "changeChannelManagerStatus-方法请求参数{}",channelManagerActivityEditParam);
        /*if (StrUtil.isEmpty(channelManagerActivityEditParam.getActivityId())){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        if (StrUtil.isEmpty(channelManagerActivityEditParam.getChannelManagerId())){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }*/
        if (StrUtil.isEmpty(channelManagerActivityEditParam.getId())){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
		/*ChannelManagerActivityEntity channelManagerActivityEntity = new ChannelManagerActivityEntity();
		//
		channelManagerActivityEntity.setStatus(channelManagerActivityEditParam.getStatus());
		channelManagerActivityEntity.setId(channelManagerActivityEditParam.getId());
		channelManagerActivityService.updateById(channelManagerActivityEntity);*/
        // 停用后把推广人员里面渠道经理所得设置为0
        QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        queryWrapper.eq("activity_id",channelManagerActivityEditParam.getActivityId());
        queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getChannelManagerId()),"channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getPromotionUserId()),"promotion_user_id",channelManagerActivityEditParam.getPromotionUserId());
        List<ChannelManagerActivityEntity> channelManagerActivityList = channelManagerActivityService.list(queryWrapper);
        channelManagerActivityList.forEach((item) ->{
            //item.setChannelManagerAmount(new BigDecimal(0));
            item.setStatus(channelManagerActivityEditParam.getStatus());
            //item.setChannelManagerId(channelManagerActivityEditParam.getId());
            item.setActivityId(channelManagerActivityEditParam.getActivityId());
            //item.setId(channelManagerActivityEditParam.getId());
            channelManagerActivityService.updateById(item);
        });
        return R.success();
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
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.groupBy("channel_manager_id");
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
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
        queryWrapper.eq("activity_id",param.getActivityId());
        // 用户ID
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        queryWrapper.eq(param.getPromotionType() != null,"promotion_type",param.getPromotionType());
        //
        //queryWrapper.eq(StrUtil.isNotEmpty(param.getChannelManagerId()),"channel_manager_id",param.getChannelManagerId());
        queryWrapper.eq(StrUtil.isNotEmpty(param.getPromotionUserId()),"promotion_user_id",param.getPromotionUserId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.groupBy("promotion_user_id");
        IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, queryWrapper);
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
        queryWrapper.eq(StrUtil.isNotEmpty(param.getProjectId()),"t1.project_id",param.getProjectId());
        //queryWrapper.groupBy("channel_manager_id");
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"t1.create_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("t1.create_time");
        queryWrapper.groupBy("t1.promotion_user_id");
        IPage<ChannelManagerActivityVo> pageList = channelManagerActivityService.findPage(page, queryWrapper);
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
        queryWrapper.eq("source",param.getActivityId());
        //推广商查询
        queryWrapper.eq("promote_user_id",param.getPromotionUserId());
        // 用户ID 查询
        queryWrapper.eq(StrUtil.isNotEmpty(param.getUserId()),"user_id",param.getUserId());
        // 时间区域查询
        queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"get_time",param.getBeginTime(),param.getEndTime());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.groupBy("user_id");
        Page<CouponGetEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<CouponGetEntity> pageList = couponGetService.page(page, queryWrapper);
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
        // 循环
        Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findActivityProjectPageList-方法请求结果{}",channelManagerActivityVoPage.getRecords());
        return R.success(channelManagerActivityVoPage);
    }

    @Override
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
        if (channelManagerActivityList !=null){
            // 设置权限
            String channelId = channelManagerActivityEditParamList.get(0).getChannelId();
            if (StrUtil.isNotEmpty(channelId)){
                ChannelEditParam channelEditParam = new ChannelEditParam();
                channelEditParam.setId(channelId);
                //  String[] arrChannel = channelVoRes.getData().getAuth().split(",");
					/*List<String> arr = new ArrayList<String>();
					Arrays.stream(arrChannel).forEach((item)->{
						arr.add(item);
					});
					arr.add(channelManagerActivityEditParam.get(0).getUserId());
					String auth = Joiner.on(",").join(arr);*/
                channelEditParam.setAuth(channelManagerActivityEditParamList.get(0).getUserId());
                channelEditParam.setUpdateTime(LocalDateTime.now());
                remoteChannelFeignService.updateChannel(channelEditParam);

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
            // 设置权限
            String channelId = channelManagerActivityEditParam.get(0).getChannelId();
            String activityId = channelManagerActivityEditParam.get(0).getActivityId();
            String userId = channelManagerActivityEditParam.get(0).getUserId();
            String tenantId = channelManagerActivityEditParam.get(0).getTenantId();
            if (StrUtil.isNotEmpty(channelId)){
                // 第一个版本
                ChannelEditParam channelEditParam = new ChannelEditParam();
                channelEditParam.setId(channelId);
                channelEditParam.setAuth(channelManagerActivityEditParam.get(0).getUserId());
                channelEditParam.setUpdateTime(LocalDateTime.now());
                remoteChannelFeignService.updateChannel(channelEditParam);
                // 第二版本
                // 设置活动权限
                /*ActivityPromoteAuthEntity activityPromoteAuthEntity = new ActivityPromoteAuthEntity();
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
            }
            return R.success(channelManagerActivityService.saveBatch(channelManagerActivityList));
        }


        return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"数据不可重复提交");
    }


}
