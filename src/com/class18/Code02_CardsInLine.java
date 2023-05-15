package com.class18;

/**
 * 给定一个整型数组arr，正数，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌，名牌
 * 玩家A和玩家B都绝顶聪明 先拿最大
 * 请返回最后获胜者的分数。
 */
public class Code02_CardsInLine {

    public static int win1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int first = f1(arr,0,arr.length-1);
        int second = g1(arr,0,arr.length-1);
        return Math.max(first, second);
    }
    public static int f1(int[] arr, int l, int r){
        if (l == r){
            return arr[l];
        }
        int p1 = arr[l] + g1(arr,l + 1,r);
        int p2 = arr[r] + g1(arr,l ,r - 1);
        return Math.max(p1,p2);
    }

    public static int g1(int[] arr, int l, int r){
        //如果数组只有一张牌，那么先手已经拿走了
        if (l == r) {
            return 0;
        }
        int p1 = f1(arr,l+1,r);
        int p2 = f1(arr,l,r-1);

        return Math.min(p1,p2);
    }

    public static int win2(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j]  = -1;
                gmap[i][j]  = -1;
            }
        }
        int frist = f2(arr,0,N - 1,fmap,gmap);
        int sccond = g2(arr,0,N - 1,fmap,gmap);
        return Math.max(frist,sccond);
    }
    public static int f2(int[] arr, int l, int r,int[][] fmap, int[][] gmap) {
        if (fmap[l][r] != -1){
            return fmap[l][r];
        }
        int ans = 0;
        if (l == r){
            ans = arr[l];
        }else{
            int p1 =arr[l] + g2(arr,l + 1, r,fmap,gmap);
            int p2 = arr[r] + g2(arr,l , r - 1,fmap,gmap);
            ans = Math.max(p1,p2);
        }
        fmap[r][l] = ans;
        return ans;

    }

    public static int g2(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (gmap[l][r] != -1){
            return gmap[l][r];
        }
        int ans = 0;
        if (l != r){
            int p1 = f2(arr,l+1,r,fmap,gmap);
            int p2 = f2(arr,l,r-1,fmap,gmap);
            ans = Math.min(p1,p2);
        }
        gmap[r][l] = ans;
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        int[] arr1 = { 30,10,10 };
        System.out.println(win1(arr1));
        System.out.println(win2(arr1));
//        System.out.println(win3(arr));

    }

}
