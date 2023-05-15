package 链表;

import java.util.HashMap;
//https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/submissions/
public class 复杂链表的复制 {
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    //第一种方法
    public static Node copyRandomList(Node head){
        HashMap<Node,Node> map = new HashMap<>();
        Node cur = head;
        while(cur != null){
            map.put(cur,new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while( cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur =cur.next;
        }
        return map.get(head);
    }


    public static Node copyRandomList2(Node head){
        if (head == null){
            return null;
        }
        Node cur = head;
        Node next = null;
        while(cur != null){
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        cur =head;
        Node copyCur = null;
        while(cur != null){
            next = cur.next.next;
            copyCur = cur.next;
            copyCur.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        //拿到新链表的头节点
        Node res = head.next;
        cur = head;
        while(cur != null){
            next = cur.next.next;
            //变量复用
            copyCur = cur.next;
            //老节点的下一个指向第二个老节点
            cur.next = next;
            //新节点指向新节点
            copyCur.next = next != null ? next.next : null;
            cur = next;

        }
        return res;

    }
}
