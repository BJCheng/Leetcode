package ben.Array;

import org.junit.Assert;
import org.junit.Test;

public class NumberOfSubsequenceSumToZero {
    int countSubSeq(int i, int sum, int cnt, int a[], int n) {

        // Base case
        if (i == n) {

            // Check if the sum is 0
            // and at least a single element
            // is in the sub-sequence
            if (sum == 0 && cnt > 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int ans = 0;

        // Do not take the number in
        // the current sub-sequence
        ans += countSubSeq(i + 1, sum, cnt, a, n);

        // Take the number in the
        // current sub-sequence
        ans += countSubSeq(i + 1, sum + a[i],
                cnt + 1, a, n);

        return ans;
    }

    @Test
    public void test() {
//        Assert.assertEquals(4, new NumberOfSubsequenceSumToZero().countSubSeq());
    }
}
