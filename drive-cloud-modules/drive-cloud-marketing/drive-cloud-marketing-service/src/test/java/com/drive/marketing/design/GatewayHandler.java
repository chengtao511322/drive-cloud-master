package com.drive.marketing.design;

/**
 * 责任链
 */
public abstract class GatewayHandler {
    protected GatewayHandler nextGatewayHandler;

    /**
     * 处理业务逻辑
     *
     * @return true 表示继续执行 false表示不继续执行..
     */
    public abstract void service();

    public void setHandler(GatewayHandler gatewayHandler) {
        this.nextGatewayHandler = gatewayHandler;
    }
    protected void nextService(){
         if(nextGatewayHandler!=null){
             nextGatewayHandler.service();;
         }
    }
}