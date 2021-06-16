package com.drive.marketing.testMar;

import com.drive.common.core.base.DriveLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 小郭
 * @Description //TODO 手写lock锁
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DriveThreadLock {

    static int i = 100;

    private static Lock lock = new ReentrantLock();




    public static void main(String[] args) throws InterruptedException {
        // 自定义lock
        DriveLock driveLock = new DriveLock();

        Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                            while(true) {
                                //判断还有没有票
                                driveLock.lock();
                                if (i > 0) {
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    String name = Thread.currentThread().getName();
                                    System.out.println(name + "正在卖：" + i);
                                    i--;
                                }

                                driveLock.unlock();
                            }
                    }
                };

                Thread t1 = new Thread(runnable);
                //t1.join();
                Thread t2 = new Thread(runnable);
                t1.start();
                t2.start();

            }


}


