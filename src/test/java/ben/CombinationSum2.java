package ben;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(0, 0, new ArrayList<>(), result, candidates, target);
        return result;
    }

    public void dfs(int curr, int sum, List<Integer> path, List<List<Integer>> result, int[] candidates, int target) {
        if(curr >= candidates.length || sum > target) {
            return;
        }
        if(sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        // add curr number
        path.add(candidates[curr]);
        dfs(curr+1, sum+candidates[curr], path, result, candidates, target);
        path.remove(path.size()-1);

        if(sum + candidates[curr] > target) {
            return;
        }

        // not to add curr number
        while(curr < candidates.length-1 && candidates[curr] == candidates[curr+1]) {
            curr++;
        }
        dfs(curr+1, sum, path, result, candidates, target);
    }

    @Test
    public void test() {
        int[][] a = new int[3][2];
        System.out.println(a);
        List<List<Integer>> result = new CombinationSum2().combinationSum2(new int[]{1,2,5}, 5);
        System.out.println(result);
    }
}
