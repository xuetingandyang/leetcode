// Given two sorted integer arrays nums1 and nums2,
// merge nums2 into nums1 as one sorted array.
//    The number of elements initialized in nums1 and nums2 are m and n respectively.
//    You may assume that nums1 has enough space (size that is equal to m + n)
//    to hold additional elements from nums2.

//    Input:
//    nums1 = [1,2,3,0,0,0], m = 3
//    nums2 = [2,5],         n = 2
//    Output: [1,2,2,3,5]

import java.util.Arrays;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i ++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    // Two pointers
    public void mergeTwoPointers(int[] nums1, int m, int[] nums2, int n) {
        // start from head: need extra O(m) space to store nums1
        // start from tail: NO extra space!
        // 2 pointers: p1, p2, set initial value to last index
        int p1 = m - 1, p2 = n - 1;

        // pointer 'p' for merged list
        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p --;
                p1 --;
            } else {
                nums1[p] = nums2[p2];
                p --;
                p2 --;
            }
            // neat one-line version:
            // nums1[p--] = (nums1[p1] > nums2[p2]) ? nums1[p1--] : nums2[p2--];
        }
        // if nums2 has kelft, add left nums2 into nums1
        if (p2 >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        }

    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};

        MergeSortedArray Solution = new MergeSortedArray();
//        Solution.merge(nums1, 3, nums2, 3);
        Solution.mergeTwoPointers(nums1, 3, nums2, 3);
        System.out.println(nums1);



    }
}
