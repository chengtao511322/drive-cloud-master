package com.drive.basics.service.impl;

import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.basics.mapper.AppVersionMapper;
import com.drive.basics.pojo.dto.AppVersionEditParam;
import com.drive.basics.pojo.entity.AppVersionEntity;
import com.drive.basics.service.AppVersionService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * app版本控制信息表 服务实现类
 *
 * @author xiaoguo
 */
@Service
@Slf4j
public class AppVersionServiceImpl extends BaseService<AppVersionMapper, AppVersionEntity> implements AppVersionService {

    @Autowired
    private RemoteStudentFeignService remoteActivityService;

    @Override
    //用户下单，模拟全局事务回滚
    @GlobalTransactional//全局事务控制
    public ResObject createOrderRollback(AppVersionEditParam appVersionEditParam) {
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", appVersionEditParam);

        //1 调用商品微服务,查询商品信息
        AppVersionEntity appVersion = new AppVersionEntity();
        log.info("查询到{}号商品的信息,内容是:{}", appVersion);

        //2 下单(创建订单)
        appVersion.setAppKey("1223");
        appVersion.setAppType(1);
        appVersion.setCreateUser("小郭");
        appVersion.setVersion("V2.0.0");
        this.getBaseMapper().insert(appVersion);
        log.info("创建订单成功,订单信息为{}",appVersion);

        //3 扣库存m
        remoteActivityService.reduceInventoryRollback();

        return R.success(appVersion);
    }
}

