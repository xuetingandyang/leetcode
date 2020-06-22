// Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//
//    Example:
//
//    Input: [1,1,2]
//    Output:
//    [
//    [1,1,2],
//    [1,2,1],
//    [2,1,1]
//    ]

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) return rst;

        // an extra boolean array " boolean[] used" to indicate whether the value is added to list.
        boolean[] visited = new boolean[nums.length];

        // Sort the array "int[] nums" to make sure we can skip the same value.
        // like [3,3,0,3] => [0,3,3,3]
        Arrays.sort(nums);

        // 4-recursion call
        helper(new ArrayList<>(), visited, rst, nums);
        return rst;
    }

    // 1-recursion definition
    // find all permutations start with 'permutation'
    // [1, 2', 2'']
    // [1, 2'', 2']
    // how to remove duplicates?
    // When search for elements, we only accept elements in order.
    // we cannot jump 2' to select 2''

    // when a number has the same value with its previous,
    // we can use this number only if his previous is used

    public void helper(List<Integer> permutation,
                       boolean[] visited,
                       List<List<Integer>> rst,
                       int[] nums) {
        // 3-recursion exit
        if (permutation.size() == nums.length) {
            // deep copy
            rst.add(new ArrayList<>(permutation));
            return;
        }

        // 2-recursion split
        for (int i = 0; i < nums.length; i ++) {
            // if already visited, skip
            if (visited[i]) continue;

            // if current element = previous UNVISITED element, skip current element
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

            // recursion is a symmetric process
            // so permutation.add(), set true <--> permutation.remove(), set false
            permutation.add(nums[i]);
            visited[i] = true;
            helper(permutation, visited, rst, nums);
            permutation.remove(permutation.size() - 1);
            visited[i] = false;
        }

    }


    public static void main(String[] args) {
        int[] nums = new int[] {3, 3, 0, 3};
        PermutationsII Solution = new PermutationsII();
        System.out.println(Solution.permuteUnique(nums));
    }
}
