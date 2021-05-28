package com.drive.marketing.testMar;

import lombok.SneakyThrows;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Thread005 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "线程执行");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程执行");
                thread1.wait();
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                thread1.join();
                System.out.println(Thread.currentThread().getName() + "线程执行");
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
