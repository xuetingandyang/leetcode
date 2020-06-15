// Given a set of candidate numbers (candidates) (can have duplicates) and a target number (target),
// find all unique combinations in candidates where the candidate numbers sums to target.
//
//The same repeated number may be chosen from candidates unlimited number of times.

//    All numbers (including target) will be positive integers.
//    The solution set must not contain duplicate combinations.
//    Example 1:
//    Input: candidates = [2,3,6,7], target = 7,
//    A solution set is:
//    [7],
//    [2,2,3]

// Input: candidates = [2,3,5], target = 8,
//        A solution set is:
//        [
//        [2,2,2,2],
//        [2,3,3],
//        [3,5]
//        ]

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> rst = new ArrayList<>();

        if (candidates == null || candidates.length == 0) {
            return rst;
        }

        Arrays.sort(candidates);

        // 4-recursion call
        recursion(candidates, new ArrayList<>(), rst, target, 0);

        return rst;

    }

    // 1-recursion definition:
    // Find all combinations start with 'combination' with target = sum
    // add combination into rst when remainTarget == 0
    // each element to be added into combination should start from startIdx
    // remaining elements to added in combination, sum = remainTarget
    public void recursion(int[] candidates,
                          List<Integer> combination,
                          List<List<Integer>> rst,
                          int remainTarget,
                          int startIdx) {

        // 3-recursion-exit
        if (remainTarget == 0) {
            // deep copy
            rst.add(new ArrayList<>(combination));
            return;
        }

        // 2-recursion split
        for (int i = startIdx; i < candidates.length; i ++) {

            // avoid minus remainTarget
            if (remainTarget < candidates[i]) {
                break;  // since we have sorted candidates, the next elements will all > remainTarget
            }

            // remove duplicates in candidates
            // candidates [2,2,2,3,4,4,5]
            if (i != 0 && candidates[i] == candidates[i - 1]) {
                continue;   // skip duplicate
            }

            combination.add(candidates[i]);
            recursion(candidates, combination, rst, remainTarget - candidates[i], i);
            combination.remove(combination.size() - 1);
        }
    }



    public static void main(String[] args) {
        int[] candidates = new int[] {2, 2, 3, 3, 4, 5};
        int target = 8;
        CombinationSum Solution = new CombinationSum();
        System.out.println(Solution.combinationSum(candidates, target));
    }
}
