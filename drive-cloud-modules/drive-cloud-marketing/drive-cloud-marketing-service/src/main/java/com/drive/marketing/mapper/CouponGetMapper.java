package com.drive.marketing.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.vo.ActivityCouponGetVo;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper 接口
 *
 * @author xiaoguo
 */
public interface CouponGetMapper extends BaseMapper<CouponGetEntity> {


    /**
     * 分页查询数据
     * @param wrapper
     * @return
     */
    IPage<ActivityCouponGetVo> findCouponPageListByActivityId(Page page, @Param("ew") Wrapper<CouponGetPageQueryParam> wrapper);

}

