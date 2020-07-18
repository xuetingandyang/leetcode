// Given an array nums of integers and an int k,
// partition the array (i.e move the elements in "nums") such that:
//    All elements < k are moved to the left
//    All elements >= k are moved to the right
//    Return the partitioning index, i.e the first index i nums[i] >= k.
//
//    You should do really partition in array nums instead of
//    just counting the numbers of integers smaller than k.
//
//    If all elements in nums are smaller than k, then return nums.length

//    Input:
//    [3,2,2,1],2
//    Output: 1
//    the real array is[1,2,2,3].So return 1
//    Challenge:
//       Can you partition the array in-place and in O(n)?

public class PartitionArray {
    public static int partitionArray(int[] nums, int k) {
        // Two pointers
        // after partition, the order is not guaranteed
        // time O(n), space: O(1)

        if (nums == null || nums.length == 0) return -1;

        int i = 0, j = nums.length - 1;
        while (i <= j) {
            // for satisfied elements, keep unchanged
            while (i <= j && nums[i] < k) i ++;
            while (i <= j && nums[j] >= k) j --;
            // other elements, switch them
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i ++; j --;
            }
        }
        // < i: all items < k, so return i
        return i;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {5, 7, 6, 3, 3, 4, 1, 2};
        int target = 5;
        int idx = partitionArray(nums, target);
        System.out.println(idx);
    }
}
