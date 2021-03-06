package com.drive.admin;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.drive.common.core.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.management.ManagementFactory;

/**
 * 后台服务
 */
/*@Import(SpringCloudConfiguration.class)*/
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
@EnableCaching
/**TC开启分布式事务注解**/
@EnableDistributedTransaction
@EnableAsync
@Slf4j
public class DriveCloudAdminApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DriveCloudAdminApplication.class,args);
        ApplicationContext applicationContext = SpringApplication.run(DriveCloudAdminApplication.class, args);
        SpringContextUtil.setApplicationContext(applicationContext);
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        log.info("后台服务启动成功   ლ(´ڡ`ლ)ﾞ " +
                "                \" .-------.       ____     __       " +
                "                \" |  _ _      /  /                  " +
                "                \" | ( ' )  |         _. /  '        " +
                "                \" |(_ o _) /        _( )_ .'        " +
                "                \" | (_,_).' __  ___(_ o _)'         " +
                "                \" |  |  |  ||   |(_,_)'             " +
                "                \" |  |  `'   /|   `-'  /            " +
                "                \" |  |     /        /               " +
                "                \" ''-'   `'-'    `-..-'               ",
                "{}当前项目进程号",jvmName.split("@")[0]);

    }
}
