import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Given a set of distinct integers, nums, return all possible subsets (the power set).
* Note: The solution set must not contain duplicate subsets.
*
* Permutation: N!
* Combination: 2^N = C_N^1 + C_N^2 + .. + C_N^N
* Subsets: 2^N
*/

public class Subsets {
    public List<List<Integer>> subsetsIerative(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        rst.add(new ArrayList<>());

        for (int num: nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            // add element into current results
            // loop1: [], [1]   loop2:[2], [1, 2]   loop3: [3], [1, 3], [2, 3], [1, 2, 3]
            for (List<Integer> curr: rst) {
                newSubsets.add(new ArrayList<>(curr) {{ add(num); }} );
            }
            // add new subsets into results
            for (List<Integer> curr: newSubsets) {
                rst.add(curr);
            }
        }
        return rst;
    }


    // DFS - recursion - backtrack
    // for each possible subset lengths
    // Time complexity: O(N×2^N) to generate all subsets and then copy them into output list.
    // the iteration of select elements in nums to add in each subset -> O(N)
    // the O(2^N) comes from the fact that total number of subsets is 2^N

    // Space complexity: O(N×2^N) to keep all the subsets of length N, since each of N elements could be present or absent.
    // since there are total 2^N subsets, each contains 0-N numbers, to cahce them, you need O(N*2^N) space.
    // in summary, we need O(0-N) to keep temporary result.

    public List<List<Integer>> subsetsDFS(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        int len;    // subset length

        if (nums == null || nums.length == 0) {
            return rst;
        }

        Arrays.sort(nums);

        // for each possible subset lengths
        for (len = 0; len <= nums.length; len ++) {
            helper(rst, len, 0, new ArrayList<>(), nums);
        }
        return rst;
    }

    public void helper(
            List<List<Integer>> rst,
            int len,
            int startIdx,
            List<Integer> subset,
            int[] nums) {

        if (subset.size() == len) {

            // System.out.println(rst);
            // rst.add(subset);
            // wrong, add(subset) only add the reference, but the referred value is changing.
            // for len=1, rst=[[], [3], [3], [3]]
            // for len=2, first set subset = [], so rst = [[],[],[],[],[2,3],[2,3],[2,3]]
            // for len=3, first set subset = [], so rst = [[],[],[],[],[],   [],   [],  [1,2,3]]
            // System.out.println(subset);
            // System.out.println(rst);

            // therefore, we need to create a new ArrayList of subset, and then add it to rst.
            rst.add(new ArrayList<>(subset));
        }

        for (int i = startIdx; i < nums.length; i++) {
            subset.add(nums[i]);
            helper(rst, len, i+1, subset, nums);
            subset.remove(subset.size() - 1);
        }

    }



    // method 3: DFS recursion without the possible lengths consideration
    public List<List<Integer>> subsetsDFS2(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }

        Arrays.sort(nums);

        // 3-recursion call:
        helper2(nums, 0, new ArrayList<>(), rst);

        return rst;

    }

    // 1-recursion definition:
    // find all combinations start with subset
    // put them into rst
    // the next element to added into subset, should start from nums[startIdx + 1]
    public void helper2(int[] nums,
                        int startIdx,
                        List<Integer> subset,
                        List<List<Integer>> rst) {

        // deep copy subset into rst
        rst.add(new ArrayList<>(subset));

        // 2-recursion split:
        for (int i = startIdx; i < nums.length; i ++) {
            subset.add(nums[i]);
            helper2(nums, i + 1, subset, rst);
            subset.remove(subset.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        Subsets solution = new Subsets();
        System.out.println(solution.subsetsIerative(nums));
        System.out.println(solution.subsetsDFS(nums));
        System.out.println(solution.subsetsDFS2(nums));
    }
}
