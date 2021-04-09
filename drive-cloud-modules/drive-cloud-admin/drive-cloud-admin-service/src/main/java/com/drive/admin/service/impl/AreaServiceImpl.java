package com.drive.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.admin.mapper.AreaMapper;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.vo.ViewDataVo;
import com.drive.admin.service.AreaService;
import com.drive.common.core.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
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
    private AreaMapper areaMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    // 创建数组型缓冲等待队列
    BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
    // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(2);
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
       List<ViewDataVo> viewList = areaMapper.findView();
       log.info(this.getClass() + "数据{}", viewList.size());
        Jedis jedis = RedisDS.create().getJedis();
        Pipeline pipe = jedis.pipelined(); // 先创建一个 pipeline 的链接对象
      // redisTemplate.opsForValue().set("viewList:", viewList);
        long startTime = System.currentTimeMillis();
       //viewList.stream().forEach((item) -> {
       for (int i = 0; i < viewList.size(); i++) {
           pipe.set("pipe:"+viewList.get(0).getCode(), viewList.get(0).getValue());
       /*    try {
               // sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
               //Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }*/
           //cachedThreadPool.execute(new Runnable() {
             //  public void run() {
                   // 打印正在执行的缓存线程信息
                   /*System.out.println(Thread.currentThread().getName()
                           + "正在被执行");*/ 
                   //pipeline.sadd("viewList:"+item.getCode(), item.getValue());
                    // redisTemplate.opsForValue().set("viewList:"+item.getCode(), item.getValue());
                  /* try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }*/
               //}
           //});
       };
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
        return Constants.AREA_KEY + configKey;
    }
}

