package com.example.demo.thread;

import java.util.concurrent.CountDownLatch;

public class Tes {

    public static void main(String[] args) {
    final   CountDownLatch countDownLatch = new CountDownLatch(1);
        char[] a="ABCDEFD".toCharArray();
        char[] b="123456".toCharArray();

         final Object o = new Object();


        new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (o) {
                try {

                    for (char c : a) {
                        System.out.print(c);
                        o.notify();
                        o.wait();
                    }
                    o.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t2").start();


        new Thread(()->{

            synchronized (o){
                try {
                    for (char c : b) {

                        System.out.print(c);
                        countDownLatch.countDown();
                        o.notify();
                        o.wait();
                    }

                    o.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        },"t1").start();
    }
}
