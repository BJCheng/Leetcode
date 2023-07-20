package ben.Graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {
    public static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        public String toString() {
            String result = String.valueOf(this.val) + ": ";
            for(Node node: this.neighbors) {
                result += node.val + ", ";
            }
            return result;
        }
    }

    public Node cloneGraph(Node node) {
        return dfs(node, new HashMap<>());
    }

    public Node dfs(Node node, Map<Integer, Node> visitedMap) {
        if(node == null) {
            return null;
        }

        Node clone = new Node(node.val);
        visitedMap.put(clone.val, clone);

        for(Node neighbor: node.neighbors) {
            if(visitedMap.containsKey(neighbor.val)) {
                clone.neighbors.add(visitedMap.get(neighbor.val));
            } else {
                clone.neighbors.add(dfs(neighbor, visitedMap));
            }
        }

        return clone;
    }

    @Test
    public void test() {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        one.neighbors.add(two);
        one.neighbors.add(four);
        two.neighbors.add(one);
        two.neighbors.add(three);
        three.neighbors.add(two);
        three.neighbors.add(four);
        four.neighbors.add(one);
        four.neighbors.add(three);
        new CloneGraph().cloneGraph(one);
    }
}
