package ben.TwoDimensionDP;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CoinChange2 {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[amount+1][coins.length];
        for(int currAmount=1; currAmount<=amount+1; currAmount++) {
            int result = 0;
            for(int coinIndex=0; coinIndex<coins.length; coinIndex++) {
                int thisCoin = coins[coinIndex];
                if(currAmount == thisCoin) {
                    result += 1;
                }
                if(currAmount > thisCoin) {
                    if(dp[currAmount-thisCoin][coinIndex] > 0) {
                        result += dp[currAmount-thisCoin][coinIndex];
                    }
                }
                dp[currAmount][coinIndex] = result;
            }
        }
        return dp[amount][coins.length-1];
    }

    public int changeDfs(int amount, int[] coins) {
        try {
            int[][] memo = new int[coins.length][amount+1];
            for(int i=0; i<memo.length; i++) {
                Arrays.fill(memo[i], -1);
            }
            return dfs(0, amount, memo, amount, coins);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int dfs(int index, int remains, int[][] memo, int amount, int[] coins) throws Exception {
        if(remains == 0) {
            return 1;
        }
        if(remains < 0) {
            throw new Exception("remains less than zero");
            // return 0;
        }

        if(memo[index][remains] > -1) {
            return memo[index][remains];
        }

        int result = 0;
        for(int i=index; i<coins.length; i++) {
            if(remains - coins[i] < 0) {
                continue;
            }
            result += dfs(i, remains - coins[i], memo, amount, coins);
        }

        memo[index][remains] = result;

        return result;
    }

    @Test
    public void test() {
//        Assert.assertEquals(4, new CoinChange2().change(5, new int[]{1, 2, 5}));
        Assert.assertEquals(6, new CoinChange2().change(14, new int[]{6,4,5,3,7,1}));
    }
}
