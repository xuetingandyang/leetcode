// Given an array of non-negative integers,
// you are initially positioned at the first index of the array.
// Each element in the array represents your maximum jump length at that position.
//    Your goal is to reach the last index in the minimum number of jumps.
//
//    Input: [2,3,1,1,4]
//    Output: 2
//   Explanation: The minimum number of jumps to reach the last index is 2.
//    Jump 1 step from index 0 to 1, then 3 steps to the last index.
//   Note:
//    You can assume that you can always reach the last index.

public class JumpGameII {
    public int jump(int[] nums) {
        int size = nums.length;
        if (size < 2) return 0;

        // maximum position that curNode can reach
        int maxPos = nums[0] + 0;
        // maximum steps that we can take inside current jump
        int maxSteps = nums[0];

        int jumps = 1; // record number of jumps
        for (int i = 1; i < size; i ++) {
            // if cannot reach current index (i) with current maxSteps, we need a jump
            if (maxSteps < i) {
                jumps ++;
                // update maxSteps of jumped newNode
                maxSteps = maxPos;
            }
            maxPos = Math.max(maxPos, nums[i] + i);
        }
        return jumps;
    }
}
