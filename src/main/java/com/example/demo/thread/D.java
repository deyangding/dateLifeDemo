package com.example.demo.thread;

public class D {

    public static void main(String[] args) {
        int i = 1;
        int j = 0;

        int a = 0;
        int sum = 0;
        int mid = 0;
        int nex = 0;

        int dddd = 100;
        while (sum < dddd) {

            mid = i * 2 + 1;
            System.out.println("mid="+mid);
            a = a +(j +1);
            System.out.println("a="+a);
            sum = mid + a*2;
            System.out.println("sum="+sum);
            i++;
            j++;
            if (sum < dddd) {
                nex = mid;
            }
        }
        System.out.println("######");
        System.out.println(nex);

        System.out.println("######");
        int c = (nex -1)/2;
        System.out.println("######c" +c);
        for (int k=0;k<c;k++) {
            int kkk = k + (k+1);
            for (int y=0;y<kkk;y++) {
                System.out.print("*");
            }
            System.out.println("");
        }

        for (int y=0;y<nex;y++) {
            System.out.print("*");
        }
        System.out.println("");
        for (int k=(c-1);k>=0;k--) {
            int kkk = (k*2)+1;
            for (int y=0;y<kkk; y++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
