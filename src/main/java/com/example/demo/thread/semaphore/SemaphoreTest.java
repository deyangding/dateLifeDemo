package com.example.demo.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(5);
        ExecutorService e = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            e.execute(()->{
                try {
                    s.acquire();
                    s.tryAcquire();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {

                    System.out.println("开始访问："+ finalI);
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                s.release();

                System.out.println("访问："+s.availablePermits());
            });
        }

        e.shutdown();
    }

}
