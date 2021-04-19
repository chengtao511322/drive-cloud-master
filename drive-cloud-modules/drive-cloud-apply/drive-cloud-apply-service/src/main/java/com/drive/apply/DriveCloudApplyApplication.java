package com.drive.apply;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 报名服务
 */
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
@Slf4j
public class DriveCloudApplyApplication {

    public static void main(String[] args) {

    }
}
