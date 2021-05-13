package com.drive.marketing.repository.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.DateUtils;
import com.drive.common.core.utils.Redeem;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.marketing.pojo.dto.CouponAcquirePageQueryParam;
import com.drive.marketing.pojo.dto.CouponEditParam;
import com.drive.marketing.pojo.dto.CouponPageQueryParam;
import com.drive.marketing.pojo.entity.*;
import com.drive.marketing.pojo.vo.CouponGetVo;
import com.drive.marketing.repository.CouponRepository;
import com.drive.marketing.service.*;
import com.drive.marketing.service.mapstruct.CouponMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CouponRepositoryImpl implements CouponRepository {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponMapStruct couponMapStruct;

    @Autowired
    private CouponProductRelationService couponProductRelationService;

    @Autowired
    private CouponGetService couponGetService;

    @Autowired
    private ChannelManagerActivityService channelManagerActivityService;

    @Autowired
    private ActivityProjectSettingService activityProjectSettingService;

    @Override
    @Transactional
    public ResObject publishCoupon(CouponEditParam couponEditParam) {
        log.info(this.getClass() + "publishCoupon方法请求参数{}",couponEditParam);
        Long userId = SecurityUtils.getLoginUser().getUserId();
        CouponEntity coupon = couponMapStruct.toEntity(couponEditParam);
        coupon.setCreateBy(String.valueOf(userId));
        coupon.setReleaseTime(LocalDateTime.now());
        Boolean result = couponService.save(coupon);
        log.info("添加优惠券{}",result);
        List<String> arr = couponEditParam.getUseTypeList();
        if (arr.size() > 0){
            QueryWrapper<CouponProductRelationEntity> queryWrapper = new QueryWrapper<CouponProductRelationEntity>();
            queryWrapper.eq("coupon_id",coupon.getId());
            Boolean delCoupon = couponProductRelationService.remove(queryWrapper);
            log.info("优惠券删除结果{}",delCoupon);
            CouponProductRelationEntity couponProductRelationEntity = new CouponProductRelationEntity();
            couponProductRelationEntity.setCouponId(coupon.getId());
            couponProductRelationEntity.setType(Long.valueOf(arr.get(0)));
            couponProductRelationEntity.setProductId(arr.get(1));
            Boolean installCoupon  = couponProductRelationService.save(couponProductRelationEntity);
            log.info("插入结果{}",installCoupon);
        }

        // 发行数量
        Integer publishCount = couponEditParam.getPublishCount();
        if (publishCount > 0){
            // 定义接收优惠券对象
            List<CouponGetEntity> couponGetList = new ArrayList<>();
            for (int i =0 ;i <=publishCount;i++){
                CouponGetEntity couponGetEntity = new CouponGetEntity();
                couponGetEntity.setStatus(StatusEnum.DISABLE.getCode());
                couponGetEntity.setCouponId(coupon.getId());
                // 设置优惠券码
                couponGetEntity.setCouponCode(Redeem.create((byte) 1, 1, 12, Redeem.password));
                couponGetList.add(couponGetEntity);
            }
            // 发行优惠券码
            Boolean isGetCoupResult =couponGetService.saveBatch(couponGetList);
            log.info("添加对象{}",isGetCoupResult);
        }

        return R.toRes(result);
    }

    @Override
    public ResObject updateCoupon(CouponEditParam couponEditParam) {
        log.info(this.getClass() + "publishCoupon方法请求参数{}",couponEditParam);
        if (StrUtil.isEmpty(couponEditParam.getId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Long userId = SecurityUtils.getLoginUser().getUserId();
        CouponEntity coupon = couponMapStruct.toEntity(couponEditParam);
        coupon.setCreateBy(String.valueOf(userId));
        coupon.setReleaseTime(LocalDateTime.now());
        Boolean result = couponService.updateById(coupon);
        log.info("添加优惠券{}",result);
        List<String> arr = couponEditParam.getUseTypeList();
        if (arr.size() > 0){
            QueryWrapper<CouponProductRelationEntity> queryWrapper = new QueryWrapper<CouponProductRelationEntity>();
            queryWrapper.eq("coupon_id",coupon.getId());
            Boolean delCoupon = couponProductRelationService.remove(queryWrapper);
            log.info("优惠券删除结果{}",delCoupon);
            CouponProductRelationEntity couponProductRelationEntity = new CouponProductRelationEntity();
            couponProductRelationEntity.setCouponId(couponEditParam.getId());
            couponProductRelationEntity.setType(Long.valueOf(arr.get(0)));
            couponProductRelationEntity.setProductId(arr.get(1));
            Boolean installCoupon  = couponProductRelationService.save(couponProductRelationEntity);
            log.info("插入结果{}",installCoupon);
        }
        return R.toRes(result);
    }

    @Override
    public ResObject<CouponGetVo> getCoupon(CouponAcquirePageQueryParam couponPageQueryParam) {
        log.info(this.getClass()+"getCoupon-请求参数{}",couponPageQueryParam);
        // 查询优惠券领取信息 通过优惠券领取表Id
        CouponGetEntity couponGet = couponGetService.getById(couponPageQueryParam.getCouponAcquireId());
        if (couponGet == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 时间判断(当前时间) 是否过期
        long betweenDay = DateUtil.between(new Date(), DateUtils.asDate(couponGet.getPeriodTimeStart()), DateUnit.DAY);
        if (betweenDay >=1) {
            //couponOutDtoList.remove(item);
            //total =total - 1;
            log.info("该优惠{},{},过期，不可使用",couponGet.getCouponId());
            //subMsg = "该优惠券已经过期";
            return R.failure(SubResultCode.DATA_NULL.subCode(),"该优惠券已经过期");
            //return setProfessionalResultSuccess(SubResultCode.COUPON_EXPIRATION_TIME.subCode(),SubResultCode.COUPON_EXPIRATION_TIME.subMsg());
        }
        // do 转化
        CouponGetVo couponGetVo = BeanConvertUtils.copy(couponGet, CouponGetVo.class);
        // 查询条件
        QueryWrapper<ChannelManagerActivityEntity> wrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        if (StrUtil.isNotEmpty(couponGet.getPromoteUserId())){
            // 查询优惠券金额
            BigDecimal couponAmount = Optional.ofNullable(couponService.getById(couponGetVo.getCouponId()))
                    .map(u-> u.getAmount()).get();
            // 设置优惠券金额
            couponGetVo.setCouponAmount(couponAmount);
            wrapper.eq("activity_id",couponGet.getSource());
            wrapper.eq("promotion_user_id",couponGet.getPromoteUserId());
            // 班型ID
            //wrapper.eq(StrUtil.isNotEmpty(couponGetInputDto.getProjectId()),"project_id",couponGetInputDto.getProjectId());
            int activityPromotionCount = channelManagerActivityService.count(wrapper);
            if (activityPromotionCount <= 0){
                return R.failure(SubResultCode.CHANNEL_MARAGER_NOT_ACTIVITY.subCode(),"该推广商没有配置过活动");
            }
            // 查询版型价格
            QueryWrapper activityProjectSettingWrapper = new QueryWrapper();
            // 活动ID
            activityProjectSettingWrapper.eq("activity_id",couponGet.getSource());
            // 版型ID
            activityProjectSettingWrapper.eq("project_id",couponPageQueryParam.getProjectId());
            ActivityProjectSettingEntity activityProjectSetting = activityProjectSettingService.getOne(activityProjectSettingWrapper);
            log.info("activityProjectSetting{}",activityProjectSetting);
            // 设置百分比
            if (activityProjectSetting != null){
                // 活动总佣金
                couponGetVo.setDeductAmount(activityProjectSetting.getDeductAmount());
                // 推广商佣金
                couponGetVo.setPromotionAmount(activityProjectSetting.getPromotionAmount());
                // 渠道经理佣金
                couponGetVo.setChannelManagerAmount(activityProjectSetting.getChannelManagerAmount());
                // 渠道经理ID
                //couponGetEntityDO.setChannelManagerId(activityPromotionEntity.getChannelManagerId());

            }
        }
        //couponGetEntityDO.setCouponEntity(couponEntity);
        return R.success(couponGetVo);
    }
}
