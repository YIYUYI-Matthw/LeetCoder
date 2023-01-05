package LinkedList;

import java.util.HashSet;

/*
给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */
public class T142 {
    public static void main(String[] args) {
        ListNode l4 = new ListNode(3);
        ListNode l3 = new ListNode(2, l4);
        ListNode l2 = new ListNode(0, l3);
        ListNode l1 = new ListNode(-4, l2);
        l4.next = l2;
        System.out.println(new Solution_T142_douP().detectCycle(l1));
    }
}

class Solution_T142_self {
    public ListNode detectCycle(ListNode head) {
        // 先用哈希表冲一个：之前重写equals和gethash是为了自定义类student的名字、学号只有一个对应的
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head))
                return head;
            set.add(head);
            head = head.next;
        }
        return null;
    }
}

class Solution_T142_douP {
    public ListNode detectCycle(ListNode head) {
        // 快慢指针你：想到了套圈，考虑的是慢指针一定不能超过快指针，但是这个想法差了点意思
        // 快指针的速度大于慢指针就可以完成套圈
        ListNode fastP = head;
        ListNode slowP = head;
        while (fastP != null && fastP.next != null) {
            fastP = fastP.next.next;
            if (fastP == null)
                return null;
            slowP = slowP.next;
            if (fastP == slowP) {
                // 环内相遇
                ListNode soldier = head;
                while (soldier != slowP) {
                    soldier = soldier.next;
                    slowP = slowP.next;
                }
                return slowP;
            }
        }
        return null;
    }
}