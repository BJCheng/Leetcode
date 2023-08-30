package ben.OneDimensionDP;

import org.junit.Test;

import java.util.Arrays;

public class ClimbingStairs {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return dfs(n, dp);
    }

    public int dfs(int stairsLeft, int[] dp) {
        if(stairsLeft < 0) {
            return 0;
        }
        if(stairsLeft == 0) {
            return 1;
        }
        if(dp[stairsLeft] > -1) {
            return dp[stairsLeft];
        }
        int result = dfs(stairsLeft-1, dp) + dfs(stairsLeft-2, dp);
        dp[stairsLeft] = result;
        return result;
    }

    @Test
    public void test() {
        int result = new ClimbingStairs().climbStairs(4);
        System.out.println(result);
    }
}
