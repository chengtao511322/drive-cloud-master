package com.drive.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.admin.mapper.AreaMapper;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.vo.ViewDataVo;
import com.drive.admin.service.AreaService;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 *  服务实现类
 *
 * @author xiaoguo
 */
@Service
@Slf4j
public class AreaServiceImpl extends ServiceImpl<AreaMapper, AreaEntity> implements AreaService {


    @Autowired
    private RedisService redisService;

    // 创建数组型缓冲等待队列
    BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
    // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(3);
    // 创建一个可缓存线程池

    @Override
    public AreaEntity getByBaCode(String baCode) {
        if (StrUtil.isEmpty(baCode)){
            return null;
        }
        QueryWrapper<AreaEntity> queryWrapper = new QueryWrapper<AreaEntity>();
        queryWrapper.eq("ba_code",baCode);
        return super.getById(queryWrapper);
    }

    @Override
    public List<ViewDataVo> findView() {
        return this.getBaseMapper().findView();
    }

    @Override
    public Boolean delAreaByCode(String code) {
        int result = this.getBaseMapper().delAreaByCode(code);
        if (result > 0)return true;
        return false;
    }

    @Override
    public Boolean saveArea(AreaEntity areaEntity) {
        int result = this.getBaseMapper().saveArea(areaEntity);
        if (result > 0)return true;
        return false;
    }


    @CacheEvict(value = "redisCache", key = "'areaItem:area_'+#entity.getId()")
    @Override
    public boolean save(AreaEntity entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "redisCache", key = "'areaItem:area_'+#entity.getId()")
    @Override
    public boolean updateById(AreaEntity entity) {
        return super.updateById(entity);
    }

    @Cacheable(value = "redisCache", key = "'areaItem:area_'+ #id")
    @Override
    public AreaEntity getById(Serializable id) {
        return super.getById(id);
    }

    @PostConstruct
    public void init() {
        QueryWrapper<AreaEntity> wrapper = new QueryWrapper<AreaEntity>();
        List<AreaEntity> operatorEntityList = super.list(wrapper);
        long startTime = System.currentTimeMillis();
         Map map = new ConcurrentHashMap(operatorEntityList.size());
        operatorEntityList.stream().forEach((item) -> {
           // 打印正在执行的缓存线程信息
            map.put(CacheConstants.REDIS_CACHE_AREA_KEY +item.getBaCode(),item);
       });
         redisService.executePipelined(map);
        //cachedThreadPool.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
   }

    /**
     *
     *
     *  Pipeline pipeline = jedis.pipelined();
     *     long startTime = System.currentTimeMillis();
     *     IntStream.range(0, 1000000).forEach(it -> pipeline.set("batch" + it, it + ""));
     *     pipeline.syncAndReturnAll();
     *     long endTime = System.currentTimeMillis();
     *     System.out.println(endTime - startTime);
     * ————————————————
     * 版权声明：本文为CSDN博主「xlecho」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/xlecho/article/details/103130855
     *
     * Jedis jedis = RedisDS.create().getJedis();
     *         QueryWrapper<AreaEntity> wrapper = new QueryWrapper<AreaEntity>();
     *         List<AreaEntity> operatorEntityList = areaMapper.selectList(wrapper);
     *         for (AreaEntity operator : operatorEntityList)
     *         {
     *             //JSONObject jsonObject = BeanConvertUtils.convertBean(operator, JSONObject.class);
     *             jedis.set(getCacheKey(operator.getBaCode()), JSONObject.toJSONString(operator));
     *         }
     * @param configKey
     * @return
     */
    private String getCacheKey(String configKey)
    {
        return CacheConstants.REDIS_CACHE_AREA_KEY + configKey;
    }
}

