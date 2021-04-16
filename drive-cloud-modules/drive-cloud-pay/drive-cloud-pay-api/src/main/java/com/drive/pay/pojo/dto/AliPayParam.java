package com.drive.pay.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author 小郭
 * @Description // 支付宝参数
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
public class AliPayParam {

    // 订单号
    private String orderNo;
    // 订单名称
    private String orderName;
    // 订单支付金额
    private BigDecimal payAmount;
}
