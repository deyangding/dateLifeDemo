package com.example.demo.thread;

public class ClassReordering {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });

            one.start();
            two.start();

            one.join();
            two.join();


            if (x == 0 && y == 0) {
                String result = "第" + i + "次循环" + "x =" + x + " y=" + y;
                System.out.println(result);
                break;
            }
        }
    }
}
