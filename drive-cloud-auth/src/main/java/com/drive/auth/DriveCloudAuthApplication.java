package com.drive.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 认证模块
 *
 * @author xiaoguo
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.drive")
public class DriveCloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudAuthApplication.class, args);
    }

}
