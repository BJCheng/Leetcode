package ben.Graph;

import org.junit.Test;

import java.util.*;

public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        // no number of nodes, so when iterating adjList,
        // remember to use getOrDefault
        tickets.sort((l1, l2) -> {
            if(l1.get(0).equals(l2.get(0))) {
                return l1.get(1).compareTo(l2.get(1));
            } else {
                return l1.get(0).compareTo(l2.get(0));
            }
        });
        Map<String, List<Edge>> adjList = new HashMap<>();
        for(List<String> ticket: tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            List<Edge> list = adjList.getOrDefault(from, new ArrayList<>());
            list.add(new Edge(from, to));
            adjList.put(from, list);
        }
        List<String> path = new ArrayList<>();
        path.add("JFK");
        return dfs("JFK", path, 0, adjList, tickets.size());
    }

    public List<String> dfs(String currCity, List<String> path, int numUsedTickets, Map<String, List<Edge>> adjList, int numTickets) {
        if(numUsedTickets == numTickets) {
            return path;
        }
        List<Edge> edges = adjList.getOrDefault(currCity, new ArrayList<>());
        for(Edge edge: edges) {
            if(edge.visited) {
                continue;
            }
            edge.visited = true;
            path.add(edge.to);
            List<String> result = dfs(edge.to, path, numUsedTickets+1, adjList, numTickets);
            if(result.size() > 0) {
                return result;
            }
            edge.visited = false;
            path.remove(path.size()-1);
        }
        return new ArrayList<>();
    }

    public static class Edge{
        boolean visited;
        String from;
        String to;
        public Edge(String from, String to) {
            this.from = from;
            this.to = to;
        }
    }

    @Test
    public void test() {
        List<List<String>> input = new ArrayList<>();
        input.add(Arrays.asList("SFO","ATL"));
        input.add(Arrays.asList("JFK","SFO"));
        input.add(Arrays.asList("JFK","ATL"));
        input.add(Arrays.asList("ATL","SFO"));
        input.add(Arrays.asList("ATL","JFK"));
        input.sort((l1, l2) -> {
            if(l1.get(0).equals(l2.get(0))) {
                return l1.get(1).compareTo(l2.get(1));
            } else {
                return l1.get(0).compareTo(l2.get(0));
            }
        });
        new ReconstructItinerary().findItinerary(input);
    }
}
