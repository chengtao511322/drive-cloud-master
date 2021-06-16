package com.drive.marketing.testMar;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DriveCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 默认代码执行非常耗时!!
        System.out.println(Thread.currentThread().getName() + ",执行计算操作");
        Thread.sleep(3000);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DriveCallable threadCallable = new DriveCallable();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(threadCallable);
        new Thread(integerFutureTask).start();
        Integer result = integerFutureTask.get();
        System.out.println(result);
    }
}
