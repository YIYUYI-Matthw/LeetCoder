package Type02_LinkedList;

public class T24 {
    static class ListNode {
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

        /*@Override
        public String toString() {
            StringBuilder sb = new StringBuilder(val + "-");
            while (this.next != null) {
                sb.append(this.next.val);
            }
            return sb.toString();
        }*/
    }

    public static void main(String[] args) {
        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        System.out.println(new Solution_T24_again().swapPairs(n1));
    }

}

class Solution_T24_again {
    // 这里借用T206的策略：递归
    public T24.ListNode swapPairs(T24.ListNode head) {
        if (head == null || head.next == null)
            return head;
        T24.ListNode left = swapPairs(head.next.next);
        T24.ListNode temp_header = head.next;
        head.next.next = head;
        head.next = left; // 这里应该是和206唯一的区别
        return temp_header;
    }
}


