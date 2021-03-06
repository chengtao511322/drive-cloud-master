package com.drive.marketing.repository.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.exception.BizException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.DateUtils;
import com.drive.common.core.utils.Redeem;
import com.drive.common.core.utils.TmDateUtil;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.marketing.pojo.dto.CouponAcquirePageQueryParam;
import com.drive.marketing.pojo.dto.CouponEditParam;
import com.drive.marketing.pojo.dto.CouponGetEditParam;
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
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private RemoteStudentFeignService remoteStudentFeignService;

    @Override
    @Transactional
    public ResObject publishCoupon(CouponEditParam couponEditParam) {
        log.info(this.getClass() + "publishCoupon??????????????????{}",couponEditParam);
        Long userId = SecurityUtils.getLoginUser().getUserId();
        CouponEntity coupon = couponMapStruct.toEntity(couponEditParam);
        coupon.setCreateBy(String.valueOf(userId));
        coupon.setReleaseTime(LocalDateTime.now());
        Boolean result = couponService.save(coupon);
        log.info("???????????????{}",result);
        List<String> arr = couponEditParam.getUseTypeList();
        if (arr.size() > 0){
            QueryWrapper<CouponProductRelationEntity> queryWrapper = new QueryWrapper<CouponProductRelationEntity>();
            queryWrapper.eq("coupon_id",coupon.getId());
            Boolean delCoupon = couponProductRelationService.remove(queryWrapper);
            log.info("?????????????????????{}",delCoupon);
            CouponProductRelationEntity couponProductRelationEntity = new CouponProductRelationEntity();
            couponProductRelationEntity.setCouponId(coupon.getId());
            couponProductRelationEntity.setType(Long.valueOf(arr.get(0)));
            couponProductRelationEntity.setProductId(arr.get(1));
            Boolean installCoupon  = couponProductRelationService.save(couponProductRelationEntity);
            log.info("????????????{}",installCoupon);
        }

        // ????????????
        Integer publishCount = couponEditParam.getPublishCount();
        if (publishCount > 0){
            // ???????????????????????????
            List<CouponGetEntity> couponGetList = new ArrayList<>();
            for (int i =0 ;i <=publishCount;i++){
                CouponGetEntity couponGetEntity = new CouponGetEntity();
                couponGetEntity.setStatus(StatusEnum.DISABLE.getCode());
                couponGetEntity.setCouponId(coupon.getId());
                // ??????????????????
                couponGetEntity.setCouponCode(Redeem.create((byte) 1, 1, 12, Redeem.password));
                couponGetList.add(couponGetEntity);
            }
            // ??????????????????
            Boolean isGetCoupResult =couponGetService.saveBatch(couponGetList);
            log.info("????????????{}",isGetCoupResult);
        }

        return R.toRes(result);
    }

    @LcnTransaction //?????????????????????
    @Override
    public ResObject saveCoupon(CouponEditParam couponEditParam) {
        log.info(this.getClass() + "publishCoupon??????????????????{}",couponEditParam);
        Long userId = SecurityUtils.getLoginUser().getUserId();
        CouponEntity coupon = couponMapStruct.toEntity(couponEditParam);
        coupon.setCreateBy(String.valueOf(userId));
        coupon.setReleaseTime(LocalDateTime.now());
        Boolean result = couponService.save(coupon);
        //int i = 1 / 0;
        return  R.success(result);
    }

    @Override
    public ResObject updateCoupon(CouponEditParam couponEditParam) {
        log.info(this.getClass() + "publishCoupon??????????????????{}",couponEditParam);
        if (StrUtil.isEmpty(couponEditParam.getId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        Long userId = SecurityUtils.getLoginUser().getUserId();
        CouponEntity coupon = couponMapStruct.toEntity(couponEditParam);
        coupon.setCreateBy(String.valueOf(userId));
        coupon.setReleaseTime(LocalDateTime.now());
        Boolean result = couponService.updateById(coupon);
        log.info("???????????????{}",result);
        List<String> arr = couponEditParam.getUseTypeList();
        if (arr.size() > 0){
            QueryWrapper<CouponProductRelationEntity> queryWrapper = new QueryWrapper<CouponProductRelationEntity>();
            queryWrapper.eq("coupon_id",coupon.getId());
            Boolean delCoupon = couponProductRelationService.remove(queryWrapper);
            log.info("?????????????????????{}",delCoupon);
            CouponProductRelationEntity couponProductRelationEntity = new CouponProductRelationEntity();
            couponProductRelationEntity.setCouponId(couponEditParam.getId());
            couponProductRelationEntity.setType(Long.valueOf(arr.get(0)));
            couponProductRelationEntity.setProductId(arr.get(1));
            Boolean installCoupon  = couponProductRelationService.save(couponProductRelationEntity);
            log.info("????????????{}",installCoupon);
        }
        return R.toRes(result);
    }

    @Override
    public ResObject<CouponGetVo> getCoupon(CouponAcquirePageQueryParam couponPageQueryParam) {
        log.info(this.getClass()+"getCoupon-????????????{}",couponPageQueryParam);
        // ??????????????????????????? ????????????????????????Id
        CouponGetEntity couponGet = couponGetService.getById(couponPageQueryParam.getCouponAcquireId());
        if (couponGet == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ????????????(????????????) ????????????
        long betweenDay = DateUtil.between(new Date(), DateUtils.asDate(couponGet.getPeriodTimeStart()), DateUnit.DAY);
        if (betweenDay >=1) {
            //couponOutDtoList.remove(item);
            //total =total - 1;
            log.info("?????????{},{},?????????????????????",couponGet.getCouponId());
            //subMsg = "????????????????????????";
            return R.failure(SubResultCode.DATA_NULL.subCode(),"????????????????????????");
            //return setProfessionalResultSuccess(SubResultCode.COUPON_EXPIRATION_TIME.subCode(),SubResultCode.COUPON_EXPIRATION_TIME.subMsg());
        }
        // do ??????
        CouponGetVo couponGetVo = BeanConvertUtils.copy(couponGet, CouponGetVo.class);
        // ????????????
        QueryWrapper<ChannelManagerActivityEntity> wrapper = new QueryWrapper<ChannelManagerActivityEntity>();
        if (StrUtil.isNotEmpty(couponGet.getPromoteUserId())){
            // ?????????????????????
            BigDecimal couponAmount = Optional.ofNullable(couponService.getById(couponGetVo.getCouponId()))
                    .map(u-> u.getAmount()).get();
            // ?????????????????????
            couponGetVo.setCouponAmount(couponAmount);
            wrapper.eq("activity_id",couponGet.getSource());
            wrapper.eq("promotion_user_id",couponGet.getPromoteUserId());
            // ??????ID
            //wrapper.eq(StrUtil.isNotEmpty(couponGetInputDto.getProjectId()),"project_id",couponGetInputDto.getProjectId());
            int activityPromotionCount = channelManagerActivityService.count(wrapper);
            if (activityPromotionCount <= 0){
                return R.failure(SubResultCode.CHANNEL_MARAGER_NOT_ACTIVITY.subCode(),"?????????????????????????????????");
            }
            // ??????????????????
            QueryWrapper activityProjectSettingWrapper = new QueryWrapper();
            // ??????ID
            activityProjectSettingWrapper.eq("activity_id",couponGet.getSource());
            // ??????ID
            activityProjectSettingWrapper.eq("project_id",couponPageQueryParam.getProjectId());
            ActivityProjectSettingEntity activityProjectSetting = activityProjectSettingService.getOne(activityProjectSettingWrapper);
            log.info("activityProjectSetting{}",activityProjectSetting);
            // ???????????????
            if (activityProjectSetting != null){
                // ???????????????
                couponGetVo.setDeductAmount(activityProjectSetting.getDeductAmount());
                // ???????????????
                couponGetVo.setPromotionAmount(activityProjectSetting.getPromotionAmount());
                // ??????????????????
                couponGetVo.setChannelManagerAmount(activityProjectSetting.getChannelManagerAmount());
                // ????????????ID
                //couponGetEntityDO.setChannelManagerId(activityPromotionEntity.getChannelManagerId());

            }
        }
        //couponGetEntityDO.setCouponEntity(couponEntity);
        return R.success(couponGetVo);
    }

    @Override
    public ResObject sendCouponUser(CouponGetEditParam couponGetEditParam) {
        log.info(this.getClass() + "sendCouponUser-??????????????????{}",couponGetEditParam);
        if (StrUtil.isEmpty(couponGetEditParam.getUserId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(couponGetEditParam.getCouponGetId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // ?????????????????????
        CouponGetEntity couponGetDo = couponGetService.getById(couponGetEditParam.getCouponGetId());
        Optional.ofNullable(couponGetDo).orElseThrow(()-> new BizException(500,SubResultCode.NOT_COUPON_USE.subCode(),SubResultCode.NOT_COUPON_USE.subMsg()));
        if (!(couponGetDo.getStatus().equals(StatusEnum.NOT_GET_COUPON.getCode())))return R.failure(SubResultCode.COUPON_ALREADY_FBAGGAGE.subCode(),"????????????????????????????????????");
        StudentInfoVo studentInfoVo = null;
        // ??????????????????
        ResObject resObject = remoteStudentFeignService.get(couponGetEditParam.getUserId());
        if (resObject.getCode().equals(ResCodeEnum.SUCCESS.getCode())){
            studentInfoVo = (StudentInfoVo) resObject.getData();
        }
        // ???????????????
        CouponEntity couponEntityDo = couponService.getById(couponGetDo.getCouponId());
        Optional.ofNullable(couponEntityDo).orElseThrow(()-> new BizException(500,SubResultCode.NOT_COUPON_USE.subCode(),SubResultCode.NOT_COUPON_USE.subMsg()));
        if (!(couponGetDo.getStatus().equals(StatusEnum.YES.getCode())))return R.failure(SubResultCode.COUPON_ALREADY_FBAGGAGE.subCode(),"????????????????????????????????????");
        // ????????????????????????
        int useDay = couponEntityDo.getUseDay();
        //????????? ??????????????????
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime periodTimeStart = LocalDateTime.parse(TmDateUtil.getCurrentDatetime(new Date()),df);
        couponGetDo.setPeriodTimeStart(periodTimeStart);
        LocalDateTime periodTimeEnd = LocalDateTime.parse(TmDateUtil.toString1(TmDateUtil.addDay(new Date(),useDay)),df);
        couponGetDo.setPeriodTimeEnd(periodTimeEnd);
        //????????? ??????????????????  ??????
        // couponGetEntity.setPeriodTimeEnd(TmDateUtil.toString(TmDateUtil.addDay(new Date(),useDay)));
        //????????? ??????????????????
        // ??????????????????    ?????????????????????:1 ?????????  2 ???????????? 3 ???????????? 4??????????????????
        couponGetDo.setStatus(StatusEnum.GET_COUPON.getCode());
        Optional.ofNullable(studentInfoVo).ifPresent(u ->{
            couponGetDo.setPhone(u.getPhone());
            couponGetDo.setUserName(u.getUsername());
        });
        couponGetDo.setGetTime(LocalDateTime.now());
        // ????????????
        couponGetDo.setGetType("0");
        couponGetDo.setTenantId(couponEntityDo.getTenantId());
        couponGetDo.setUserId(couponGetEditParam.getUserId());
        Boolean result = couponGetService.updateById(couponGetDo);
        if (!result){
            return R.failure();
        }
        return R.success(SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.COUPON_PRESENT_SUCCESS.subMsg());
    }


}
