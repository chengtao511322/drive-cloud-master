package com.drive.basics.service.impl;

import com.drive.basics.mapper.BannerMapper;
import com.drive.basics.pojo.entity.BannerEntity;
import com.drive.basics.service.BannerService;
import com.drive.common.core.base.BaseService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * banner 轮播图 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class BannerServiceImpl extends BaseService<BannerMapper, BannerEntity> implements BannerService {

    @Override
    @CacheEvict(value = "redisCache", key = "'banner:'+ #entity.getTenantId()")
    public boolean save(BannerEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = "redisCache", key = "'banner:'+ #entity.getTenantId()")
    public boolean updateById(BannerEntity entity) {
        return super.updateById(entity);
    }
}

