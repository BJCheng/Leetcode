package ben.Graph.Array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        if(nums.length < 2) {
            return;
        }
        Node node = new Node(nums[0]);
        Node head = node;
        Node tail = new Node(0);
        for(int i=1; i<nums.length; i++) {
            Node newNode = new Node(nums[i]);
            newNode.prev = node;
            node.next = newNode;
            node = newNode;
            tail = node;
        }
        for(int i=0; i<k; i++) {
            Node temp = tail;
            tail.next = head;
            head.prev = tail;
            head = tail;
            tail = temp.prev;
        }
        for(int i=0; i<nums.length; i++) {
            nums[i] = head.val;
            head = head.next;
        }
    }

    public static class Node{
        int val;
        Node prev;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1,5,7,9,13,20,22);
        int result = Collections.binarySearch(list, 7);
        System.out.println(result);
//        new RotateArray().rotate(new int[]{1,2}, 3);
    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid; // Found the target at index mid
            } else if (array[mid] < target) {
                left = mid + 1; // Target is in the right half
            } else {
                right = mid; // Target is in the left half
            }
        }

        return left; // Target not found, return the insertion index
    }

    @Test
    public void test2() {
        int result = binarySearch(new int[]{0,1,9,11,13,19,20}, 18);
        System.out.println(result);
    }
}
