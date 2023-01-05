package LinkedList;

/*
给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
输入：head = [1,2,6,3,4,5,6], val = 6
输出：[1,2,3,4,5]
 */

public class T203 {
    public static void main(String[] args) {
        ListNode_T203 l6 = new ListNode_T203(6, null);
        ListNode_T203 l5 = new ListNode_T203(5, l6);
        ListNode_T203 l4 = new ListNode_T203(3, l5);
        ListNode_T203 l3 = new ListNode_T203(3, l4);
        ListNode_T203 l2 = new ListNode_T203(2, l3);
        ListNode_T203 raw = new ListNode_T203(1, l2);
        ListNode_T203 res = new Solution_T203_self().removeElements(raw, 3);
    }
}

class ListNode_T203 {
    int val;
    ListNode_T203 next;

    public ListNode_T203() {
    }

    public ListNode_T203(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode_T203(int val, ListNode_T203 next) {
        this.val = val;
        this.next = next;
    }
}

class Solution_T203_self {
    public ListNode_T203 removeElements(ListNode_T203 head, int val) {
        while (head != null) {
            // 如果不设空的头节点，那就需要单独处理头节点的移除，建议：额外写一个节点.next=head
            if (head.val != val)
                break;
            head = head.next;
        }
        if (head != null) {
            ListNode_T203 p = head; // 此时的p指向的一定不是val
            ListNode_T203 sensor = head.next;
            while (sensor != null) {
                if (sensor.val == val)
                    p.next = p.next.next; // 去掉
                else
                    p = p.next; // 后移
                sensor = sensor.next; // 后面检测一个
            }
        }
        return head;
    }
}