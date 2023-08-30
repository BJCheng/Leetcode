package ben.Graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {
    int[][] dirs = new int[][]{new int[]{0,1}, new int[]{1,0}, new int[]{0,-1}, new int[]{-1,0}};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        for(String word: words) {
            for(int i=0; i<board.length; i++) {
                for(int j=0; j<board[i].length; j++) {
                    if(word.charAt(0) == board[i][j]) {
                        boolean exist = dfs(board, i, j, word.substring(1, word.length()), new HashSet<>());
                        if(exist) {
                            result.add(word);
                        }
                    }
                }
            }
        }
        return result;
    }

    boolean dfs(char[][] board, int i, int j, String word, Set<String> visited) {
        if(word.length() == 0) {
            return true;
        }
        visited.add(getCord(i, j));
        for(int[] dir: dirs) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            String newCord = getCord(newI, newJ);
            if(newI >= 0 && newI < board.length && newJ >= 0 && newJ < board[i].length &&
                    board[newI][newJ] == word.charAt(0) && !visited.contains(newCord)) {
                visited.add(newCord);
                boolean result = dfs(board, newI, newJ, word.substring(1, word.length()), visited);
                if(result) {
                    return true;
                }
                visited.remove(newCord);
            }
        }
        return false;
    }

    String getCord(int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append(i).append(",").append(j);
        return sb.toString();
    }

    @Test
    public void test() {
        new WordSearchII().findWords(new char[][]{
                {'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}
        }, new String[]{"oath","pea","eat","rain"});
    }
}
