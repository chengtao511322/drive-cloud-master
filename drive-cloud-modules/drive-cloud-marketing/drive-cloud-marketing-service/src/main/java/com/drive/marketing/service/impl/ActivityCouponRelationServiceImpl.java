package com.drive.marketing.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.marketing.mapper.ActivityCouponRelationMapper;
import com.drive.marketing.pojo.entity.ActivityCouponRelationEntity;
import com.drive.marketing.service.ActivityCouponRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动优惠券关联表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class ActivityCouponRelationServiceImpl extends ServiceImpl<ActivityCouponRelationMapper, ActivityCouponRelationEntity> implements ActivityCouponRelationService {

    @Autowired
    private ActivityCouponRelationMapper activityCouponRelationMapper;

    @Override
    public int removeBatch(List<ActivityCouponRelationEntity> activityCouponRelationList) {
        return activityCouponRelationMapper.removeBatch(activityCouponRelationList);
    }

    @Override
    public Boolean deleteByCondition(ActivityCouponRelationEntity activityCouponRelationEntity) {
        int result = activityCouponRelationMapper.deleteByCondition(activityCouponRelationEntity);
        return result > 0?true:false;
    }

    @Override
    public Boolean deleteByActivityId(String activityId) {
        if (StrUtil.isEmpty(activityId)){
            return false;
        }
        return activityCouponRelationMapper.deleteByActivityId(activityId) >0 ?true:false;
    }


}

