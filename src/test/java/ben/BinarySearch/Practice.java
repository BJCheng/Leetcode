package ben.BinarySearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practice {

    static int searchInsertIndex(int[] ary, int num) {
        int left=0, right=ary.length-1;
        while(left <= right) {
            int mid = (left+right) / 2;
//            if(left == ary.length) {
//                return left;
//            }
            Map<String, List<String>> map = new HashMap<>();
            List<List<String>> list = new ArrayList<>(map.values());
            if(num < ary[mid]) {
                right = mid - 1;
            } else if(num > ary[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
    @Test
    public void testMiddle() {
        Assert.assertEquals(3, searchInsertIndex(new int[]{1,2,4,6,7,8}, 5));
        Assert.assertEquals(3, searchInsertIndex(new int[]{1,2,4,6,7}, 5));
    }
    @Test
    public void testFirst() {
        Assert.assertEquals(0, searchInsertIndex(new int[]{1,2,4,6,7,8}, 0));
        Assert.assertEquals(0, searchInsertIndex(new int[]{1,2,4,6,7}, 0));
    }
    @Test
    public void testLast() {
        Assert.assertEquals(6, searchInsertIndex(new int[]{1,2,4,6,7,8}, 9));
        Assert.assertEquals(5, searchInsertIndex(new int[]{1,2,4,6,7}, 9));
    }
    @Test
    public void testExist() {
        Assert.assertEquals(2, searchInsertIndex(new int[]{1,2,4,6,7,8}, 4));
        Assert.assertEquals(2, searchInsertIndex(new int[]{1,2,4,6,7}, 4));
    }
}
