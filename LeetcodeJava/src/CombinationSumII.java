import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
//
//    Each number in candidates may only be used once in the combination.
//
//    Note:
//
//    All numbers (including target) will be positive integers.
//    The solution set must not contain duplicate combinations.
//    Example 1:
//
//    Input: candidates = [10,1,2,7,6,1,5], target = 8,
//    A solution set is:
//    [
//    [1, 7],
//    [1, 2, 5],
//    [2, 6],
//    [1, 1, 6]
//    ]
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> rst = new ArrayList<>();

        if (candidates == null || candidates.length == 0) {
            return rst;
        }

        Arrays.sort(candidates);
        // 4-recursion call
        recursion(candidates, new ArrayList<>(), rst, target, 0);
        return rst;

    }

    // 1-recursion definition
    // Find all combinations start with 'combination' and sum = target
    // all legal elements start from StartIdx to be added into combination, sum = remainTarget
    public void recursion(int[] candidates,
                          List<Integer> combination,
                          List<List<Integer>> rst,
                          int remainTarget,
                          int startIdx) {

        // 3-recursion exit
        if (remainTarget == 0) {
            // deep copy
            rst.add(new ArrayList<>(combination));
            return;
        }

        // 2-recursion split
        for (int i = startIdx; i < candidates.length; i ++) {

            if (remainTarget < candidates[i]) break;

            // [1, 1, 1, 2, 3] target=4
            // duplicates: [1', 1'', 2](accepted), [1', 1''', 2](discard), [1'', 1''', 2](discard)
            // to remove duplicates: we need to ensure when we select elements, we do not skip/jump.

            // for i>startIdx, means that (i-1)>=startIdx is not included in current 'combination'
            // in this situation, if we add (i) into 'combination' and skip (i-1), will cause duplicate.
            // hence, for i>startIdx and (i)==(i-1), just 'continue' and check next element
            if (i > startIdx && candidates[i] == candidates[i - 1]) continue;

            combination.add(candidates[i]);
            recursion(candidates, combination, rst, remainTarget - candidates[i], i + 1);
            combination.remove(combination.size() - 1);
        }

    }


    public static void main(String[] args) {
        int[] candidates = new int[] {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        CombinationSumII Solution = new CombinationSumII();
        System.out.println(Solution.combinationSum2(candidates, target));

    }
}
