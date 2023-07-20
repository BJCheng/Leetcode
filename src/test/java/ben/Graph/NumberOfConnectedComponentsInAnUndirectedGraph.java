package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] rank = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }

        // union nodes by all edges
        int result = n;
        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            if (parent[a] != parent[b]) {
                if(rank[parent[a]] >= rank[parent[b]]) {
                    if(parent[b] != b) {
                        parent[parent[b]] = parent[a];
                    }
                    parent[b] = parent[a]; // or a?
                    rank[parent[a]]++;
                } else {
                    if(parent[a] != a) {
                        parent[parent[a]] = parent[b];
                    }
                    parent[a] = parent[b]; // or b?
                    rank[parent[b]]++;
                }
                result--;
            }
        }

        return result;
    }

    @Test
    public void test() {
//        Assert.assertEquals(1, new NumberOfConnectedComponentsInAnUndirectedGraph().countComponents(7, new int[][]{
//                {0,1},{1,2},{2,0},{3,4},{2,3},{5,6},{4,5}
//        }));
        Assert.assertEquals(1, new NumberOfConnectedComponentsInAnUndirectedGraph().countComponents(10, new int[][]{
                {5,6},{0,2},{1,7},{5,9},{1,8},{3,4},{0,6},{0,7},{0,3},{8,9}
        }));
    }
}
