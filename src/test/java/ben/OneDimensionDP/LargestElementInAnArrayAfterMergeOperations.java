package ben.OneDimensionDP;

import org.junit.Assert;
import org.junit.Test;

public class LargestElementInAnArrayAfterMergeOperations {
    public long maxArrayValue(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            result = Math.max(result, dfs(i, nums));
        }
        return result;
    }

    public int dfs(int index, int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[0] + nums[1];
        }
        if (nums[index] > nums[index+1]) {
            return 0;
        }
        int[] mergedArray = mergeArray(nums, index);
        int maxResult = 0;
        for (int i = 0; i < mergedArray.length - 1; i++) {
            int result = dfs(i, mergedArray);
            maxResult = Math.max(maxResult, result);
        }
        return maxResult;
    }

    public int[] mergeArray(int[] nums, int index) {
        int[] result = new int[nums.length - 1];
        int i = 0;
        for (; i < index; i++) {
            result[i] = nums[i];
        }
        result[i] = nums[index] + nums[index + 1];
        i++;
        for (; i < result.length; i++) {
            result[i] = nums[i + 1];
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(11, new LargestElementInAnArrayAfterMergeOperations().maxArrayValue(new int[]{5, 3, 3}));
    }
}
