package com.drive.marketing.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseService;
import com.drive.marketing.mapper.CouponGetMapper;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.vo.ActivityCouponGetVo;
import com.drive.marketing.service.CouponGetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author xiaoguo
 */
@Service
@Slf4j
public class CouponGetServiceImpl extends BaseService<CouponGetMapper, CouponGetEntity> implements CouponGetService {


    @Autowired
    private CouponGetMapper couponGetMapper;

    @Override
    public IPage<ActivityCouponGetVo> findCouponPageListByActivityId(Page page, Wrapper<CouponGetPageQueryParam> wrapper) {
        /*AND t2.is_delete = 0
        AND t2.status =1*/
        return couponGetMapper.findCouponPageListByActivityId(page,wrapper);
    }
}

