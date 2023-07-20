package ben.Graph;

import org.junit.Test;

import java.util.*;

public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge: edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            List<Integer> list1 = adjList.getOrDefault(node1, new ArrayList<>());
            List<Integer> list2 = adjList.getOrDefault(node2, new ArrayList<>());
            list1.add(node2);
            list2.add(node1);
            adjList.put(node1, list1);
            boolean result = undirectedDfs(node1, 0, adjList, new HashSet<>());
            if(result) {
                return edge;
            }
        }
        return new int[]{};
    }

    public boolean undirectedDfs(int node, int prev, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        if(visited.contains(node)) {
            return true;
        }
        visited.add(node);
        List<Integer> neighbors = adjList.getOrDefault(node, new ArrayList<>());
        for(Integer neighbor: neighbors) {
            if(neighbor == prev) {
                continue;
            }
            boolean result = undirectedDfs(neighbor, node, adjList, visited);
            if(result) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        new RedundantConnection().findRedundantConnection(new int[][]{
                {1,2},{1,3},{2,3}
        });
    }
}
