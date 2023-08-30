package ben.Backtrack;

import org.junit.Test;

import java.util.*;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> substrings = new ArrayList<>();
        dfs(0, 0, new ArrayList<>(), substrings, s);
        List<List<String>> result = new ArrayList<>();
        for(List<String> partitions: substrings) {
            boolean isAllPalindrome = true;
            for(String partition: partitions) {
                if(!isPalindrome(partition)) {
                    isAllPalindrome = false;
                    break;
                }
            }

            if(isAllPalindrome)
                result.add(partitions);
        }
        return result;
    }

    public void dfs(int start, int index, List<String> path, List<List<String>> result, String s) {
        if(index >= s.length()) {
            path.add(s.substring(start, index));
            result.add(new ArrayList<>(path));
            return;
        }

        // will fail the case for "acca", as not having ["ac, "ca"]
        // 比較，為什麼用for loop的解會通過這個test case
        if(!isPalindrome(s.substring(start, index))) {
            return;
        }

        // 選擇連續
        dfs(start, index+1, new ArrayList<>(path), result, s);

        // 選擇不連續
        if(index != 0) {
            path.add(s.substring(start, index));
            dfs(index, index+1, path, result, s);
            path.remove(path.size()-1);
        }
    }

    public boolean isPalindrome(String s) {
        int i=0, j=s.length()-1;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    @Test
    public void test() {
        List<List<String>> result = new PalindromePartitioning().partition("acca");
        System.out.println(result);
    }

    public List<List<String>> partition2(String s) {
        List<List<String>> result = new ArrayList<>();
        dfs2(0, new ArrayList<>(), result, s);
        return result;
    }

    // acca
    public void dfs2(int index, List<String> path, List<List<String>> result, String s) {
        if(index == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=index; i<s.length(); i++) {
            if(isPalindrome(s.substring(index, i+1))) {
                path.add(s.substring(index, i+1));
                dfs2(i+1, path, result, s);
                path.remove(path.size()-1);
            }
        }
    }

    @Test
    public void test2() {
        // next: debug這個case
        List<List<String>> result = new PalindromePartitioning().partition2("acca");
        System.out.println(result);
    }
}
