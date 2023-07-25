package ben;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Premium100 {
    public List<String> questions = Arrays.asList(
            "https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters",
            "https://leetcode.com/problems/max-consecutive-ones-ii",
            "https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters",
            "https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters",
            "https://leetcode.com/problems/shortest-way-to-form-string",
            "https://leetcode.com/problems/reverse-words-in-a-string-ii",
            "https://leetcode.com/problems/one-edit-distance",
            "https://leetcode.com/problems/perform-string-shifts",
            "https://leetcode.com/problems/confusing-number",
            "https://leetcode.com/problems/wiggle-sort",
            "https://leetcode.com/problems/maximum-distance-in-arrays",
            "https://leetcode.com/problems/basic-calculator-iii",
            "https://leetcode.com/problems/find-permutation",
            "https://leetcode.com/problems/ternary-expression-parser",
            "https://leetcode.com/problems/remove-interval",
            "https://leetcode.com/problems/add-bold-tag-in-string",
            "https://leetcode.com/problems/meeting-rooms-ii",
            "https://leetcode.com/problems/meeting-rooms",
            "https://leetcode.com/problems/missing-ranges",
            "https://leetcode.com/problems/candy-crush",
            "https://leetcode.com/problems/sparse-matrix-multiplication",
            "https://leetcode.com/problems/lonely-pixel-i",
            "https://leetcode.com/problems/valid-word-square",
            "https://leetcode.com/problems/find-smallest-common-element-in-all-rows",
            "https://leetcode.com/problems/counting-elements",
            "https://leetcode.com/problems/largest-unique-number",
            "https://leetcode.com/problems/group-shifted-strings",
            "https://leetcode.com/problems/single-row-keyboard",
            "https://leetcode.com/problems/sentence-similarity",
            "https://leetcode.com/problems/palindrome-permutation",
            "https://leetcode.com/problems/find-anagram-mappings",
            "https://leetcode.com/problems/diameter-of-n-ary-tree",
            "https://leetcode.com/problems/find-root-of-n-ary-tree",
            "https://leetcode.com/problems/clone-n-ary-tree",
            "https://leetcode.com/problems/largest-bst-subtree",
            "https://leetcode.com/problems/two-sum-bsts",
            "https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree",
            "https://leetcode.com/problems/closest-binary-search-tree-value-ii",
            "https://leetcode.com/problems/closest-binary-search-tree-value",
            "https://leetcode.com/problems/binary-tree-vertical-order-traversal",
            "https://leetcode.com/problems/find-leaves-of-binary-tree",
            "https://leetcode.com/problems/boundary-of-binary-tree",
            "https://leetcode.com/problems/maximum-average-subtree",
            "https://leetcode.com/problems/count-univalue-subtrees",
            "https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii",
            "https://leetcode.com/problems/binary-tree-longest-consecutive-sequence",
            "https://leetcode.com/problems/print-immutable-linked-list-in-reverse",
            "https://leetcode.com/problems/plus-one-linked-list",
            "https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list",
            "https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list",
            "https://leetcode.com/problems/first-unique-number",
            "https://leetcode.com/problems/moving-average-from-data-stream",
            "https://leetcode.com/problems/maximum-average-subarray-ii",
            "https://leetcode.com/problems/divide-chocolate",
            "https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array",
            "https://leetcode.com/problems/find-the-index-of-the-large-integer",
            "https://leetcode.com/problems/missing-element-in-sorted-array",
            "https://leetcode.com/problems/missing-number-in-arithmetic-progression",
            "https://leetcode.com/problems/rearrange-string-k-distance-apart",
            "https://leetcode.com/problems/campus-bikes",
            "https://leetcode.com/problems/minimum-cost-to-connect-sticks",
            "https://leetcode.com/problems/high-five",
            "https://leetcode.com/problems/design-search-autocomplete-system",
            "https://leetcode.com/problems/design-in-memory-file-system",
            "https://leetcode.com/problems/alien-dictionary",
            "https://leetcode.com/problems/shortest-distance-from-all-buildings",
            "https://leetcode.com/problems/walls-and-gates",
            "https://leetcode.com/problems/minimum-knight-moves",
            "https://leetcode.com/problems/the-maze-iii",
            "https://leetcode.com/problems/the-maze-ii",
            "https://leetcode.com/problems/the-maze",
            "https://leetcode.com/problems/parallel-courses",
            "https://leetcode.com/problems/number-of-distinct-islands",
            "https://leetcode.com/problems/number-of-islands-ii",
            "https://leetcode.com/problems/web-crawler",
            "https://leetcode.com/problems/all-paths-from-source-lead-to-destination",
            "https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph",
            "https://leetcode.com/problems/kill-process",
            "https://leetcode.com/problems/find-the-celebrity",
            "https://leetcode.com/problems/maximum-number-of-ones",
            "https://leetcode.com/problems/guess-the-majority-in-a-hidden-array",
            "https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter",
            "https://leetcode.com/problems/armstrong-number",
            "https://leetcode.com/problems/handshakes-that-dont-cross",
            "https://leetcode.com/problems/4-keys-keyboard",
            "https://leetcode.com/problems/paint-house-ii",
            "https://leetcode.com/problems/paint-fence",
            "https://leetcode.com/problems/brace-expansion",
            "https://leetcode.com/problems/factor-combinations",
            "https://leetcode.com/problems/strobogrammatic-number-ii",
            "https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree",
            "https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree",
            "https://leetcode.com/problems/design-a-leaderboard",
            "https://leetcode.com/problems/max-stack",
            "https://leetcode.com/problems/zigzag-iterator",
            "https://leetcode.com/problems/encode-and-decode-strings",
            "https://leetcode.com/problems/design-compressed-string-iterator",
            "https://leetcode.com/problems/design-snake-game",
            "https://leetcode.com/problems/design-tic-tac-toe"
    );

    public static List<String> completed = Arrays.asList(
            "https://leetcode.com/problems/paint-house" // easy 2D dp
    );

    @Test
    public void test() {
        for (int i = 0; i < 3; i++) {
            int randomInt = (int) Math.floor(Math.random() * (questions.size() + 1));
            String question = new Premium100().questions.get(randomInt);
            System.out.println(question);
        }
    }
}
