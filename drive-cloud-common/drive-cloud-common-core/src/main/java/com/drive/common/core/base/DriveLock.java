package com.drive.common.core.base;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

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

    /**
     * 当前谁持有了该锁
     */
    private static Thread ownerThread;

    /**
     * 记录锁的重入次数
     */
    private int recursions;

    /**
     * 没有获取到锁的线程
     */
    private LinkedBlockingDeque<Thread> notLockThreadList = new LinkedBlockingDeque<>();


    // 上锁
    public void lock(){
        if (ownerThread != null && ownerThread == Thread.currentThread()) {
            // 重入次数+1
            recursions++;
            return;
        }
        // 轻量锁
        // 自旋
        /**
         * 两个线程同时执行 cas 操作 将锁的状态从0改成===11
         * 最终只有一个线程修改成功 自旋
         */
        int spinCount = 0;
        for (;;){
            // 如果自旋次数超过10次+
            if (spinCount > 10) {
                // 当前线程直接阻塞 该过程为重量级锁
                notLockThreadList.offer(Thread.currentThread());
                LockSupport.park();
            }
            // CAS比较 交换
            Boolean flag = lockStatus.compareAndSet(0,1);
            if (flag) {
                ownerThread = Thread.currentThread();
                recursions++;
                return;
            }
            spinCount++;
            // 自旋会消耗CPU资源，CPU彪高，线程休眠解决
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 解锁
    public void unlock() throws Exception {
        /**
         *  如果不是当前线程获取的锁 无法释放锁
         */
        if (ownerThread != Thread.currentThread()) {
            throw new Exception("当前线程没有获取到锁无法释放锁");
        }
        recursions--;
        if (recursions == 0) {
            for (; ; ) {
                // 释放锁 需要通过 cas 将 1 == 变成0
                if (lockStatus.compareAndSet(1, 0)) {
                    // 唤醒正在阻塞的线程， 进入到获取锁的状态
                    notifyNotLockThread();
                    return;
                }
            }
        }
    }

    private void notifyNotLockThread() {
        /**
         * 唤醒所有的线程  非公平锁的形式
         */
        notLockThreadList.forEach((t) -> {
            LockSupport.unpark(t);
        });
    }

    public static void main(String[] args) {
        DriveLock driveLock = new DriveLock();

        Runnable runnable = new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        while (true){
                            if(i>0) {
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


