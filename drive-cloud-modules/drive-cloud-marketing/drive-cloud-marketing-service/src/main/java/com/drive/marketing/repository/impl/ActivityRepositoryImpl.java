package com.drive.marketing.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.marketing.pojo.dto.ActivityEditParam;
import com.drive.marketing.pojo.dto.ActivityProjectSettingEditParam;
import com.drive.marketing.pojo.entity.ActivityCouponRelationEntity;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.entity.ActivityProjectSettingEntity;
import com.drive.marketing.repository.ActivityRepository;
import com.drive.marketing.service.ActivityCouponRelationService;
import com.drive.marketing.service.ActivityProjectSettingService;
import com.drive.marketing.service.CouponProductRelationService;
import com.drive.marketing.service.IActivityInfoService;
import com.drive.marketing.service.mapstruct.ActivityMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ActivityRepositoryImpl implements ActivityRepository {

    @Autowired
    private IActivityInfoService activityInfoService;

    @Autowired
    private ActivityMapStruct activityMapStruct;

    @Autowired
    private ActivityProjectSettingService activityProjectSettingService;

    @Autowired
    private CouponProductRelationService couponProductRelationService;

    @Autowired
    private ActivityCouponRelationService activityCouponRelationService;

  /*  @Autowired
    private RemoteAccountFeignService accountFeignService;*/



    @Override
    @Transactional
    public ResObject publishActivity(ActivityEditParam activityEditParam) {
        log.info(this.getClass() + "publishActivity-方法请求参数{}",activityEditParam);
        if (activityEditParam == null){
            log.error("添加参数空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }


        // 先保存优惠券
        // 转化DTO
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        activityInfoEntity.setCreateTime(LocalDateTime.now());
        Boolean saveActivityResult = activityInfoService.save(activityInfoEntity);
        if (!saveActivityResult){
            log.error("数据添加失败");
            return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }

        List<ActivityProjectSettingEditParam> activityProjectSettingList = activityEditParam.getActivityProjectSettingList();
        // 产品班型不等于空的话 就去增加班型
        if (activityProjectSettingList != null && activityProjectSettingList.size() > 0){

            //删除数据
            Boolean delResult = activityProjectSettingService.deleteByActivityId(activityEditParam.getId());
            if (!delResult)log.error("删除班型-活动出错");
            // 删除结果
            Boolean couponDelResult = activityCouponRelationService.deleteByActivityId(activityEditParam.getId());
            if (!couponDelResult)log.error("删除优惠券-活动出错");
            // 设置班型 ,价格 修改 -
            activityProjectSettingList.forEach((item) -> {
                // 创建 班型 对象
                ActivityProjectSettingEntity activityProjectSetting = new ActivityProjectSettingEntity();
                //  设置活动ID
                activityProjectSetting.setActivityId(activityInfoEntity.getId());
                // 设置 总金额
                activityProjectSetting.setDeductAmount(item.getDeductAmount());
                // 推广商 获得金额
                activityProjectSetting.setPromotionAmount(item.getPromotionAmount());
                // 渠道经理 获得金
                activityProjectSetting.setChannelManagerAmount(item.getChannelManagerAmount());
                // 设置产品名称
                activityProjectSetting.setProjectName(item.getProjectName());
                // 设置产品ID
                activityProjectSetting.setProjectId(item.getId());
                // 设置班型
                activityProjectSetting.setTenantId(item.getTenantId());
                // 设置创建时间
                activityProjectSetting.setCreateTime(LocalDateTime.now());
                // 设置产品价格
                activityProjectSetting.setProjectAmount(item.getProjectAmount());
                //删除数据
               /* Boolean delResult = activityProjectSettingService.deleteByCondition(activityProjectSetting);
                if (!delResult)log.error("删除班型-活动出错");*/
                // 保存班型
                Boolean projectSettingResult = activityProjectSettingService.save(activityProjectSetting);
                // 判断结果
                if (!projectSettingResult)log.error("添加班型-活动出错");

                // 优惠券信息
                ActivityCouponRelationEntity activityCouponRelationEntity = new ActivityCouponRelationEntity();
                // 活动ID
                activityCouponRelationEntity.setActivityId(activityInfoEntity.getId());
                // 设置优惠券ID
                activityCouponRelationEntity.setCouponId(item.getCouponId());
                // 优惠券名称
                activityCouponRelationEntity.setCouponName(item.getCouponName());
                // 设置产品Id
                if (StrUtil.isNotEmpty(item.getProjectId()))activityCouponRelationEntity.setProjectId(item.getProjectId());


                // 保存优惠券活动关联表
                Boolean couponRelationResult = activityCouponRelationService.save(activityCouponRelationEntity);
                // 判断结果
                if (!couponRelationResult)log.error("添加优惠券-活动出错");


                // 优惠券指定分类使用
                //couponProductRelationService.
            });
        }

       /* // 优惠券
        List<CouponEditParam> couponList = activityEditParam.getCouponList();
        // 判断提交的优惠券是否数据空
        if (couponList != null && couponList.size() > 0){
            // 循环优惠券
            couponList.forEach((item) ->{
                // 优惠券信息
                ActivityCouponRelationEntity activityCouponRelationEntity = new ActivityCouponRelationEntity();
                // 活动ID
                activityCouponRelationEntity.setActivityId(activityInfoEntity.getId());
                // 设置优惠券ID
                activityCouponRelationEntity.setCouponId(item.getId());
                // 设置产品Id
                if (StrUtil.isNotEmpty(item.getProjectId()))activityCouponRelationEntity.setProjectId(item.getProjectId());

                // 删除结果
                Boolean delResult = activityCouponRelationService.deleteByCondition(activityCouponRelationEntity);
                if (!delResult)log.error("删除优惠券-活动出错");
                // 保存优惠券活动关联表
                Boolean couponRelationResult = activityCouponRelationService.save(activityCouponRelationEntity);
                // 判断结果
                if (!couponRelationResult)log.error("添加优惠券-活动出错");

            });
        }
        // -修改数据成功-*/
        return R.success("修改数据成功");
    }

    @Override
    public ResObject updateActivity(ActivityEditParam activityEditParam) {
        log.info(this.getClass() + "updateActivity -请求参数{}",activityEditParam);
        if (StrUtil.isEmpty(activityEditParam.getId())){
            log.error("出错");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        // 先保存优惠券
        // 转化DTO
        ActivityInfoEntity activityInfoEntity = activityMapStruct.toEntity(activityEditParam);
        activityInfoEntity.setUpdateTime(LocalDateTime.now());
        Boolean updateActivityResult = activityInfoService.updateById(activityInfoEntity);
        if (!updateActivityResult){
            log.error("数据修改失败");
            return R.failure(SubResultCode.DATA_UPDATE_FAILL.subCode(),SubResultCode.DATA_UPDATE_FAILL.subMsg());
        }

        List<ActivityProjectSettingEditParam> activityProjectSettingList = activityEditParam.getActivityProjectSettingList();
        // 产品班型不等于空的话 就去增加班型
        if (activityProjectSettingList != null && activityProjectSettingList.size() > 0){
            //删除数据
            Boolean delResult = activityProjectSettingService.deleteByActivityId(activityEditParam.getId());
            if (!delResult)log.error("删除班型-活动出错");

            // 删除结果
            Boolean couponDelResult = activityCouponRelationService.deleteByActivityId(activityEditParam.getId());
            if (!couponDelResult)log.error("删除优惠券-活动出错");
            // 设置班型 ,价格 修改 -
            activityProjectSettingList.forEach((item) -> {
                // 创建 班型 对象
                ActivityProjectSettingEntity activityProjectSetting = new ActivityProjectSettingEntity();
                //  设置活动ID
                activityProjectSetting.setActivityId(activityInfoEntity.getId());
                // 设置 总金额
                activityProjectSetting.setDeductAmount(item.getDeductAmount());
                // 推广商 获得金额
                activityProjectSetting.setPromotionAmount(item.getPromotionAmount());
                // 渠道经理 获得金
                activityProjectSetting.setChannelManagerAmount(item.getChannelManagerAmount());
                // 设置产品名称
                activityProjectSetting.setProjectName(item.getProjectName());
                // 设置产品ID
                activityProjectSetting.setProjectId(item.getProjectId());
                // 设置班型
                activityProjectSetting.setTenantId(item.getTenantId());
                // 设置创建时间
                activityProjectSetting.setCreateTime(LocalDateTime.now());
                // 设置产品价格
                activityProjectSetting.setProjectAmount(item.getProjectAmount());
                // 保存班型
                Boolean projectSettingResult = activityProjectSettingService.save(activityProjectSetting);
                // 判断结果
                if (!projectSettingResult)log.error("添加班型-活动出错");


                // 优惠券信息
                ActivityCouponRelationEntity activityCouponRelationEntity = new ActivityCouponRelationEntity();
                // 活动ID
                activityCouponRelationEntity.setActivityId(activityInfoEntity.getId());
                // 设置优惠券ID
                activityCouponRelationEntity.setCouponId(item.getCouponId());
                // 优惠券名称
                activityCouponRelationEntity.setCouponName(item.getCouponName());
                // 设置产品Id
                if (StrUtil.isNotEmpty(item.getProjectId()))activityCouponRelationEntity.setProjectId(item.getProjectId());


                // 保存优惠券活动关联表
                Boolean couponRelationResult = activityCouponRelationService.save(activityCouponRelationEntity);
                // 判断结果
                if (!couponRelationResult)log.error("添加优惠券-活动出错");
            });
        }

       /* // 优惠券
        List<CouponEditParam> couponList = activityEditParam.getCouponList();
        // 判断提交的优惠券是否数据空
        if (couponList != null && couponList.size() > 0){
            // 删除结果
            Boolean delResult = activityCouponRelationService.deleteByActivityId(activityEditParam.getId());
            if (!delResult)log.error("删除优惠券-活动出错");
            // 循环优惠券
            couponList.forEach((item) ->{
                // 优惠券信息
                ActivityCouponRelationEntity activityCouponRelationEntity = new ActivityCouponRelationEntity();
                // 活动ID
                activityCouponRelationEntity.setActivityId(activityInfoEntity.getId());
                // 设置优惠券ID
                activityCouponRelationEntity.setCouponId(item.getId());
                // 设置产品Id
                if (StrUtil.isNotEmpty(item.getProjectId()))activityCouponRelationEntity.setProjectId(item.getProjectId());


                // 保存优惠券活动关联表
                Boolean couponRelationResult = activityCouponRelationService.save(activityCouponRelationEntity);
                // 判断结果
                if (!couponRelationResult)log.error("添加优惠券-活动出错");
            });
        }*/
        // -修改数据成功-
        return R.success("修改数据成功");
    }

    @Transactional
    public ResObject transfer(String account){
        ActivityInfoEntity activityInfoEntity = new ActivityInfoEntity();
        activityInfoEntity.setId("12151861092245791930");
        activityInfoEntity.setDescription("测试事务");
        Boolean result = activityInfoService.updateById(activityInfoEntity);

        //accountFeignService.increaseAmount("fe42c41dc6b74b918919");
        return R.success();
    }
}
