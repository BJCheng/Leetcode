package ben.TwoDimensionDP;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LongestIncreasingPathInMatrix {
    // if the curr cell is larger than neighbor cell
    // pick the largest neighbor cell and increment the value
    // what is state? what is transition?
    // should it be the longest this cell can have
    // or should it be the longest of all cells?
    // if it is the longest this cell can have, then probably other cell will have to recursively update it
    // then it should be the longest of all cells
    // also this question's trick is to have two DP arrays
    public int longestIncreasingPath(int[][] matrix) {
        int[][] minDP = new int[matrix.length][matrix[0].length];
        int[][] maxDP = new int[matrix.length][matrix[0].length];
        for(int i=0; i<minDP.length; i++) {
            Arrays.fill(minDP[i], 1);
            Arrays.fill(maxDP[i], 1);
        }
        int finalResult = 0;
        for(int i=0; i<minDP.length; i++) {
            for(int j=0; j<minDP[i].length; j++) {
                int minResult = 0;
                int maxResult = 0;
                // up
                if(i-1 >= 0 && matrix[i][j] < matrix[i-1][j]) {
                    minResult = Math.max(minResult, minDP[i-1][j]);
                }
                if(i-1 >= 0 && matrix[i][j] > matrix[i-1][j]) {
                    maxResult = Math.max(maxResult, maxDP[i-1][j]);
                }
                // down
                if(i+1 < minDP.length && matrix[i][j] < matrix[i+1][j]) {
                    minResult = Math.max(minResult, minDP[i+1][j]);
                }
                if(i+1 < minDP.length && matrix[i][j] > matrix[i+1][j]) {
                    maxResult = Math.max(maxResult, maxDP[i+1][j]);
                }
                // left
                if(j-1 >= 0 && matrix[i][j] < matrix[i][j-1]) {
                    minResult = Math.max(minResult, minDP[i][j-1]);
                }
                if(j-1 >= 0 && matrix[i][j] > matrix[i][j-1]) {
                    maxResult = Math.max(maxResult, maxDP[i][j-1]);
                }
                // right
                if(j+1 < minDP[i].length && matrix[i][j] < matrix[i][j+1]) {
                    minResult = Math.max(minResult, minDP[i][j+1]);
                }
                if(j+1 < minDP[i].length && matrix[i][j] > matrix[i][j+1]) {
                    maxResult = Math.max(maxResult, maxDP[i][j+1]);
                }
                minDP[i][j] = minResult + 1;
                maxDP[i][j] = maxResult + 1;
                finalResult = Math.max(finalResult, Math.max(minDP[i][j], maxDP[i][j]));
            }
        }
        return finalResult;
    }

    // each cell can have four different moves
    // when curr cell is <= than last cell
    // or have visited
    // or out of bound, return
    public int longestIncreasingPathDfs(int[][] matrix) {
        int maxResult = 0;
        int[][] memo = new int[matrix.length][matrix[0].length];
        for(int i=0; i<memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                int result = dfs(i, j, -1, memo, new boolean[matrix.length][matrix[0].length], matrix);
                maxResult = Math.max(maxResult, result);
            }
        }
        return maxResult;
    }

    public int dfs(int i, int j, int lastCell, int[][] memo, boolean[][] visited, int[][] matrix) {
        if(i<0 || i>=matrix.length || j<0 || j>=matrix[0].length || visited[i][j] || matrix[i][j] <= lastCell) {
            // return currLength; backtrack can retrun current length but not DP
            // DP needs to know how long from end to this cell
            return 0;
        }

        if(memo[i][j] > -1) {
            return memo[i][j];
        }

        visited[i][j] = true;
        int resultUp = dfs(i-1, j, matrix[i][j], memo, visited, matrix);
        int resultRight = dfs(i, j+1, matrix[i][j], memo, visited, matrix);
        int resultDown = dfs(i+1, j, matrix[i][j], memo, visited, matrix);
        int resultLeft = dfs(i, j-1, matrix[i][j], memo, visited, matrix);
        visited[i][j] = false;

        memo[i][j] = Math.max(Math.max(Math.max(resultUp, resultRight), resultDown), resultLeft) + 1;

        return memo[i][j];
    }

    @Test
    public void test() {
//        Assert.assertEquals(4, new LongestIncreasingPathInMatrix().longestIncreasingPath(new int[][]{
//                {5,4,1},
//                {6,3,2},
//                {1,5,1}}));
        Assert.assertEquals(4, new LongestIncreasingPathInMatrix().longestIncreasingPath(new int[][]{
                {9,9,4},
                {6,6,8},
                {2,1,1}}));
        Assert.assertEquals(4, new LongestIncreasingPathInMatrix().longestIncreasingPath(new int[][]{
                {3,4,5},
                {3,2,6},
                {2,2,1}}));

    }
}
