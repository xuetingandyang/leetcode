// Given an integer array nums,
// find the contiguous subarray (containing at least one number)
// which has the largest sum and return its sum.

//    Input: [-2,1,-3,4,-1,2,1,-5,4],
//    Output: 6
//    Explanation: [4,-1,2,1] has the largest sum = 6.

//    Follow up:
//    If you have figured out the O(n) solution,
//    try coding another solution using the divide and conquer approach, which is more subtle.

public class MaximumSubarray {

    private int crossSum(int[] nums, int left, int right, int mid) {
        if (left == right) return nums[left];

        int leftSubSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = mid; i > left - 1; --i) {
            curSum += nums[i];
            leftSubSum = Math.max(leftSubSum, curSum);
        }

        int rightSubSum = Integer.MIN_VALUE;
        curSum = 0;
        for (int i = mid + 1; i < right + 1; ++i) {
            curSum += nums[i];
            rightSubSum = Math.max(rightSubSum, curSum);
        }

        return leftSubSum + rightSubSum;
    }

    private int helper(int[] nums, int left, int right) {
        // find maxSubarray of nums[left: right]
        if (left == right) return nums[left];

        int mid  = (left + right) / 2;

        int leftSum = helper(nums, left, mid);
        int rightSum = helper(nums, mid + 1, right);
        int crossSum = crossSum(nums, left, right, mid);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    public int maxSubArray(int[] nums) {
        // method-1 divide and conquer
        return helper(nums, 0, nums.length - 1);
    }


    // method-2 greedy search
    public int maxSubArrayGreedy(int[] nums) {
        int n = nums.length;
        int curSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < n ; i ++ ) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }


    // method-3 prefixSum[j]: nums[0] + .. nums[j - 1]
    // subSum[i, j] = prefixSum[j + 1] - prefixSum[i]
    // max subSum -> find max of { prefixSum - min(prefixSum) }
    public int maxSubArrayPrefixSum(int[] nums) {
        int prefixSum = 0;
        int max = Integer.MIN_VALUE, min = 0;
        for (int i = 0; i < nums.length; i ++) {
             prefixSum += nums[i];
             max = Math.max(max, prefixSum - min);
             min = Math.min(prefixSum, min);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaximumSubarray Solution = new MaximumSubarray();
        int maxSum = Solution.maxSubArray(nums);
        int maxSUm1 = Solution.maxSubArrayGreedy(nums);
        int maxSum2 = Solution.maxSubArrayPrefixSum(new int[]{-2,-1});
        System.out.println(maxSum);
    }
}
