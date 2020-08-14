package codesignal;

import java.util.*;

//525. Contiguous Array
// Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
//    Input: [0,1]
//    Output: 2
//    Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

//    Input: [0,1,0]
//    Output: 2
//    Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

public class ContiguousArray {
    // The idea is to change 0 in the original array to -1.
    // Thus, if we find SUM[i, j] == 0
    // then we know there are even number of -1 and 1 between index i and j.
    // Also put the sum to index mapping to a HashMap to make search faster.

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int sum = 0, maxLen = 0;

        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i] == 0 ? -1 : 1;;
            if (sumToIndex.containsKey(sum)) {
                maxLen = Math.max( maxLen, i - sumToIndex.get(sum) );
            } else {
                sumToIndex.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,0,0,0,1,1};
        ContiguousArray contiguousArray = new ContiguousArray();
        int rst = contiguousArray.findMaxLength(nums);
        System.out.println(rst);
    }
}
