package ben.LinkedList;

import org.junit.Test;

import java.util.Stack;

public class ReorderList {
    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        int n = 0;
        while(node != null) {
            stack.push(node);
            n++;
            node = node.next;
        }
        node = head;
        for(int i=0; i<=(n-1)/2; i++) {
            ListNode next = node.next;
            node.next = stack.pop();
            node = node.next;
            node.next = next;
            node = node.next;
        }
        node.next = null;
    }
    @Test
    public void test() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        new ReorderList().reorderList(node);
    }
}
