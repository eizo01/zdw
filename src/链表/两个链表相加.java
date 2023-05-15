package 链表;
// https://leetcode.cn/problems/lMSNwu/
public class 两个链表相加 {


      public class ListNode {
         int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l11 = reverse(l1);
        ListNode l22 = reverse(l2);

        int l1Len = listLength(l11);
        int l2len = listLength(l22);

        ListNode l = l1Len > l2len ? l11 : l22;
        ListNode s = l == l11 ? l22 : l11;
        ListNode lcur = l;
        ListNode scur = s;
        ListNode last =lcur;
        int carry = 0;
        int curNum = 0;
        while(scur != null){
            curNum = lcur.val + scur.val + carry;
            lcur.val = (curNum % 10);
            carry = curNum / 10;
            last=lcur;
            lcur = lcur.next;
            scur = scur.next;
        }
        while (lcur != null){
            curNum = lcur.val + carry;
            lcur.val = (curNum % 10);
            carry = curNum/10;
            last = lcur;
            lcur = lcur.next;
        }
        if(carry != 0){
            ListNode i = new ListNode(carry);
            last.next = i;
        }
        ListNode x=reverse(l);

        return x;

    }
    public static int listLength(ListNode head){
        int len = 0;
        while (head.next != null){
            len++;
            head = head.next;
        }
        return len;
    }
    public static ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
