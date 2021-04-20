package com.drive.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.admin.mapper.AreaMapper;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.vo.ViewDataVo;
import com.drive.admin.service.AreaService;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
 *  服务实现类
 *
 * @author xiaoguo
 */
@Service
@Slf4j
public class AreaServiceImpl extends ServiceImpl<AreaMapper, AreaEntity> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    // 创建数组型缓冲等待队列
    BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
    // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(100);
    // 创建一个可缓存线程池

    @Override
    public AreaEntity getByBaCode(String baCode) {
        if (StrUtil.isEmpty(baCode)){
            return null;
        }
        QueryWrapper<AreaEntity> queryWrapper = new QueryWrapper<AreaEntity>();
        queryWrapper.eq("ba_code",baCode);
        return areaMapper.selectOne(queryWrapper);
    }

    @Override
    public List<ViewDataVo> findView() {
        return areaMapper.findView();
    }


     @PostConstruct
    public void init() {
        QueryWrapper<AreaEntity> wrapper = new QueryWrapper<AreaEntity>();
        List<AreaEntity> operatorEntityList = areaMapper.selectList(wrapper);
        Jedis jedis = RedisDS.create().getJedis();
        Pipeline pipe = jedis.pipelined(); // 先创建一个 pipeline 的链接对象
        long startTime = System.currentTimeMillis();
        operatorEntityList.stream().forEach((item) -> {
           cachedThreadPool.execute(new Runnable() {
               public void run() {
                   // 打印正在执行的缓存线程信息
                   redisTemplate.opsForValue().set(getCacheKey(item.getBaCode()), item);
               }
           });
       });
        cachedThreadPool.shutdown();
        pipe.sync(); // 获取所有的 response
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

