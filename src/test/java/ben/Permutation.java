package ben;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, new ArrayList<>(), new HashSet<>(), result, nums);
        return result;
    }

    public void dfs(int curr, List<Integer> path, Set<Integer> set, List<List<Integer>> result, int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (set.contains(nums[curr])) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            path.add(nums[i]);
            set.add(nums[i]);
            dfs(i, path, set, result, nums);
            path.remove(path.size() - 1);
            set.remove(nums[i]);
        }
    }

    @Test
    public void test() {
    }
}
