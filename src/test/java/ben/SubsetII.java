package ben;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SubsetII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, new ArrayList<>(), new boolean[nums.length][10], result, nums);
        return result;
    }

    public void dfs(int curr, List<Integer> path, boolean[][] visited, List<List<Integer>> result, int[] nums) {
        if (curr >= nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (path.size() > 0 && visited[path.size() - 1][nums[curr]]) {
            return;
        }
        dfs(curr + 1, path, visited, result, nums);
        path.add(nums[curr]);
        dfs(curr + 1, path, visited, result, nums);
        visited[path.size() - 1][nums[curr]] = true;
    }

    @Test
    public void test() {
        List<List<Integer>> result = new SubsetII().subsetsWithDup(new int[] { 1, 2, 2 });
        System.out.println(result);

    }
}