package com.drive.admin.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.*;


/**
 * @author : chentao
 * @createdDate : 2021/05/26
 * @updatedDate
 * 定时任务管理器
 *
 */
public class JobManager {

    /**
     * 定时任务工厂
     */
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    private static Scheduler scheduler;

    static {
        scheduler = getScheduler();
    }

    /**
     * @Fields JOB_GROUP_NAME : 定时任务组名称
     */
    private static String JOB_GROUP_NAME = "XCXWZ";

    /**
     * @Fields TRIGGER_GROUP_NAME : 触发器组名称
     */
    private static String TRIGGER_GROUP_NAME = "trigger1";

    private JobManager(){

    }

    public static SchedulerFactory getSchedulerFactory(){
        return schedulerFactory;
    }

    /**
     * 获取定时任务调度器
     */
    public static Scheduler getScheduler(){
        Scheduler scheduler = null;
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }

    /**
     * 向scheduler中添加一个简单定时任务
     * @Description: 创建一个简单定时任务，支持N久执行一次
     * @param jobName 定时任务名称
     * @param triggerName 触发器名称
     * @param time 间隔时间，单位秒
     * @param cls 定时任务执行类，实现了Job接口的类
     * @throws SchedulerException
     * 	 * @return void    返回类型
     * 	 * @throws
     */
    public static void addJob(String jobName, String triggerName, int time, Class<? extends Job> cls) throws SchedulerException {
        //创建一个实现了Job接口的实例
        JobDetail jobDetail = JobBuilder.newJob(cls)
                .withIdentity(jobName, JOB_GROUP_NAME)
                .build();
        //创建一个自定义的触发器,出发时间根据传入的time决定
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, TRIGGER_GROUP_NAME)
                .startNow().forJob(jobDetail)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(time)
                        .repeatForever()
                ).build();
        scheduler.scheduleJob(jobDetail,trigger);
    }

    /**
     * 根据传入的cron表达式,来创建定时任务,定时策略根据传入的cron表达式确定
     * @param jobName
     * @param triggerName
     * @param cronExpressStr
     * @param cls
     */
    public static void addJob(String jobName, String triggerName, String cronExpressStr, Class<? extends Job> cls) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, TRIGGER_GROUP_NAME)
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpressStr)
                        .withMisfireHandlingInstructionFireAndProceed()
                ).build();
        scheduler.scheduleJob(jobDetail,trigger);
    }

    /**
     * 开启任务调度
     * @throws SchedulerException
     */
    public static void start() throws SchedulerException {
        scheduler.start();
    }

    /**
     * 获取定时任务的jobKey
     * 根据jobName获取jobKeyi
     * @param jobName 定时任务名称
     * @return
     */
    public static JobKey getJobKey(String jobName){
        return JobKey.jobKey(jobName,JOB_GROUP_NAME);
    }

    /**
     * 根据jobName暂停一个定时任务
     * @param jobName 定时任务名称
     */
    public static boolean pauseJob(String jobName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName);
        if(jobKey !=null){
            scheduler.pauseJob(jobKey);
            return true;
        }else {
            return false;
        }
    }

    /**
     * @Title: resumeJob
     * @Description: 恢复一个定时任务
     * @param jobName 定时任务名称
     */
    public static boolean resumeJob(String jobName) throws Exception{
        JobKey jobKey = getJobKey(jobName);
        if(jobKey!=null){
            scheduler.resumeJob(jobKey);
            return true;
        }else {
            return false;
        }
    }

    /**
     * @Title: removeJob
     * @Description: 移除一个定时任务
     * @param jobName
     */
    public static void removeJob(String jobName) throws Exception{
        JobKey jobKey = getJobKey(jobName);
        if (jobKey!=null) {
            scheduler.deleteJob(jobKey);
        }
    }

    /**
     * 从任务调度器当中获取执行中的定时任务
     */
    public static List<Map<String,Object>> getRunningJobs() throws SchedulerException {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<JobExecutionContext> runningJobs = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext runningJob : runningJobs) {
            Map<String, Object> map = new HashMap<>();
            JobDetail jobDetail = runningJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            map.put("jobKey",jobKey.getName());
            map.put("jobGroup",jobKey.getGroup());
            Trigger trigger = runningJob.getTrigger();
            map.put("description","触发器是:"+trigger.getKey());
            //下一次执行时间
            map.put("nextFireTime",trigger.getNextFireTime());
            //开始时间
            map.put("startTime",trigger.getStartTime());
            //触发器triggerState对象
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            map.put("triggerState",triggerState.name());
            list.add(map);
        }
        return list;
    }

    /**
     * 获取所有定时任务
     */
    public static List<Map<String,Object>> getAllJobs() throws SchedulerException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Set<JobKey> jobKeySet = getJobKeySet();
        //双层循环拿到jobKey对应的job和每个job所有的触发器
        for (JobKey jobKey : jobKeySet) {
            //获取jobKey所有的trigger触发器
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                Map<String, Object> map = new HashMap<>();
                map.put("name",jobKey.getName());
                map.put("group",jobKey.getGroup());
                map.put("description","触发器是:" + trigger.getKey());
                map.put("nextFireTime",trigger.getNextFireTime());
                map.put("startTime", trigger.getStartTime());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("state",triggerState.name());
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 获取所有的jobKey
     */
    private static Set<JobKey> getJobKeySet() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        return scheduler.getJobKeys(matcher);
    }

    /**
     * 单独调用一次定时任务
     * @param jobName 定时任务名称
     * @throws Exception
     */
    public static void triggerJob(String jobName) throws Exception{
        if(scheduler!=null){
            JobKey jobKey  = getJobKey(jobName);
            scheduler.triggerJob(jobKey);
        }
    }

    /**
     * 判断一个job是否存在
     * @param jobName 定时任务名称
     */
    public static boolean isJobExist(String jobName){
        boolean exist = false;
        try {
            Set<JobKey> jobKeys = getJobKeySet();
            for(JobKey jobKey : jobKeys){
                if(jobKey.getName().equals(jobName)){
                    exist = true;
                    break;
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return exist;
    }

    /**
     * 获取指定jobName的第一个trigger
     */
    public static Trigger getFirstTriggerByJobName(String jobName) throws SchedulerException {
        Set<JobKey> jobKeySet = getJobKeySet();
        for (JobKey jobKey : jobKeySet) {
            if(jobKey.getName().equals(jobName)){
                List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
                if(triggersOfJob != null && triggersOfJob.size() > 0){
                    return triggersOfJob.get(0);
                }else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 关闭定时任务调度
     */
    public static void shutdown(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
        }
    }
}
