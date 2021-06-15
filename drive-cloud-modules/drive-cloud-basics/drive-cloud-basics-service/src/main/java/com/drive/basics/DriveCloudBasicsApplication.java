package com.drive.basics;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 基础服务
 */
/*@Import(SpringCloudConfiguration.class)*/
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
/**TC开启分布式事务注解**/
@EnableDistributedTransaction
@Slf4j
public class DriveCloudBasicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudBasicsApplication.class,args);
    }
}
