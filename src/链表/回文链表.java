package 链表;

public class 回文链表 {
    public class ListNode {
        int val;
       ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public boolean isPalindrome(ListNode head) {
    if(head == null || head.next == null){
        return true;
    }
    ListNode flast = head;
    ListNode slow = head;
    while(flast != null && flast.next != null){
        slow = slow.next;
        flast= flast.next.next;
    }
    ListNode l = head;
    ListNode r =  reverse(slow);
    while(l != null && r != null){
        if(l.val != r.val){
            return false;
        }
        l = l.next;
        r = r.next;
    }
    // ListNode x =   reverse(r);
    return true;

}
    private ListNode reverse(ListNode start){
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
