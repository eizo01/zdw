package com.class20;
// 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 *
 * 子序列是不连续的
 * 子串是连续的
 */
public class Code01_PalindromeSubsequence {
    //范围模型
    public static int longestPalindromeSubseq(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str,0,str.length -1);
    }
    //L不要  R不要   L 要 R 不要
    //L不要  R要     L 要 R 要
    public static int f(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int p1 = f(str, L + 1, R - 1);
        int p2 = f(str, L, R - 1);
        int p3 = f(str, L + 1, R);
        //为什么要加2？ 因为首尾相等 长度是2 再加上f递归返回的长度
        //可能4 要首尾相等才有 因为是要 L和 R
        int p4 = str[L] != str[R] ? 0 : (2 + f(str, L + 1, R - 1));
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }
    public static int longestPalindromeSubseq2(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n  = s.length();
        int[][] dp = new int[s.length()][s.length()];
        dp[n - 1][n - 1] = 1;
        for (int i  =0; i < n; i ++){
            dp[i][i] = 1;
            dp[i][i + 1] =  str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = n - 3; L >= 0; L --){
            for (int R = L + 2;R < n; R ++){
                int p1 = dp[L + 1][R - 1];
                int p2 = dp[L][R - 1];
                int p3 = dp[L + 1][R];
                int p4 = str[L] != str[R] ? 0 : (2 + dp[L + 1][ R - 1]);
                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
                //优化版本
//                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
//                if (str[L] == str[R]) {
//                    dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
//                }
            }
        }
        return dp[0][n - 1];
    }

    // 思路二 生成逆序串
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        char[] reverse = reverse(str);
        return longestCommonSubsequence(str, reverse);
    }

    public static char[] reverse(char[] str) {
        int N = str.length;
        char[] reverse = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            reverse[--N] = str[i];
        }
        return reverse;
    }

    public static int longestCommonSubsequence(char[] str1, char[] str2) {
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N - 1][M - 1];
    }
}
