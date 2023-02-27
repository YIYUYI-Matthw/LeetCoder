package Type02_LinkedList;

public class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode node) {
        this.val = val;
        this.next = node;
    }
}
