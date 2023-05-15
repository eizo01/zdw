package com.class19;

/** 背包问题
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Code01_Knapsack {
    /*
        w 是 重量， v 是 价值，没有负数
        bag是背包容量，不能超重
        返回 不超重的情况下，能够得到的最大值
     */
    public static int maxValue(int[] w,int[] v,int bag){
        if (w == null || v == null || w.length !=v.length || w.length == 0){
            return 0;
        }
       return process(w,v,0,bag);
    }
    //只考虑index号货物和index后所有的货自由选择
    public static int process(int[] w,int[] v,int index,int bag){
        if (bag < 0){
            return -1;
        }
        //越界了
        if (index == w.length ){
            return 0;
        }
        //有货 index位置 0w也算有空间
        //要和不要两种选择
        int p1 = process(w,v,index + 1,bag);
        //不要选择
        int p2 = 0;
        int next =process(w,v,index + 1,bag-w[index]);
        if (next != -1){
            p2 = next + v[index];
        }
       return Math.max(p1,p2);
    }

    public static int dp(int[] w,int[] v,int bag){
        if (w == null || v == null || v.length != w.length || w.length == 0){
            return 0;
        }
        int N = w.length;
        //弄清楚dp的行列是啥，列是bag那就不能超出背包容量，尝试函数也给出了
        int[][] dp = new int[N+1][bag+1];
        for (int index = N - 1; index >= 0; index --){
            for (int rest = 0; rest <= bag; rest ++){
                int p1 =dp[index + 1][rest];
                int p2 = 0;
                //防止数组越界，有可能在dp的左边 要设置为-1
                int next =rest - w[index]< 0 ? -1 : dp[index + 1][rest-w[index]];
                if (next != -1){
                    p2 = next + v[index];
                }
                dp[index][rest] = Math.max(p1,p2);
            }

        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
