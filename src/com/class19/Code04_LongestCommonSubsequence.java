package com.class19;
// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/
/**
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 *
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 *
 * 属于样本模型
 */
public class Code04_LongestCommonSubsequence {
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        return process(str1,str2,str1.length - 1,str2.length -1);
    }

    private static int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0){
            return str1[i] == str2[j] ? 1 : 0;
        }else
        if (i == 0){
            if (str1[i] == str2[j]){
                return 1;
            }else {
                return process(str1,str2,i,j-1);
            }
        }else if (j == 0){
            if (str1[i] == str2[j]){
                return 1;
            }else {
                return process(str1,str2,i-1,j);
            }
        }else {
            // i != 0 && j != 0
            int p1 = process(str1,str2,i-1,j);
            int p2 = process(str1,str2,i,j-1);
            // p3就是可能性d)，如果可能性d)存在，即str1[i] == str2[j]，那么p3就求出来，参与pk
            // 如果可能性d)不存在，即str1[i] != str2[j]，那么让p3等于0，然后去参与pk，反正不影响
            //考虑i，j位置的话，直接调会死循环process(str1,str2,i,j)
            //所以i，j必须相等才有可能性3，要相等str1[i] == str2[j]
            int p3 = str1[i] == str2[j] ? (1 + process(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        //确定值
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i <= M; i ++ ){
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i][0];
        }
        for (int j = 1; j <= M; j ++ ){
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j];
        }
        for (int i = 1; i < N; i ++){
            for (int j =1; j < M; j ++){
                int p1 = dp[i-1][j];
                int p2 = dp[i][j-1];

                int p3 = str1[i] == str2[j] ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j]= Math.max(p1,Math.max(p2,p3));
            }
        }

        return dp[N-1][M-1];
    }


}
