package ben;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, new ArrayList<>(), result, nums);
        return result;
    }

    public void dfs(int curr, List<Integer> path, List<List<Integer>> result, int[] nums) {

        result.add(new ArrayList<>(path));
        for(int i=curr; i<nums.length; i++) {
            path.add(nums[i]);
            dfs(i+1, path, result, nums);
            path.remove(path.size()-1);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> result = new Subset().subsets(new int[]{1,2,3});
        System.out.println(result);
    }
}
