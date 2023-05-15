package com.class03;

public class Code02_DeleteGivenValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static Node removeValue(Node head, int num) {
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                //跳过是num的节点
                pre.next = cur.next;
            } else {
                //连接
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
