package com.drive.marketing.repository;

import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.common.core.biz.ResObject;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.dto.ActivityInfoPageQueryParam;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.vo.ActivityInfoVo;

import java.util.Map;

public interface ActivityInfoRepository {

    ResObject reduceInventoryRollback();

    /**+
     * 条件查询优惠券
     * @param activityCouponRelationEditParam
     * @return
     */
    ResObject getActivityCouponRelationByCondition(ActivityCouponRelationEditParam activityCouponRelationEditParam);

    /**+
     * 通过活动ID 获取优惠券信息
     * @return
     */
    ResObject getCouponByActivityId(String activityId);

    /**
     * 分页条件查询
     * @param activityId
     * @return
     */
    ResObject findCouponPageListByActivityId(CouponGetPageQueryParam activityEditParam);


    /**
     * 分页查询
     * @param param
     * @return
     */
    ResObject pageList(ActivityInfoPageQueryParam param);

    /**
     * 批量查询活动数据
     * @param ids 活动id
     * @return
     */
    ResObject<Map<String, ActivityInfoVo>> batchActivityInfo(String[] ids);
}
