package LinkedList;

import java.util.Iterator;

public class T206 {
    public static void main(String[] args) {
        ListNode l5 = new ListNode(5);
        ListNode l4 = new ListNode(4, l5);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(2, l3);
        ListNode l1 = new ListNode(1, l2);
        System.out.println(new Solution_T206_self().reverseList(l1));
    }
}

class Solution_T206_self {
    // M1：双指针迭代
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p1 = head.next;
        ListNode p2 = head;
        head.next = null;
        ListNode temp;
        boolean isp1 = true;
        while (p1 != null && p2 != null) {
            if (isp1) {
                temp = p2;
                p2 = p1.next;
                p1.next = temp;
                isp1 = false;
            } else {
                temp = p1;
                p1 = p2.next;
                p2.next = temp;
                isp1 = true;
            }
        }
        System.out.println();
        return isp1 ? p2 : p1;
    }
}

class Solution_T206_recursive_self {
    // M2：递归：注意，递归并不一定要return recursive()，递归可以在方法体内部使用
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode left = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return left;
    }
}