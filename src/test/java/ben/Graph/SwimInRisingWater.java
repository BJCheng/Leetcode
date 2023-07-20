package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SwimInRisingWater {
    public int swimInWater(int[][] grid) {
        // build adjList
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        pq.offer(new Node(0, 0, grid[0][0]));
        int currTime = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visited.contains(node.getKey())) {
                continue;
            }
            visited.add(node.getKey());

            if(node.weight > currTime) {
                currTime = node.weight;
            }

            if(node.i == grid.length-1 && node.j == grid[0].length-1) {
                return currTime;
            }

            if(node.i-1 >= 0) {
                pq.offer(new Node(node.i-1, node.j, grid[node.i-1][node.j]));
            }
            if(node.i+1 < grid.length) {
                pq.offer(new Node(node.i+1, node.j, grid[node.i+1][node.j]));
            }
            if(node.j-1 >= 0) {
                pq.offer(new Node(node.i, node.j-1, grid[node.i][node.j-1]));
            }
            if(node.j+1 < grid[0].length) {
                pq.offer(new Node(node.i, node.j+1, grid[node.i][node.j+1]));
            }
        }
        return -1;
    }

    public static class Node implements Comparable<Node>{
        int i;
        int j;
        int weight;
        public Node(int i, int j, int weight) {
            this.i = i;
            this.j = j;
            this.weight = weight;
        }
        public String getKey() {
            return i+","+j;
        }
        public int compareTo(Node another) {
            return this.weight - another.weight;
        }

        public String toString() {
            return this.i+","+this.j+": "+this.weight;
        }
    }

    @Test
    public void test() {
//        Assert.assertEquals(3, new SwimInRisingWater().swimInWater(new int[][]{
//                {0,2},
//                {1,3}
//        }));
        Assert.assertEquals(3, new SwimInRisingWater().swimInWater(new int[][]{
                {10,12,4,6},
                {9,11,3,5},
                {1,7,13,8},
                {2,0,15,14}
        }));
    }
}
