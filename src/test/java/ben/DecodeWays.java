package ben;

import org.junit.Test;

import java.util.Arrays;

public class DecodeWays {
    public int numDecodings(String s) {
        // 11126
        // single digit
        // if digit itself is valid, then use num from previous index
        // if digit itself is invalid, set num to 0
        // two digits
        // if combination itself is valid, then use num from previous two index
        // if combination itself is invalid, set num to 0
        if(s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length()];
        if("0".equals(s.substring(0, 1))) {
            return 0;
        } else {
            dp[0] = 1;
        }

        if(s.length() == 1) {
            return dp[0];
        }

        if("0".equals(s.substring(1, 2))) {
            if(Integer.valueOf(s.substring(0, 2)) > 26) {
                return 0;
            } else {
                dp[1] = 1;
            }
        } else {
            if(Integer.valueOf(s.substring(0, 2)) > 26) {
                dp[1] = 1;
            } else {
                dp[1] = 2;
            }
        }

        for(int i=2; i<s.length(); i++) {
            if("0".equals(s.substring(i, i+1))) {
                if(Integer.valueOf(s.substring(i-1, i+1)) > 26
                        || "0".equals(s.substring(i-1, i))) { // 沒想到#2
                    return 0;
                } else {
                    dp[i] = dp[i-2];
                }
            } else {
                if(Integer.valueOf(s.substring(i-1, i+1)) > 26
                        || "0".equals(s.substring(i-1, i))) { // 沒想到
                    dp[i] = dp[i-1];
                } else {
                    dp[i] = dp[i-1] + dp[i-2];
                }
            }
        }
        return dp[s.length()-1];
    }

    public int numDecodingsDfs(String s) {
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
        int result = new DecodeWays().numDecodings("1110");
        System.out.println(result);
    }
}
