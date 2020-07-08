// Given a sorted array nums, remove the duplicates in-place
// such that each element appear only once and return the new length.
//
//   Do not allocate extra space for another array,
//   you must do this by modifying the input array in-place with O(1) extra memory.

//        Given nums = [1,1,2],
//        Your function should return length = 2,
//        with the first two elements of nums being 1 and 2 respectively.
//        It doesn't matter what you leave beyond the returned length.

//        Given nums = [0,0,1,1,1,2,2,3,3,4],
//        Your function should return length = 5,
//        with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
//        It doesn't matter what values are set beyond the returned length.

public class RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums) {
        // Two Pointers: slow runner i, fast runner j
        // when i == j, j++ to skip duplicate
        // when i != j, i++, update nums[i]

        int i = 0;
        for (int j = 1; j < nums.length; j ++) {
            if (nums[j] != nums[i]) {
                i ++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public int removeDuplicates2(int[] nums) {
        // more easy! must understand!
        int n = nums.length, i = 0, j = 0;

        while (i < n && j < n) {
            // j points to next unique element
            while (j < n - 1 && nums[j] == nums [j + 1]) j ++;

            // i stores current unique element
            nums[i] = nums[j];
            // move next step
            i ++; j ++;
        }
        // final i = uniqye.length - 1
        // len = i + 1 (alredy i ++, so return i)
        return i;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        RemoveDuplicatesfromSortedArray Solution = new RemoveDuplicatesfromSortedArray();
//        int len = Solution.removeDuplicates(nums);
        int len = Solution.removeDuplicates2(nums);
        System.out.println(len);
    }
}
