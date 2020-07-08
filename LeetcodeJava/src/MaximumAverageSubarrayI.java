// Given an array consisting of n integers,
// find the contiguous subarray of given length k
//      that has the maximum average value.
// And you need to output the maximum average value.

//    Input: [1,12,-5,-6,50,3], k = 4
//    Output: 12.75
//    Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        // Two Pointers: traverse only once
        // 0, 1, 2, 3, 4, 5     k = 3
        // |     |
        // p1   p2

        int p1 = 0, p2 = k - 1;
        double sum = 0;
        for (int i = 0; i < k; i ++) {
            sum += nums[i];
        }
        double maxSum = sum;

        for (int j = k; j < nums.length; j ++) {
            sum = sum + nums[j] - nums[j - k];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum / k;
    }

    public static void main(String[] args) {
        MaximumAverageSubarrayI Solution = new MaximumAverageSubarrayI();
        int[] nums = new int[] {1, 12, -5, -6, 50, 3};
        int k = 4;
        double maxAvg = Solution.findMaxAverage(nums, k);
        System.out.println(maxAvg);
    }
}
