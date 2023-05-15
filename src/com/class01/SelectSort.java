package com.class01;

/**
 * 思想：找到最小值，然后从第一个位置开始交换，依次进行
 */
public class SelectSort {
    public static void selectSort(int[] arr){
        if (arr == null || arr.length < 2) return;

        for (int i = 0; i < arr.length -1; i ++){
            int minIdx = i;
            for (int j = i + 1; j < arr.length -1; j ++){
                 minIdx = arr[j] < arr[minIdx] ?  j : minIdx;
            }
            swap(arr,i,minIdx);
        }
    }

    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
