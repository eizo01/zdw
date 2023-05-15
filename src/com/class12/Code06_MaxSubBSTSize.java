package com.class12;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小
 * 思路：
 *  x 做为头部
 *  1) 左树 是不是 搜索二叉树 BST
 *  2) 右树 是不是 整体BST
 *  3) 左树 的max值要小于 x
 *  4) 右树 的max值要大于 x
 *  5) 左树 的size 和 右树的size + 1
 *  x 不做头部
 *  1) x 左树 max BSTSubSize
 *  2) x 右树 max BSTSubSize
 */
public class Code06_MaxSubBSTSize {
    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int value) {
            val = value;
        }
    }
    public static int maxSubBSTSize(Node head){
        if (head == null) return 0;
        return process(head).maxBSTSubtreeSize;
    }

    public static  class Info {
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;

        public Info(int maxBSTSubtreeSize, int allSize, int max, int min) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }

    public static  Info process(Node x){
        if (x == null){
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.val; int min = x.val; int allSize = 1;

        if (leftInfo !=null){
            max = Math.max(leftInfo.max,max);
            min = Math.min(leftInfo.min,min);
            allSize += leftInfo.allSize;
        }

        if (rightInfo !=null){
            max = Math.max(rightInfo.max,max);
            min = Math.min(rightInfo.min,min);
            allSize += rightInfo.allSize;
        }
        int p1 = -1;
        if (leftInfo != null){
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        int p2 = -1;
        if (rightInfo != null){
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;
        boolean leftBST = leftInfo==null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo==null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        while(leftBST && rightBST){
            boolean leftMaxValue = leftInfo == null ? true : (leftInfo.max < x.val);
            boolean rightMaxValue = rightInfo == null ? true : (rightInfo.min > x.val);
            while(leftMaxValue && rightMaxValue){
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        return new Info(Math.max(p1, Math.max(p2,p3)),allSize,max,min);
    }

}
