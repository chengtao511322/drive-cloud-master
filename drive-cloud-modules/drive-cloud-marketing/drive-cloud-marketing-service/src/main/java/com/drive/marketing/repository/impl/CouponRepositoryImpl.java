package com.drive.marketing.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.marketing.pojo.dto.CouponEditParam;
import com.drive.marketing.pojo.entity.CouponEntity;
import com.drive.marketing.pojo.entity.CouponProductRelationEntity;
import com.drive.marketing.repository.CouponRepository;
import com.drive.marketing.service.CouponProductRelationService;
import com.drive.marketing.service.CouponService;
import com.drive.marketing.service.mapstruct.CouponMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CouponRepositoryImpl implements CouponRepository {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponMapStruct couponMapStruct;

    @Autowired
    private CouponProductRelationService couponProductRelationService;

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
            couponProductRelationEntity.setCouponId(couponEditParam.getId());
            couponProductRelationEntity.setType(Long.valueOf(arr.get(0)));
            couponProductRelationEntity.setProductId(arr.get(1));
            Boolean installCoupon  = couponProductRelationService.save(couponProductRelationEntity);
            log.info("插入结果{}",installCoupon);
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
}
