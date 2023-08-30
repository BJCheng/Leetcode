package ben.TwoDimensionDP;

import org.junit.Test;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        // or create adjacent list
        boolean result = false;
        for(int i=0; i<board.length; i++) {
            for(int y=0; y<board[i].length; y++) {
               result = dfs(i, y, 0, new boolean[board.length][board[0].length], board, word);
               if(result) return true;
            }
        }
        return false;
    }

    public boolean dfs(int y, int x, int curr, boolean[][] visited, char[][] board, String word) {
        if(word.charAt(curr) != board[y][x]) {
            return false;
        }
        if(visited[y][x]) {
            return false;
        }

        if(curr == word.length()-1) {
            return true;
        }

        visited[y][x] = true;

        if(y > 0 && !visited[y-1][x]) {
            boolean result = dfs(y-1, x, curr+1, visited, board, word);
            if(result) return true;
            visited[y-1][x] = false;
        }

        if(y < board.length-1 && !visited[y+1][x]) {
            boolean result = dfs(y+1, x, curr+1, visited, board, word);
            if(result) return true;
            visited[y+1][x] = false;
        }

        if(x > 0 && !visited[y][x-1]) {
            boolean result = dfs(y, x-1, curr+1, visited, board, word);
            if(result) return true;
            visited[y][x-1] = false;
        }

        if(x < board[y].length-1 && !visited[y][x+1]) {
            boolean result = dfs(y, x+1, curr+1, visited, board, word);
            if(result) return true;
            visited[y][x+1] = false;
        }

        return false;
    }

    @Test
    public void test() {
        char[][] board = {
                {'A','A','A','A'},
                {'A','A','A','A'},
                {'A','A','A','A'}
        };
        boolean result = new WordSearch().exist(board, "AAAAAAAAAAAAA");
        System.out.println(result);
    }

    public boolean[][] cloneArray(boolean[][] ary) {
        boolean[][] clone = new boolean[ary.length][ary[0].length];
        for(int i=0; i<ary.length; i++) {
            for(int j=0; j<ary[i].length; j++) {
                clone[i][j] = ary[i][j];
            }
        }
        return clone;
    }
}
