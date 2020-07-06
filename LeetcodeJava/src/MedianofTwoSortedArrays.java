// There are two sorted arrays nums1 and nums2 of size m and n respectively.
//    Find the median of the two sorted arrays.
//    The overall run time complexity should be O(log (m+n)).
//    You may assume nums1 and nums2 cannot be both empty.

//    nums1 = [1, 3]
//    nums2 = [2]
//    The median is 2.0

//    nums1 = [1, 2]
//    nums2 = [3, 4]
//    The median is (2 + 3)/2 = 2.5

// Hard question. check leetcode discussion solution.

public class MedianofTwoSortedArrays {

    double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) { // ensure n >= m, switch nums1 and nums2
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;

            int tempInt = m;
            m = n;
            n = tempInt;
        }
        int imin = 0, imax = m, mid = (m + n + 1) / 2;
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = mid - i;

            if (i < imax && nums2[j - 1] > nums1[i]) {
                // case-1: i is too small
                imin = i + 1;
            } else if (i > imin && nums1[i - 1] > nums2[j]) {
                // case-2: i is too big
                imax = i - 1;
            } else {
                // case-3 find the i
                int maxLeft = 0;    // left node of median range
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                // if odd elements
                if ((m + n) % 2 == 1) return maxLeft;

                int minRight = 0;   // right node of median range
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                // if even elements
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};

        MedianofTwoSortedArrays Solution = new MedianofTwoSortedArrays();
        double median = Solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(median);


    }
}
