package com.drive.marketing.design;

/**
 * 抽象观察者
 */
public interface ObServer {


    /**
     * 更新消息内容
     *
     * @param message
     * @return
     */
    public void update(String message);

}
