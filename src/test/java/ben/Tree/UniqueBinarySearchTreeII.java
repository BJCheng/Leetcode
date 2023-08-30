package ben.Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueBinarySearchTreeII {
    public List<TreeNode> generateTrees(int n) {
        return createBst(1, n);
    }

    // dfs function itself is kind of hard to know whether it is ready to be added to the result list
    // by ready it means if entire tree has n nodes
    List<TreeNode> createBst(int min, int max) {
        if(min > max) {
            List<TreeNode> empty = new ArrayList<>();
            empty.add(null);
            return empty;
        }
        if(min == max) {
            return Arrays.asList(new TreeNode(min));
        }
        List<TreeNode> result = new ArrayList<>();
        for(int i=min; i<=max; i++) {
            List<TreeNode> leftTrees = createBst(min, i-1);
            for(TreeNode left: leftTrees) {
                List<TreeNode> rightTrees = createBst(i+1, max);
                for(TreeNode right: rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        new UniqueBinarySearchTreeII().generateTrees(3);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
