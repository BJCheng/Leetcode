package ben.TwoDimensionDP;

import org.junit.Assert;
import org.junit.Test;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for(int i=0; i<s.length(); i++) {
            dp[i][i] = 1;
            if(i < s.length() - 1) {
                dp[i][i+1] = s.substring(i, i+1).equals(s.substring(i+1 ,i+2)) ? 2 : 0;
            }
        }
        int resultLength = 0;
        String result = "";
        for(int i=dp.length-1; i>=0; i--) {
            for(int j=i+2; j<dp.length; j++) {
                if(s.substring(i, i+1).equals(s.substring(j, j+1))
                        && dp[i+1][j-1] > 0) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                    if(j-i+1 > resultLength) {
                        resultLength = j-i+1;
                        result = s.substring(i, j+1);
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals("hkjjkh", new LongestPalindromicSubstring().longestPalindrome("cbbdhkjjkh"));
//        Assert.assertEquals("aaaa", new LongestPalindromicSubstring().longestPalindrome("aaaa"));
    }
}
