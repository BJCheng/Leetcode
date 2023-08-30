package ben.Tree;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeMaximumPathSum {
    int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        int rootResult = dfs(root);
        return Math.max(result, rootResult);
    }

    int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);

        if(root.val < 0) {
            result = Math.max(result, Math.max(left, right));
            return root.val;
        } else {
            return Math.max(Math.max(root.val + left + right, root.val + left), root.val + right);
        }
    }

    @Test
    public void test() {
        TreeNode node = TreeNode.deserialize("1,2,3,-1,3,null,null,null,null,1,-1,-2,4,null,null,20");
        Assert.assertEquals(28, new BinaryTreeMaximumPathSum().maxPathSum(node));
    }
}
