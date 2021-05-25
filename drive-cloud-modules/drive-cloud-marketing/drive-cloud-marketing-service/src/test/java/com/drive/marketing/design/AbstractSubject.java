package com.drive.marketing.design;

/**
 * 抽象主题
 */
public interface AbstractSubject {


    /**
     * 添加obServer
     *
     * @param obServer
     */
    void addObServer(ObServer obServer);

    /**
     * 移除obServer
     *
     * @param obServer
     */
    void removeObServer(ObServer obServer);

    /**
     * 通知所有的notifyObServerAll
     *
     * @param message
     */
    void notifyObServerAll(String message);

    /**
     * 设置更新内容
     */
    void setNtifyMessage(String message);

}