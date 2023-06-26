package ben;

import org.junit.Test;

import java.util.Arrays;

public class DecodeWays {
    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dfs(0, memo, s);
    }

    public int dfs(int index, int[] memo, String s) {
        // constraints
        if(index >= s.length()) {
            return 1;
        }
        if(memo[index] > -1) {
            return memo[index];
        }
        // pick one digit
        String oneDigit = s.substring(index, index+1);
        int oneDigitResult = 0;
        if(!"0".equals(oneDigit)) {
            oneDigitResult = dfs(index+1, memo, s);
        }
        // pick two digits
        int twoDigitsResult = 0;
        if(index < s.length()-1) {
            String twoDigits = s.substring(index, index+2);
            if(!twoDigits.startsWith("0")
                    && !(Integer.valueOf(twoDigits) > 26)) {
                twoDigitsResult = dfs(index+2, memo, s);
            }
        }

        memo[index] = oneDigitResult + twoDigitsResult;

        return oneDigitResult + twoDigitsResult;
    }

    @Test
    public void test() {
        int result = new DecodeWays().numDecodings("11126");
        System.out.println(result);
    }
}
