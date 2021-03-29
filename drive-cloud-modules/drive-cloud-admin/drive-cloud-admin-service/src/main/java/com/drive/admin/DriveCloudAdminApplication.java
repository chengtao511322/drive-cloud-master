package com.drive.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.management.ManagementFactory;

/**
 * 后台服务
 */
/*@Import(SpringCloudConfiguration.class)*/
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
@Slf4j
public class DriveCloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudAdminApplication.class,args);
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
