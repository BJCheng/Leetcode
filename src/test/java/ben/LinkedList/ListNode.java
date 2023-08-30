package ben.LinkedList;

public class ListNode {
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.val);
        if(this.next != null) {
            sb.append(" > ").append(this.next.val);
        }
        return sb.toString();
    }
}
