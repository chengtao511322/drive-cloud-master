package com.drive.marketing.design.impl;

import com.drive.marketing.design.GatewayHandler;
import org.springframework.stereotype.Component;

@Component
public class BlacklistHandler extends GatewayHandler {
    @Override
    public void service() {
        System.out.println("第二关黑名单拦截判断....");
        nextService();
    }
}