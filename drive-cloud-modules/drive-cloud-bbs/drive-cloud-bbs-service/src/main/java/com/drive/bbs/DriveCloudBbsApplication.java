package com.drive.bbs;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 小郭
 * @Description //TODO  论坛
 * @Date $ $
 * @Param $
 * @return $
 **/
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
@EnableCaching
/**TC开启分布式事务注解**/
@EnableDistributedTransaction
@Slf4j
public class DriveCloudBbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudBbsApplication.class,args);
    }
}
