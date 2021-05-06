package com.example.demo.thread;

import lombok.SneakyThrows;

public class TestWaitNotify {


    static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        String[] s = {"A", "B", "C", "D", "E", "F"};
        String[] n = {"1", "2", "3", "4", "5", "6"};

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    for (int i = 0; i < s.length - 1; i++) {
                        try {
                            System.out.print(s[i]);
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notify();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    for (int i = 0; i < n.length - 1; i++) {
                        try {
                            System.out.print(n[i]);
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notify();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
