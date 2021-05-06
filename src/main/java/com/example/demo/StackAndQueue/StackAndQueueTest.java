package com.example.demo.StackAndQueue;

public class StackAndQueueTest {

    private static String[] stack = new String[10];

    private static  String[] queue = new String[10];

    public static void main(String[] args) {
        stackpush("a");
        stackpush("b");

        System.out.println(stack);
    }

    public static void stackpush(String s){
        stack[stack.length] = s;
    }

    public static String stackPop(String s){
        return stack[stack.length-1];
    }


}
