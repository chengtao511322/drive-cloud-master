package com.drive.admin.job.jobList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob2 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("测试一下定时任务22=======================");
        System.out.println("定时任务名称为"+context.getJobDetail().getKey());
        System.out.println("定时任务执行策略下一次执行时间"+context.getTrigger().getNextFireTime());
    }
}
