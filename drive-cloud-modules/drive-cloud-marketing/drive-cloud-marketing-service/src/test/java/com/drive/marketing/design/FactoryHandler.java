package com.drive.marketing.design;

import com.drive.marketing.design.impl.BlacklistHandler;
import com.drive.marketing.design.impl.ConversationHandler;
import com.drive.marketing.design.impl.CurrentLimitHandler;

public class FactoryHandler {
    public static GatewayHandler getGatewayHandler() {
        GatewayHandler gatewayHandler1 = new CurrentLimitHandler();
        GatewayHandler gatewayHandler2 = new BlacklistHandler();
        gatewayHandler1.setHandler(gatewayHandler2);
        GatewayHandler gatewayHandler3 = new ConversationHandler();
        gatewayHandler2.setHandler(gatewayHandler3);
        return gatewayHandler1;
    }

    public static void main(String[] args) {
        FactoryHandler factoryHandler = new FactoryHandler();
        factoryHandler.getGatewayHandler();
    }
}