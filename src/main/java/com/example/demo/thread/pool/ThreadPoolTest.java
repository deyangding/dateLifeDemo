package com.example.demo.thread.pool;

import com.example.demo.thread.PriorityBlockingQuque.PriorityTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    //



    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(3));

        for (int i = 1; i <= 11; i++) {
            threadPoolExecutor.execute(new PoolTask("test"+i, i));
        }

        threadPoolExecutor.shutdown();
    }
}
