package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> fromMap = new HashMap<>();
        Map<Integer, List<Integer>> toMap = new HashMap<>();
        for(int[] pre: prerequisites) {
            int from = pre[1];
            int to = pre[0];
            fromMap.put(to, fromMap.getOrDefault(to, 0)+1);
            List<Integer> toList = toMap.getOrDefault(from, new ArrayList<>());
            toList.add(to);
            toMap.put(from, toList);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses; i++) {
            if(!fromMap.containsKey(i)) {
                queue.offer(i);
            }
        }
        int numTookCourses = 0;
        while(!queue.isEmpty()) {
            numTookCourses++;
            Integer course = queue.poll();
            List<Integer> toCourses = toMap.get(course);
            for(Integer toCourse: toCourses) {
                fromMap.put(toCourse, fromMap.get(toCourse)-1);
                if(fromMap.get(toCourse) == 0) {
                    queue.offer(toCourse);
                }
            }
        }
        return numTookCourses == numCourses;
    }

    @Test
    public void test() {
        Assert.assertEquals(false, new CourseSchedule().canFinish(6, new int[][]{
                {1,0},{2,1},{3,2},{5,4}
        }));
    }
}
