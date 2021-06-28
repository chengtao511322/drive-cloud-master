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

    @LcnTransaction //分布式事务注解
    @Override
    public ResObject saveCoupon(CouponEditParam couponEditParam) {
        log.info(this.getClass() + "publishCoupon方法请求参数{}",couponEditParam);
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

    @Override
    public ResObject sendCouponUser(CouponGetEditParam couponGetEditParam) {
        log.info(this.getClass() + "sendCouponUser-方法请求参数{}",couponGetEditParam);
        if (StrUtil.isEmpty(couponGetEditParam.getUserId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(couponGetEditParam.getCouponGetId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 查询优惠券状态
        CouponGetEntity couponGetDo = couponGetService.getById(couponGetEditParam.getCouponGetId());
        Optional.ofNullable(couponGetDo).orElseThrow(()-> new BizException(500,SubResultCode.NOT_COUPON_USE.subCode(),SubResultCode.NOT_COUPON_USE.subMsg()));
        if (!(couponGetDo.getStatus().equals(StatusEnum.NOT_GET_COUPON.getCode())))return R.failure(SubResultCode.COUPON_ALREADY_FBAGGAGE.subCode(),"该优惠券已经领取或者使用");
        StudentInfoVo studentInfoVo = null;
        // 查询用户信息
        ResObject resObject = remoteStudentFeignService.get(couponGetEditParam.getUserId());
        if (resObject.getCode().equals(ResCodeEnum.SUCCESS.getCode())){
            studentInfoVo = (StudentInfoVo) resObject.getData();
        }
        // 查询优惠券
        CouponEntity couponEntityDo = couponService.getById(couponGetDo.getCouponId());
        Optional.ofNullable(couponEntityDo).orElseThrow(()-> new BizException(500,SubResultCode.NOT_COUPON_USE.subCode(),SubResultCode.NOT_COUPON_USE.subMsg()));
        if (!(couponGetDo.getStatus().equals(StatusEnum.YES.getCode())))return R.failure(SubResultCode.COUPON_ALREADY_FBAGGAGE.subCode(),"该优惠券已经领取或者使用");
        // 优惠券可使用期限
        int useDay = couponEntityDo.getUseDay();
        //优惠券 有效期开始日
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime periodTimeStart = LocalDateTime.parse(TmDateUtil.getCurrentDatetime(new Date()),df);
        couponGetDo.setPeriodTimeStart(periodTimeStart);
        LocalDateTime periodTimeEnd = LocalDateTime.parse(TmDateUtil.toString1(TmDateUtil.addDay(new Date(),useDay)),df);
        couponGetDo.setPeriodTimeEnd(periodTimeEnd);
        //优惠券 设置有效期内  结束
        // couponGetEntity.setPeriodTimeEnd(TmDateUtil.toString(TmDateUtil.addDay(new Date(),useDay)));
        //优惠券 设置优惠券码
        // 设置领取状态    优惠券使用状态:1 未领取  2 已经领取 3 已经使用 4：优惠券过期
        couponGetDo.setStatus(StatusEnum.GET_COUPON.getCode());
        Optional.ofNullable(studentInfoVo).ifPresent(u ->{
            couponGetDo.setPhone(u.getPhone());
            couponGetDo.setUserName(u.getUsername());
        });
        couponGetDo.setGetTime(LocalDateTime.now());
        // 后台发送
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
