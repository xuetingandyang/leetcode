package binarysearch;
//34. Find First and Last Position of Element in Sorted Array
//Given an array of integers nums sorted in ascending order,
// find the starting and ending position of a given target value.

//Your algorithm's runtime complexity must be in the order of O(log n).
//If the target is not found in the array, return [-1, -1].

//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4]

//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1]


import java.lang.module.FindException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Arrays;

public class FirstAndLastPositionInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int [] rst = {-1, -1};
        int firstIdx = searchFirst(nums, target);
        int lastIdx = searchLast(nums, target);
        if (firstIdx != -1 && lastIdx != -1) {
            rst[0] = firstIdx;
            rst[1] = lastIdx;
        }
        return rst;
    }

    int searchFirst(int[] nums, int target) {
        // find the first occurred index of target
        // binary search
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
//            if (nums[mid] < target) start = mid;
//            else if (nums[mid] > target) end = mid;
//            else end = mid; // firstIdx may occur before mid, so set end to mid
            if (nums[mid] >= target) end = mid;
            else start = mid;
        }
        // left 2 elements: start & end
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }

    int searchLast(int[] nums, int target) {
        // find the first occurred index of target
        // binary search
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
//            if (nums[mid] < target) start = mid;
//            else if (nums[mid] > target) end = mid;
//            else start = mid; // lastIdx may occur after mid, so set start to mid
            if (nums[mid] <= target) start = mid;
            else end = mid;
        }
        // left 2 elements: start & end
        if (nums[end] == target) return end;
        if (nums[start] == target) return start;

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8,8,8, 10};
        int target = 10;
        FirstAndLastPositionInSortedArray obj = new FirstAndLastPositionInSortedArray();
        int[] rst = obj.searchRange(nums, target);
        System.out.println(rst[0]);
        System.out.println(rst[1]);

        System.out.println(obj.searchLast(nums, target));
    }
}
