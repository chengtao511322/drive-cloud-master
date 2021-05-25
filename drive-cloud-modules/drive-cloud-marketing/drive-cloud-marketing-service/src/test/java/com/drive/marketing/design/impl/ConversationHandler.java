package com.drive.marketing.design.impl;

import com.drive.marketing.design.GatewayHandler;
import org.springframework.stereotype.Component;

@Component
public class ConversationHandler extends GatewayHandler {
    @Override
    public void service() {
        System.out.println("第三关用户会话拦截判断....");
        nextService();
    }
}
