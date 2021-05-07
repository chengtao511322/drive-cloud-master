package com.drive.admin.controller;

import com.drive.admin.future.StatisticsFutureTask;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@RestController
@RequestMapping("/statistics")
public class ApiStatisticsService {

    @Autowired
    private StatisticsFutureTask statisticsFutureTask;

    /**
     * 获取统计数据
     * @return
     */
    @ApiOperation("获取统计数据")
    @GetMapping(value = "/getStatisticsData")
    public ResObject getStatisticsData(){
        return R.success(statisticsFutureTask.getHomeResult());
    }

}
