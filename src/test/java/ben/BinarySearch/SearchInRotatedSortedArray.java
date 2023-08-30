package ben.BinarySearch;

import org.junit.Assert;
import org.junit.Test;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left=0, right=nums.length-1, pivotIndex=-1;
        while(left <= right) {
            int middle = (left+right) / 2;
            // if middle greater than rightmost, pivot is in between middle and rightmost
            if(nums[middle] > nums[right]) {
                pivotIndex = middle;
                left = middle + 1;
            }
            // if middle less than left most, pivot is in between leftmost and middle
            else if(nums[middle] < nums[left]) {
                right = middle - 1;
                pivotIndex = right;
            }
            else {
                break;
            }
        }
        return pivotIndex;
    }

    @Test
    public void test() {
        // middle > rightmost
        Assert.assertEquals(3, new SearchInRotatedSortedArray().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        Assert.assertEquals(3, new SearchInRotatedSortedArray().search(new int[]{4, 5, 6, 7, 0, 1, 2, 3}, 0));
        // middle < leftmost
        Assert.assertEquals(2, new SearchInRotatedSortedArray().search(new int[]{4, 5, 6, 0, 1, 2, 3}, 0));
        Assert.assertEquals(2, new SearchInRotatedSortedArray().search(new int[]{4, 5, 6, 0, 1, 2}, 0));
        Assert.assertEquals(1, new SearchInRotatedSortedArray().search(new int[]{3,4,1,2}, 0));
        Assert.assertEquals(2, new SearchInRotatedSortedArray().search(new int[]{3,4,5,1,2}, 0));
        Assert.assertEquals(0, new SearchInRotatedSortedArray().search(new int[]{3,1,2}, 0));
        Assert.assertEquals(1, new SearchInRotatedSortedArray().search(new int[]{3,4,1,2}, 0));
        Assert.assertEquals(-1, new SearchInRotatedSortedArray().search(new int[]{1,2,3,4}, 0));
        Assert.assertEquals(-1, new SearchInRotatedSortedArray().search(new int[]{1,2,3}, 0));
    }

    public static int binarySearch1(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int middle = (left+right) / 2;
            if(nums[middle] == target) {
                return middle;
            } else if(nums[middle] > target) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    public static int binarySearch2(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left <= right) {
            int middle = (left+right) / 2;
            if(nums[middle] == target) {
                return middle;
            } else if(nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, binarySearch2(new int[]{1,2,3}, 1));
        Assert.assertEquals(2, binarySearch2(new int[]{1,2,3}, 3));
        Assert.assertEquals(0, binarySearch2(new int[]{1,2,3,4}, 1));
        Assert.assertEquals(3, binarySearch2(new int[]{1,2,3,4}, 4));
        Assert.assertEquals(0, binarySearch1(new int[]{1,2,3}, 1));
        Assert.assertEquals(2, binarySearch1(new int[]{1,2,3}, 3));
        Assert.assertEquals(0, binarySearch1(new int[]{1,2,3,4}, 1));
        Assert.assertEquals(3, binarySearch1(new int[]{1,2,3,4}, 4));
    }
}
