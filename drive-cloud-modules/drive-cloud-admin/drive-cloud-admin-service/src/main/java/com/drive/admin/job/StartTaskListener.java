package com.drive.admin.job;

import com.drive.admin.pojo.entity.SysTaskEntity;
import com.drive.admin.service.SysTaskService;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.SubResultCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : chentao
 * @createdDate : 2021/05/26
 * @updatedDate
 * 初始化定时任务,根据定时任务的stState,当值为1时，项目启动就开始加载定时任务
 *
 */
@Slf4j
@Component
public class StartTaskListener implements ApplicationListener<ApplicationStartedEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        JobManager.start();
        //项目启动后开启所有加载时启动的定时任务
        SysTaskService sysTaskService = event.getApplicationContext().getBean(SysTaskService.class);
        List<SysTaskEntity> taskList = sysTaskService.list();
        for (SysTaskEntity sysTaskEntity : taskList) {
            //开机时启动
            if(sysTaskEntity.getStState() != null && sysTaskEntity.getStState().equals("1")){
                Class clazz = null;
                try {
                    clazz = Class.forName(sysTaskEntity.getStJobClass());
                    if(clazz != null && !Job.class.isAssignableFrom(clazz)){
                        log.error("启动定时任务的类出错:{} 定时任务类必须实现Job接口",sysTaskEntity.getStJobClass());
                        continue;
                    }
                    JobManager.addJob(sysTaskEntity.getStTaskId(),sysTaskEntity.getStTaskName(),sysTaskEntity.getStCronExpression(),clazz);
                } catch (ClassNotFoundException e) {
                    log.error("未找到"+sysTaskEntity.getStJobClass());
                    continue;
                } catch (SchedulerException e) {
                    log.error("添加定时任务失败");
                }
            }
        }
        
    }
}
