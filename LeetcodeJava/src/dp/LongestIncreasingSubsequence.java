package dp;

//Given an unsorted array of integers, find the length of longest increasing subsequence.
//Input: [10,9,2,5,3,7,101,18]
//Output: 4
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
//Note:
//There may be more than one LIS combination, it is only necessary for you to return the length.
//Your algorithm should run in O(n2) complexity.

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // dp[i]: length of longest increasing subseq ended with nums[i]
        // dp[i] = max(dp[k], k in [0,i-1] && nums[i] > nums[k]) + 1
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = 1;
        for (int i = 1; i < n; i ++) {
            int maxPrevLen = 0;
            for (int k = 0; k < i; k ++) {
                if (nums[i] > nums[k]) {
                    maxPrevLen = Math.max(maxPrevLen, dp[k]);
                }
            }
            dp[i] = maxPrevLen + 1;
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
