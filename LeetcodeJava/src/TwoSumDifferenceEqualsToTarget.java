// Given an array of integers, find two numbers that
// their difference equals to a target value.
// where index1 must be less than index2.
// Please note that your returned answers (both index1 and index2) are NOT zero-based.

//    Input: nums = [2, 7, 15, 24], target = 5
//    Output: [1, 2]
//    Explanation:
//    (7 - 2 = 5)

//    Input: nums = [1, 1], target = 0
//    Output: [1, 2]
//    Explanation:
//    (1 - 1 = 0)
//    Notice
//      It's guaranteed there is only one available solution

import java.util.HashMap;
import java.util.Map;

public class TwoSumDifferenceEqualsToTarget {
    public static int[] twoSumDiff(int[] nums, int target) {
        // Hashmap: nums[i], idx + 1
        // a - b = target or b - a = target
        // ===> find 'b + target', 'b - target'
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i ++) {
            if (map.containsKey(nums[i] + target)) {
                result[0] = map.get(nums[i] + target);
                result[1] = i + 1;
                return result;
            }
            if (map.containsKey(nums[i] - target)) {
                // a < b
                result[0] = map.get(nums[i] - target);
                result[1] = i + 1;
                return result;
            }
            map.put(nums[i], i + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 7, 15, 24};
        int target = 8;
        int[] result = twoSumDiff(nums, target);
        System.out.println(result[0]);
    }

}
