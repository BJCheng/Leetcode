package ben;

import org.junit.Test;

import java.util.*;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        dfs(0, new ArrayList<>(), result, new HashSet<>(), new HashSet<>(), new HashSet<>(), n);
        return result;
    }

    public void dfs(int rowIndex, List<String> path, List<List<String>> result, Set<Integer> colSet, Set<Integer> forwardDiagonalSet, Set<Integer> backwardDiagonalSet, int n) {
        if(rowIndex >= n) {
            result.add(new ArrayList<>(path));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            sb.append(".");
        }
        for(int col=0; col<n; col++) {
            if(colSet.contains(col) || forwardDiagonalSet.contains(rowIndex+col) || backwardDiagonalSet.contains(rowIndex-col)) {
                continue;
            }
            sb.setCharAt(col, 'Q');
            path.add(sb.toString());
            colSet.add(col);
            forwardDiagonalSet.add(rowIndex+col);
            backwardDiagonalSet.add(rowIndex-col);
            dfs(rowIndex+1, path, result, colSet, forwardDiagonalSet, backwardDiagonalSet, n);
            sb.setCharAt(col, '.');
            path.remove(path.size()-1);
            colSet.remove(col);
            forwardDiagonalSet.remove(rowIndex+col);
            backwardDiagonalSet.remove(rowIndex-col);
        }
    }

    public List<List<String>> solveNQueens1(int n) {
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                map.put(i+","+j, 0);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                dfs1(i, j, n, map, new StringBuilder(), new ArrayList<>(), result, n);
            }
        }
        return result;
    }

    public void dfs1(int rowIndex, int colIndex, int numberOfQueens, Map<String, Integer> map, StringBuilder sb, List<String> placement, List<List<String>> result, int n) {
        if(numberOfQueens <= 0) {
            if(sb.length() > 0) {
                for(int i=sb.length(); i<n; i++) {
                    sb.append(".");
                }
                placement.add(sb.toString());
            }
            result.add(new ArrayList<>(placement));
            return;
        }
        if(rowIndex == n && colIndex >= n && numberOfQueens > 0) {
            return;
        }

        for(int i=rowIndex; i<n; i++) {
            for(int j=colIndex; j<n; j++) {
                if(map.get(i+","+j) == 0) {
                    updateQueenPlacements(i, j, n, 1, map);
                    sb.append("Q");
                    // reach last column
                    if (j == n-1){
                        List<String> newPlacement = new ArrayList<>(placement);
                        newPlacement.add(sb.toString());
                        dfs1(i+1, 0, numberOfQueens-1, map, new StringBuilder(), newPlacement, result, n);
                    }
                    else {
                        dfs1(i, j+1, numberOfQueens-1, map, new StringBuilder(sb), new ArrayList<>(placement), result, n);
                    }
                    updateQueenPlacements(i, j, n, -1, map);
                    sb.deleteCharAt(sb.length()-1);
                    sb.append(".");
                } else {
                    sb.append(".");
                }
            }
            placement.add(sb.toString());
            sb = new StringBuilder();
            colIndex = 0;
        }
    }

    public void updateQueenPlacements(int rowIndex, int colIndex, int n, int num, Map<String, Integer> map) {
        // update row
        int tempColIndex = colIndex-1;
        while(tempColIndex >= 0) {
            map.put(rowIndex+","+tempColIndex, map.get(rowIndex+","+tempColIndex)+num);
            tempColIndex -= 1;
        }
        tempColIndex = colIndex+1;
        while(tempColIndex < n) {
            map.put(rowIndex+","+tempColIndex, map.get(rowIndex+","+tempColIndex)+num);
            tempColIndex += 1;
        }
        // update column
        int tempRowIndex = rowIndex-1;
        while(tempRowIndex >= 0) {
            map.put(tempRowIndex+","+colIndex, map.get(tempRowIndex+","+colIndex)+num);
            tempRowIndex -= 1;
        }
        tempRowIndex = rowIndex+1;
        while(tempRowIndex < n) {
            map.put(tempRowIndex+","+colIndex, map.get(tempRowIndex+","+colIndex)+num);
            tempRowIndex += 1;
        }
        // update diagonal
        tempRowIndex = rowIndex-1;
        tempColIndex = colIndex-1;
        while (tempRowIndex >= 0 && tempColIndex >= 0) {
            map.put(tempRowIndex+","+tempColIndex, map.get(tempRowIndex+","+tempColIndex)+num);
            tempRowIndex -= 1;
            tempColIndex -= 1;
        }
        tempRowIndex = rowIndex-1;
        tempColIndex = colIndex+1;
        while (tempRowIndex >= 0 && tempColIndex < n) {
            map.put(tempRowIndex+","+tempColIndex, map.get(tempRowIndex+","+tempColIndex)+num);
            tempRowIndex -= 1;
            tempColIndex += 1;
        }
        tempRowIndex = rowIndex+1;
        tempColIndex = colIndex-1;
        while (tempRowIndex < n && tempColIndex >= 0) {
            map.put(tempRowIndex+","+tempColIndex, map.get(tempRowIndex+","+tempColIndex)+num);
            tempRowIndex += 1;
            tempColIndex -= 1;
        }
        tempRowIndex = rowIndex+1;
        tempColIndex = colIndex+1;
        while (tempRowIndex < n && tempColIndex < n) {
            map.put(tempRowIndex+","+tempColIndex, map.get(tempRowIndex+","+tempColIndex)+num);
            tempRowIndex += 1;
            tempColIndex += 1;
        }
        // udpateRow(s, enable);
        // udpateColumn(s, enable);
        // udpateDiagonal(s, enable);
    }

    @Test
    public void test() {
        new NQueens().solveNQueens(4);
    }
}
