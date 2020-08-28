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
//                rst.add(new ArrayList<>(List.of(nums[i], nums[p1], nums[p2])));
                rst.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
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



    private static void twoSumHashset(int[] nums, int target, int startIdx, List<List<Integer>> rst) {

        Set<Integer> seen = new HashSet<>();
        for (int i = startIdx; i < nums.length; i ++) {
            int remainTarget = target - nums[i];

            if (seen.contains(remainTarget)) {
                rst.add(Arrays.asList(-target, remainTarget, nums[i]));

                // jump to next different i
                // eg: 2,2,2,3 => get the last '2'
                while (i+1 < nums.length && nums[i] == nums[i+1]) i ++;
            }
            seen.add(nums[i]);
        }
    }

    public static List<List<Integer>> threeSumHashSet(int[] nums) {
        // HashMap

        List<List<Integer>> rst = new ArrayList<>();

        if (nums == null || nums.length == 0) return rst;

        // sort array to help remove duplicate
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n; i ++) {
            // skip duplicates
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int newTarget = - nums[i];
            twoSumHashset(nums, newTarget, i+1, rst);
        }
        return rst;
    }




    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> rst = threeSumTwoPointers(nums);
        List<List<Integer>> rst1 = threeSumHashSet(nums);
        System.out.println(rst);
        System.out.println(rst1);
    }

}
