package Type02_LinkedList;

public class T19 {
}

class Solution_T19_self {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 滑动窗口
        ListNode fastP = head;
        ListNode slowP = new ListNode(-1, head);
        for (int i = 0; i < n; i++) {
            fastP = fastP.next;
        }
        while (fastP != null) {
            fastP = fastP.next;
            slowP = slowP.next;
        }
        if (slowP.next == head)
            head = head.next;
        slowP.next = slowP.next.next;
        return head;
    }
}