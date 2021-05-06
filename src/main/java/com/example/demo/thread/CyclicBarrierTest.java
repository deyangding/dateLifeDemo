package com.example.demo.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import org.apache.poi.ss.formula.functions.T;

//线程同时运行
public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        new Thread(()->{
            System.out.println("A 准备");

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("A 执行");
        }).start();

        new Thread(()->{
            System.out.println("B 准备");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("B 执行");
        }).start();

        System.out.println("睡觉");
        Thread.sleep(11111);
        System.out.println("睡觉结束");


        new Thread(()->{
            System.out.println("C 准备");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("C 执行");
        }).start();
    }
}
