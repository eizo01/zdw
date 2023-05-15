package com.class19;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/stickers-to-spell-word
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 */
public class Code03_StickersToSpellWord {
    //方式一
    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers,target);
        return ans == Integer.MAX_VALUE ? -1 : ans;


    }
    // 所有贴纸stickers，每一种贴纸都有无穷张
    // target
    // 最少张数
    private static int process1(String[] stickers, String target) {
        if (target.length() == 0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String frist : stickers){
            String rest = minus(target,frist);
            if (rest.length() != target.length()){
                min =Math.min(min,process1(stickers,rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }
    public static String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        // 把字符个数都放在count表中，s1 = target，frist 是s2
        //s2是来尝试剪掉s1的字符
        for (char c1 : str1){
            count[c1 - 'a']++;
        }
        for (char c2 : str1){
            count[c2 - 'a']--;
        }
        //最后遍历count表，把s1被s2剪掉的剩下字符重新拼接 返回
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i ++){
            if (count[i] > 0){
                //i 就是字符  count[i] 是字符个数
                //所以恢复时要 i + 'a' 转成char 类型， 因为之前 字符 - ’a‘；变成数字i下标
                for (int j = 0; j <count[i]; j ++){
                    sb.append((char)(i + 'a'));
                }
            }
        }
        return sb.toString();
    }



    //方式二 重点看
    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i ++){
            char[] str =stickers[i].toCharArray();
            for (char c1 : str){
                counts[i][c1 - 'a']++;
            }
        }
        int ans = process2(counts,target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    // stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
    // 每一种贴纸都有无穷张
    // 返回搞定target的最少张数
    // 最少张数
    public static int process2(int[][] stickers ,String t){
        if (t.length() == 0){
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tcount = new int[26];//目标字符串的字符个数统计
        for (char c1 : target){
            tcount[c1 - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i ++){
            //拿出一张贴纸
            int[] sticker = stickers[i];
            //当前贴张我都要看是不是第一个能对应上
            if (sticker[target[0] - 'a'] > 0){
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++){
                    if (tcount[j] > 0){
                        int nums = tcount[j] - sticker[j];
                        for (int k = 0; k < nums;k++ ){
                            sb.append((char) k + 'a');
                        }
                    }
                }
                String rest = sb.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    //傻缓存 只加了hashmap
    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }

}
