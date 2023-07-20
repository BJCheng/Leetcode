package ben.Graph;

import org.junit.Assert;
import org.junit.Test;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        // to find how many 'disconnected graphs'
        // iterate through the grid, upon visit 1, dfs it till every 1 is visited.
        // keep iterating the grid, skip those have been visited.
        // count number of dfs being called.
        if(grid.length == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int result = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(visited[i][j]) {
                    continue;
                }
                result++;
                dfs(i, j, visited, grid);
            }
        }
        return result;
    }

    public void dfs(int i, int j, boolean[][] visited, char[][] grid) {
        if(i < 0 || i >= grid.length
                || j < 0 || j >=grid[i].length
                || '0' == grid[i][j]
                || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs(i-1, j, visited, grid);
        dfs(i+1, j, visited, grid);
        dfs(i, j-1, visited, grid);
        dfs(i, j+1, visited, grid);
    }

    @Test
    public void test() {
        Assert.assertEquals(3, new NumberOfIslands().numIslands(new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        }));
    }
}
