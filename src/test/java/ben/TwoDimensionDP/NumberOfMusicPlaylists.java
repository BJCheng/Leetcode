package ben.TwoDimensionDP;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NumberOfMusicPlaylists {
    public int numMusicPlaylists(int n, int goal, int k) {
        int[][] memo = new int[goal+1][n+1];
        for(int[] ary: memo) {
            Arrays.fill(ary, -1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=1; i<=n; i++) {
            map.put(i, 1);
        }
        int result = dfs(n, goal, k, new ArrayList<>(), 0, memo, new HashSet<>(), new ArrayDeque<>(), map, n);
        return result;
    }

    public int dfs(int n, int goal, int k, List<Integer> playlist, int song, int[][] memo, Set<Integer> set, Queue<Integer> queue, Map<Integer, Integer> map, int distinctNumber) {
        if(playlist.size() == goal) {
            if(distinctNumber == 0) {
                return 1;
            } else {
                return 0;
            }
        }

//         if(memo[playlist.size()][song] > -1) {
//             return memo[playlist.size()][song];
//         }

        int result = 0;
        for(int i=1; i<=n; i++) {
            // handle k
            if(set.contains(i)) {
                continue;
            }
            Set<Integer> copiedSet = new HashSet<>(set);
            Queue<Integer> copiedQueue = new ArrayDeque<>(queue);
            copiedSet.add(i);
            copiedQueue.offer(i);
            if(copiedQueue.size() > k) {
                int removing = copiedQueue.poll();
                copiedSet.remove(removing);
            }
            // handle all song has to be played once
            map.put(i, map.get(i)-1);
            if(map.get(i) == 0) {
                distinctNumber--;
            }
            // append song to playlist
            playlist.add(i);

            result += dfs(n, goal, k, playlist, i, memo, copiedSet, copiedQueue, map, distinctNumber)   ;

            // backtrack
            playlist.remove(playlist.size()-1);
            map.put(i, map.get(i)+1);
            if(map.get(i) > 0) {
                distinctNumber++;
            }
        }
        memo[playlist.size()][song] = result;
        return result;
    }

    @Test
    public void test() {
        new NumberOfMusicPlaylists().numMusicPlaylists(3, 7, 2);
    }
}
