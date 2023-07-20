package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MinCostToConnectAllPoints {
    public int minCostConnectPoints(int[][] points) {
        // build adjList時，用points的index當作key、value為一個二元陣列，第一個存cost，第二個存向的node
        // minimum heap只用來存現在這個node所擁有的edges，也就是說minimum heap會儲存所有node的所有edges
        // 我想這達成的效果是，如果曾經用過a指向b，但是當b指向c的weight>a指向c的話，那就會選擇a指向c而不是b指向c
        // 記得visited先加上起始的node
        Map<Integer, List<WeightedEdge>> adjList = new HashMap<>();
        for(int i=0; i<points.length; i++) {
            for(int j=0; j<points.length; j++) {
                if(i == j) {
                    continue;
                }
                List<WeightedEdge> list = adjList.getOrDefault(i, new ArrayList<>());
                list.add(new WeightedEdge(Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]), j));
                adjList.put(i, list);
            }
        }
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
        pq.offer(new WeightedEdge(0, 0));
        int result = 0;
        // 因為一定可以把所有點連起來，所以可以用visited的size當作while的條件而不是pq的size
        while(visited.size() < points.length) {
            WeightedEdge edge = pq.poll();
            if(visited.contains(edge.to)) {
                continue;
            }
            visited.add(edge.to);

            result += edge.weight;

            List<WeightedEdge> nextEdges = adjList.getOrDefault(edge.to, new ArrayList<>());
            for(WeightedEdge nextEdge: nextEdges) {
                pq.offer(nextEdge);
            }
        }
        return result;
    }

    public int minCostConnectPointsOld(int[][] points) {
        // build adjList時，用points的index當作key、value為一個二元陣列，第一個存cost，第二個存向的node
        // minimum heap只用來存現在這個node所擁有的edges，也就是說minimum heap會儲存所有node的所有edges
        // 我想這達成的效果是，如果曾經用過a指向b，但是當b指向c的weight>a指向c的話，那就會選擇a指向c而不是b指向c
        // 記得visited先加上起始的node
        Map<Integer, List<WeightedEdge>> adjList = new HashMap<>();
        for(int i=0; i<points.length; i++) {
            for(int j=0; j<points.length; j++) {
                if(i == j) {
                    continue;
                }
                List<WeightedEdge> list = adjList.getOrDefault(i, new ArrayList<>());
                list.add(new WeightedEdge(Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]), j));
                adjList.put(i, list);
            }
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        int currIndex = 0;
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
        int result = 0;
        while(visited.size() < points.length) {
            // get edges
            List<WeightedEdge> edges = adjList.getOrDefault(currIndex, new ArrayList<>()); // 或著是先針對所有node initiate adjList
            // skip visited and put edges to queue
            for(WeightedEdge edge: edges) {
                pq.offer(edge);
            }
            // visit next lightest edge
            while(visited.contains(pq.peek().to)) {
                pq.poll();
            }
            WeightedEdge lightestEdge = pq.poll();
            result += lightestEdge.weight;
            currIndex = lightestEdge.to;
            visited.add(currIndex);
        }
        List<String> list = new ArrayList<>();
        return result;
    }

    public static class WeightedEdge implements Comparable<WeightedEdge> {
        public int weight;
        public int to;
        public WeightedEdge(int weight, int to) {
            this.weight = weight;
            this.to = to;
        }
        @Override
        public int compareTo(WeightedEdge another) {
            return Integer.compare(this.weight, another.weight);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(20, new MinCostToConnectAllPoints().minCostConnectPoints(new int[][]{
                {0,0},{2,2},{3,10},{5,2},{7,0}
        }));
        Assert.assertEquals(4, new MinCostToConnectAllPoints().minCostConnectPoints(new int[][]{
                {0,0},{1,1},{1,0},{-1,1}
        }));
    }
}
