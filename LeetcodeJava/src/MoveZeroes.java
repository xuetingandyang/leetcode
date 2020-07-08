// Given an array nums, write a function to move all 0's to the end of it
// while maintaining the relative order of the non-zero elements.
//    Input: [0,1,0,3,12]
//    Output: [1,3,12,0,0]

//    You must do this in-place without making a copy of the array.
//    Minimize the total number of operations.

import java.util.Arrays;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        // Two pointers
        // i points to next first non-zero element
        // j                      zero            (must before i)
        // switch i and j elements

        int n = nums.length, i = 0, j = 0;

        while (i < n && j < n) {
            while (i < n && nums[i] == 0) i ++;
            while (j < i && nums[j] != 0) j ++;


            // swap nums[i] and nums[j]
            if (i < n && j < n) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                // move next step
                i ++; j ++;
            }
        }
    }

    public void moveZeroesOnePass(int[] nums) {
        // Two Pointers: i, j (j < i)
        // any nodes before j are non-zero
        // anu nodes between j and i are zero
        // non-zero ... j .. zero, .., i, unknown

        int j = 0;
        for (int i = 0; i < nums.length; i ++) {
            // for non-zero nodes, i, j ++
            if (nums[i] != 0) {
                if (i != j) {
                    // swap nums[i], nums[j]
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
                j ++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 0, 3, 12};
        MoveZeroes Solution = new MoveZeroes();
//        Solution.moveZeroes(nums);
        Solution.moveZeroesOnePass(nums);
        System.out.println(nums);
    }
}
