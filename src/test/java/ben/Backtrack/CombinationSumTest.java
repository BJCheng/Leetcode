package ben.Backtrack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CombinationSumTest {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, result, path, candidates, target);
        return result;
    }

    public void dfs(int sum, List<List<Integer>> result, List<Integer> path, int[] candidates, int target) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(sum + target, result, path, candidates, target);
            path.remove(path.size() - 1);
        }
    }

    @Test
    void test() {
        new CombinationSumTest().combinationSum(new int[] { 2, 3, 5 }, 8);
    }
}
