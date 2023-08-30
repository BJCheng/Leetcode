package ben.Tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class TreeNode {


    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return this.val + ": " + this.left + ", " + this.right;
    }

    public static TreeNode deserialize(String data) {
        // use a queue to store nodes from string
        // create new nodes a new number and put them to queue if not null
        String[] dataAry = data.split(",");
        if(data.length() == 0 && dataAry.length == 0) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(new TreeNode(Integer.valueOf(dataAry[0])));
        TreeNode result = queue.peek();
        int index = 1;
        while(!queue.isEmpty()) {
            TreeNode root = queue.poll();
            if(index < dataAry.length) {
                String val = dataAry[index];
                if(!"null".equals(val)) {
                    root.left = new TreeNode(Integer.valueOf(val));
                    queue.offer(root.left);
                }
                index++;
            }
            if(index < dataAry.length) {
                String val = dataAry[index];
                if(!"null".equals(val)) {
                    root.right = new TreeNode(Integer.valueOf(val));
                    queue.offer(root.right);
                }
                index++;
            }
        }
        String[] b = new String[]{};
        Arrays.sort(b, (s1, s2) -> s1.length() - s2.length());
        int[][] array = new int[][]{};

        // Sorting using a custom lambda comparator
        Arrays.sort(array, (a1, a2) -> a1[2]-a2[2]);
        return result;
    }
}
