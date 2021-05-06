package com.example.demo.thread;

import java.util.concurrent.ExecutorService;

public class deadLock {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
//        ExecutorService
        new Thread(()->{
            synchronized(a) {
                System.out.println("A 等");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(b) {
                    System.out.println("A 等 b");
                }
            }

        }).start();

        new Thread(()->{
            synchronized(b) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B 等");
                synchronized(a) {
                    System.out.println("B 等 a.json");
                }
            }

        }).start();
    }

}
