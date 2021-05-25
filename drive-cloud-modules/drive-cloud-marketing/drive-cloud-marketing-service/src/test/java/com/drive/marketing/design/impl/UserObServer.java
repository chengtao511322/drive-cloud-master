package com.drive.marketing.design.impl;

import com.drive.marketing.design.ObServer;

/**
 * 具体观察者
 */
public class UserObServer implements ObServer {
    /**
     * 订阅者用户名称
     */
    private String name;

    /**
     * 发送内容
     */
    private String message;

    public UserObServer(String name) {
        this.name = name;
    }


    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + ",老师收到推送消息:" + message);
    }
}