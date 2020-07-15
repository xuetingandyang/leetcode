// Given an array consists of non-negative integers, your task is to
// count the number of triplets chosen from the array that can make triangles
//
//    Input: [2,2,3,4]
//    Output: 3
//    Valid combinations are:
//    2,3,4 (using the first 2)
//    2,3,4 (using the second 2)
//    2,2,3

import java.util.Arrays;

public class ValidTriangleNumber {
    public static int triangleNumber(int[] nums) {
        // Two Pointers
        // Sort array, a <= b <= c, if a+b>c then it is triangle
        // p1, .., p'.., p2 ==> if p1+p2>nums[i], then all p'+p2>nums[i] for any p'>p1
        // so triangle count = p2-p1
        Arrays.sort(nums);
        int count = 0, n = nums.length;

        for (int i = 2; i < n; i ++) {
            // a = p1, b = p2, c = i
            // --> so p2 starts from i - 1
            int p1 = 0, p2 = i - 1;
            while (p1 < p2) {
                if (nums[p1] + nums[p2] > nums[i]) {
                    count += p2 - p1;
                    p2 --;
                } else {
                    p1 ++;
                }
            }
        }
        return count;
    }

    public static int triangleNumberBruteForce(int[] nums) {
        // Brute Force
         int count = 0, n = nums.length;
         for (int i = 0; i < n; i ++) {
             for (int j = i + 1; j < n; j ++) {
                 for (int k = j + 1; k < n; k ++) {
                     if (nums[i] + nums[j] > nums[k] && nums[i] + nums[k] > nums[j] && nums[j] + nums[k] > nums[i])
                         count++;
                 }
             }
         }
         return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 2, 3, 4};
        int count = triangleNumber(nums);
        int count1 = triangleNumberBruteForce(nums);
        System.out.println(count);
    }
}
