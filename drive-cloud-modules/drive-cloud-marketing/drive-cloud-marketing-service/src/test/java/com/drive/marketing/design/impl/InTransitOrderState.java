package com.drive.marketing.design.impl;

import com.drive.marketing.design.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InTransitOrderState implements OrderState {
    @Override
    public void orderService() {
        log.info(">>>切换为正在运送状态...");
    }
}