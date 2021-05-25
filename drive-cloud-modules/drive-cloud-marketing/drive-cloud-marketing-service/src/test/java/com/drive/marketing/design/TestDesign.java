package com.drive.marketing.design;

import com.drive.marketing.design.impl.AlreadySignedOrderState;
import com.drive.marketing.design.impl.ShippedAlreadyOrderState;
import com.drive.marketing.design.impl.UserObServer;
import com.drive.marketing.design.impl.WeChatSubject;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public class TestDesign {

    public static void main(String[] args) {
        // 1.注册主题
        AbstractSubject weChatSubject = new WeChatSubject();
// 2.添加观察者 订阅主题
        weChatSubject.addObServer(new UserObServer("小薇"));
        weChatSubject.addObServer(new UserObServer("小敏"));
// 3.设置发送消息
        weChatSubject.setNtifyMessage("学车小王子发布新版本了！");


        ContextState contextState = new ContextState(new ShippedAlreadyOrderState());
        contextState.switchStateOrder();
    }
}
