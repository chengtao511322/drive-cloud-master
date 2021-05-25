package com.drive.marketing.design.impl;

import com.drive.marketing.design.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 状态切换
 */
@Slf4j
@Component
public class AlreadySignedOrderState implements OrderState {
    @Override
    public void orderService() {
        log.info(">>>切换为已经签收状态..");
    }
}