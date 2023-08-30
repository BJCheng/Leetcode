package ben.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheTest {

    public static class LRUCache {
        int capacity;
        ListNode head;
        ListNode tail;
        Map<Integer, ListNode> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
        }

        public int get(int key) {
            if(!this.map.containsKey(key)) {
                return -1;
            }
            ListNode node = this.map.get(key);
            if(node == head) {
                return node.val;
            }
            ListNode prev = node.prev;
            ListNode next = node.next;
            if(prev != null) {
                prev.next = next;
            }
            if(next != null) {
                next.prev = prev;
            } else {
                // when capacity is 1, next and prev are both null
                this.tail = prev;
            }
            node.next = head;
            head.prev = node; // 忘記
            head = node;
            head.prev = null; // 看解答怎麼處理這一行
            return node.val;
        }

        public void put(int key, int val) {
            ListNode newNode = new ListNode(key, val);
            this.map.put(key, newNode);
            if(this.head != null) {
                this.head.prev = newNode;
            }
            newNode.next = this.head;
            this.head = newNode;
            if(this.tail == null) {
                this.tail = newNode;
            }

            if(this.map.size() > this.capacity) {
                this.map.remove(this.tail.key);
                this.tail = this.tail.prev;
//                this.tail.next = null;
            }
        }

        static class ListNode {
            int key;
            int val;
            ListNode prev;
            ListNode next;
            public ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
            public String toString() {
                return this.key+": " + this.val;
            }
        }
    }

    @Test
    public void test() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        Assert.assertEquals(1, cache.get(1));
        cache.put(3, 3);
        Assert.assertEquals(-1, cache.get(2));
        cache.put(4, 4);
        Assert.assertEquals(-1, cache.get(1));
        Assert.assertEquals(3, cache.get(3));
        Assert.assertEquals(4, cache.get(4));
    }

    @Test
    public void test1() {
        LRUCache cache = new LRUCache(1);
        cache.put(2, 1);
        Assert.assertEquals(1, cache.get(2));
        cache.put(3, 2);
        Assert.assertEquals(-1, cache.get(2));
        Assert.assertEquals(2, cache.get(3));
    }

    @Test
    public void test2() {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        Assert.assertEquals(-1, cache.get(1));
        Assert.assertEquals(3, cache.get(2));
    }
}
