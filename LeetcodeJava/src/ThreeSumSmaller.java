// Given an array of n integers nums and a target,
// find the number of index triplets i, j, k with 0 <= i < j < k < n
// that satisfy the condition nums[i] + nums[j] + nums[k] < target.
//
//    Input: nums = [-2,0,1,3], and target = 2
//    Output: 2
//    Explanation: Because there are two triplets which sums are less than 2:
//    [-2,0,1]
//    [-2,0,3]

//    Follow up: Could you solve it in O(n2) runtime?

import java.util.Arrays;

public class ThreeSumSmaller {
    private static int twoSumSmaller(int[] nums, int startIdx, int target) {
        // Two Pointers
        int count = 0;
        int j = startIdx, k = nums.length - 1;
        while (j < k) {
            int sum = nums[j] + nums[k];
            if (sum < target) {
                // all j + k' < newTarget for any k' in [j, k]
                count += k - j;
                j ++;
            } else {
                k --;
            }
        }
        return count;
    }

    public static int threeSumSmaller(int[] nums, int target) {
        // Two Pointers
        Arrays.sort(nums);
        int count = 0, n = nums.length;

        for (int i = 0; i < n - 2; i ++) {
            count += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 1, 3};
        int target = 2;
        int count = threeSumSmaller(nums, target);
        System.out.println(count);
    }

}
