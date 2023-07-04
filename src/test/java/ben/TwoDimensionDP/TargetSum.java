package ben.TwoDimensionDP;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        int[][] dp = new int[sum*2+1][nums.length];
        dp[Math.abs(nums[0]-sum)][0] = 1;
        dp[nums[0]+sum][0] = 1;
        for(int j=1; j<nums.length; j++) {
            for(int i=0; i<dp.length; i++) {
                if(i-nums[j] >= 0 && i+nums[j] <= sum*2) {
                    dp[i][j] = dp[i-nums[j]][j-1] + dp[i+nums[j]][j-1];
                } else if (i+nums[j] > sum*2) {
                    dp[i][j] = dp[i-nums[j]][j-1];
                } else {
                    dp[i][j] = dp[i+nums[j]][j-1];
                }
            }
        }
        return dp[target+sum][nums.length-1];
    }

    public int findTargetSumWaysDfs(int[] nums, int target) {
        int maxBound = Arrays.stream(nums).sum();
        int minBound = Arrays.stream(nums).reduce(0, (result, num) -> result-num);
        int bound = maxBound + Math.abs(minBound) + 1;
        return dfs(0, 0, minBound, new boolean[bound][nums.length], new int[bound][nums.length], nums, target);
    }

    public int dfs(int sum, int index, int minBound, boolean[][] memoVisited, int[][] memoTarget, int[] nums, int target) {
        if(index == nums.length) {
            if(sum == target) {
                return 1;
            } else {
                return 0;
            }
        }

        if(memoVisited[sum-minBound][index]) {
            return memoTarget[sum-minBound][index];
        }

        int resultOfPlus = dfs(sum + nums[index], index+1, minBound, memoVisited, memoTarget, nums, target);
        int resultOfMinus = dfs(sum - nums[index], index+1, minBound, memoVisited, memoTarget, nums, target);

        memoVisited[sum-minBound][index] = true;
        memoTarget[sum-minBound][index] = resultOfPlus + resultOfMinus;

        return memoTarget[sum-minBound][index];
    }
    @Test
    public void test() {
//        Assert.assertEquals(0, new TargetSum().findTargetSumWays(new int[]{1,3,4}, 0));
        Assert.assertEquals(256, new TargetSum().findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1));
    }
}
