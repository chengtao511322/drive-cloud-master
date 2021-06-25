package com.drive.marketing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.common.core.biz.ResObject;
import com.drive.marketing.pojo.entity.CouponEntity;
import com.drive.marketing.pojo.vo.CouponVo;

import java.util.Map;

/**
 *
 *  服务类
 *
 * @author xiaoguo
 */
public interface CouponService extends IService<CouponEntity>{


    /**
     * 批量查询数据
     * @param ids
     * @return
     */
    Map<String, CouponEntity> batchCoupon(String[] ids);
}

