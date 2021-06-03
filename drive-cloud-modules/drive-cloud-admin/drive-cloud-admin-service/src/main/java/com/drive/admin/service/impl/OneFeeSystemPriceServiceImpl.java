package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drive.admin.mapper.OneFeeSystemPriceMapper;
import com.drive.admin.pojo.dto.TreeNodeCategoryDto;
import com.drive.admin.pojo.entity.OneFeeSystemPriceEntity;
import com.drive.admin.service.OneFeeSystemPriceService;
import com.drive.common.core.base.BaseService;
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
import java.util.concurrent.ConcurrentHashMap;

/**
 * 学车一费制定价表 服务实现类
 *
 * @author xiaoguo
 */
@Service
@Slf4j
public class OneFeeSystemPriceServiceImpl extends BaseService<OneFeeSystemPriceMapper, OneFeeSystemPriceEntity> implements OneFeeSystemPriceService {


    // 指向自己实例的私有静态引用
    //private static Jedis jedis =RedisDS.create().getJedis();

    @Autowired
    private RedisService redisService;
    @Override
    public List<TreeNodeCategoryDto> getServicePackageTree(String tenantId) {
        return this.getBaseMapper().getServicePackageTree(tenantId);
    }

    @CacheEvict(value = "redisCache", key = "'classItem:class_'+#entity.getId()")
    @Override
    public boolean save(OneFeeSystemPriceEntity entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "redisCache", key = "'classItem:class_'+#id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @CacheEvict(value = "redisCache", key = "'classItem:class_'+#entity.getId()")
    @Override
    public boolean updateById(OneFeeSystemPriceEntity entity) {
        return super.updateById(entity);
    }

    @Override
    public <E extends IPage<OneFeeSystemPriceEntity>> E page(E page, Wrapper<OneFeeSystemPriceEntity> queryWrapper) {
        return super.page(page,queryWrapper);
    }

    @Cacheable(value = "redisCache", key = "'classItem:class_'+ #id")
    @Override
    public OneFeeSystemPriceEntity getById(Serializable id) {
        return super.getById(id);
    }

    @PostConstruct
    public void init() {
        QueryWrapper coachQueryWrapper = new QueryWrapper();
        //coachQueryWrapper.eq("status", StatusEnum.ENABLE.getCode());
        List<OneFeeSystemPriceEntity> oneFeeSystemPriceList = this.getBaseMapper().selectList(coachQueryWrapper);
        log.info(this.getClass() + "数据{}", oneFeeSystemPriceList);
        if (oneFeeSystemPriceList.size() <= 0){
            return;
        }

        // redisTemplate.opsForValue().set("viewList:", viewList);
        long startTime = System.currentTimeMillis();
        Map<String, Object> map = new ConcurrentHashMap(oneFeeSystemPriceList.size());
        oneFeeSystemPriceList.stream().forEach((item) -> {
            //for (int i = 0; i < viewList.size(); i++) {
            //pipe.set("pipe:"+viewList.get(0).getCode(), viewList.get(0).getValue());
            // sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
            //Thread.sleep(1000);
            // 打印正在执行的缓存线程信息
            //pipeline.sadd("viewList:"+item.getCode(), item.getValue());
            //jedis.set(CacheConstants.REDIS_CACHE_CLASS_KEY +item.getId(), JSONObject.toJSONString(item));
            map.put(CacheConstants.REDIS_CACHE_CLASS_KEY +item.getId(),item);
        });
        redisService.executePipelined(map);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) +"毫秒");
    }
}

