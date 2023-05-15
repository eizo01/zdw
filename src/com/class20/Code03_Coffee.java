package com.class20;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 题目
// 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
// 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
// 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
// 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
// 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
// 四个参数：arr, n, a, b
// 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，返回从开始等到所有咖啡机变干净的最短时间
// 两道题 第一道是排队喝的人给出最优排队策略 第二题 返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，最短时间。
public class Code03_Coffee {

    /**
     * 三个参数：int[] arr、int N，int a、int b
     *  小根堆解决最优排队问题，设计堆的参数是（工作时间，泡咖啡时间）比较规则是两个相加
     *  Besttime  index号杯子决定洗 或者 挥发 求两种方式的最大值 返回最小
     */
    //第一题
    public static class Machine{
        public int timePonint;
        public int workTime;
        public Machine(int t,int w){
            timePonint = t;
            workTime = w;
        }
    }

    public static class MachineComparator implements Comparator<Machine> {
        public int compare(Machine o1, Machine o2) {
            //小根堆 CompareTo 的返回值有 -1、0、1。 若比较者大于被比较者，那么返回1，等于则返回0，小于返回-1。
            return (o1.timePonint + o1.workTime) - (o2.workTime + o2.timePonint) ;
        }
    }
    // 数组arr代表每一个咖啡机冲一杯咖啡的时间
    // 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
    // n人
    public static int minTime1(int[] arr, int n, int a, int b){
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        for (int i = 0; i < arr.length; i++){
            heap.add(new Machine(0,arr[i]));
        }
        int[] drinks = new int[n];
        for(int i = 0; i < n; i ++){
            //咖啡机开始工作,最小堆弹出第几号咖啡机，记录他的时间点，然后加入堆
            //因为人是一口气来的，所以要记录好时间点给下一人判断他在第几号机子排队
            Machine cur = heap.poll();
            cur.timePonint += cur.workTime;
            drinks[i] = cur.timePonint;
            heap.add(cur);
        }
        return bestTime(drinks,a,b,0,0);
    }

    /**
     *
     * @param drinks 所有杯子可以开始洗的时间
     * @param wash 单杯洗干净的时间（串行）
     * @param air 挥发干净的时间(并行)
     * @param index 第几号杯子/第几个人
     * @param free 洗的机器什么时间点可用
     * drinks[index.....]都变干净，最早的结束时间（返回）
     * @return
     */
    public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length){
            return 0;
        }
        //index号杯子 决定洗
        int selfClean1 = Math.max(drinks[index],free) + wash;
        int restClean1 = bestTime(drinks,wash,air,index + 1,selfClean1);
        int p1 = Math.max(selfClean1, restClean1);

        // index号杯子 决定挥发
        int selfClean2 =drinks[index] + air;
        int restClean2 = bestTime(drinks, wash, air, index + 1, free);
        int p2 = Math.max(selfClean2, restClean2);
        return Math.min(p1, p2);
    }

    // 贪心+优良尝试改成动态规划
    public static int minTime2(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePonint += cur.workTime;
            drinks[i] = cur.timePonint;
            heap.add(cur);
        }
        return bestTimeDp(drinks, a, b);
    }

    private static int bestTimeDp(int[] drinks, int wash, int air) {
        int N = drinks.length;
        int maxFree = 0;
        for (int i = 0; i < N; i ++){
            maxFree = Math.max(maxFree,drinks[i]) + wash;
        }

        int[][] dp = new int[N+1][maxFree + 1];
        //位置依赖，递归看出 上面层 依赖 下面，所以从下面填
        for (int index = N -1;  index >= 0; index --){
            for (int free = 0; free <= maxFree; free ++){
                int selfClean1 = Math.max(drinks[index],free) + wash;
                if (selfClean1 > maxFree){
                    break;
                }
                //index号杯子 决定洗
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);

                // index号杯子 决定挥发
                int selfClean2 =drinks[index] + air;
                int restClean2 = dp[index + 1] [free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }

        }
   return dp[0][0];
    }


    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }
    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = minTime1(arr, n, a, b);
            int ans3 = minTime2(arr, n, a, b);
            if ( ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println( ans1+" , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }
 }