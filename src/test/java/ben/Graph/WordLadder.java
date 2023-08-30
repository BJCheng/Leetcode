package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<Edge>> adjList = new HashMap<>();
        for(int i=0; i<wordList.size(); i++) {
            for(int j=0; j<wordList.size(); j++) {
                if(i == j) {
                    continue;
                }
                if(diffByOneChar(wordList.get(i), wordList.get(j))) {
                    List<Edge> iList = adjList.getOrDefault(wordList.get(i), new ArrayList<>());
                    List<Edge> jList = adjList.getOrDefault(wordList.get(j), new ArrayList<>());
                    iList.add(new Edge(wordList.get(i), wordList.get(j), 1));
                    jList.add(new Edge(wordList.get(j), wordList.get(i), 1));
                    adjList.put(wordList.get(i), iList);
                    adjList.put(wordList.get(j), jList);
                }
            }
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        for(String word: wordList) {
            if(diffByOneChar(beginWord, word)) {
                pq.offer(new Edge(beginWord, word, 1));
            }
        }
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(visited.contains(edge.to)) {
                continue;
            }
            visited.add(edge.to);
            if(edge.to.equals(endWord)) {
                return edge.weight;
            }
            List<Edge> nextEdges = adjList.getOrDefault(edge.to, new ArrayList<>());
            for(Edge nextEdge: nextEdges) {
                pq.offer(new Edge(edge.to, nextEdge.to, edge.weight + nextEdge.weight));
            }
        }
        return 0;
    }

    boolean diffByOneChar(String s1, String s2) {
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

    static class Edge implements Comparable<Edge> {
        String from;
        String to;
        int weight;
        public Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public int compareTo(Edge another) {
            return this.weight - another.weight;
        }
        public String toString() {
            return this.from + ", " + this.to + ": " + this.weight;
        }
    }
    public int ladderLengthDfs(String beginWord, String endWord, List<String> wordList) {
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

    // aba, baa
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
    }
}
