package com.drive.marketing.repository;

import com.drive.common.core.biz.ResObject;
import com.drive.marketing.pojo.dto.ActivityEditParam;

/**
 *活动
 */
public interface ActivityRepository {

    /**
     * 发布活动
     * @param activityEditParam
     * @return
     */
    ResObject publishActivity(ActivityEditParam activityEditParam);

    /**
     * 修改活动
     * @param activityEditParam
     * @return
     */
    ResObject updateActivity(ActivityEditParam activityEditParam);


    /**
     * 分布式事务
     * @param account
     * @return
     */
    ResObject transfer(String account);
}
