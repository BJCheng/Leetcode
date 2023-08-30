package ben.TwoDimensionDP;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        dfs(0, new ArrayList<>(), new boolean[nums.length+1][10], result, nums);
        return result;
    }

    public void dfs(int curr, List<Integer> path, boolean[][] pathMemo, List<List<Integer>> result, int[] nums) {
        if(curr >= nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        // have put this number to this position in the path
        // all the combination will be the same
        if(pathMemo[curr][nums[curr]]) {
            return;
        }


        dfs(curr+1, path, pathMemo, result ,nums);

        path.add(nums[curr]);
        dfs(curr+1, path, pathMemo, result, nums);
        pathMemo[curr][nums[curr]] = true;
        path.remove(path.size()-1);
    }

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> path, int [] nums, int curr){
        result.add(new ArrayList<>(path));
        for(int i = curr; i < nums.length; i++){
            if(i > curr && nums[i] == nums[i-1])
                continue; // skip duplicates
            path.add(nums[i]);
            backtrack(result, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> result = new SubsetII().subsetsWithDup2(new int[] { 1, 2, 2 });
        System.out.println(result);

    }
}