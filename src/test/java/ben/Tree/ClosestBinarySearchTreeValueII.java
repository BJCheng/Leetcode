package ben.Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClosestBinarySearchTreeValueII {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        List<Integer> list = new ArrayList<>();
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
        while(!stack.isEmpty()) {
            node = stack.pop();
            list.add(node.val);
            node = node.right;
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        int left=0, right=list.size()-1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(list.get(mid) == target) {
                left = mid;
                break;
            } else if(target < list.get(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        List<Integer> result = new ArrayList<>();
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for(int i=left; i>=0; i--) {
            // for the case of target is greater than list's max
            if(i == list.size()) {
                leftList.add(list.get(list.size()-1));
                break;
            }
            leftList.add(list.get(i));
        }
        for(int i=left+1; i<list.size(); i++) {
            rightList.add(list.get(i));
        }
        int leftIndex=0, rightIndex=0;
        for(int i=0; i<k; i++) {
            if(leftIndex < leftList.size() && rightIndex < rightList.size()) {
                if(Math.abs(leftList.get(leftIndex) - target) < Math.abs(rightList.get(rightIndex) - target)) {
                    result.add(leftList.get(leftIndex));
                    leftIndex++;
                } else {
                    result.add(rightList.get(rightIndex));
                    rightIndex++;
                }
            } else if(leftIndex < leftList.size()) {
                result.add(leftList.get(leftIndex));
                leftIndex++;
            } else {
                result.add(rightList.get(rightIndex));
                rightIndex++;
            }
        }
        return result;
    }

    @Test
    public void test() {
        // 42513
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(5);
        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);
        new ClosestBinarySearchTreeValueII().closestKValues(node, 3.714286, 2);
    }

    @Test
    public void test1() {
        TreeNode node = new TreeNode(1);
        new ClosestBinarySearchTreeValueII().closestKValues(node, 0, 1);
    }

    @Test
    public void test2() {
        TreeNode node = new TreeNode(1);
        new ClosestBinarySearchTreeValueII().closestKValues(node, 3.714286, 1);
    }

    @Test
    public void test3() {
        // 42513
        TreeNode node = new TreeNode(9);
        node.left = new TreeNode(7);
        node.right = new TreeNode(10);
        node.left.left = new TreeNode(6);
        node.left.right = new TreeNode(8);
        new ClosestBinarySearchTreeValueII().closestKValues(node, 11, 3);
    }
}
