package ben.OneDimensionDP;

import org.junit.Test;

import java.util.Arrays;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, Integer.MIN_VALUE);
        int result = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++) {
            result = Math.max(result, dfs(i, memo, nums));
        }
        return result;
    }

    public int dfs(int index, int[] memo, int[] nums) {
        if(index >= nums.length) {
            return 0;
        }
        if(memo[index] > Integer.MIN_VALUE) {
            return memo[index];
        }
        int result = nums[index];
        int resultThisTime = dfs(index+1, memo, nums);
        result = Math.max(result, nums[index] + resultThisTime);
        memo[index] = result;
        return memo[index];
    }

    @Test
    public void test() {
        System.out.println(new MaximumSubarray().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
