package com.example.demo.thread.pool;

public class PoolTask implements Runnable {
    private String name;
    private int num;

    public PoolTask(String name, int num) {
        this.name = name;
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(name +":"+num);
    }
}
