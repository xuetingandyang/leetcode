// Given an array of integers, return indices of the two numbers
// such that they add up to a specific target.
//
//    You may assume that each input would have exactly one solution,
//    and you may not use the same element twice.
//
//    Given nums = [2, 7, 11, 15], target = 9,
//    Because nums[0] + nums[1] = 2 + 7 = 9,
//    return [0, 1].

import java.util.*;


public class TwoSum {

    private class Pair {
        int idx, val;

        private Pair (int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        // Two Pointers: p1 from head, p2 from tail
        // sum < target: p1 is too small -> p1++
        //     >       : p2        large -> p2--

        // since return original index,
        // construct Pair array to maintain original index

        // time: O(nlogn) space: O(n)
        Pair[] numsPairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i ++) {
            numsPairs[i] = new Pair(i, nums[i]);
        }
        // override comparator
        Arrays.sort(numsPairs, new Comparator<Pair> () {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.val - o2.val;
            }
        });

        int i = 0, j = nums.length - 1;

        while (i < j) {
            if (numsPairs[i].val + numsPairs[j].val == target) {
                return new int[] {numsPairs[i].idx, numsPairs[j].idx};
            } else if (numsPairs[i].val + numsPairs[j].val > target) {
                // 2,3,4,7, target = 7
                // 2+7>7 -> j--
                j--;
            } else {
                // 2,3,4,7 target = 10
                // 2 + 7 < 10 ==> i++
                i++;
            }
        }

        return new int[]{};
    }


    public int[] twoSumHashMap(int[] nums, int target) {
        // hashmap: (val, idx)
        // for i, add new key-value pair in map, find target-nums[i]
        // time: hashmap lookup is O(1) -> O(n)
        // space: hashmap: O(n)

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        // you do not need to println the int[] results.
        // just in debug mode, and then see the results in debug variables.

        TwoSum answer = new TwoSum();
        int[] nums = new int[] {3, 2, 4, 1, 5};
        int target = 10;

        int[] rst = answer.twoSum(nums, target);
        int[] rst2 = answer.twoSumHashMap(nums, target);

        /* Test println function and toString() method  */
        System.out.println(rst);    // print address [I@6537cf78
        System.out.println(Arrays.toString(rst));   // [1, 2]

    }
}
