package com.drive.basics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 基础服务
 */
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
@Slf4j
public class DriveCloudBasicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudBasicsApplication.class,args);
    }
}
