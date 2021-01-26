package com.drive.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 日志模块
 * @author DreamChan
 */
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
public class DriveCloudMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudMonitorApplication.class, args);
    }

}
