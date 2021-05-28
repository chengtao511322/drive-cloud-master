package com.drive.admin.future;

import com.drive.admin.pojo.vo.StatisticsTaskDataVo;
import com.drive.admin.service.StudentInfoService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @ClassName HomeFutureTask
 * @Description TODO：首页接口线程查询
 * @Author @{用户} 小郭
 * @Date @{时间} 2020/7/2 19:20
 * @Version 1.0
 **/
@Component
@Slf4j
public class StatisticsFutureTask {

    @Autowired
    private StudentInfoService studentInfoService;


    /**
     * 核心线程 8 最大线程 20 保活时间30s 存储队列 10 有守护线程 拒绝策略:将超负荷任务回退到调用者
     */
    private static ExecutorService executor = new ThreadPoolExecutor(8, 20,
            30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),
            new ThreadFactoryBuilder().setNameFormat("statistics_Async_FutureTask-%d").setDaemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    /**
     * 获取首页数据
     * @return
     */
    public StatisticsTaskDataVo getHomeResult(){
        log.info("请求参数是{}");
        //学员总数
        int studentTotal = studentInfoService.count();
      try {
          // 开启线程处理
          Future<Integer> studentTotalFuture = executor.submit(() -> studentTotal);

          //get阻塞
          StatisticsTaskDataVo statisticsTaskDataVo = new StatisticsTaskDataVo();
          // 学员总数
          statisticsTaskDataVo.setStudentTotal(studentTotalFuture.get());
          return statisticsTaskDataVo;
      }catch (Exception e){
          e.printStackTrace();
      }
      return null;
    }
}
