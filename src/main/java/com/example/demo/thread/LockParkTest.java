package com.example.demo.thread;

import java.util.concurrent.locks.LockSupport;

public class LockParkTest {
    static  Thread t1;
    static Thread t2 ;
    public static void main(String[] args) {
        String[] s = {"A", "B", "C", "D", "E", "F"};
        String[] n = {"1", "2", "3", "4", "5", "6"};

         t1 = new Thread(()->{
            for (int i = 0; i <s.length-1 ; i++) {
                System.out.print(s[i]);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

         t2 = new Thread(()->{
            for (int i = 0; i <n.length-1 ; i++) {
                LockSupport.park();
                System.out.print(n[i]);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }
}
