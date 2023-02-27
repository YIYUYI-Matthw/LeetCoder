package Type02_LinkedList;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
        ListNode lv2 = new ListNode(2);
        ListNode lv1 = new ListNode(1, lv2);
        lv2.next = lv1;
        System.out.println(new Solution_T142_again_m2().detectCycle(l1).val);
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

class Solution_T142_again_m1 {
    /*
    构建集合存储各节点地址，遍历一遍寻找相同的，，，由于不需要得到index，只用返回元素，所以可以用set来实现
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        ListNode tempNode = head;
        Set<ListNode> nodeSet = new LinkedHashSet<>(); // linkedhashset保证元素的添加顺序
        nodeSet.add(tempNode);
        while (tempNode.next != null) {
            tempNode = tempNode.next;
            if (nodeSet.contains(tempNode)) {
                return tempNode;
            }
            nodeSet.add(tempNode);
        }
        return null;
    }
}

class Solution_T142_again_m2 {
    /*
    快慢指针套圈，当快指针速度2、慢指针速度1时，有这么个规律：
        假设第(m+1)个节点位于环的入口，环由(n+1)个节点组成（包括第(m+1)个）：共有m+n个节点
        则快慢指针第一次相遇是在倒数第m个节点【⭐】：由此就可以得到入口点了：head->入口点；相交点->入口点 距离一样都是m
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != null && slow != null) {
            if (fast == slow) {
                // 相交了
                ListNode temp = head;
                while (temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return temp;
            }
            if (fast.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        return null;
    }
}