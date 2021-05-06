package com.example.demo.thread.PriorityBlockingQuque;

public class PriorityTask implements Runnable, Comparable {

    private int priority;

    public PriorityTask(int priority) {
        this.priority = priority;
    }

    @Override
    public void run() {

        System.out.println("优先级为" + priority + "的任务执行完毕！");
    }

    @Override
    public int compareTo(Object arg) {
        PriorityTask task = (PriorityTask) arg;
        if (this.priority == task.priority) {
            return 0;
        }
        return this.priority > task.priority ? 1 : -1;
    }
}
