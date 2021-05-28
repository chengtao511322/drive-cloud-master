package com.drive.marketing.testMar;

public class Test003 {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            threadLocal.set("666");
            System.out.println("成功设置threadLocal");

        });
        Thread thread2 = new Thread(() -> {
            System.out.println(threadLocal.get());
            System.out.println("成功获取threadLocal");

        });
        thread.start();
        thread2.start();
        thread.join();
        System.out.println(Thread.currentThread().getName() + "," + threadLocal.get());
        // 循环数据
        for (;;) {
            // 解决CPU 标高
            Thread.sleep(3000);
            System.out.println("循环");
        }
    }
}