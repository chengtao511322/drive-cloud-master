package com.drive.marketing.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.vo.ActivityCouponGetVo;
import org.apache.ibatis.annotations.Param;

/**
 *
 *  服务类
 *
 * @author xiaoguo
 */
public interface CouponGetService extends IService<CouponGetEntity>{

  /*  *//**
     * 分页查询
     * @param couponGetPageQueryParam
     * @return
     *//*
    List<CouponGetEntity> getCouponGetPageList(CouponGetPageQueryParam couponGetPageQueryParam);*/



    /**
     * 分页查询数据
     * @param wrapper
     * @return
     */
    IPage<ActivityCouponGetVo> findCouponPageListByActivityId(Page page, @Param("ew") Wrapper<CouponGetPageQueryParam> wrapper);

}

