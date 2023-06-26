package ben;

import org.junit.Test;
import java.util.*;

public class PartitionKSubsetsEqualSum {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int target = Arrays.stream(nums).sum() / k;
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, 0, new ArrayList<>(), result, nums, target);
        return result.size() == k;
    }

    public void dfs(int index, int sum, List<Integer> path, List<List<Integer>> result, int[] nums, int target) {
        if(sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if(sum > target) {
            return;
        }
        if(index >= nums.length) {
            return;
        }

        // not to add curr number
        dfs(index+1, sum, path, result, nums, target);

        path.add(nums[index]);
        dfs(index+1, sum+nums[index], path, result, nums, target);
        path.remove(path.size()-1);
    }

    @Test
    public void test() {
        boolean result = new PartitionKSubsetsEqualSum().canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4);
        System.out.println(result);
    }
}
