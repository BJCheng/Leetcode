package ben;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Leetcode150 {
    public List<String> questions = Arrays.asList(
            "https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/",
            "https://leetcode.com/problems/insert-interval/",
            "https://leetcode.com/problems/merge-intervals/",
            "https://leetcode.com/problems/summary-ranges/",
            "https://leetcode.com/problems/longest-consecutive-sequence/",
            "https://leetcode.com/problems/contains-duplicate-ii/",
            "https://leetcode.com/problems/happy-number/",
            "https://leetcode.com/problems/two-sum/",
            "https://leetcode.com/problems/group-anagrams/",
            "https://leetcode.com/problems/valid-anagram/",
            "https://leetcode.com/problems/game-of-life/",
            "https://leetcode.com/problems/set-matrix-zeroes/",
            "https://leetcode.com/problems/rotate-image/",
            "https://leetcode.com/problems/spiral-matrix/",
            "https://leetcode.com/problems/valid-sudoku/",
            "https://leetcode.com/problems/minimum-window-substring/",
            "https://leetcode.com/problems/substring-with-concatenation-of-all-words/",
            "https://leetcode.com/problems/longest-substring-without-repeating-characters/",
            "https://leetcode.com/problems/minimum-size-subarray-sum/",
            "https://leetcode.com/problems/3sum/",
            "https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/",
            "https://leetcode.com/problems/is-subsequence/",
            "https://leetcode.com/problems/text-justification/",
            "https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/",
            "https://leetcode.com/problems/zigzag-conversion/",
            "https://leetcode.com/problems/longest-common-prefix/",
            "https://leetcode.com/problems/length-of-last-word/",
            "https://leetcode.com/problems/roman-to-integer/",
            "https://leetcode.com/problems/trapping-rain-water/",
            "https://leetcode.com/problems/candy/",
            "https://leetcode.com/problems/gas-station/",
            "https://leetcode.com/problems/insert-delete-getrandom-o1/",
            "https://leetcode.com/problems/h-index/",
            "https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/",
            "https://leetcode.com/problems/rotate-array/",
            "https://leetcode.com/problems/majority-element/",
            "https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/",
            "https://leetcode.com/problems/remove-duplicates-from-sorted-array/",
            "https://leetcode.com/problems/remove-element/",
            "https://leetcode.com/problems/merge-sorted-array/",
            "https://leetcode.com/problems/lru-cache/",
            "https://leetcode.com/problems/partition-list/",
            "https://leetcode.com/problems/rotate-list/",
            "https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/",
            "https://leetcode.com/problems/remove-nth-node-from-end-of-list/",
            "https://leetcode.com/problems/reverse-nodes-in-k-group/",
            "https://leetcode.com/problems/reverse-linked-list-ii/",
            "https://leetcode.com/problems/copy-list-with-random-pointer/",
            "https://leetcode.com/problems/merge-two-sorted-lists/",
            "https://leetcode.com/problems/add-two-numbers/",
            "https://leetcode.com/problems/linked-list-cycle/",
            "https://leetcode.com/problems/basic-calculator/",
            "https://leetcode.com/problems/evaluate-reverse-polish-notation/",
            "https://leetcode.com/problems/min-stack/",
            "https://leetcode.com/problems/simplify-path/",
            "https://leetcode.com/problems/valid-parentheses/",
            "https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/",
            "https://leetcode.com/problems/binary-tree-level-order-traversal/",
            "https://leetcode.com/problems/binary-tree-right-side-view/",
            "https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/",
            "https://leetcode.com/problems/count-complete-tree-nodes/",
            "https://leetcode.com/problems/binary-search-tree-iterator/",
            "https://leetcode.com/problems/binary-tree-maximum-path-sum/",
            "https://leetcode.com/problems/sum-root-to-leaf-numbers/",
            "https://leetcode.com/problems/path-sum/",
            "https://leetcode.com/problems/flatten-binary-tree-to-linked-list/",
            "https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/",
            "https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/",
            "https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/",
            "https://leetcode.com/problems/symmetric-tree/",
            "https://leetcode.com/problems/maximum-depth-of-binary-tree/",
            "https://leetcode.com/problems/word-search-ii/",
            "https://leetcode.com/problems/design-add-and-search-words-data-structure/",
            "https://leetcode.com/problems/implement-trie-prefix-tree/",
            "https://leetcode.com/problems/word-ladder/",
            "https://leetcode.com/problems/minimum-genetic-mutation/",
            "https://leetcode.com/problems/snakes-and-ladders/",
            "https://leetcode.com/problems/evaluate-division/",
            "https://leetcode.com/problems/clone-graph/",
            "https://leetcode.com/problems/surrounded-regions/",
            "https://leetcode.com/problems/number-of-islands/",
            "https://leetcode.com/problems/validate-binary-search-tree/",
            "https://leetcode.com/problems/kth-smallest-element-in-a-bst/",
            "https://leetcode.com/problems/minimum-absolute-difference-in-bst/",
            "https://leetcode.com/problems/find-median-from-data-stream/",
            "https://leetcode.com/problems/find-k-pairs-with-smallest-sums/",
            "https://leetcode.com/problems/ipo/",
            "https://leetcode.com/problems/kth-largest-element-in-an-array/",
            "https://leetcode.com/problems/median-of-two-sorted-arrays/",
            "https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/",
            "https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/",
            "https://leetcode.com/problems/search-in-rotated-sorted-array/",
            "https://leetcode.com/problems/find-peak-element/",
            "https://leetcode.com/problems/search-a-2d-matrix/",
            "https://leetcode.com/problems/search-insert-position/",
            "https://leetcode.com/problems/maximum-sum-circular-subarray/",
            "https://leetcode.com/problems/maximum-subarray/",
            "https://leetcode.com/problems/merge-k-sorted-lists/",
            "https://leetcode.com/problems/construct-quad-tree/",
            "https://leetcode.com/problems/sort-list/",
            "https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/",
            "https://leetcode.com/problems/word-search/",
            "https://leetcode.com/problems/generate-parentheses/",
            "https://leetcode.com/problems/n-queens-ii/",
            "https://leetcode.com/problems/combination-sum/",
            "https://leetcode.com/problems/permutations/",
            "https://leetcode.com/problems/combinations/",
            "https://leetcode.com/problems/maximal-square/",
            "https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/",
            "https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/",
            "https://leetcode.com/problems/edit-distance/",
            "https://leetcode.com/problems/interleaving-string/",
            "https://leetcode.com/problems/longest-palindromic-substring/",
            "https://leetcode.com/problems/minimum-path-sum/",
            "https://leetcode.com/problems/triangle/",
            "https://leetcode.com/problems/longest-increasing-subsequence/",
            "https://leetcode.com/problems/coin-change/",
            "https://leetcode.com/problems/word-break/",
            "https://leetcode.com/problems/house-robber/",
            "https://leetcode.com/problems/climbing-stairs/",
            "https://leetcode.com/problems/max-points-on-a-line/",
            "https://leetcode.com/problems/powx-n/",
            "https://leetcode.com/problems/sqrtx/",
            "https://leetcode.com/problems/factorial-trailing-zeroes/",
            "https://leetcode.com/problems/plus-one/",
            "https://leetcode.com/problems/palindrome-number/",
            "https://leetcode.com/problems/bitwise-and-of-numbers-range/",
            "https://leetcode.com/problems/single-number-ii/",
            "https://leetcode.com/problems/single-number/",
            "https://leetcode.com/problems/number-of-1-bits/",
            "https://leetcode.com/problems/reverse-bits/",
            "https://leetcode.com/problems/add-binary/"
    );

    public static List<String> completed = Arrays.asList(
            "https://leetcode.com/problems/course-schedule-ii/", // done
            "https://leetcode.com/problems/course-schedule/", // done
            "https://leetcode.com/problems/same-tree/", // done
            "https://leetcode.com/problems/average-of-levels-in-binary-tree/", // simple tree BFS
            "https://leetcode.com/problems/best-time-to-buy-and-sell-stock/", // two pointers
            "https://leetcode.com/problems/jump-game/", // 剛好30分鐘完成
            "https://leetcode.com/problems/integer-to-roman/", // 沒有做但是是因為覺得好像幫助不大
            "https://leetcode.com/problems/container-with-most-water/", // two pointers
            "https://leetcode.com/problems/letter-combinations-of-a-phone-number/", // 記憶還很深刻
            "https://leetcode.com/problems/word-pattern/", // 別因為題目簡單而忽略思考test case以及edge case的非常好的題目！
            "https://leetcode.com/problems/valid-palindrome/", // 跳過了，
            "https://leetcode.com/problems/reverse-words-in-a-string/", // 可能比較重要的是該如何不用stack
            "https://leetcode.com/problems/product-of-array-except-self/", // 不屬於任何演算法或data structure，單純是一個從左邊遍歷、和右邊遍歷的trick!
            "https://leetcode.com/problems/isomorphic-strings/", // 不錯的easy題，有一個edge case沒有想到
            "https://leetcode.com/problems/jump-game-ii/", // todo greedy solution
            "https://leetcode.com/problems/invert-binary-tree/", // dfs
            "https://leetcode.com/problems/ransom-note/", // hash map
            "https://leetcode.com/problems/unique-paths-ii/" // add obstacle to the grid
    );

    @Test
    public void test() {
//        for(int i=0; i<30; i++) {
//            System.out.println(Math.random()*4);
//        }
        for(int i=0; i<3; i++) {
            int randomInt = (int) Math.floor(Math.random() * (questions.size() + 1));
            String question = new Leetcode150().questions.get(randomInt);
            System.out.println(question);
        }
    }
}
