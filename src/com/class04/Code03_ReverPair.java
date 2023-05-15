package com.class04;

/**
 * 在一个数组中，
 * 任何一个前面的数a，和任何一个后面的数b，
 * 如果(a,b)是降序的，就称为逆序对
 * 返回数组中所有的逆序对
 */
public class Code03_ReverPair {

    public static int reverPairNumber(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        return process(arr,0,arr.length);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) return 0;
        int mid = l + ((r - l) >> 1);
        return process(arr,l,mid) + process(arr,mid+1,r) + merge(arr,l,mid,r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[l - r + 1];
        int i = arr.length - 1;
        int p1 = mid;
        int p2 = r;
        int res = 0;
        while(p1 >= l && p2 > mid ){
            res += arr[p1] < arr[p2] ? (p2 - mid) : 0;
            //相反
            help[i--] = arr[p1] > arr[p2] ? arr[p1 --] : arr[p2 --];
        }

        while(p1 >= l){
            help[i --] = arr[p1--];
        }
        while (p2 > mid){
            help[i--] = arr[p2 --];
        }
        for (i = 0; i < help.length; i ++){
            arr[l + i] = help[i];
        }
        return res;

    }
}
