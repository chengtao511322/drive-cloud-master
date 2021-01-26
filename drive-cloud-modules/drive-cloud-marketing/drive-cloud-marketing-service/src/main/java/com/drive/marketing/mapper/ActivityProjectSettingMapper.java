package com.drive.marketing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.marketing.pojo.entity.ActivityProjectSettingEntity;

/**
 * 活动项目设置 Mapper 接口
 *
 * @author xiaoguo
 */
public interface ActivityProjectSettingMapper extends BaseMapper<ActivityProjectSettingEntity> {

    /**
     * 根据条件删除班型
     * @param activityProjectSettingEntity
     * @return
     */
    int deleteByCondition(ActivityProjectSettingEntity activityProjectSettingEntity);

    /**
     * 通过活动ID 删除数据
     * @param activityId
     * @return
     */
    int deleteByActivityId(String activityId);
}

