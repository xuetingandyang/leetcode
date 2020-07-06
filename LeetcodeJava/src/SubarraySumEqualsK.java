// Given an array of integers and an integer k,
// you need to find the total number of continuous subarrays whose sum equals to k.
//
//    Input:nums = [1,1,1], k = 2
//    Output: 2

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        // brute force
        int equals = 0;
        for (int i = 0; i < nums.length; i ++) {
            int sum = 0;
            for (int j = i; j < nums.length; j ++) {
                sum += nums[j];
                if (sum == k) equals++;
            }
        }
        return equals;
    }

    public int subarraySumPrefixSum(int[] nums, int k) {
        // use prefixSum, then subSum[i, j] = prefixSum[i] - prefixSum[j]
        // hashmap: (prefixSum, count of prefixSum)
        int prefixSum = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i ++) {
            prefixSum += nums[i];

            if (prefixSum == k) count++;

            // prefixSum[curt] - prefixSum[prev] = k
            // prefix[prev] = prefix[curt] - k
            // check if we already have prefixSum[prev]
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 1};
        int k = 2;
        SubarraySumEqualsK Solution = new SubarraySumEqualsK();
        int equals = Solution.subarraySum(nums, k);
        int equals1 = Solution.subarraySumPrefixSum(nums, k);
        System.out.println(equals);
    }
}
