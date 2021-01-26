package com.drive.system;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 系统模块
 *
 * @author xiaoguo
 */
@EnableFeignClients(basePackages = "com.drive")
@EnableTransactionManagement
@SpringCloudApplication
public class DriveCloudSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudSystemApplication.class, args);
    }

}
