public class T21 {

    public static void main(String[] args) {
        ListNode l12 = new ListNode(4); // 应该搞个-51出来，这样就只用比较val了，也不用do-while
        ListNode l11 = new ListNode(2, l12);
        ListNode l1 = new ListNode(1, l11);

        ListNode l22 = new ListNode(4);
        ListNode l21 = new ListNode(3, l22);
        ListNode l2 = new ListNode(1, l21);

        ListNode mergedList = new Solution_stand_T21().mergeTwoLists(l1, l2);
        while (mergedList != null) {
            System.out.println(mergedList.val + " ");
            mergedList = mergedList.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution_self_T21 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode mergedList = new ListNode();
        ListNode temp_pointer = mergedList;
        while (list1 != null & list2 != null) {
            if (list1.val <= list2.val) {
                temp_pointer.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                temp_pointer.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            temp_pointer = temp_pointer.next;
        }

        if (list1 != null)
            temp_pointer.next = list1;

        if (list2 != null)
            temp_pointer.next = list2;

        return mergedList.next;
    }
}

class Solution_stand_T21 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null)
            return list2;

        if (list2 == null)
            return list1;

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}