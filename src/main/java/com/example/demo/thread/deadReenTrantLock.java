package com.example.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

public class deadReenTrantLock {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            lock.lock();
                System.out.println("A 等");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            lock.lock();
                    System.out.println("A 等 b");



        }).start();

        new Thread(()->{
            lock.lock();
            System.out.println("B 等");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.println("B 等 a.json");

        }).start();
    }

}
