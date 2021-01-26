package com.drive.marketing.service.impl;

import cn.hutool.core.util.StrUtil;
import com.drive.common.core.base.BaseService;
import com.drive.marketing.mapper.ActivityProjectSettingMapper;
import com.drive.marketing.pojo.entity.ActivityProjectSettingEntity;
import com.drive.marketing.service.ActivityProjectSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动项目设置 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class ActivityProjectSettingServiceImpl extends BaseService<ActivityProjectSettingMapper, ActivityProjectSettingEntity> implements ActivityProjectSettingService {

    @Autowired
    private ActivityProjectSettingMapper activityProjectSettingMapper;

    @Override
    public Boolean deleteByCondition(ActivityProjectSettingEntity activityProjectSettingEntity) {
        if (StrUtil.isEmpty(activityProjectSettingEntity.getActivityId())){
            return false;
        }
       /* if (StrUtil.isEmpty(activityProjectSettingEntity.getProjectId())){
            return false;
        }*/

        return activityProjectSettingMapper.deleteByCondition(activityProjectSettingEntity) >0 ?true:false;
    }

    @Override
    public Boolean deleteByActivityId(String activityId) {
        if (StrUtil.isEmpty(activityId)){
            return false;
        }
       /* if (StrUtil.isEmpty(activityProjectSettingEntity.getProjectId())){
            return false;
        }*/

        return activityProjectSettingMapper.deleteByActivityId(activityId) >0 ?true:false;
    }
}

