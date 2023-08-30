package ben.Interval;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        Map<Character, int[]> map = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            Character c = s.charAt(i);
            int[] interval = map.getOrDefault(c, new int[]{501, -1});
            interval[0] = Math.min(interval[0], i);
            interval[1] = Math.max(interval[1], i);
            map.put(c, interval);
        }
        List<int[]> intervals = new ArrayList<>(map.values());
        Collections.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        LinkedList<int[]> merged = new LinkedList<>();
        for(int[] interval: intervals) {
            if(!merged.isEmpty() && interval[0] < merged.getLast()[1]) {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            } else {
                merged.add(interval);
            }
        }
        return merged.stream().map((int[] ary) -> ary.length).collect(Collectors.toList());
    }

    @Test
    public void test() {
        new PartitionLabels().partitionLabels("ababcbacadefegdehijhklij");
    }
}
