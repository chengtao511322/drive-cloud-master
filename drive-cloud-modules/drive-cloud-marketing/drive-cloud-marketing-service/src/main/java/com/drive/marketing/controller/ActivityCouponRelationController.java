package com.drive.marketing.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.dto.ActivityCouponRelationPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityCouponRelationEntity;
import com.drive.marketing.service.ActivityCouponRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "活动关联管理")
@Slf4j
@RestController
@RequestMapping("/activityCouponRelation")
public class ActivityCouponRelationController extends BaseController<ActivityCouponRelationPageQueryParam, ActivityCouponRelationEntity> {


    @Autowired
    private ActivityCouponRelationService activityCouponRelationService;

    /**
     * 新增
     */
    @ApiOperation("新增")
    @ApiImplicitParam(name = "CouponEditParam ", value = "新增", dataType = "CouponEditParam")
    //@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:add')")
    @EventLog(message = "新增", businessType = EventLogEnum.CREATE)
    @PostMapping("addActivityCoupon")
    public ResObject addActivityCoupon(@Valid @RequestBody ActivityCouponRelationEditParam activityCouponRelationEditParam) {
        if (StrUtil.isEmpty(activityCouponRelationEditParam.getActivityId())){
            R.failure("活动ID数据空");
        }
        if (StrUtil.isEmpty(activityCouponRelationEditParam.getCouponId())){
            R.failure("优惠券ID数据空");
        }
        QueryWrapper<ActivityCouponRelationEntity> queryWrapper = new QueryWrapper<ActivityCouponRelationEntity>();
        queryWrapper.eq("activity_id",activityCouponRelationEditParam.getActivityId());
        queryWrapper.eq("coupon_id",activityCouponRelationEditParam.getCouponId());
        ActivityCouponRelationEntity activityCouponRelation = activityCouponRelationService.getOne(queryWrapper);
        if (activityCouponRelation != null){
            log.error("出错");
            return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),"已经添加过数据");
        }
        ActivityCouponRelationEntity activityCouponRelationEntity = BeanConvertUtils.copy(activityCouponRelationEditParam,ActivityCouponRelationEntity.class);
        return R.toRes(activityCouponRelationService.save(activityCouponRelationEntity));
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @ApiImplicitParam(name = "CouponEditParam ", value = "修改", dataType = "CouponEditParam")
   // @PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:edit')")
    @EventLog(message = "修改", businessType = EventLogEnum.UPDATE)
    @PostMapping("updateActivityCoupon")
    public ResObject updateActivityCoupon(@Valid @RequestBody ActivityCouponRelationEditParam activityCouponRelationEditParam) {
        if (StrUtil.isEmpty(activityCouponRelationEditParam.getActivityId())){
            R.failure("活动ID数据空");
        }
        if (StrUtil.isEmpty(activityCouponRelationEditParam.getCouponId())){
            R.failure("优惠券ID数据空");
        }
        ActivityCouponRelationEntity activityCouponRelationEntity = BeanConvertUtils.copy(activityCouponRelationEditParam,ActivityCouponRelationEntity.class);
        return R.toRes(activityCouponRelationService.updateById(activityCouponRelationEntity));
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
    //@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:delete')")
    @EventLog(message = "删除", businessType = EventLogEnum.DELETE)
    @PostMapping("/deleteActivityCoupon/{id}")
    public ResObject deleteActivityCoupon(@PathVariable("id") String id) {
        if (StrUtil.isEmpty(id)){
            R.failure("ID数据空");
        }
        return R.toRes(activityCouponRelationService.removeById(id));
    }

}
