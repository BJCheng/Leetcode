package ben.BinarySearch;

import org.junit.Assert;
import org.junit.Test;

public class CheckIfANumberIsMajorityElementInASortedArray {
    public boolean isMajorityElement(int[] nums, int target) {
        int startIndex = binarySearchFindStartIndex(nums, target);
        int endIndex = binarySearchFindEndIndex(nums, target);
        return endIndex - startIndex + 1 > nums.length / 2;
    }

    // [1,1,1,1,2,2,3] (0+6)/2=3; (0+3)/2=1; (0+1)/2=0
    // [0,0,0,1,1,1,1] (0+6)/2=3;
    // [1,1,1,1,2,3] (0+5)/2=2; (0+2)/2=1; (0+1)/2=0
    // [0,0,1,1,1,1] (0+5)/2=2;
    // [0,1,1,1,3] (0+4)/2=2; (0+2)/2=1;
    // [2,4,5,5,5,5,5,6,6] (0+8)/2=4; (0+4)/2=2;
    int binarySearchFindStartIndex(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        boolean found = false;
        while(left < right) {
            int middle = (left+right) / 2;
            // update right when find target
            if(target == nums[middle]) {
                if(middle > 0 && nums[middle-1] != target) {
                    return middle;
                }
                found = true;
                right = middle;
            } else if(target < nums[middle]) {
                if(found) {
                    return middle + 1;
                }
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    // [1,1,1,1,2,2,3] (0+6)/2=3;
    // [0,0,0,1,1,1,1] (0+6)/2=3; (4+6)/2=5; (6+6)/2=6; (5+6)/2=5;
    // [1,1,1,1,2,3] (0+5)/2=2; (3+5)/2=4
    // [0,0,1,1,1,1] (0+5)/2=2; (3+5)/2=4
    // [0,1,1,1,3] (0+4)/2=2; (3+4)/2=3
    // [2,4,5,5,5,5,5,6,6] (0+8)/2=4; (5+8)/2=6;
    int binarySearchFindEndIndex(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        boolean found = false;
        while(left < right) {
            int middle = (left+right) / 2;
            // move left when find target
            if(target == nums[middle]) {;
                if(middle < nums.length && nums[middle+1] != target) {
                    return middle;
                }
                found = true;
                left = middle + 1;
            } else if(target < nums[middle]) {
                left = middle + 1;
            } else {
                if(found) {
                    return middle - 1;
                }
                right = middle;
            }
        }

        return left;
        // return nums[left] == target ? left : middle - 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(true,
                new CheckIfANumberIsMajorityElementInASortedArray().isMajorityElement(
                        new int[]{1,1,1,2,2,2,2}, 1));
    }
}
