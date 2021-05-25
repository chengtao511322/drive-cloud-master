package com.drive.marketing.design.impl;

import com.drive.marketing.design.GatewayHandler;
import org.springframework.stereotype.Component;

@Component
public class CurrentLimitHandler extends GatewayHandler {
    @Override
    public void service() {
        System.out.println("第一关网关限流判断....");
        nextService();
    }
}





