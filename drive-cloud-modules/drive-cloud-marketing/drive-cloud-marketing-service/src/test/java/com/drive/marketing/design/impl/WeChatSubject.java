package com.drive.marketing.design.impl;

import com.drive.marketing.design.AbstractSubject;
import com.drive.marketing.design.ObServer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题
 */
public class WeChatSubject implements AbstractSubject {
    /**
     * 存放所有的ObServer
     */
    private List<ObServer> listObServer = new ArrayList<ObServer>();
    /**
     * 更新的内容
     */
    private String message;

    public void addObServer(ObServer obServer) {
        listObServer.add(obServer);
    }

    public void removeObServer(ObServer obServer) {
        listObServer.remove(obServer);
    }

    public void notifyObServerAll(String message) {
        for (int i = 0; i < listObServer.size(); i++) {
            ObServer obServer = listObServer.get(i);
            obServer.update(message);
        }
    }

    public void setNtifyMessage(String message) {
        this.message = message;
        System.out.println("微信公众号设置message:" + message);
        notifyObServerAll(message);

    }
}
