package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.mapper.ServiceInfoMapper;
import com.drive.admin.pojo.entity.ServiceInfoEntity;
import com.drive.admin.service.ServiceInfoService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.datascope.annotation.DataScope;
import com.drive.common.redis.service.RedisService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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


    @DataScope(deptAlias = "operator_id", userAlias = "id",module = "admin")
    @Override
    public <E extends IPage<ServiceInfoEntity>> E page(E page, Wrapper<ServiceInfoEntity> queryWrapper) {
        return super.page(page,queryWrapper);
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
            map.put(getCacheKey(item.getId()),item);
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

