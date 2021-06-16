package com.drive.common.core.base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 小郭
 * @Description //TODO 手写lock锁
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DriveLock {

    // 锁状态
    private static AtomicInteger lockStatus = new AtomicInteger(0);
    static int i = 100;


    // 上锁
    public void lock(){
        // 自旋
        for (;;){
            int expect = lockStatus.get();
            // CAS比较 交换
            Boolean flag = lockStatus.compareAndSet(0,1);
            if (flag)return;
            // 自旋会消耗CPU资源，CPU彪高，线程休眠解决
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 解锁
    public void unlock(){
        int expect = lockStatus.get();
        Boolean flag = lockStatus.compareAndSet(1,0);
        if (flag)return;
    }

    public static void main(String[] args) {
        DriveLock driveLock = new DriveLock();

        Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        while (true){
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
                    }
                };

                Thread t1 = new Thread(runnable);
                Thread t2 = new Thread(runnable);
                t1.start();
                t2.start();

            }


}


