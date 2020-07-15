// Given an array nums of n integers and an integer target,
// find three integers in nums such that the sum is closest to target.
// Return the sum of the three integers.
// You may assume that each input would have exactly one solution.
//
//    Input: nums = [-1,2,1,-4], target = 1
//    Output: 2
//    The sum that is closest to the target is 2.
//    (-1 + 2 + 1 = 2).

import java.util.Arrays;

public class ThreeSumClosestToTarget {

    private static int twoSumClosest(int[] nums, int newTarget, int startIdx, int diff) {

        int j = startIdx, k = nums.length - 1;

        while (j < k) {
            int sum = nums[j] + nums[k];

            if (Math.abs(newTarget - sum) < Math.abs(diff)) {
                diff = newTarget - sum;
            }

            if (sum < newTarget) {
                j ++;
            } else if (sum > newTarget) {
                k --;
            } else {
                break;
            }
        }
        return diff;
    }


    public static int threeSumClosest(int[] nums, int target) {
        // two pointers
        // time: O(n^2 + nlogn) = O(n^2)  space: O(1)
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i ++) {
            // continuously update diff (abs(diff) is min)
            diff = twoSumClosest(nums, target - nums[i], i + 1, diff);
        }
        return target - diff;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-1, 2, 1, -4};
        int target = 1;
        int sum = threeSumClosest(nums, target);
        System.out.println(sum);
    }
}
