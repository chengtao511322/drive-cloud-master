package com.drive.admin.service.impl;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.mapper.OneFeeSystemPriceMapper;
import com.drive.admin.pojo.dto.TreeNodeCategoryDto;
import com.drive.admin.pojo.entity.OneFeeSystemPriceEntity;
import com.drive.admin.service.OneFeeSystemPriceService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 学车一费制定价表 服务实现类
 *
 * @author xiaoguo
 */
@Service
@Slf4j
public class OneFeeSystemPriceServiceImpl extends BaseService<OneFeeSystemPriceMapper, OneFeeSystemPriceEntity> implements OneFeeSystemPriceService {

    private  final Jedis jedis = RedisDS.create().getJedis();

    @Autowired
    private OneFeeSystemPriceMapper oneFeeSystemPriceMapper;

    @Override
    public List<TreeNodeCategoryDto> getServicePackageTree(String tenantId) {
        return oneFeeSystemPriceMapper.getServicePackageTree(tenantId);
    }


    @PostConstruct
    public void init() {
        QueryWrapper coachQueryWrapper = new QueryWrapper();
        coachQueryWrapper.eq("status", StatusEnum.ENABLE.getCode());
        List<OneFeeSystemPriceEntity> oneFeeSystemPriceList = this.getBaseMapper().selectList(coachQueryWrapper);
        log.info(this.getClass() + "数据{}", oneFeeSystemPriceList);
        if (oneFeeSystemPriceList.size() <= 0){
            return;
        }

        // redisTemplate.opsForValue().set("viewList:", viewList);
        long startTime = System.currentTimeMillis();
        oneFeeSystemPriceList.stream().forEach((item) -> {
            //for (int i = 0; i < viewList.size(); i++) {
            //pipe.set("pipe:"+viewList.get(0).getCode(), viewList.get(0).getValue());
            // sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
            //Thread.sleep(1000);
            // 打印正在执行的缓存线程信息
            //pipeline.sadd("viewList:"+item.getCode(), item.getValue());
            jedis.set(CacheConstants.REDIS_CACHE_CLASS_KEY +item.getId(), JSONObject.toJSONString(item));
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}

