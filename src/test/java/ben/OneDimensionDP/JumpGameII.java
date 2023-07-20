package ben.OneDimensionDP;

import org.junit.Test;

public class JumpGameII {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for(int i=1; i<nums.length; i++) {
            int minSteps = nums.length;
            for(int j=0; j<i; j++) {
                if(j + nums[j] >= i) {
                    minSteps = Math.min(minSteps, dp[j]) + 1;
                }
            }
            dp[i] = minSteps;
        }
        return dp[nums.length-1];
    }

    @Test
    public void test() {
        new JumpGameII().jump(new int[]{2,3,1,1,4});
    }
}
