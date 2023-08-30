package ben.SlidingWindow;

import org.junit.Test;

import java.util.Arrays;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // maxSoFar, being used for updating startIndex
        // maxEndingHere, set to 0 when is less than zero
        // startIndex, update when maxEndingHere < 0
        // when maxEndingHere > maxSoFar, update startIndex
        int[] diff = new int[gas.length];
        for(int i=0; i<gas.length; i++) {
            diff[i] = gas[i] - cost[i];
        }
        int[] a = new int[]{1};
        int b = Arrays.stream(a).sum();
        int left=0, maxSoFar=0, maxEndingHere=0, result=-1, sum=0;
        for(int right=0; right<diff.length; right++) {
            sum += diff[right];
            maxEndingHere += diff[right];
            if(maxEndingHere < 0) {
                maxEndingHere = 0;
                left = right + 1;
                result = left; // ?, because if current answer ever reached to negatvie gas, left is  next candidate result
            }
            if(maxEndingHere > maxSoFar) {
                result = left;
                maxSoFar = maxEndingHere;
            }
        }
        return sum >= 0 ? result : -1;
    }

    @Test
    public void test() {
        new GasStation().canCompleteCircuit(new int[]{2}, new int[]{2});
    }
}
