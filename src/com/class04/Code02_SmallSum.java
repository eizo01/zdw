package com.class04;

/**
 *在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 */
public class Code02_SmallSum {
    public static int smallSum (int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }
    // arr[L..R]既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    // 左 排序   merge
    // 右 排序  merge
    // merge
    private static int process(int[] arr, int l, int r) {
        if (l == r) return 0;

        int mid = l + ((r-l) >> 1);
        return process(arr,l,mid) + process(arr,mid+1,r) +merge(arr,l,mid,r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r -l + 1];
        int i= 0;
        int p1 = l;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= r){
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= mid){
            help[i++] = arr[p1++];
        }
        while(p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++){
            arr[l + i] = help[i];
        }
        return res;
    }
}
