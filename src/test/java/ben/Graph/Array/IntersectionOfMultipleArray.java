package ben.Graph.Array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IntersectionOfMultipleArray {
    public List<Integer> intersection(int[][] nums) {

        List<Integer> ans = new ArrayList<>();

        int[] count  = new int[1001];

        for(int[] arr : nums){
            for(int i : arr){
                count[i]++;
            }
        }

        for(int i=0;i<count.length;i++){
            if(count[i]==nums.length){
                ans.add(i);
            }
        }

        return ans;
    }

    @Test
    public void test() {
        List<Integer> result = new IntersectionOfMultipleArray().intersection(new int[][]{{3, 1, 2, 4, 5},{1, 2, 3, 4},{3, 4, 5, 6}});
    }
}
