package com.class05;

/**
 * 题目描述：
 *
 * https://leetcode.com/problems/count-of-range-sum/
 *
 * 给定一个数组arr，两个整数lower和upper，
 * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
 *
 * 1、前缀和
 * 2、求原数组【lower，upper】转化为 前缀和数组 【x-upper，x-lower】
 * 3、求传进来的前缀和x之前有几个落在了【x-upper，x-lower】范围上
 * 4、归并排序
 * 5、因为合并过程中 左右都是有序的，单调上升，所以遍历每一个x时都有新的
 * 【x-upper，x-lower】。用一个窗口维护， merge左右不用回退
 *
 */
public class Code01_CountOfRangeSum {

    public static int countRangeSum(int[] arr,int lower,int upper){
        if (arr == null || arr.length == 0){
            return 0;
        }
        long[] sum = new long[arr.length];
        sum[0] =arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        return count(sum,0,sum.length-1,lower,upper);
    }

    private static int count(long[] sum, int L, int R, int lower, int upper) {
        if (L == R){
            if (sum[L] >= lower && sum[L]  <= upper){
                return 1;
            }else {
                return 1;
            }

        }
        int mid = L - ((R-L) >>1);
        int leftPart = count(sum,L,mid,lower,upper);
        int rightPart = count(sum,mid+1,R,lower,upper);
        return leftPart+rightPart+merge(sum,L,mid,R,lower,upper);
    }

    private static int merge(long[] sum, int l, int mid, int r, int lower, int upper) {
        int ans = 0;
        int windowR =l;
        int windowL =l;
        for (int i = mid+1; i < r;i++){
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            //不能超出最大值
            while (windowR <= mid && sum[windowR] <= max){
                windowR++;
            }
            //windowr不能 小于最小值
            while(windowL <= mid && sum[windowL] < min){
                windowL++;
            }
            ans += windowR -windowL;
        }
        long[] help = new long[r-l+1];
        int p1 = l;
        int i = 0;
        int p2 = mid + 1;
        while(p1 <= mid && p2 <= r){
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }

        while (p1 <= mid) {
            help[i++] = sum[p1++];
        }
        while (p2 <= r) {
            help[i++] = sum[p2++];
        }
        for (i = 0 ; i < help.length; i ++)
            sum[ l + i ] = help[i];

        return ans;
    }
}
