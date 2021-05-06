package demo;

public class BinarySearch {

    public static void main(String[] args) {
        int[] aa = {1,2,3,4,5};
        System.out.println(binaySerach(aa,5));
    }
    public static  int binaySerach(int [] arr, int data) {
        int low = 0;
        int hight = arr.length-1;

        while (low <= hight) {
            int mid = low + (hight - low)/2;
            if (arr[mid] < data) {
                low = mid+1;
            } else if (arr[mid] == data) {
                return mid;
            } else {
                hight = mid-1;
            }
        }
        return 0;
    }
}
