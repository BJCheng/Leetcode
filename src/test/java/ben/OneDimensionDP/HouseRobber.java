package ben.OneDimensionDP;

import org.junit.Test;

import java.util.Arrays;

public class HouseRobber {
    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dfs(0, 0, memo, nums);
    }

    public int dfs(int index, int money, int[] memo, int[] nums) {
        if(index >= nums.length) {
            return money;
        }
        if(memo[index] > -1) {
            return memo[index] + money;
        }
        int thisHouse = dfs(index+2, nums[index], memo, nums);
        int nextHouse = dfs(index+1, 0, memo, nums);
        memo[index] = Math.max(thisHouse, nextHouse);
        return Math.max(thisHouse, nextHouse) + money;
    }

    @Test
    public void test() {
        int result = new HouseRobber().rob(new int[] {1,2,1,1});
        System.out.println(result);
    }
}
