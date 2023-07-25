package ben.Array.Sort;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CampusBike {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer, PriorityQueue<Pair>> workerBikeList = new HashMap<>();
        PairFactory factory = new PairFactory(workers, bikes);
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                Pair pair = factory.createPair(i, j);
                PriorityQueue<Pair> pq = workerBikeList.getOrDefault(i, new PriorityQueue<>((p1, p2) -> {
                    if (p1.distance == p2.distance) {
                        return p1.bikeIndex - p2.bikeIndex;
                    } else {
                        return p1.distance - p2.distance;
                    }
                }));
                pq.offer(pair);
                workerBikeList.put(i, pq);
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < workers.length; i++) {
            pq.offer(workerBikeList.get(i).poll());
        }
        int[] result = new int[workers.length];
        int assignedNumber = 0;
        boolean[] workerAssigned = new boolean[workers.length];
        boolean[] bikeAssigned = new boolean[bikes.length];
        while (assignedNumber < workers.length) {
            Pair pair = pq.poll();
            if (!workerAssigned[pair.workerIndex] && !bikeAssigned[pair.bikeIndex]) {
                workerAssigned[pair.workerIndex] = true;
                bikeAssigned[pair.bikeIndex] = true;
                result[pair.workerIndex] = pair.bikeIndex;
                assignedNumber++;
            } else {
                pq.offer(workerBikeList.get(pair.workerIndex).poll());
            }
        }
        return result;
    }

    static class PairFactory {
        int[][] workers;
        int[][] bikes;

        public PairFactory(int[][] workers, int[][] bikes) {
            this.workers = workers;
            this.bikes = bikes;
        }

        public Pair createPair(int workerIndex, int bikeIndex) {
            int[] worker = this.workers[workerIndex];
            int[] bike = this.bikes[bikeIndex];
            int manhattanDistance = calculateDistance(worker[0], worker[1], bike[0], bike[1]);
            return new Pair(workerIndex, bikeIndex, manhattanDistance);
        }

        private int calculateDistance(int workerX, int workerY, int bikeX, int bikeY) {
            return Math.abs(workerX - bikeX) + Math.abs(workerY - bikeY);
        }
    }

    static class Pair implements Comparable<Pair> {
        int workerIndex;
        int bikeIndex;
        int distance;

        public Pair(int workerIndex, int bikeIndex, int distance) {
            this.workerIndex = workerIndex;
            this.bikeIndex = bikeIndex;
            this.distance = distance;
        }

        public int compareTo(Pair another) {
            if (this.distance == another.distance) {
                return this.workerIndex - another.workerIndex;
            }
            if (this.distance == another.distance
                    && this.workerIndex == another.workerIndex) {
                return this.bikeIndex = another.bikeIndex;
            }
            return this.distance - another.distance;
        }

        public String toString() {
            return this.workerIndex + ", " + this.bikeIndex + ": " + this.distance;
        }
    }

    @Test
    public void test() {
//        new CampusBike().assignBikes(
//                new int[][]{{2,2}, {5,6}, {7,4}},
//                new int[][]{{1,1}, {1,3}, {5,4}});
//        new CampusBike().assignBikes(
//                new int[][]{{2, 2}, {5, 6}, {7, 4}, {1, 5}, {3, 6}},
//                new int[][]{{1, 1}, {1, 3}, {5, 4}, {3, 5}, {1, 6}});

        new CampusBike().mergeArray(new int[]{1,2,3,4,5,6}, 3);
    }

    // [1,2,3,4,5], index=2
    public int[] mergeArray(int[] nums, int index) {
        int[] result = new int[nums.length-1];
        int i=0;
        for(; i<index; i++) {
            result[i] = nums[i];
        }
        result[i] = nums[index] + nums[index+1];
        i++;
        for(; i<result.length; i++) {
            result[i] = nums[i+1];
        }
        return result;
    }
}
