package ben;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, CountAndCooldown> map = new HashMap<>();
        for(char task: tasks) {
            CountAndCooldown obj = map.getOrDefault(task, new CountAndCooldown(task, 0, 0));
            obj.count++;
            map.put(task, obj);
        }
        PriorityQueue<CountAndCooldown> pq = new PriorityQueue<>();
        for(Map.Entry<Character, CountAndCooldown> entry: map.entrySet()) {
            pq.offer(entry.getValue());
        }
        int completedTasks=0, result=0;
        while(completedTasks < tasks.length) {
            CountAndCooldown selectedTask = null;
            if(!pq.isEmpty() && pq.peek().cooldown == 0) {
                completedTasks++;
                selectedTask = pq.poll();
            }
            PriorityQueue<CountAndCooldown> temp = new PriorityQueue<>();
            while(!pq.isEmpty()) {
                CountAndCooldown obj = pq.poll();
                if(obj.cooldown > 0) {
                    obj.cooldown--;
                }
                temp.offer(obj);
            }
            pq.addAll(temp);
            if(selectedTask != null) {
                selectedTask.count--;
                selectedTask.cooldown = n;
                if(selectedTask.count > 0) {
                    pq.offer(selectedTask);
                }
            }
            result++;
        }
        List<Integer> list = new ArrayList<>();
        Collections.sort(list, (i1, i2) -> i1-i2);
        return result;
    }
    class CountAndCooldown implements Comparable<CountAndCooldown> {
        Character task;
        Integer count;
        Integer cooldown;
        public CountAndCooldown(Character task, Integer count, Integer cooldown) {
            this.task = task;
            this.count = count;
            this.cooldown = cooldown;
        }
        public int compareTo(CountAndCooldown another) {
            if(this.cooldown == another.cooldown) {
                return another.count - this.count;
            }
            return this.cooldown - another.cooldown;
        }
    }

    @Test
    public void test() {
        Queue<String> queue = new LinkedList<>();
        queue.offer(null);
//        Assert.assertEquals(8, new TaskScheduler().leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
        Assert.assertEquals(16, new TaskScheduler().leastInterval(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'}, 2));
    }

    public int leastIntervalNotWorkingGreedyByCharacterButNotCount(char[] tasks, int n) {
        int[] cooldownMap = new int[26];
        int[] countMap = new int[26];
        Arrays.fill(cooldownMap, -1);
        for(char c: tasks) {
            cooldownMap[c-'A'] = 0;
            countMap[c-'A']++;
        }
        int completedTasks = 0;
        int result = 0;
        int prevTask = -1;
        while(completedTasks < tasks.length) {
            int selectedTask = -1;
            for(int i=1; i<=26; i++) {
                int task = prevTask + i;
                task = task % 26;
                if(cooldownMap[task] == 0 && countMap[task] > 0) {
                    selectedTask = task;
                    countMap[task]--;
                    break;
                }
            }
            // reduce all tasks' cooldown by 1
            for(int i=0; i<cooldownMap.length; i++) {
                // only update tasks in the input array
                if(cooldownMap[i] > 0) {
                    cooldownMap[i]--;
                }
            }
            // not idle
            if(selectedTask != -1) {
                completedTasks++;
                cooldownMap[selectedTask] = n;
            }
            prevTask = selectedTask;
            result++;
        }
        return result;
        // time: O(n), n=number of tasks
    }
}
