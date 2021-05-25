package com.drive.marketing.testMar;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DriveThread extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +",>线程正在执行<");
    }
}
