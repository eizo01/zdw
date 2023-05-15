package com;

import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;

public class pool {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        return t;

                    }
                }, new ThreadPoolExecutor.AbortPolicy());
//        executor.execute();
//        // 可以拿到返回结果
//        executor.submit();

    }

}
