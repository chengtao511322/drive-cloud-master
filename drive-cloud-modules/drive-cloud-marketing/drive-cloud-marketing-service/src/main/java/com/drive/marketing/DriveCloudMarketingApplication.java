package com.drive.marketing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.management.ManagementFactory;

/**
 * 营销服务
 */
/*@Import(SpringCloudConfiguration.class)*/
@EnableFeignClients(basePackages = "com.drive")
@SpringCloudApplication
@EnableAsync//开启异步调用
@Slf4j
public class DriveCloudMarketingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveCloudMarketingApplication.class,args);
        System.out.println("(♥◠‿◠)ﾉﾞ  营销服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        log.info("{}当前项目进程号" , jvmName.split("@")[0]);
    }
}
