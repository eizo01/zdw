package 链表;

/**
 *
 */
public class 链表中删除指定的数字 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public static ListNode deleteValue(ListNode head, int target){
        while(head != null){
            if (head.val == target){
                break;
            }
            head = head.next;
        }
        ListNode pre = head;
        ListNode cur = head;
        while (cur !=null){
            if (cur.val == target){
                pre.next = cur.next.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
