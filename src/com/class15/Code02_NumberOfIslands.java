package com.class15;
// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/number-of-islands/

/**
 *  给定一个二维数组matrix，里面的值不是1就是0，
 * 上、下、左、右相邻的1认为是一片岛，
 * 返回matrix中岛的数量
 */
public class Code02_NumberOfIslands {
    //法一 感染法
    public static int numIslands3(char[][] board) {
        int islands = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    islands++;
                    infect(board, i, j);
                }
            }
        }
        return islands;
    }

    // 从(i,j)这个位置出发，把所有连成一片的'1'字符，变成0
    private static void infect(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            return;
        }
        //每个位置会被看五遍
        board[i][j] = 0;
        infect(board, i - 1, j);
        infect(board, i + 1, j);
        infect(board, i, j - 1);
        infect(board, i, j + 1);
    }

    //并查集 法二 通过 i*列数 +j 来转换下标对应并查集的各数组下标

    public static int numIslands2(char[][] board) {
        int col = board[0].length;
        int row = board.length;
        UnionFind2 uf = new UnionFind2(board);
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(i - 1, 0, i, 0);
            }
        }
        for (int j = 1; j < col; j++) {
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                uf.union(0, j - 1, 0, j);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1'){
                    if (board[i][j-1]=='1'){
                        uf.union(i,j-1,i,j);
                    }
                    if (board[i-1][j]=='1'){
                        uf.union(i-1,j,i,j);
                    }
                }
            }
        }
        return uf.sets();
    }

    public static class UnionFind2{
        private int[] parent;
        private int[] size;
        private int[] help;
        private int col;
        private int sets;

        public UnionFind2(char[][] board) {
            col = board[0].length;
            sets = 0;
            int row = board.length;
            //把二维数组变成一维数组
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1'){
                        int i = index(r,c);
                        parent[i] = i;
                        size[i] = 1;
                        sets ++;
                    }
                }
            }
        }
        // (r,c) -> i
        private int index(int r,int c){
            return r * col + r;
        }

        public int find(int i){
            int hi = 0;
            while( i != parent[i]){
                help[hi++] = i;
                i = parent[i];
            }

            for (hi--; hi >= 0; hi --){
                parent[help[hi]] = i;
            }
            return i;
        }
        public void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }
}
