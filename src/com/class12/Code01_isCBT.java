package com.class12;

import java.util.LinkedList;

/**
 * 判断二叉树 是否 完全二叉树
 *
 *  1) 有一个节点 只有右孩子没有左孩子 不用遍历了 返回false
 *  2) 在第一个原则，当第一次遇到左右孩子 不双权 的时候，剩下的节点一定是 叶子节点
 *  不违反 两个原则，并且能遍历完 就是完全二叉树
 */
public class Code01_isCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

     public static  boolean isCBT1(Node head){
         if (head == null) return true;

         LinkedList<Node> queue = new LinkedList<>();

         boolean leaf = false;
         Node l = null;
         Node r = null;
         queue.add(head);
         while(!queue.isEmpty()){
             head = queue.poll();
             l = head.left;
             r = head.right;
             if (
                     (leaf && (l != null || r != null))
                     || (r != null && l == null)
             ){
                 return false;
             }

             if (l != null){

                 queue.add(l);
             }
             if (r != null) {
                queue.add(r);
             }
             //第一次遇到不 双全的节点 ，开启开关，认为后面的必须要是叶子节点
            if (l == null || r == null){
                leaf =true;
            }

         }
         return true;
     }
    /**
     * 递归套路
     * 1、 左树是满 ，右树是 满 左高 等于 右高
     * 2、 左是完全二叉树 右是满二叉  左高 = 右高 + 1
     * 3、 左满 ，右满  左高 = 右高 + 1
     * 4、左满，右完全   左高 = 右高
     * 信息收集： 满  完全 高度
     */

    public static  boolean isCBT2(Node head){
        return process(head).isCBT;
    }

    public static class Info{
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean isFull, boolean isCBT, int heigh) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = heigh;
        }
    }
    public static Info process(Node x){
        if (x == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;

        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
            isCBT = true;
        } else if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
            isCBT = true;
        }


        return new Info(isFull,isCBT,height);
    }
    // for test
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
