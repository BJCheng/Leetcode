package ben.SlidingWindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountCompleteSubarraysInAnArray {
    public int countCompleteSubarrays(int[] A) {
        Set<Integer> s = new HashSet<>();
        for (int a : A)
            s.add(a);
        int n = A.length, k = s.size(), res = 0, i = 0;
        Map<Integer, Integer> count = new HashMap<>();

        for (int j = 0; j < n; ++j) {
            if (count.getOrDefault(A[j], 0) == 0)
                k--;
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            while (k == 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0)
                    k++;
                i++;
            }
            res += i;
        }
        return res;
    }

    @Test
    public void test() {
        // next: debug這個test case
        new CountCompleteSubarraysInAnArray().countCompleteSubarrays(new int[]{1,2,1,2,1,3,1,3});
        new CountCompleteSubarraysInAnArray().countCompleteSubarrays(new int[]{1,3,1,2,2});
    }
}
