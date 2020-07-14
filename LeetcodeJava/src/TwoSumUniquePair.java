// Given an int array nums and an int target,
// find how many unique pairs in the array such that
// their sum is equal to target. Return the number of pairs.
//
//    Example 1:
//    Input: nums = [1, 1, 2, 45, 46, 46], target = 47
//    Output: 2
//    Explanation:
//    1 + 46 = 47
//    2 + 45 = 47

//    Example 2:
//    Input: nums = [1, 5, 1, 5], target = 6
//    Output: 1
//    Explanation:
//    [1, 5] and [5, 1] are considered the same.

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSumUniquePair {
    public static int uniquePairs(int[] nums, int target) {
        // Two pointers: time: O(nlogn) space:O(1)
        Arrays.sort(nums);

        int count = 0, i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                count ++;
                // jump to next different element
                while (i + 1 < j && nums[i] == nums[i + 1]) i++;

                while (i < j - 1 && nums[j] == nums[j - 1]) j --;

                i++; j--;
            } else if (sum < target) {
                // jump to next different larger element
                while (i + 1 < j && nums[i] == nums[i + 1]) i++;

                i++;
            }
            else {
                while (i < j - 1 && nums[j] == nums[j - 1]) j --;

                j--;
            }
        }
        return count;
    }

    public static int uniquePairsHashSet(int[] nums, int target) {
        // HashSet:
        // 'uniques' save unique elements
        // 'seen' save already seen pairs (a, b) where a+b = target
        // TIME: O(n), SPACE: O(n)
        Set<Integer> uniques = new HashSet<>();
        Set<Integer> seen = new HashSet<>();

        int count = 0;

        for (int i = 0; i < nums.length; i ++) {
            int newTarget = target - nums[i];

            // !!! check if uniques contain newTarget first!
            // should before check uniques contain 'nums[i]'
            // Otherwise, we will encounter wrong case:
            //   like: nums[i] = 2, newTarget = 2 ==> use nums[i] twice
            if ( uniques.contains(newTarget) && ! seen.contains(newTarget)) {
                // uniques contains NewTarget and did not seen NewTarget
                count++;
                // add pair (nums[i], newTarget) into seen
                seen.add(newTarget);
                seen.add(nums[i]);
            }
            if (! uniques.contains(nums[i]) ) {
                uniques.add(nums[i]);
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{46, 1, 46, 10, 45, 2, 1};
        int target = 47;
//        int cnt = uniquePairs(nums, target);
        int cnt1 = uniquePairsHashSet(nums, target);
        System.out.println(cnt1);
    }
}
