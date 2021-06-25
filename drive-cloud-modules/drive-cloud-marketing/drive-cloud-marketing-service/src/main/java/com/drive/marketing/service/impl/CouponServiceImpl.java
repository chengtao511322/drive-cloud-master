package com.drive.marketing.service.impl;

import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.marketing.mapper.CouponMapper;
import com.drive.marketing.pojo.entity.CouponEntity;
import com.drive.marketing.pojo.vo.CouponVo;
import com.drive.marketing.service.CouponService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author xiaoguo
 */
@Service
public class CouponServiceImpl extends BaseService<CouponMapper, CouponEntity> implements CouponService {

    @Override
    public Map<String, CouponEntity> batchCoupon(String[] ids) {
        if (ids.length <=0){
            return null;
        }
        List<CouponEntity> couponEntityList = this.getBaseMapper().selectBatchIds(Arrays.asList(ids));
        if (couponEntityList.isEmpty())return null;
        Map<String, CouponEntity> appleMap = couponEntityList.stream().collect(Collectors.toMap(CouponEntity::getId, a -> a,(k1, k2)->k1));
        return appleMap;
    }
}

