package ben.Graph;

import org.junit.Test;

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxResult = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int result = dfs(i, j, grid);
                maxResult = Math.max(maxResult, result);
            }
        }
        return maxResult;
    }

    public int dfs(int i, int j, int[][] grid) {
        if (i + 1 < grid.length
                || i - 1 >= 0
                || grid[i][j] == 0
                || j + 1 < grid[i].length
                || j - 1 >= 0) {
            return 0;
        }

        grid[i][j] = 0; // to replace a boolean visited map

        int result = 0;
        result += dfs(i + 1, j, grid);
        result += dfs(i - 1, j, grid);
        result += dfs(i, j + 1, grid);
        result += dfs(i, j - 1, grid);
"".split(",");
        return result + 1;
    }

    @Test
    public void test() {
        int result = new MaxAreaOfIsland().maxAreaOfIsland(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        });
    }
}
