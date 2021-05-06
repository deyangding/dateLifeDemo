package com.example.demo.thread.PriorityBlockingQuque;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//优先级执行队列
public class PriorityBlockingQuqueThread {

    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,
                0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());

        executor.execute(new PriorityTask(1));
        executor.execute(new PriorityTask(1));
        executor.execute(new PriorityTask(10));
        executor.execute(new PriorityTask(2));
        executor.execute(new PriorityTask(5));
        executor.execute(new PriorityTask(9));
        executor.execute(new PriorityTask(1));

    }

}
