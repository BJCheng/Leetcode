package ben.OneDimensionDP;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class JumpGameII {

    public int jump(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dfs(0, nums, memo);
    }

    public int dfs(int index, int[] nums, int[] memo) {
        if(index >= nums.length-1) {
            return 0;
        }
        if(memo[index] > -1) {
            return memo[index];
        }
        int result = Integer.MAX_VALUE;
        int maxReachIndex = index + nums[index];
        for(int i=index+1; i<=maxReachIndex; i++) {
            result = Math.min(result, dfs(i, nums, memo) + 1);
        }
        memo[index] = result;
        return memo[index];
    }

    @Test
    public void test() {
//        Assert.assertEquals(2, new JumpGameII().jump(new int[]{2,3,1,1,4}));
        Assert.assertEquals(2, new JumpGameII().jump(new int[]{2,3,0,1,4}));
//        new JumpGameII().jump(new int[]{1,2,3,4,5,6,7,0,0,0,0,0});
    }
}
