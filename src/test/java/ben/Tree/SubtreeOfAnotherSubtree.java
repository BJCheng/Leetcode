package ben.Tree;

import org.junit.Assert;
import org.junit.Test;

public class SubtreeOfAnotherSubtree {

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
        public String toString() {
            return String.valueOf(this.val);
        }
    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null) {
            return true;
        }
        if(root == null || subRoot == null) {
            return false;
        }
        if(root.val == subRoot.val) {
            boolean leftResult = isSubtree(root.left, subRoot.left);
            boolean rightResult = isSubtree(root.right, subRoot.right);
            if(leftResult && rightResult) {
                return true;
            }
        }
        boolean leftResult = isSubtree(root.left, subRoot);
        if(leftResult) {
            return true;
        }
        boolean rightResult = isSubtree(root.right, subRoot);
        if(rightResult) {
            return true;
        }
        return false;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.right = new TreeNode(2);
        root.left.left.left.left = new TreeNode(1);
        root.left.left.left.left.left = new TreeNode(1);
        TreeNode subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);
        Assert.assertFalse(new SubtreeOfAnotherSubtree().isSubtree(root, subRoot));
    }
}
