package ben.OneDimensionDP;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CoinChange {
    // 1. watch out for dp[i] already has smaller value
    // 2. cannot fill the dp array with -1, as we are taking the smaller value
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // for(int coin: coins) {
        //     if(coin > amount) {
        //         continue;
        //     }
        //     dp[coin] = 1;
        // }
        for(int i=1; i<=amount; i++) {
            for(int coin: coins) {
                if(i-coin >= 0) {
                    // if(dp[i-coin] == Integer.MAX_VALUE
                    //    || dp[coin] == Integer.MAX_VALUE) {
                    //     dp[i] = Integer.MAX_VALUE;
                    //     continue;
                    // }
                    int result = 1 + dp[i-coin];
                    dp[i] = Math.min(dp[i], result);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
    public int coinChangeDfs(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        int[] memo = new int[amount];
        Arrays.fill(memo, -1);
        // Arrays.sort(coins); // not needed if i==0 instead of i==index
        int result = dfs(0, 0, memo, coins, amount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public int dfs(int index, int sum, int[] memo, int[] coins, int amount) {
        if(sum > amount || sum < 0) { // sum < 0 注意看解答怎麼避免的
            return -1;
        }
        if(sum == amount) {
            return 0;
        }

        if(memo[sum] > -1) {
            return memo[sum];
        }

        int result = Integer.MAX_VALUE;
        // i=0搭配不需要sort，是我沒解出這題的關鍵一開始我一直把i設成index
        for(int i=0; i<coins.length; i++) {
            int resultThisTime = dfs(i, sum + coins[i], memo, coins, amount);
            if(resultThisTime != -1) {
                result = Math.min(result, resultThisTime);
            }
        }

        if(result != Integer.MAX_VALUE) {
            memo[sum] = result+1;
            return result+1;
        } else {
            // returning max vale, after returned, result will still be max value hence be treated as "not found answer"
            // which means this sum cannot reach to target, no need to further DFS down
            memo[sum] = Integer.MAX_VALUE;
        }

        return -1;
    }

    @Test
    public void test() {
        int result;
//        result = new CoinChange().coinChange(new int[]{1,2,5}, 11);
//        Assert.assertEquals(3, result);
//        result = new CoinChange().coinChange(new int[]{2}, 3);
//        Assert.assertEquals(-1, result);
//        result = new CoinChange().coinChange(new int[]{1}, 0);
//        Assert.assertEquals(0, result);
//
        result = new CoinChange().coinChange(new int[]{6,4,5,3,7,1}, 14);
        Assert.assertEquals(2, result);
//
//        result = new CoinChange().coinChange(new int[]{1,2147483647}, 2);
//        Assert.assertEquals(2, result);
//
//        result = new CoinChange().coinChange(new int[]{2,3,4,5}, 27);
//        Assert.assertEquals(6, result);

//        result = new CoinChange().coinChange(new int[]{411,412,413,414,415,416,417,418,419,420,421,422}, 9864);
//        Assert.assertEquals(24, result);

        result = new CoinChange().coinChange(new int[]{484,395,346,103,329}, 4259);
        Assert.assertEquals(11, result);
    }
}
