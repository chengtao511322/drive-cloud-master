package com.drive.marketing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.marketing.pojo.entity.ActivityProjectSettingEntity;

/**
 *
 * 活动项目设置 服务类
 *
 * @author xiaoguo
 */
public interface ActivityProjectSettingService extends IService<ActivityProjectSettingEntity>{

    /**
     * 根据条件删除班型
     * @param activityProjectSettingEntity
     * @return
     */
    Boolean deleteByCondition(ActivityProjectSettingEntity activityProjectSettingEntity);

    /**
     * 通过活动ID删除数据
     * @param activityId
     * @return
     */
    Boolean deleteByActivityId(String activityId);
}

