package com.example.demo.thread;

import java.util.concurrent.CountDownLatch;
//"D"最后执行
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(()-> {
            System.out.println("D1");
            try {
                countDownLatch.await();
                System.out.println("D");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {

            countDownLatch.countDown();
            System.out.println("a.json");
        }).start();

        new Thread(()-> {
            countDownLatch.countDown();
            System.out.println("b");
        }).start();

        new Thread(()-> {
            countDownLatch.countDown();
            System.out.println("c");
        }).start();


    }

}
