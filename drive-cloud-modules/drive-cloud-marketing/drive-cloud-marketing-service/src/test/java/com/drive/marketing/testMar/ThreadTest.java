package com.drive.marketing.testMar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $-
 * @return $
 **/
public class ThreadTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + ",>我是子线程<");
        }).start();

        new DriveThread().start();
        // 需要进程来启动
        new Thread(new DriveRunable()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类启动");
            }
        }).start();

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + ",>.lambda表达式创建线程<");
        }).start();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ">我是子线程<");
            }
        });
    }


}
