package com.drive.marketing.service.impl;

import cn.hutool.core.util.StrUtil;
import com.drive.common.core.base.BaseService;
import com.drive.marketing.mapper.ActivityInfoMapper;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.vo.ActivityCouponRelationVo;
import com.drive.marketing.service.IActivityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityInfoServiceImpl extends BaseService<ActivityInfoMapper, ActivityInfoEntity> implements IActivityInfoService {

    @Autowired
    private ActivityInfoMapper activityInfoMapper;

    @Override
    public List<ActivityCouponRelationVo> getActivityCouponRelation(String activityId) {
        if (StrUtil.isEmpty(activityId)){
            return null;
        }
        return activityInfoMapper.getActivityCouponRelation(activityId);
    }

    @Override
    public ActivityCouponRelationVo getActivityCouponRelationByCondition(ActivityCouponRelationEditParam activityCouponRelationEditParam) {
        return activityInfoMapper.getActivityCouponRelationByCondition(activityCouponRelationEditParam);
    }
}
