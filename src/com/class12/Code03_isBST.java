package com.class12;

import java.util.ArrayList;

/**
 *  判断二叉树是不是搜索二叉树
 * 二叉搜索树定义 ： 左边 要 比你小， 右边 比你大
 *  1) x 左 是不是 BST
 *  2) x 右 是不是 BST
 *  3) x 左 的max 是不是比我大
 *  4) x 右 的min 是不是比我小
 *
 *  对与左右子树的要求不用 ，但我们全都要 ，最小值 和 最大值 求全集
 */
public class Code03_isBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static boolean isBST2(Node head) {
        if (head == null) return true;
        return process(head).isBST;
    }

    public static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node x){
        if (x == null) return null;

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max =  x.value;
        if (leftInfo != null){
            max = Math.max(max,leftInfo.max);
        }
        if (rightInfo != null){
            max = Math.max(max,rightInfo.max);
        }
        int min =  x.value;
        if (leftInfo != null){
            min = Math.min(min,leftInfo.min);
        }
        if (rightInfo != null){
            min = Math.min(min,rightInfo.min);
        }
        boolean isBST = true;
        /*  判断的四个原则 ，因为空一开始没有处理，所以判断的时候要处理空值
         *  1) x 左 是不是 BST
         *  2) x 右 是不是 BST
         *  3) x 左 的max 是不是比我大
         *  4) x 右 的min 是不是比我小
         */
        if (leftInfo!=null && !leftInfo.isBST){
            isBST = false;
        }
        if (rightInfo!=null && !rightInfo.isBST){
            isBST = false;
        }

        if (leftInfo != null && leftInfo.max >= x.value){
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= x.value){
            isBST = false;
        }

        return new Info(isBST,max,min);
    }
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
