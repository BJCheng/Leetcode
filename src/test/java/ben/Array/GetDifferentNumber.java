package ben.Array;

import org.junit.Assert;
import org.junit.Test;

public class GetDifferentNumber {
    int getDifferentNumber(int[] arr) {
        // your code goes here
        for(int i=0; i<arr.length; i++) {
            int temp = arr[i];
            while(temp < arr.length && arr[i] != i) {
                arr[i] = arr[temp];
                arr[temp] = temp;
                temp = arr[i];
            }
        }
        for(int i=0; i<arr.length; i++) {
            if(i != arr[i]) {
                return i;
            }
        }
        return arr.length;
    }

    @Test
    public void test() {
//        Assert.assertEquals(5, new GetDifferentNumber().getDifferentNumber(new int[]{4,3,2,1,0}));
//        Assert.assertEquals(3, new GetDifferentNumber().getDifferentNumber(new int[]{4,5,2,1,0}));
        Assert.assertEquals(0, new GetDifferentNumber().getDifferentNumber(new int[]{10,5,2,1,9}));
    }
}
