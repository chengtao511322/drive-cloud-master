package com.drive.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关模块
 * @author xiaoguo
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DriveCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudGatewayApplication.class, args);
    }

}