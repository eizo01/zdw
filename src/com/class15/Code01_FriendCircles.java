package com.class15;

import org.junit.Test;

/**  547.Friend Circles
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知
 * A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。
 * 所谓的朋友圈，是指所有朋友的集合。给定一个 N * N 的矩阵 M，
 * 表示班级中学生之间的朋友关系。如果 M[i][j] = 1，
 * 表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。
 * 你必须输出所有学生中的已知的朋友圈总数。
 * 输入:[
 *   [1,1,0],
 *   [1,1,0],
 *   [0,0,1]
 *   ]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 *
 */
public class Code01_FriendCircles {

    public static int findCircleNum(int[][] M){
        int N = M.length;

        UnionFind unionFind = new UnionFind(N);
        //只遍历右上角
        //行
        for (int i = 0;i < N; i++){
            //列
            for (int j = i + 1; j < N; j++) {
                //(0,1)
                if (M[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.getSets();
    }

    public static class UnionFind{
        private int[] parent;
        private int[] size;
        //stack
        private int[] help;
        private int sets;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i){
            int hi = 0;
            //压栈
            while(i != parent[i]){
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0 ; hi --) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i,int j){
            int f1 = find(i);
            int f2 = find(j);

            if (f1 != f2){
                if (size[f1] >= size[f2]){
                    size[f1] += size[f2];
                    parent[f2] = f1;
                }else {
                    size[f2] += size[f1];
                    parent[f1] =f2;
                }
                sets--;
            }
        }
        public int getSets(){
            return sets;
        }
    }
}
