package com.class22;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class Code01_KillMonster {
    public static double right1(int N,int M, int K){
        if(N < 1 || M < 1 || K < 1){
            return 0;
        }
        long all = (long) Math.pow(M+1,K);
        long kill = process1(K,M,N);
        return (double)((double)kill/(double) all);
    }
    // 怪兽还剩hp点血
    // 每次的伤害在[0~M]范围上
    // 还有times次可以砍
    // 返回砍死的情况数！
    public static long process1(int times, int M, int hp) {
        if (times == 0){
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0){
            return (long)Math.pow(M + 1,times);
        }
        long ways = 0;
        //概率
        for (int i = 0; i <= M; i ++){
            ways += process1(times - 1, M, hp - i);
        }
        return ways;
    }
    public static double dp1(int N,int M, int K){
        if (N < 1 || M < 1 || K < 1){
            return 0;
        }
        long[][] dp = new long[K + 1][N + 1];
        long all = (long) Math.pow(M+1,K);
        dp[0][0] = 1;
        for (int times = 1; times <= K; times ++){
            //当前剩下砍的次数，但是血量为0时，就是（M + 1）的times次方
            dp[times][0] = (long) Math.pow(M + 1,times);
            for (int hp = 1; hp <= N; hp ++){
                long ways = 0;
                //概率
                for (int i = 0; i <= M; i++) {
                if (hp - i >= 0) {
                    ways += dp[times - 1][hp - i];
                } else {
                    ways += (long) Math.pow(M + 1, times - 1);
                }
                }
                dp[times][hp]= ways;
            }
        }
        long kill = dp[K][N];
        return (double)((double)kill/(double) all);
    }
    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times ++){

            dp[times][0] = (long) Math.pow(M + 1,times);
            for (int hp = 1; hp <= N; hp ++){
                dp[times][hp] = dp[times][hp - 1]+ dp[times -1][hp];
                    if (hp - 1 -M >=0){
                        //正数的情况
                        dp[times][hp] -= dp[times - 1][hp - 1 - M];
                    }else{
                        //负数的情况
                        dp[times][hp] -= (long) Math.pow(M + 1,times -1);
                    }
                }
            }
        long kill = dp[K][N];
        return (double)((double)kill/(double) all);
    }
    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right1(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
    }