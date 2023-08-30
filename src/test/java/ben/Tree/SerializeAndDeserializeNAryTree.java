package ben.Tree;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class SerializeAndDeserializeNAryTree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    public String serialize(Node root) {
        if(root == null) {
            return "";
        }
        Queue<Queue<Node>> all = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(root.val));
        sb.append("#");
        Queue<Node> rootChildren = new ArrayDeque<>();
        for(Node child: root.children) {
            rootChildren.offer(child);
        }
        all.offer(rootChildren);
        while(!all.isEmpty()) {
            Queue<Node> queue = all.poll();
            if(queue.isEmpty()) {
                sb.append("null");
            }
            while(!queue.isEmpty()) {
                Node node = queue.poll();
                sb.append(String.valueOf(node.val)).append(",");
                Queue<Node> newQueue = new ArrayDeque<>();
                if(node.children != null && !node.children.isEmpty()) {
                    for(Node child: node.children) {
                        newQueue.offer(child);
                    }
                }
                all.offer(newQueue);
            }
            // sb.deleteCharAt(sb.length()-1);
            sb.append("#");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
