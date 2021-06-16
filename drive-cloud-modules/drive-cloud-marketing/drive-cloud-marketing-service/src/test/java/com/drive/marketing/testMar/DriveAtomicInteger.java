package com.drive.marketing.testMar;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author 小郭
 * @Description //TODO  手写原子类
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DriveAtomicInteger {

    // 内存 V 值
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    int incrementAndGet() {
        return getAndAddInt() + 1;
    }


    int getAndAddInt(){
        /**
         * int expect,  预期值
         * int update   要修改的值
         */
        for (;;){
            int expect = atomicInteger.get();
            Boolean flag = atomicInteger.compareAndSet(expect,expect+1);
            if (flag){
                return expect;
            }
            try {
                Thread.sleep(50);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DriveAtomicInteger driveAtomicInteger = new DriveAtomicInteger();
        LongAdder longAdder = new LongAdder();
        driveAtomicInteger.incrementAndGet();
        driveAtomicInteger.incrementAndGet();
        driveAtomicInteger.incrementAndGet();
        driveAtomicInteger.incrementAndGet();
        driveAtomicInteger.incrementAndGet();
        driveAtomicInteger.incrementAndGet();
        driveAtomicInteger.incrementAndGet();
        driveAtomicInteger.incrementAndGet();
        System.out.println(driveAtomicInteger.getAndAddInt());
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.intValue());
    }
}
