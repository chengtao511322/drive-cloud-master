package com.drive.marketing.repository;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.common.core.biz.ResObject;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;

/**
 * 优惠券领取
 */
public interface CouponGetRepository {

    /**
     * 分页查询
     */
    ResObject pageList(CouponGetPageQueryParam param, QueryWrapper queryWrapper);
}
