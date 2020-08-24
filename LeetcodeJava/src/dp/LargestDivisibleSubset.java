package dp;

//Given a set of distinct positive integers,
// find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
//  Si % Sj = 0 or Sj % Si = 0.
// If there are multiple solutions, return any subset is fine.
//    Input: [1,2,3]
//    Output: [1,2] (of course, [1,3] will also be ok)

//    Input: [1,2,4,8]
//    Output: [1,2,4,8]

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // List<Integer> dp[i]: largest divisible subset ended with nums[i]
        // dp[i] = maxLen(dp[k], k in [0, i-1] && nums[i]%nums[k] == 0) .add(nums[i])
        // keep maxLen, update maxlen and rst together.

        List<Integer> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) return rst;

        Arrays.sort(nums);

        int n = nums.length, maxLen = 0;
        List<List<Integer>> dp = new ArrayList<>();

        // initialize
        for (int num : nums) {
            dp.add(new ArrayList<>());
        }

        dp.get(0).add(nums[0]);
        rst = dp.get(0);
        maxLen = 1;

        for (int i = 1; i < n; i ++) {
            List<Integer> curSubset = new ArrayList<>();
            for (int k = 0; k < i; k ++) {
                if (nums[i] % nums[k] == 0) {
                    if (dp.get(k).size() > curSubset.size()) {
                        curSubset = dp.get(k);
                    }
                }
            }
            dp.get(i).addAll(curSubset);
            dp.get(i).add(nums[i]);

            // update maxLen and rst
            if (dp.get(i).size() > maxLen) {
                rst = dp.get(i);
                maxLen = dp.get(i).size();
            }
        }
        return rst;
    }
}
