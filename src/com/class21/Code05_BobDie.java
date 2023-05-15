package com.class21;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class Code05_BobDie {

    public static double livePosibility(int row, int col, int k, int N, int M) {
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    private static long process(int row, int col, int rest, int N, int M) {
        if (row < 0 || col < 0 || row == N || col == M) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        //上
        long p1 = process(row - 1, col, rest - 1, N, M);
        //左
        long p2 = process(row, col - 1, rest - 1, N, M);
        //下
        long p3 = process(row + 1, col, rest - 1, N, M);
        //右
        long p4 = process(row, col + 1, rest - 1, N, M);

        return p1 + p2 + p3 + p4;
    }

    public static double livePosibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[N][M][k + 1];
        // 三维dp rest == 0时， 可以推出第一个面，而每一个面都依赖下面的面
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 0; rest <= k; rest++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    //当每一分支都要判断边界时，可以使用prick函数代替原来的递归，返回判断好的dp
                    //求相加上下左右全部的走法
                    //上
                    dp[r][c][rest] = pick(dp,N, M,r - 1, c, rest - 1);
                    //左
                    dp[r][c][rest] += pick(dp,  N, M,r,c - 1, rest - 1);
                    //下
                    dp[r][c][rest] += pick(dp, N, M,r + 1, c, rest - 1);
                    //右
                    dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
                }
            }
        }
        // 求生存概率
        return (double) dp[row][col][k] / Math.pow(4, k);
    }
    public static long pick(long[][][] dp, int N,int M, int row, int col,int rest ){
        if (row < 0 || col < 0 || row == N || col == M) {
            return 0;
        }
        return dp[row][col][rest];
    }

}
