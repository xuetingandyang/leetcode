//Given a collection of distinct integers, return all possible permutations.
//
//    Example:
//
//    Input: [1,2,3]
//    Output:
//    [
//    [1,2,3],
//    [1,3,2],
//    [2,1,3],
//    [2,3,1],
//    [3,1,2],
//    [3,2,1]
//    ]


import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return rst;
        }

        // 4-recursion call
        helper(new ArrayList<>(), rst, nums);
        return rst;
    }

    // 1-recursion definition:
    // Find all permutations that start with 'permutation'
    public void helper(List<Integer> permutation,
                       List<List<Integer>> rst,
                       int[] nums) {
        // 3-recursion exit
        if (permutation.size() == nums.length) {
            // deep copy
            rst.add(new ArrayList<>(permutation));
            return;
        }

        // 2-recursion split
        // [3, 1] => [3, 1, 2]
        for (int i = 0; i < nums.length; i ++) {
            if (permutation.contains(nums[i])) continue;
            permutation.add(nums[i]);
            helper(permutation, rst, nums);
            permutation.remove(permutation.size() - 1);
        }
    }

    public static void main(String[] args) {
        Permutations Solution = new Permutations();
        int[] nums = new int[] {1, 2, 3};
        System.out.println(Solution.permute(nums));
    }
}
