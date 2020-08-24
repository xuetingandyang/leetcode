// Given an int array nums and an int target.
// Find two integers in nums such that the sum is closest to target.
//
//    Input: nums = [1, 2, 3, 4, 5], target = 10
//    Output: [4, 5]

//    Input: nums= [-1, 2, 1, -4], target = 4
//    Output: [2, 1]

import java.util.Arrays;

public class TwoSumClosestToTarget {
    public static int[] twoSumCloset(int[] nums, int target) {
        // Two Pointers
        Arrays.sort(nums);

        int[] result = new int[2];
        int p1 = 0, p2 = nums.length - 1;
        int minDiff = Integer.MAX_VALUE;
        while (p1 < p2) {
            // EG: nums = [-3, -1, 1, 4, 6, 7], target = -1
            int sum = nums[p1] + nums[p2];
            int diff = Math.abs(sum - target);
            if (diff < minDiff) {
               minDiff = Math.min(minDiff, diff);
               result[0] = nums[p1];
               result[1] = nums[p2];
            }
            if (sum < target) {
                p1++;
            } else if (sum > target) {
                p2--;
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-4, -3, 1, 6, 7};
        int target = 7;
        int[] result = twoSumCloset(nums, target);
        System.out.println(result);
    }
}
