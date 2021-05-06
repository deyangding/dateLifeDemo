package com.example.demo.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//超卖
public class OverSoldTest {

    public static int a = 200;


    public static void main(String[] args) {

        //模拟 25 个人订票
        for (int i = 0; i < 25; i++){
            Dod thread = new Dod();
            thread.start();
        }


        ExecutorService executorService = Executors.newFixedThreadPool(9);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(()->{
                for (int j = 0; j <2 ; j++) {
                    if (a != 0) {
                        a --;
                    }
                    System.out.println(Thread.currentThread().getName()+"a.json="+a);
                }
            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("做种："+a);
    }

}
