package ben.BinarySearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CountNumberOfRectanglesContainingEachPoint {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] rec: rectangles) {
            List<Integer> list = map.getOrDefault(rec[1] , new ArrayList<>());
            list.add(rec[0]);
            map.put(rec[1] , list);
        }

        TreeMap<Integer, List<Integer>> a = new TreeMap<>(map);
        System.out.println(a.size());

        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            Collections.sort(entry.getValue());
        }
        int[] result = new int[points.length];
        for(int i=0; i<points.length; i++) {
            int[] point = points[i];
            int length = point[0];
            int height = point[1];
            int count = 0;
            for(int j=height; j<100; j++) {
                List<Integer> lengthList = map.getOrDefault(j, new ArrayList<>());
                int numberOfLess = numElementGreaterThanTarget(lengthList, length);
                count += numberOfLess;
            }
            result[i] = count;
        }
        return result;
    }

    public int numElementGreaterThanTarget(List<Integer> list, int target) {
        if(list.size() == 0) {
            return 0;
        }
        if(target > list.get(list.size()-1)) {
            return 0;
        }
        int left=0, right=list.size();
        while(left < right) {
            int mid = (left+right) / 2;
            if (target == list.get(mid)) {
                return list.size() - mid;
            } else if(target < list.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return list.size() - left;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, new CountNumberOfRectanglesContainingEachPoint().numElementGreaterThanTarget(Arrays.asList(1,2,3), 0));
        Assert.assertEquals(2, new CountNumberOfRectanglesContainingEachPoint().numElementGreaterThanTarget(Arrays.asList(1,3,5), 2));
        Assert.assertEquals(4, new CountNumberOfRectanglesContainingEachPoint().numElementGreaterThanTarget(Arrays.asList(1,2,3,4), 0));
        Assert.assertEquals(1, new CountNumberOfRectanglesContainingEachPoint().numElementGreaterThanTarget(Arrays.asList(1,3,5,7), 6));
        Assert.assertEquals(0, new CountNumberOfRectanglesContainingEachPoint().numElementGreaterThanTarget(Arrays.asList(1,2,3,4), 5));
        Assert.assertEquals(0, new CountNumberOfRectanglesContainingEachPoint().numElementGreaterThanTarget(Arrays.asList(1,3,5), 8));
        Assert.assertEquals(0, new CountNumberOfRectanglesContainingEachPoint().numElementGreaterThanTarget(Arrays.asList(1), 2));
        Assert.assertEquals(1, new CountNumberOfRectanglesContainingEachPoint().numElementGreaterThanTarget(Arrays.asList(1,2), 2));
        Assert.assertEquals(1, new CountNumberOfRectanglesContainingEachPoint().numElementGreaterThanTarget(Arrays.asList(1,2,3), 3));
        int[] result;
//        result = new CountNumberOfRectanglesContainingEachPoint().countRectangles(
//                new int[][]{{1,2}, {2,3}, {2,5}},
//                new int[][]{{2,1}, {1,4}}
//        );
//        Arrays.stream(result).forEach(System.out::print);
//        result = new CountNumberOfRectanglesContainingEachPoint().countRectangles(
//                new int[][]{{1,1}, {2,2}, {3,3}},
//                new int[][]{{1,3}, {1,1}}
//        );
//        Arrays.stream(result).forEach(System.out::print);
//        result = new CountNumberOfRectanglesContainingEachPoint().countRectangles(
//                new int[][]{{7,1}, {2,6}, {1,4}, {5,2}, {10,3}, {2,4}, {5,9}},
//                new int[][]{{10,3}, {8,10}, {2,3}, {5,4}, {8,5}, {7,10}, {6,6}, {3,6}}
//        );
//        Arrays.stream(result).forEach(System.out::print);
        result = new CountNumberOfRectanglesContainingEachPoint().countRectangles(
                new int[][]{{16,17},{7,61},{70,66},{79,47},{28,54},{54,96},{21,58},{61,3},{74,65},{66,48},{95,27},{46,53},{47,92},{56,98},{9,30},{39,37},{31,56},{97,31},{53,53},{29,20},{52,75},{57,29},{42,5},{10,100},{51,73}},
                new int[][]{{6,62},{3,37},{5,85},{6,30},{1,25}}
        );
        Arrays.stream(result).forEach(System.out::print);
    }
}
