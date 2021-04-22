package com.drive.admin.service.impl;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.mapper.ServiceInfoMapper;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.entity.ServiceInfoEntity;
import com.drive.admin.service.ServiceInfoService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.CacheConstants;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    private final Jedis jedis = RedisDS.create().getJedis();

    @PostConstruct
    public void init() {
        QueryWrapper<ServiceInfoEntity> wrapper = new QueryWrapper<ServiceInfoEntity>();
        List<ServiceInfoEntity> serviceInfoList = this.getBaseMapper().selectList(wrapper);

        Pipeline pipe = jedis.pipelined(); // 先创建一个 pipeline 的链接对象
        long startTime = System.currentTimeMillis();
        serviceInfoList.stream().forEach((item) -> {
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    // 打印正在执行的缓存线程信息
                    jedis.set(getCacheKey(item.getId()), JSONObject.toJSONString(item));
                }
            });
        });
        cachedThreadPool.shutdown();
        pipe.sync(); // 获取所有的 response
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    private String getCacheKey(String configKey)
    {
        return CacheConstants.REDIS_CACHE_SERVICE_KEY + configKey;
    }
}

