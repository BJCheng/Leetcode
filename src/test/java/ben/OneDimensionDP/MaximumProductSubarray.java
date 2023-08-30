package ben.OneDimensionDP;

import org.junit.Test;

import java.util.Arrays;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int currMax = nums[0];
        int currMin = nums[0];
        int result = nums[0];
        for(int i=1; i<nums.length; i++) {
            int num = nums[i];
            // if(num == 0) {
            //     currMax = 1;
            //     currMin = 1;
            //     continue;
            // }
            int prevMax = currMax;
            currMax = Math.max(num, Math.max(currMax * num, currMin * num));
            currMin = Math.min(num, Math.min(prevMax * num, currMin * num));
            result = Math.max(result, currMax);
        }
        return result;
    }

    @Test
    public void test() {
//        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{2,3,-2,4}));
//        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{-2,-1,4,-9}));
        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{2,-5,3,1,-4,0,-10,2,8}));
    }
}
