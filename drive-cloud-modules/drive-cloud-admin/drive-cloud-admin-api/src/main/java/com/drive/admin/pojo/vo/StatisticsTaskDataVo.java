package com.drive.admin.pojo.vo;

import lombok.Data;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
public class StatisticsTaskDataVo implements java.io.Serializable{

    // 学员总数
    private int studentTotal;
    // 今日订单数
    private int todayOrderTotal;
    // 待处理订单数
    private int pendingOrderTotal;
}
