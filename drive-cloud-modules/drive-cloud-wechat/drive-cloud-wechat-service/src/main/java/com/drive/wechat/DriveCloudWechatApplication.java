package com.drive.wechat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
@Slf4j
public class DriveCloudWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudWechatApplication.class,args);
        log.info("微信服务启动成功");
    }
}
