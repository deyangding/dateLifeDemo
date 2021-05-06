package com.example.demo.test;

public class SA {
    private int i = 0;

    public void coun() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
        System.out.println(Thread.currentThread().getName() + "====" + i);
    }


    public int cou(int n) {
        if (n == 1) {
            return 1;
        }
        return n * cou(n - 1);
    }
    public static void main(String[] args) {


        Producer p = new Producer();
        p.regist(new Red());
        p.regist(new Black());

        p.notifiy("wooo");



//        System.out.println(new SA().cou(10));
//        long start =  System.currentTimeMillis();
//        int[] arr = {1,2,3,4,5,6,7,8,9};
//        System.out.println(binarySearch(arr,3));
//        long end =  System.currentTimeMillis();
//        System.out.println(end-start);
    }

    public static int binarySearch(int[] arr,int x){
        int low = 0;
        int high = arr.length-1;
        int i = 0;
        while (low <= high) {
            int mid = (low + high)/2;
            if (arr[mid]== x){
                System.out.println(i);
                return mid;
            } else if (arr[mid] < x){
                low = mid + 1;
                ++i;
            } else {
                high = mid -1;
                ++i;
            }
        }
        System.out.println("not found");
        return 0;
    }
}
