package com.class12;

/**
 *  1) x 左树 是平衡
 *  2) x 右树 是平衡
 *  3)|x 左高 - 右高| < 2
 *
 *  思路 定义一个info结构体 来存储 每一个节点 是否平衡 和高度
 */
public class Code02_IsBalanced {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static class Info {
        public boolean isBlanced;
        public int hight;

        public Info(boolean isBlanced, int hight) {
            this.isBlanced = isBlanced;
            this.hight = hight;
        }
    }

    public static boolean isBlanceTree(Node head){
        return process(head).isBlanced;
    }



    public static Info process(Node x){
        if (x == null){
            return new Info(true,0);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.hight,rightInfo.hight);
        boolean isBlanced =  true;
        if (!leftInfo.isBlanced){
            isBlanced=false;
        }
        if (!rightInfo.isBlanced){
            isBlanced=false;
        }
        if (Math.abs(leftInfo.hight - rightInfo.hight) > 1){
            isBlanced =false;
        }

        return new Info(isBlanced,height);
    }
}
