package com.drive.admin.service.impl;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.mapper.ServiceInfoMapper;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.entity.ServiceInfoEntity;
import com.drive.admin.service.ServiceInfoService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.redis.service.RedisService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 客服人员信息表 服务实现类
 *
         * @author xiaoguo
        */
@Service
public class ServiceInfoServiceImpl extends BaseService<ServiceInfoMapper, ServiceInfoEntity> implements ServiceInfoService {

    // 创建数组型缓冲等待队列
    BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
    // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(100);

    @Resource
    private RedisService redisService;

    @CacheEvict(value = "redisCache", key = "'serviceItem:service_'+#entity.getId()")
    @Override
    public boolean save(ServiceInfoEntity entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "redisCache", key = "'serviceItem:service_'+#entity.getId()")
    @Override
    public boolean updateById(ServiceInfoEntity entity) {
        return super.updateById(entity);
    }

    //@Cacheable(value = "redisCache", key = "'serviceItem:service_'+ #id")
    @Override
    public ServiceInfoEntity getById(Serializable id) {
        return super.getById(id);
    }

    @PostConstruct
    public void init() {
        QueryWrapper<ServiceInfoEntity> wrapper = new QueryWrapper<ServiceInfoEntity>();
        wrapper.eq("status", StatusEnum.ENABLE.getCode());
        List<ServiceInfoEntity> serviceInfoList = this.getBaseMapper().selectList(wrapper);

        long startTime = System.currentTimeMillis();
        Map map = new ConcurrentHashMap(serviceInfoList.size());
        serviceInfoList.stream().forEach((item) -> {
            // 打印正在执行的缓存线程信息
            map.put(getCacheKey(item.getId()),JSONObject.toJSONString(item));
        });
        // 提交
        redisService.executePipelined(map);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    private String getCacheKey(String configKey)
    {
        return CacheConstants.REDIS_CACHE_SERVICE_KEY + configKey;
    }
}

