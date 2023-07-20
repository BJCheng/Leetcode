package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Edge>> adjList = new HashMap<>();
        for(int i=1; i<=n; i++) {
            List<Edge> list = new ArrayList<>();
            adjList.put(i, list);
        }
        for(int[] time: times) {
            int from = time[0];
            int to = time[1];
            int weight = time[2];
            adjList.get(from).add(new Edge(from, to, weight));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int result = 0;
        Set<Integer> visited = new HashSet<>();
        pq.offer(new Edge(-1, k, 0));
        while(!pq.isEmpty()) {
            int timeSpentThisRound = pq.peek().weight;
            List<Edge> edgeToVisitAtTheSameTime = new ArrayList<>();
            while(!pq.isEmpty() && timeSpentThisRound == pq.peek().weight) {
                edgeToVisitAtTheSameTime.add(pq.poll());
            }
            for(Edge edge: edgeToVisitAtTheSameTime) {
                if(visited.contains(edge.to)) {
                    continue;
                }
                visited.add(edge.to);

                List<Edge> nextEdges = adjList.get(edge.to);
                for(Edge nextEdge: nextEdges) {
                    pq.offer(nextEdge);
                }
            }
            result += timeSpentThisRound;
            if(visited.size() == n) {
                return result;
            }
        }
        return visited.size() == n ? result : -1;
    }

    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }

        public String toString() {
            return this.from+">"+this.to+": "+this.weight;
        }
    }

    @Test
    public void test() {
//        Assert.assertEquals(2, new NetworkDelayTime().networkDelayTime(new int[][]{
//                {2,1,1},{2,3,1},{3,4,1}
//        }, 4, 2));
//        new NetworkDelayTime().networkDelayTime(new int[][]{
//                {1,2,1}
//        }, 2, 1);
//        new NetworkDelayTime().networkDelayTime(new int[][]{
//                {1,2,1}
//        }, 2, 2);
//        new NetworkDelayTime().networkDelayTime(new int[][]{
//                {1,4,1},{2,1,1},{2,3,5},{4,3,1}
//        }, 4, 2);
        Assert.assertEquals(2, new NetworkDelayTime().networkDelayTime(new int[][]{
                {1,2,1},{2,3,7},{1,3,4},{2,1,2}
        }, 3, 1));
//        Assert.assertEquals(1, new NetworkDelayTime().networkDelayTime(new int[][]{
//                {1,2,0},{2,3,0},{3,4,0},{1,4,1},{4,5,1}
//        }, 5, 1));
    }


    public int usingPQ(int[][] times, int n, int k) {
        Map<Integer, List<Edge>> adjList = new HashMap<>();
        for(int i=1; i<=n; i++) {
            List<Edge> list = new ArrayList<>();
            adjList.put(i, list);
        }
        for(int[] time: times) {
            int from = time[0];
            int to = time[1];
            int weight = time[2];
            adjList.get(from).add(new Edge(from, to, weight));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        pq.offer(new Edge(-1, k, 0));
        int prevWeight = -1;
        int result = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            // if(visited.contains(edge.to)) {
            //     continue;
            // }
            if(prevWeight != edge.weight) {
                result += edge.weight;
                prevWeight = edge.weight;
            }
            visited.add(edge.to);
            if(visited.size() == n) {
                return result;
            }

            List<Edge> detinations = adjList.get(edge.to);
            for(Edge dest: detinations) {
                pq.offer(dest);
            }
        }
        // -1 because of virtual edge added in the beginning
        return visited.size() == n ? result : -1;
    }

    public int usingNormalQueueToBFS(int[][] times, int n, int k) {
        Map<Integer, List<Edge>> adjList = new HashMap<>();
        for(int i=1; i<=n; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for(int[] edge: times) {
            int from = edge[0];
            int to = edge[1];
            int time = edge[2];
            adjList.get(from).add(new Edge(from, to, time));
        }
        int result = 0;
        Queue<Edge> queue = new LinkedList<>();
        Set<Integer> visitedNode = new HashSet<>();
        if(adjList.containsKey(k)) {
            visitedNode.add(k);
        }
        //although has initiate adjList with all the node, this is to prevent input has invalid node
        for(Edge edge: adjList.getOrDefault(k, new ArrayList<>())) {
            queue.offer(edge);
        }
        // process by "node"
        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            Queue<Edge> zeroTimeQueue = new LinkedList<>();
            for(int i=0; i<queueSize; i++) { // ！！！迴圈要記得不要用queue.size()!!!！！！
                Edge edge = queue.poll();
                if(visitedNode.contains(edge.to)) {
                    continue;
                }
                if(edge.weight == 0) {
                    zeroTimeQueue.add(edge);
                } else if (edge.weight == 1) {
                    visitedNode.add(edge.to);
                    List<Edge> newEdges = adjList.getOrDefault(edge.to, new ArrayList<>());
                    for(Edge newEdge: newEdges) {
                        queue.offer(newEdge);
                    }
                } else {
                    edge.weight--;
                    queue.offer(edge);
                }
            }
            // handle time = 0, process by "edge"
            while(!zeroTimeQueue.isEmpty()) {
                Edge zeroTimeEdge = zeroTimeQueue.poll();
                List<Edge> zeroTimeNeighbors = adjList.get(zeroTimeEdge.to);
                for(Edge edge: zeroTimeNeighbors) {
                    if(visitedNode.contains(edge.to)) {
                        continue;
                    }
                    if(edge.weight == 0) {
                        zeroTimeQueue.add(edge);
                        visitedNode.add(edge.to);
                    } else if (edge.weight == 1) {
                        visitedNode.add(edge.to);
                        List<Edge> newEdges = adjList.getOrDefault(edge.to, new ArrayList<>());
                        for(Edge newEdge: newEdges) {
                            queue.offer(newEdge);
                        }
                    } else {
                        edge.weight--;
                        queue.offer(edge);
                    }
                }
                if(visitedNode.size() == n) {
                    return result;
                }
            }

            result++;
            if(visitedNode.size() == n) {
                return result;
            }
        }
        return visitedNode.size() == n ? result : -1;
    }
}
