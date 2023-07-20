package ben.Graph;

import org.junit.Test;

import java.util.*;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> degree = new HashMap<>();
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int i=0; i<numCourses; i++) {
            degree.put(i, 0);
            adjList.put(i, new ArrayList<>());
        }
        for(int[] pre: prerequisites) {
            int from = pre[1];
            int to = pre[0];
            degree.put(to, degree.getOrDefault(to, 0) + 1);
            adjList.get(from).add(to);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses; i++) {
            if(degree.get(i) == 0) {
                queue.offer(i);
            }
        }
        int[] result = new int[numCourses];
        int index = 0;
        while(!queue.isEmpty()) {
            Integer course = queue.poll();
            result[index] = course;
            index++;
            for(Integer nextCourse: adjList.get(course)) {
                degree.put(nextCourse, degree.get(nextCourse)-1);
                if(degree.get(nextCourse) == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        return index == numCourses-1 ? result : new int[]{};
    }

    @Test
    public void test() {
        int[] result = new CourseScheduleII().findOrder(2, new int[][]{{1,0}});
    }
}
