package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int[] memo = new int[wordList.size()];
        Arrays.fill(memo, -1);
        int minResult = Integer.MAX_VALUE;
        for(int i=0; i<wordList.size(); i++) {
            if(diffByOne(beginWord, wordList.get(i))) {
                boolean[] visited = new boolean[wordList.size()];
                visited[i] = true;
                int result = dfs(i, memo, visited, endWord, wordList);
                if(result != 0) {
                    minResult = Math.min(minResult, result);
                }
            }
        }
        return minResult == Integer.MAX_VALUE ? 0 : minResult;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int dfs(int index, int[] memo, boolean[] visited, String endWord, List<String> wordList) {
        if(index >= wordList.size()) {
            return 0;
        }
        if(endWord.equals(wordList.get(index))) {
            return 1;
        }
        if(memo[index] > -1) {
            return memo[index];
        }

        int minResult = Integer.MAX_VALUE;
        for(int i=0; i< wordList.size(); i++) {
            if(visited[i]) {
                continue;
            }
            if(diffByOne(wordList.get(index), wordList.get(i))) {
                visited[i] = true;
                int result = dfs(i, memo, visited, endWord, wordList);
                if(result != 0) {
                    minResult = Math.min(minResult, result);
                }
                visited[i] = false;
            }
        }
        if(minResult != Integer.MAX_VALUE) {
            memo[index] = minResult+1;
        } else {
            memo[index] = 0;
        }
        return memo[index];
    }

    public boolean diffByOne(String s1, String s2) {
        boolean hadDiff = false;
        for(int i=0; i<s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i) && hadDiff) {
                return false;
            } else if(s1.charAt(i) != s2.charAt(i)) {
                hadDiff = true;
            }
        }
        return hadDiff;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, new WordLadder().ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
        PriorityQueue<String> pq = new PriorityQueue<>();
        Map<Integer, List<int[]>> map = new HashMap<>();
        map.put(0, new ArrayList<>());
        int i = map.get(0).get(0)[0];
    }
}
