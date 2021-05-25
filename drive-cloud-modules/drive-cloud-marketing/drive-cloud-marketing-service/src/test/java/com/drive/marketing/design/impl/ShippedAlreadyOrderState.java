package com.drive.marketing.design.impl;

import com.drive.marketing.design.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShippedAlreadyOrderState implements OrderState {
    public void orderService() {
        log.info(">>>切换为已经发货状态..");
    }
}