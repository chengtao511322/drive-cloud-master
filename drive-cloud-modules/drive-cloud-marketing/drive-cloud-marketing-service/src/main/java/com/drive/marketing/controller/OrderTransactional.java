package com.drive.marketing.controller;

import com.drive.basics.feign.RemoteOperatorFeignService;
import com.drive.basics.pojo.dto.OperatorAreaEditParam;
import com.drive.basics.pojo.dto.OperatorEditParam;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.marketing.repository.ActivityRepository;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $ 订单分布式事务解决方案
 * @Param $
 * @return $
 **/
@RestController
public class OrderTransactional {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private RemoteOperatorFeignService remoteOperatorFeignService;

    @RequestMapping("/insertOrder")
    @GlobalTransactional
    public ResObject insertOrder(int age) {
        String orderId = System.currentTimeMillis() + "";
        activityRepository.transfer("123");
        // 1.向订单数据库表插入数据
        OperatorEditParam operatorEditParam = new OperatorEditParam();
        operatorEditParam.setName("测试运营商分布式事务问题");
        // 2.调用派单服务，实现对该笔订单派单 远程调用派单接口
       remoteOperatorFeignService.saveOperator(operatorEditParam);
        int j = 1 / age;
        return R.success();
    }
}
