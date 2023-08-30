package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
    int[] parent;
    int[] rank;

    public int countComponents(int n, int[][] edges) {
        rank = new int[n];
        Arrays.fill(rank, 1);
        parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        for(int[] edge: edges) {
            int result = union(edge[0], edge[1]);
            n -= result;
        }
        return n;
    }

    int find(int node) {
        while(parent[node] != node) {
            node = parent[node];
        }
        return node;
    }

    int union(int n1, int n2) {
        int parent1 = find(n1);
        int parent2 = find(n2);
        if(parent1 == parent2) {
            return 0;
        }
        if(rank[parent1] > rank[parent2]) {
            rank[parent1] += rank[parent2];
            parent[parent2] = parent1;
        } else {
            rank[parent2] += rank[parent1];
            parent[parent1] = parent2;
        }
        return 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, new NumberOfConnectedComponentsInAnUndirectedGraph().countComponents(7, new int[][]{
                {0,1},{1,2},{2,0},{3,4},{2,3},{5,6},{4,5}
        }));
//        Assert.assertEquals(1, new NumberOfConnectedComponentsInAnUndirectedGraph().countComponents(10, new int[][]{
//                {5,6},{0,2},{1,7},{5,9},{1,8},{3,4},{0,6},{0,7},{0,3},{8,9}
//        }));
//        Assert.assertEquals(2, new NumberOfConnectedComponentsInAnUndirectedGraph().countComponents(5, new int[][]{
//                {0,1},{1,2},{3,4}
//        }));
    }
}
