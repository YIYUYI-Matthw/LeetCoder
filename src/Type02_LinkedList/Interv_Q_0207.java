package Type02_LinkedList;

/*
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 */
public class Interv_Q_0207 {
    public static void main(String[] args) {
        /*
        4,1,8,4,5]
        [5,0,1,8,4,5]
         */
        /*
        ListNode a4 = new ListNode(4);
        ListNode a3 = new ListNode(5, a4);
        ListNode a2 = new ListNode(8, a3);
        ListNode a1 = new ListNode(1, a2);
        ListNode a0 = new ListNode(4, a1);
        ListNode b2 = new ListNode(1, a2);
        ListNode b1 = new ListNode(0, b2);
        ListNode b0 = new ListNode(5, b1);
         */
        ListNode a4 = new ListNode(3);
        ListNode a3 = new ListNode(2, a4);
        System.out.println(new Solution_0207_again().getIntersectionNode(a3, a4).val);
    }
}

class Solution_0207_again {
    // 两个指针都走完全程：a-a-c-c-c-b-b-b和b-b-b-c-c-c-a-a
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        if (headA.next == null && headB.next == null)
            return headA == headB ? headA : null;
        ListNode tempA = headA;
        ListNode tempB = headB;
        // 计数
        int cnt = 2;
        while (tempA.next != null) {
            tempA = tempA.next;
            cnt++;
        }
        while (tempB.next != null) {
            tempB = tempB.next;
            cnt++;
        }
        // 相交点
        tempA = headA;
        tempB = headB;
        int temp_cnt = 1;
        while (tempA != tempB) {
            if (tempA.next != null)
                tempA = tempA.next;
            else
                tempA = headB;
            if (tempB.next != null)
                tempB = tempB.next;
            else
                tempB = headA;
            temp_cnt++;
            if (temp_cnt == cnt)
                break;
        }
        return tempA == tempB ? tempA : null;
    }
}