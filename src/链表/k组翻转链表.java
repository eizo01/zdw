package 链表;

public class k组翻转链表 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getGroupEnd(start,k);
        if (end == null){
            return head;
        }
        head = end;//这个就是头节点，因为下面准备翻转了，尾巴就是头
        reverse(start,end);
        //上一组的结尾节点
        ListNode listEnd = start;//start已经是1组中的尾节点

        while(listEnd != null){
            start = listEnd.next; // d
            end = getGroupEnd(start,k); // f
            if (end == null){
                return head;
            }
            reverse(start,end);
            listEnd.next = end;//上一组的next指针应该指向翻转后的end（下一组的头节点）
            listEnd = start;//用来纠正每一次的尾巴指针，指向下一组翻转链表的正确位置
        }
            return head;
    }
    public static ListNode getGroupEnd(ListNode start, int k){
        while(--k != 0 && start != null){
            start = start.next;
        }
        return start;
    }
    public static void reverse(ListNode start, ListNode end){
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != end){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }
}
