// Given an array nums of n integers, are there elements a, b, c in nums
// such that a + b + c = 0?
// Find all unique triplets in the array which gives the sum of zero.
//
//    The solution set must not contain duplicate triplets.
//
//    Given array nums = [-1, 0, 1, 2, -1, -4],
//    A solution set is:
//    [
//    [-1, 0, 1],
//    [-1, -1, 2]
//    ]


import java.util.*;

public class ThreeSum {

    private static void twoSumUnique(int[] nums, int target, int i, List<List<Integer>> rst) {
        // find uniqye two sum to target
        // Two Pointers to search for nums[p1] + nums[p2] = target

        int p1 = i + 1, p2 = nums.length - 1;
        while (p1 < p2) {
            int sum = nums[p1] + nums[p2];
            if (sum == target) {
                rst.add(new ArrayList<>(List.of(nums[i], nums[p1], nums[p2])));
                // jump to next different element
                while (p1 < p2 && nums[p1] == nums[p1 + 1]) p1++;
                while (p1 < p2 && nums[p2] == nums[p2 - 1]) p2--;

                p1++; p2--;
            } else if (sum < target) {
                while (p1 < p2 && nums[p1] == nums[p1 + 1]) p1 ++;

                p1++;
            } else {
                while (p1 < p2 && nums[p2] == nums[p2 - 1]) p2--;

                p2--;
            }
        }
    }

    public static List<List<Integer>> threeSumTwoPointers(int[] nums) {
        // Two Pointers
        // 1. sort array
        // 2. find a, b that a + b = target - c = -c ==> convert to TwoSum
        // time: O(nlogn + n^2) = o(n^2), space: O(1)

        List<List<Integer>> rst = new ArrayList<>();
        int n = nums.length;

        if (nums == null || n < 3) return rst;

        Arrays.sort(nums);

        for (int i = 0; i < n; i ++) {
            // go to next different element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int target = - nums[i];

            twoSumUnique(nums, target, i, rst);
        }
        return rst;
    }


    public static List<List<Integer>> threeSumHashSet(int[] nums) {
        // PASS!!! TOO SLOW AND JAVA HAS NO EASY PAIR DATE STRUCTURE.

        // HashSet: time: O(n^2), space: O(n^2)
        // for each different nums[i], find twosum with target = - nums[i]
        // Note: handling duplicates in pairs of 3 numbers
        // -> put pairs of 3 into 'found' set & ascending numbers in pairs
        // (to avoid we have same numbers in different positions of pair)

        List<List<Integer>> rst = new ArrayList<>();
        Set<Map.Entry> found = new HashSet<>();

        if (nums == null || nums.length < 3) return rst;

        for (int i = 0; i < nums.length - 2; i ++) {

            // find unique TwoSum with target = - nums[i]
            int target = - nums[i];
            Set<Integer> uniques = new HashSet<>();
//            Set<Integer> seen = new HashSet<>();

            for (int j = i + 1; j < nums.length; j ++) {
                int newTarget = target - nums[j];

                // !!! check if uniques contain newTarget first!
                // should before check uniques contain 'nums[j]'
                // Otherwise, we will encounter wrong case:
                //  like: nums[j] = 2, newTarget = 2 ==> use nums[j] twice
                if (uniques.contains(newTarget)) {
                    // ascending order to avoid duplicates in pairs
                    // head: min num, tail: max num
                    // Pair = (head, _, tail)
                    int head = Math.min(nums[i], Math.min(nums[j], newTarget));
                    int tail = Math.max(nums[i], Math.max(nums[j], newTarget));

                    Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<Integer, Integer>(head, tail);
                    if (found.add(entry) ){
                        rst.add(new ArrayList<>(List.of(nums[i], nums[j], newTarget)));
                    }
                }
                uniques.add(nums[j]);
            }
        }
        return rst;
    }



    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
//        List<List<Integer>> rst = threeSumTwoPointers(nums);
        List<List<Integer>> rst1 = threeSumHashSet(nums);
        System.out.println(rst1.get(0));
    }

}
