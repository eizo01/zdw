package com.class12;

/**
 * 判断 整个树的节点 是否等于 2^h -1
 */
public class Code05_isFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 第一种方法
    // 收集整棵树的高度h，和节点数n
    // 只有满二叉树满足 : 2 ^ h - 1 == n

    public static boolean isFull(Node head) {
        if (head == null) return true;
        Info all =process(head);

        return (2 << all.height) -1 == all.nodes;
    }

    public static class Info{
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static Info process(Node x){
        if (x == null) return  new Info(0, 0);

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;

        return new Info(height,nodes);

    }
}
