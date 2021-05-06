package com.example.demo.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.util.CollectionUtils;

public class Demo {

    public static void main(String[] args) {
//        Collections.sort(Arrays.asList(1,2,4));

//        System.out.println(4>>>1);

        int [] a = {1,6,8,9,11,44,66,99};
        System.out.println(bannriySearch(a,44));
    }

    public static int bannriySearch(int[] a,int k) {
        int low = 0;
        int hig = a.length -1;

        while (low <hig) {
            int mid = (low+hig)>>>1;
            if (a[mid] < k) {
                low = mid +1;
            } else if (a[mid] > k){
                hig = mid -1;
            } else {
                return mid;
            }
        }

        return -1;


    }


}
