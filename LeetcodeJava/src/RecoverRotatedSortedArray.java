// Given a rotated sorted array,
// recover it to sorted array in-place.（Ascending）
//    Example1:
//    [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
//    Example2:
//    [6,8,9,1,2] -> [1,2,6,8,9]

//    Challenge
//    In-place, O(1) extra space and O(n) time.
//
//    What is rotated array?
//      For example, the original array is [1,2,3,4],
//      The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]


import java.util.ArrayList;
import java.util.List;

public class RecoverRotatedSortedArray {
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // Three steps reverse
        // 1. find the rotated point [4,5,1,2,3]
        //    nums[i] > nums[i + 1]
        // 2. Three steps reverse
        //    1) reverse [4,5]   => [5,4,  1,2,3]
        //    2) reverse [1,2,3] => [5,4,   3,2,1]
        //    3) reverse whole   => [1, 2, 3, 4, 5]

//        int pos = -1;   // pos initial is -1, not 0, cause reverse(pos+1)
        // from 0 to size-1
        // For no-reversing arrayLists,
        //  put 3-reverses under for loop
        //  so if no 'pos' found, no processes
        // 将三步翻转放在 for 循环中可以避免单独处理该情况，
        // 也即如果循环判断所有元素都小于下一个元素，那么不会进行任何操作。
        for (int i = 0; i < nums.size() - 1; i ++) {
            if (nums.get(i) > nums.get(i + 1)) {
//                pos = i;
                reverse(nums, 0, i);
                reverse(nums, i + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
                return;
//                break;
            }
        }

//        reverse(nums, 0, pos);
//        reverse(nums, pos + 1, nums.size() - 1);
//        reverse(nums, 0, nums.size() - 1);
    }

    private void reverse(List<Integer> nums, int i, int j) {
        // reverse ArrayList
        while (i < j) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
            // move next step
            i ++; j--;
        }
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of( 6,7, 1, 2, 3, 4, 5));
        RecoverRotatedSortedArray Solution = new RecoverRotatedSortedArray();
        Solution.recoverRotatedSortedArray(nums);
        System.out.println(nums);
    }

}
