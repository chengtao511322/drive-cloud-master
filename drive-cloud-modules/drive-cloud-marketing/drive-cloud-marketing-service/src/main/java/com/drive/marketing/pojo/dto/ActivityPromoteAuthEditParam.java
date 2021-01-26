package com.drive.marketing.pojo.dto;

import lombok.Data;


/**
 * 活动推广权限配置
 *
 * @author xiaoguo
 */
@Data
public class ActivityPromoteAuthEditParam {


    // 活动ID
    private String activityId;

    private String userId;

    // 栏目ID
    private String channelId;


}