package 链表;
//将单向链表按某值划分成左边小、中间相等、右边大的形式
public class 链表分区 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode listPartition(ListNode head,int pivot){
        ListNode sH = null;
        ListNode sT = null;
        ListNode eH = null;
        ListNode eT = null;
        ListNode mH = null;
        ListNode mT = null;
        ListNode next  = null;
        while(head != null){
            next = head.next;
            head = null;
            if (head.val < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    sT = head;
                }
            }else
            if (head.val == pivot){
                if (eH == null){
                    eH = head;
                    eT = head;
                }else{
                    eT.next = head;
                    eT = head;
                }
            }else {
                if (mH == null){
                    mH = head;
                    mT = head;
                }else{
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        if (sT != null){
            sT.next = eH;
            eT = eT == null ? sT : eT;//检查等于区域的尾巴是不是空，如果是空，那整个链表尾巴还是st
        }

        if (eT != null){//et不等于空，把指向大头区域
            eT.next = mH;
        }


        //检查三个区域的头是不是空的
        return sH != null ? sH : (eH == null ? mH : eH);
    }
}
