package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        // BFS from each rotten orange
        // return the size of the queue to indicate how many minutes to iterate all cell where having fresh orange
        // part I missing: only need to use queue to store rotten orange only
        Queue<Cord> queue = new LinkedList<>();
        int numFreshOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Cord(i, j));
                } else if (grid[i][j] == 1) {
                    numFreshOranges++;
                }
            }
        }

        int result = 0;
        while (!queue.isEmpty()) {
            result++;
            Cord cord = queue.poll();
            if (cord.i - 1 >= 0 && grid[cord.i - 1][cord.j] == 1) {
                queue.offer(new Cord(cord.i - 1, cord.j));
                grid[cord.i - 1][cord.j] = 2;
                numFreshOranges--;
            }
            if (cord.i + 1 < grid.length && grid[cord.i + 1][cord.j] == 1) {
                queue.offer(new Cord(cord.i + 1, cord.j));
                grid[cord.i + 1][cord.j] = 2;
                numFreshOranges--;
            }
            if (cord.j - 1 >= 0 && grid[cord.i][cord.j - 1] == 1) {
                queue.offer(new Cord(cord.i, cord.j - 1));
                grid[cord.i][cord.j - 1] = 2;
                numFreshOranges--;
            }
            if (cord.j + 1 < grid[cord.i].length && grid[cord.i][cord.j + 1] == 1) {
                queue.offer(new Cord(cord.i, cord.j + 1));
                grid[cord.i][cord.j + 1] = 2;
                numFreshOranges--;
            }
        }

        return numFreshOranges == 0 ? result : -1;
    }

    public class Cord {
        int i;
        int j;

        public Cord(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(4, new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        });
    }
}
