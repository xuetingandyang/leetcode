// Given an array nums of n integers and an integer target,
// are there elements a, b, c, and d in nums such that a + b + c + d = target?
// Find all unique quadruplets in the array which gives the sum of target.
//
//    The solution set must not contain duplicate quadruplets.
//
//    Example:
//
//    Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
//
//    A solution set is:
//    [
//    [-1,  0, 0, 1],
//    [-2, -1, 1, 2],
//    [-2,  0, 0, 2]
//    ]

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    private static List<List<Integer>> twoSum(int[] nums, int target, int start) {
        // start from nums[start], find nums[p1] + nums[p2] = target
        List<List<Integer>> result = new ArrayList<>();
        int p1 = start, p2 = nums.length - 1;
        while (p1 < p2) {
            int sum = nums[p1] + nums[p2];
            if (sum == target) {
                result.add(new ArrayList<>(List.of(nums[p1], nums[p2])));

                // move to next different element
                while (p1 < p2 && nums[p1] == nums[p1 + 1]) p1 ++;
                while (p1 < p2 && nums[p2] == nums[p2 - 1]) p2 --;
                p1 ++; p2--;
            } else if (sum < target) {
                while (p1 < p2 && nums[p1] == nums[p1 + 1]) p1++;
                p1 ++;
            } else {
                while (p1 < p2 && nums[p2] == nums[p2 - 1]) p2 --;
                p2 --;
            }
        }
        return result;
    }

    private static List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        // start from nums[start], find sum of k numbers = target
        List<List<Integer>> result = new ArrayList<>();

        // largely speed up algorithm
        // (since when call recursion, "target, start, k" all easily become invalid)
        if (start == nums.length || target < nums[0] * k || target > nums[nums.length - 1] * k)
            return result;
        // recursion-exit
        if (k == 2) {
            return twoSum(nums, target, start);
        }
        for (int i = start; i < nums.length; i ++) {
            // skip same element, notice: i > start not i > 0
            if (i > start && nums[i] == nums[i - 1]) continue;

            // recursive call kSum, --> k-1 SUM
            // with target = target-nums[i], start = i + 1, k = k-1
            List<List<Integer>> kMinusOne = kSum(nums, target - nums[i], i + 1, k - 1);
            // add k-1SUM results into 'result'
            for (List<Integer> combination : kMinusOne) {
                // [nums[i], combination]
                result.add(new ArrayList<>(List.of(nums[i])));
                result.get(result.size() - 1).addAll(combination);
            }
        }
        return result;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 0, -1, 0, -2, 2};
//        int[] nums = new int[] {0,0,0,0};
        int target = 0;
        List<List<Integer>> result = fourSum(nums, target);
        System.out.println(result.get(0));
    }
}
