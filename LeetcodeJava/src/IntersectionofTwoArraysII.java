// Given two arrays, write a function to compute their intersection.
//    Input: nums1 = [1,2,2,1], nums2 = [2,2]
//    Output: [2,2]

//    Note:
//    Each element in the result should appear as many times as it shows in both arrays.
//    The result can be in any order.

//    Follow up:
//    1. What if the given array is already sorted?
//       How would you optimize your algorithm?
//       ---> method 2
//    2. What if nums1's size is small compared to nums2's size?
//       Which algorithm is better?
//       ---> method1: hashmap
//    3. What if elements of nums2 are stored on disk,
//       and the memory is limited such that you cannot load all elements into the memory at once?
//       --->
//          Case1: nums1 fits into memory:
//              use method1-hashmap to count for nums1
//              then sequentially load and process nnums2
//          Case2: neither of arrays fit into memory
//              method-1: split into subranges that fits into memory
//                 use method1-hashmap of subranges for multiple times
//              method-2: external sort of nums1 and nums2
//r                read 2 elements from each array at a time in memory, record intersections.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IntersectionofTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        // method 1: use hashmap to count nums1 (num, count)
        // for each element in nums2, find a duplicate the count - 1
        // time: O(m + n), space: O( min(m, n) )

        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1); // ensure min space complexity
        }

        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int j : nums2) {
            if (map.containsKey(j) && map.get(j) > 0) {
                result.add(j);
                // find one pair of duplicate, count - 1
                map.put(j, map.get(j) - 1);
            }
        }

        // convert arraylist into int array
        int[] inter = new int[result.size()];
        int i = 0;
        for (Integer num : result) {
            inter[i] = num;
            i++;
        }
        return inter;
    }

    public int[] intersectSorted(int[] nums1, int[] nums2) {
        // time: O(mlogm + nlogn) space: O(n)
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // Two Pointers: p1, p2
        // p: points to result array
        int p1 = 0, p2 = 0, p = 0;
        ArrayList<Integer> result = new ArrayList<>();
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                result.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        // convert arraylist into int array
        int[] inter = new int[result.size()];
        int i = 0;
        for (Integer num : result) {
            inter[i] = num;
            i++;
        }
        return inter;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        IntersectionofTwoArraysII Solution = new IntersectionofTwoArraysII();
        int[] result = Solution.intersect(nums1, nums2);
//        int[] result1 = Solution.intersectSortedMerge(nums1, nums2);
//        int[] result2 = Solution.intersectBinarySearch(nums1, nums2);
        System.out.println(result);
    }
}
