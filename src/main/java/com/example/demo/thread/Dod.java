package com.example.demo.thread;

public class Dod extends Thread{


    public static Integer  a = 20;

    @Override
    public void run() {
        sellTicket1();
    }

    /**
     * 模拟售票,多线程下会出现线程安全问题,会发生超卖
     */
    public void sellTicket1(){
        if(OverSoldTest.a > 0){
            OverSoldTest.a--;
            System.out.println(Thread.currentThread().getName() + "抢票成功");
        }else{
            System.out.println(Thread.currentThread().getName()+"票已经售罄,不好意思");
        }
    }
}
