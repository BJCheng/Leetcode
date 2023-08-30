package ben.SlidingWindow;

import org.junit.Test;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int max = 1;
        int min = 1;
        int left=0, right=0, result=Integer.MIN_VALUE, curr=1;
        while(right < nums.length) {
            if(nums[right] == 0) {
                result = Math.max(result, 0);
                curr = 1;
                left = right + 1;
                right++;
                continue;
            }
            curr *= nums[right];
            max = Math.max(max, curr);
            min = Math.min(min, curr);
            result = Math.max(Math.max(result, max), min);
            right++;
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        new MaximumProductSubarray().maxProduct(new int[]{-2,0,-1});
    }
}
