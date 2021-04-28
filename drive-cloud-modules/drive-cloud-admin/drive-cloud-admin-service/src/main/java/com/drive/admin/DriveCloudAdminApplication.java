package com.drive.admin;

import com.drive.common.core.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.lang.management.ManagementFactory;

/**
 * 后台服务
 */
/*@Import(SpringCloudConfiguration.class)*/
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
@EnableCaching
@Slf4j
public class DriveCloudAdminApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DriveCloudAdminApplication.class,args);
        ApplicationContext applicationContext = SpringApplication.run(DriveCloudAdminApplication.class, args);
        SpringContextUtil.setApplicationContext(applicationContext);
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        log.info("后台服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "                \" .-------.       ____     __        \n" +
                "                \" |  _ _   \\\\      \\\\   \\\\   /  /    \n" +
                "                \" | ( ' )  |       \\\\  _. /  '       \n" +
                "                \" |(_ o _) /        _( )_ .'         \n" +
                "                \" | (_,_).' __  ___(_ o _)'          \n" +
                "                \" |  |\\\\ \\\\  |  ||   |(_,_)'         \n" +
                "                \" |  | \\\\ `'   /|   `-'  /           \n" +
                "                \" |  |  \\\\    /  \\\\      /           \n" +
                "                \" ''-'   `'-'    `-..-'              ","{}当前项目进程号",jvmName.split("@")[0]);

    }
}
