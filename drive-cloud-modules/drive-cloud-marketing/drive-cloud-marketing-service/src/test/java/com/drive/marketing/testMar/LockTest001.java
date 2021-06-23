package com.drive.marketing.testMar;


import org.apache.logging.log4j.spi.CopyOnWrite;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public class LockTest001 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                //copyOnWriteArrayList.add(i);
            }).start();
        }
    }
}
