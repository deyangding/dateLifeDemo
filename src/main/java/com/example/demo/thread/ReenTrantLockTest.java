package com.example.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockTest {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("---------t1");
                lock.unlock();
            }
        }, "t1");
    }

}
