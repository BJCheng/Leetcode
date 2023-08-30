package ben.GoogleInterview;

import java.util.Map;

// 我不需要講講到太多實作的細節，例如left跟right pointer怎麼遍歷
// 有點類似Letter Combinations of a Phone Number
public class ReplaceString {
    // "%X%_%Y%", X:"1", Y:"2"
    // "%X%_%Y%, X:"%X%_%Y%"
//    public String replace(String input, Map<String, String> patterns) {
//        int left=0, right=0;
//        while(right < input.length()) {
//            if(input.charAt(right) == '%' && input.charAt(left) == '%') {
//                dfs(patterns, input.substring(left+1, right));
//            }
//            right++;
//        }
//    }
//
//    public void dfs(String input, Map<String, String> pattern, Set<String> visited) {
//        if(input.indexOf("%") >= 0) {
//            visited.add(input);
//            dfs(input, pattern);
//            visited.remove(input);
//        }
//
//    }
}
