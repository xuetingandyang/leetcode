// Given an array of non-negative integers,
// you are initially positioned at the first index of the array.
// Each element in the array represents your maximum jump length at that position.
// Determine if you are able to reach the last index.

//    Input: nums = [2,3,1,1,4]
//    Output: true
//    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

//    Input: nums = [3,2,1,0,4]
//    Output: false
//    Explanation: You will always arrive at index 3 no matter what.
//    Its maximum jump length is 0, which makes it impossible to reach the last index.

public class JumpGame {
    public boolean canJumpGreedy (int[] nums) {
        // Greedy problems usually look like:
        //  -"Find minimum number of something to do something"
        //  - or "Find maximum number of something to fit in some conditions".
        // The idea of greedy algorithm is to pick locally optimal move at each step,
        // that will lead to globally optimal solution.
        //
        // Greedy Algorithm: start from the last node, check if current node can reach destination
        // update 'dest' to curNode if curNode can reach curDest
        int size = nums.length;
        int dest = size - 1;

        for (int i = size - 1; i >= 0; i --) {
            if (nums[i] + i >= dest) {
                dest = i;
            }
        }
        return dest == 0;
    }

    public boolean canJumpGreedyTopDown(int[] nums) {
        // if max of previous nodes'furtherDest < curNode, return false
        // update curNode's furtherDest
        // until reach last node, return true
        int size = nums.length;
        int furtherDest = nums[0] + 0;

        for (int i = 1; i < size; i ++) {
            // if cannot reach furtherDest, return false
            if (furtherDest < i) return false;
            // update furtherDest
            furtherDest = Math.max(furtherDest, nums[i] + i);
        }
        return true;
    }

    public boolean canJumpBottomUp (int[] nums) {
        // DP-bottom up
        // start from lastNode,
        // if [curNode ~ curNode's furtherDest] can reach lastNode => return true
        // dp values: can reach lastNode: 1, cannot: -1, unknown: 0
        int size = nums.length;
        int[] dp = new int[size];

        // initialize
        dp[size - 1] = 1;

        for (int i = size - 2; i >= 0; i --) {
            int furtherDest = Math.min(nums[i] + i, size - 1);
            for (int j = i + 1; j <= furtherDest; j ++) {
                if (dp[j] == 1) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        return dp[0] == 1;
    }



    public boolean canJump (int[] nums) {
        // Skip this solution, since:
        //    1. too much side cases
        //    2. not a DP, actually its more like 'greedy' from top-bottom
        // Check if a node's max jump destination = the last node
        // for nums[0 ~ nums.len-1], dp[i] = max jump destination = nums[i] + i
        // if dp[i] > nums.length - 1, dp[i] = nums.length - 1 (cannot jump out of nums)
        // ---> dp[i] = min(nums[i]+i, nums.len-1)
        // [2,3,1,1,4] => dp[0]=0+2=2, dp[1]=1+3=4, dp[2]=2+1=3
        // if we found dp[i] = nums.len-1, return true
        int size = nums.length;
        if (nums == null || size == 0) return false;
        if (size == 1) return true;
        if (nums[0] == 0) return false;

        int[] dp = new int[size - 1];
        dp[0] = 0;

        for (int i = 0; i < size - 1; i ++) {
            dp[i] = Math.min(nums[i] + i, size - 1);
            if (dp[i] == i) {
                boolean canContinue = false;
                for (int j = 0; j < i; j ++) {
                    if (dp[j] > i) {
                        canContinue = true;
                    }
                }
                if (canContinue) {
                    continue;
                } else {
                    break;
                }
            }
            if (dp[i] == (size - 1)) return true;
        }
        return false;
    }
}
