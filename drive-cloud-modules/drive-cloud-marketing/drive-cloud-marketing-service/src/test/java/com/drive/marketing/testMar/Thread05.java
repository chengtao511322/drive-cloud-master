package com.drive.marketing.testMar;

import java.util.HashMap;

public class Thread05 {


    public   static void main(String[] args) throws InterruptedException {
        HashMap hashMap = new HashMap();
        hashMap.put("xiaoguo","123");
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }
            System.out.println(Thread.currentThread().getName() + ",线程执行");
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() + ",线程执行");
        }, "t2");
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() + ",线程执行");
        }, "t3");
        Thread t4 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ",线程执行");
        },"t4");
        Thread t5 = new Thread(() -> {
            try {
                t3.join();
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() + ",线程执行");
        },"t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}