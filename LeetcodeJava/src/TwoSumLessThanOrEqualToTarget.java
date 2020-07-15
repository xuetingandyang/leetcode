// Given an array of integers, find how many pairs in the array
// such that their sum <=  target. Please return the number of pairs.
//
//    Given nums =[2, 7, 11, 15], target =24.
//    Return5.
//    2 + 7 < 24
//    2 + 11 < 24
//    2 + 15 < 24
//    7 + 11 < 24
//    7 + 15 < 24

import java.util.Arrays;

public class TwoSumLessThanOrEqualToTarget {
    public static int twoSumLessOrEqualK(int[] nums, int K) {
        // Two Pointers
        // 1. sort
        // 2. if p1 + p2 < K, then all i between p1 and p2, satisfy p1 + i < k
        // so count += p2 - p1
        Arrays.sort(nums);
        int count = 0, p1 = 0, p2 = nums.length - 1;
        while (p1 < p2) {
            int sum = nums[p1] + nums[p2];
            if (sum < K) {
                // p1 + i < k for any i in [p1, p2]
                count += p2 - p1;
                // increase p1
                p1 ++;
            } else {
                p2 --;
            }
        }
        return count;
    }

    public static int twoSumLargeOrEqualK(int[] nums, int K) {
        // Two Pointers
        // 1. sort
        // 2. if p1 + p2 > K, then all i between p1 and p2, satisfy i + p2 > k
        // so count += p2 - p1
        Arrays.sort(nums);
        int count = 0, p1 = 0, p2 = nums.length - 1;
        while (p1 < p2) {
            int sum = nums[p1] + nums[p2];
            if (sum > K) {
                // i + p2 > k for any i in [p1, p2]
                count += p2 - p1;
                // decrease p2
                p2 --;
            } else {
                p1 ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 7, 11, 15};
        int K = 15;
        int count = twoSumLessOrEqualK(nums, K);
        int count1 = twoSumLargeOrEqualK(nums, K);
        System.out.println(count);
    }
}
