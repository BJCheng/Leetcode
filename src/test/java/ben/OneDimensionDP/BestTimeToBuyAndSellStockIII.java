package ben.OneDimensionDP;

import org.junit.Assert;
import org.junit.Test;

public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        if(prices.length == 1) {
            return 0;
        }
        if(prices.length == 2) {
            return Math.max(0, prices[1] - prices[0]);
        }

        int[] dpLeft = new int[prices.length];
        dpLeft[0] = 0;
        dpLeft[1] = Math.max(0, prices[1] - prices[0]);
        dpLeft[2] = dpLeft[1];

        int result = 0;
        for(int i=3; i<prices.length; i++) {
            int maxProfit = 0;
            for(int j=0; j<i; j++) {
                int profit = Math.max(0, prices[i] - prices[j]);
                if(j > 1 && dpLeft[j-1] > 0) { // choose to do one more transaction or not
                    profit += dpLeft[j-1];
                }
                maxProfit = Math.max(maxProfit, profit);
            }
            dpLeft[i] = maxProfit;
            result = Math.max(result, dpLeft[i]);
        }
        return result;
        // int[] dpRight = new int[prices.length];
        // dpRight[dpRight.length-1] = 0;
        // dpRight[dpRight.length-2] = Math.max(0, prices[prices.length-1] - prices[prices.length-2]);
        // dpRight[dpRight.length-3] = dpRight[dpRight.length-2];
        // for(int i=dpRitght.length-4; i>=0; i--) {
        //     int maxProfit = 0;
        //     for(int j=dpRight.length-1; j>i; j--) {
        //         int profit = Math.max(0, prices[j] - prices[i]);
        //         if(j < dpRight.length-2 && dpRight[j+1] > 0) {
        //             profit += dpRight[j+1];
        //         }
        //     }
        //     dpRight[i] = maxProfit;
        // }
        // int result = 0;
        // for(int i=0; i<prices.length-1; i++) {
        //     result = Math.max(result, dpLeft[i] + dpRight[i+1]);
        // }
    }

    @Test
    public void test() {
        Assert.assertEquals(6, new BestTimeToBuyAndSellStockIII().maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }
}
