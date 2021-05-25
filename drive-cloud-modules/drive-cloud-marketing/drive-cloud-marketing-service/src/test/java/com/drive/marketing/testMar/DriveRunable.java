package com.drive.marketing.testMar;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DriveRunable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +"Runable-接口实现多线程");
    }
}
