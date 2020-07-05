// Given two arrays, write a function to compute their intersection.

//    Input: nums1 = [1,2,2,1], nums2 = [2,2]
//    Output: [2]

//    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//    Output: [9,4]

//    Note:
//    Each element in the result must be unique.
//    The result can be in any order.

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionofTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        // method 1: Hash Set, time O(n) (for n > m), space O(m) or O(n)
        Set<Integer> nums = new HashSet<>();
        Set<Integer> res = new HashSet<>();

        for (int value : nums1) {
            nums.add(value);
        }

        for (int value : nums2) {
            if (nums.contains(value)) {
                res.add(value);
            }
        }
        // convert set to int array
        int[] result = new int[res.size()];
        int i = 0;
        for (Integer num : res) {
            result[i ++] = num;
        }

        return result;
    }

    public int[] intersectionSortedMerge(int[] nums1, int[] nums2) {
        // method 2: sorted 2 arrays, then merge them to get the intersection
        // two pointers: once nums1 == nums2, put it in hashset
        // time: O(mlogm + nlogn) = O(nlogn) for n> m
        // space: O(m) or O(n)
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> nums = new HashSet<>();
        int p1 = 0, p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                nums.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        // convert set to array
        int[] result = new int[nums.size()];
        int i = 0;
        for (Integer num : nums) {
            result[i] = num;
            i++;
        }
        return result;
    }

    public int[] intersectionBinarySearch(int[] nums1, int[] nums2){
        // sort nums2, then for each element in nums1,
        // use binary search to check if nums1[i] in nums2
        Arrays.sort(nums2);
        Set<Integer> intersection = new HashSet<>();
        for (int num : nums1) {
            if (binarySearch(num, nums2)) {
                intersection.add(num);
            }
        }
        // convert set to int array
        int[] result = new int[intersection.size()];
        int i = 0;
        for (Integer num : intersection) {
            result[i] = num;
            i++;
        }
        return result;
    }

    // use binary search to check if num in nums
    private boolean binarySearch(int num, int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == num) {
                return true;
            } else if (nums[mid] < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        IntersectionofTwoArrays Solution = new IntersectionofTwoArrays();
        int[] result = Solution.intersection(nums1, nums2);
        int[] result1 = Solution.intersectionSortedMerge(nums1, nums2);
        int[] result2 = Solution.intersectionBinarySearch(nums1, nums2);
        System.out.println(result);
    }

}
