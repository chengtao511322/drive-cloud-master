package com.drive.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 对象存储 模块
 * @author xiaoguo
 */
@EnableFeignClients(basePackages = "com.drive")
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DriveCloudOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudOssApplication.class, args);
    }

}
