package com.drive.basics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.basics.mapper.ChannelMapper;
import com.drive.basics.pojo.entity.ChannelEntity;
import com.drive.basics.pojo.entity.OperatorEntity;
import com.drive.basics.service.ChannelService;
import com.drive.common.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 栏目 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class ChannelServiceImpl extends BaseService<ChannelMapper, ChannelEntity> implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @CacheEvict(value = "redisCache", key = "'channel:'+ #entity.getParentId()+'tenantId:'+#entity.getTenantId()")
    @Override
    public boolean save(ChannelEntity entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "redisCache", key = "'channel:'+ #entity.getParentId()+'tenantId:'+#entity.getTenantId()")
    @Override
    public boolean updateById(ChannelEntity entity) {
        return super.updateById(entity);
    }

    @Override
    public ChannelEntity moveDown(Integer sortNum) {
        /*return channelMapper.moveDown(sortNum);*/
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tenant_id","bbdc1bd499b241daa6fe99063e63a193");
        // 大于
        queryWrapper.gt("sort",sortNum);
        queryWrapper.orderByAsc("sort");
        queryWrapper.apply("limit",1);
       return this.getOne(queryWrapper);
    }

    @Override
    public ChannelEntity moveUp(Integer sortNum) {
        // return channelMapper.moveUp(sortNum);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tenant_id","bbdc1bd499b241daa6fe99063e63a193");
        // 大于
        queryWrapper.lt("sort",sortNum);
        queryWrapper.orderByAsc("sort");
        return this.getOne(queryWrapper);
    }
}

