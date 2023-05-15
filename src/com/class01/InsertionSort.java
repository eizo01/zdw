package com.class01;

public class InsertionSort {
    public static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2) return;

        for (int i = 1; i < arr.length -1; i ++){
            //眼睛看当前的数，然后往后看有没有比他大的数，有就交换
            //arr[j] < arr[j+1] 4 7 2 。 j 就是4 ，j + 1 就是7，这个条件就不用交换
            for (int j = i -1; j >= 0 && arr[j] > arr[j+1]; j --){
//                进入这个for循环就要交换
                swap(arr,j,j+1);
            }
        }
    }
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
