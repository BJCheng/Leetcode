package ben.OneDimensionDP;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (String word : wordDict) {
                if (s.substring(i).startsWith(word)) {
                    dp[i + word.length()] = dp[i];
                }
            }
        }
        return dp[s.length()];
    }

    @Test
    public void test() {
        Assert.assertTrue(new WordBreak().wordBreak("aaaaaaa", Arrays.asList("aaaa", "aaa")));
    }
}
