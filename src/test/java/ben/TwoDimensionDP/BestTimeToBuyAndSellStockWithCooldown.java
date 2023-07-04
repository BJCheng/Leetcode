package ben.TwoDimensionDP;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[n-1][0] = Math.max(0-prices[n-1], 0);
        dp[n-1][1] = Math.max(prices[n-1], 0);
        dp[n-1][2] = 0;
        for(int i=n-2; i>=0; i--) {
            dp[i][0] = Math.max(dp[i+1][1]-prices[i], dp[i+1][0]);
            dp[i][1] = Math.max(dp[i+1][2]+prices[i], dp[i+1][1]);
            dp[i][2] = dp[i+1][0];
        }
        return dp[0][0];
    }

    public int maxProfitDfs(int[] prices) {
        int[][] memo = new int[prices.length][3];
        for(int i=0; i<memo.length; i++) {
            int[] ary = memo[i];
            Arrays.fill(ary, -1);
        }
        return dfs(0, 0, memo, 0, prices);
    }

    // state=0: can buy
    // state=1: can sell
    // state=2: in cooldown
    public int dfs(int index, int state, int[][] memo, int result, int[] prices) {
        if(index == prices.length) {
            return result;
        }

         if(memo[index][state] > -1) {
           return memo[index][state];
         }

        int maxProfit = 0;
        if(state % 3 == 0) {
            int profitNotBuy = dfs(index+1, 0, memo, 0, prices);
            int profitBuy = dfs(index+1, 1, memo, 0-prices[index], prices);

            maxProfit = Math.max(maxProfit, Math.max(profitNotBuy, profitBuy));
        } else if(state % 3 == 1) {
            int profitNotSell = dfs(index+1, 1, memo, 0, prices);
            int profitSell = dfs(index+1, 2, memo, 0+prices[index], prices);

            maxProfit = Math.max(maxProfit, Math.max(profitNotSell, profitSell));
        } else {
            int profitCooldown = dfs(index+1, 0, memo, 0, prices);

            maxProfit = Math.max(maxProfit, profitCooldown);
        }

        memo[index][state] = maxProfit;

        return maxProfit + result;
    }

    @Test
    public void test() {
        int result = 0;
//        result = new BestTimeToBuyAndSellStockWithCooldown().maxProfit(new int[]{5, 0, 7, 4, 6, 3, 8});
//        Assert.assertEquals(12, result);

        result = new BestTimeToBuyAndSellStockWithCooldown().maxProfit(new int[]{5,7,6,4,8,5,6,7,9,4,5,3,7,4,6,5,8});
        Assert.assertEquals(16, result);

        result = new BestTimeToBuyAndSellStockWithCooldown().maxProfit(new int[]{5,7,62,4,8,5,6,72,9,4,5,33,7,43,6,5,8});
        Assert.assertEquals(166, result);
    }
}
