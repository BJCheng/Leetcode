package ben.OneDimensionDP;

import org.junit.Test;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int[] memo = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            int result = dfs(nums, target, i, memo);
            if(result == 2) {
                return true;
            }
        }
        return  false;
    }

    public int dfs(int[] nums, int target, int index, int[] memo) {
        if(target < 0) {
            return 1;
        }
        if(target == 0) {
            return 2;
        }
        if(index >= nums.length) {
            return 1;
        }
        if(memo[index] == 1) {
            return 1;
        }
        for(int i=index; i<nums.length; i++) {
            int result = dfs(nums, target-nums[i], i+1, memo);
            if(result == 2) {
                return 2;
            }
        }
        memo[index] = 1;
        return 1;
    }

    @Test
    public void test() {
        new PartitionEqualSubsetSum().canPartition(new int[]{1,2,3,4,5,6,7});
    }
}
