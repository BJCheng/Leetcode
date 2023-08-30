package ben.TwoDimensionDP;

import org.junit.Assert;
import org.junit.Test;

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int[] newNums = new int[nums.length+2];
        newNums[0] = 1;
        newNums[newNums.length-1] = 1;
        for(int i=0; i<nums.length; i++) {
            newNums[i+1] = nums[i];
        }
        int[][] memo = new int[newNums.length][newNums.length];
        return dfs(1, newNums.length-2, memo, newNums);
    }

    public int dfs(int left, int right, int[][] memo, int[] nums) {
        if(left > right) {
            return 0;
        }
        if(memo[left][right] > 0) {
            return memo[left][right];
        }

        int result = 0;
        for(int i=left; i<=right; i++) {
            int coinsGained = nums[left-1] * nums[i] * nums[right+1];
            int leftResult = dfs(left, i-1, memo, nums);
            int rightResult = dfs(i+1, right, memo, nums);
            int resultBurstThisBalloonLast =
                    coinsGained + leftResult + rightResult;
            result = Math.max(result, resultBurstThisBalloonLast);
        }

        memo[left][right] = result;
        return memo[left][right];
    }

    @Test
    public void test() {
        Assert.assertEquals(0, new BurstBalloons().maxCoins(new int[]{2,3,5}));
    }
}
