package ben.OneDimensionDP;

import org.junit.Test;

import java.util.Arrays;

// DP of LIS
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        int result = 1;
        for(int i=0; i<nums.length; i++) {
            result = Math.max(result, dfs(i, memo, nums));
        }
        return result;
    }

    public int dfs(int index, int[] memo, int[] nums) {
        if(memo[index] > -1) {
            return memo[index];
        }
        int result = 0;
        for(int i=index+1; i<nums.length; i++) {
            if(nums[i] <= nums[index]) {
                continue;
            }
            result = Math.max(result, dfs(i+1, memo, nums));
        }
        memo[index] = result + 1;
        return memo[index];
    }

    public int lengthOfLISDP(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;
        for(int i=1; i<nums.length; i++) {
            int maxHeight = 0;
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i]) {
                    maxHeight = Math.max(maxHeight, dp[j]);
                }
            }
            dp[i] = maxHeight + 1;
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    @Test
    public void test() {
        new LongestIncreasingSubsequence().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
    }
}
