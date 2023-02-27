package Type02_LinkedList;

public class T160 {
    public static void main(String[] args) {
        // 1
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l1.next = l2;
        // 2
        ListNode l6 = new ListNode(5);
        ListNode l7 = new ListNode(6);
        ListNode l8 = new ListNode(1);
        l6.next = l7;
        l7.next = l8;
        // 3
        ListNode l3 = new ListNode(8);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l3.next = l4;
        l4.next = l5;
        // connect
        l2.next = l3;
        l8.next = l3;

        ListNode temp = new ListNode(1);
        ListNode lL = temp;
        ListNode lR = temp;
        System.out.println(new Solution_T160_half().getIntersectionNode(lL, lR).val);
    }
}

class Solution_T160_self {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 个人：倒着找：翻转链表
        ListNode oneP = new ListNode();
        oneP.next = headA;
        ListNode twoP = new ListNode();
        twoP.next = headB;
        while (oneP != null) {
            while (twoP != null) {
                if (oneP == twoP)
                    return oneP;
                twoP = twoP.next;
            }
            oneP = oneP.next;
            twoP = new ListNode();
            twoP.next = headB;
        }
        return null;
    }
}

class Solution_T160_half {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 个人：倒着找：翻转链表
        ListNode oneP = headA;
        ListNode twoP = headB;
        int[] lens = new int[2];
        while (oneP != null) {
            oneP = oneP.next;
            lens[0]++;
        }
        while (twoP != null) {
            twoP = twoP.next;
            lens[1]++;
        }
        ListNode one_P = headA;
        ListNode two_P = headB;
        int diff = lens[0] - lens[1];
        if (diff > 0)
            for (int i = 0; i < diff; i++) {
                one_P = one_P.next;
            }
        if (diff < 0)
            for (int i = 0; i < -diff; i++) {
                two_P = two_P.next;
            }
        while (one_P!=null){
            if(one_P==two_P)
                return one_P;
            one_P = one_P.next;
            two_P = two_P.next;
        }
        return null;
    }
}