package com.drive.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author 小郭
 * @Description //支付平台
 * @Date $ $
 * @Param $
 * @return $
 **/
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
@EnableAsync
@Slf4j
public class DriveCloudPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudPayApplication.class,args);
        log.info("支付平台启动成功-******");
    }
}
