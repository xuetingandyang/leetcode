// method1: Brute-force O(n^2), O(1)
// class Solution {
//     public int[] twoSum(int[] nums, int target) {
//         int[] res = new int[2];
//         for (int i = 0; i < nums.length; i++) {
//             for (int j = i + 1; j< nums.length; j++) {
//                 if (nums[i] + nums[j] == target) {
//                     res[0] = i;
//                     res[1] = j;
//                     return res;
//                 }
//             }
//         }
//         return res;
//     }
// }

// method2: hash-table
// class Solution {
//     public int[] twoSum(int[] nums, int target) {
//         Map<Integer, Integer> map = new HashMap<>();

//         // key: numer, value: index
//         for (int i = 0; i < nums.length; i++) {
//             map.put(nums[i], i);
//         }

//         // search for key 'target - nums[i]'
//         for (int i = 0; i < nums.length; i++) {
//             int curTarget = target - nums[i];
//             if (map.containsKey(curTarget) && map.get(curTarget) != i) {
//                 return new int[] {i, map.get(curTarget)};
//             }
//         }

//         throw new IllegalArgumentException("No two sum solution");
//     }
// }

// time: hashmap lookup: O(1) -> O(n) + o(n) = O(n)
// space: hashmap: O(n)

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

// method3: one-pass hash table
// check key 'target - num[i]' first, then put new element in map
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] rst;

        for (int i = 0; i < nums.length; i++) {
            int curTarget = target - nums[i];
            if (map.containsKey(curTarget)) {
                rst = new int[] {i, map.get(curTarget)};
                return rst;
            }

            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }
}

public class TwoSum {
    public static void main(String[] args) {
        // you do not need to println the int[] results.
        // just in debug mode, and then see the results in debug variables.

        Solution answer = new Solution();
        int[] nums = new int[] {3, 4, 5, 6};
        int target = 10;

        int[] rst= answer.twoSum(nums, target);

        /* Test println function and toString() method  */
        System.out.println(rst);    // [I@6537cf78
        System.out.println(Arrays.toString(rst));   // [1, 2]

        List<Integer>[] list = new List[3];
        for(int i = 0; i < 3; i++){
            list[i] = new ArrayList<>() {{add(1); add(2);}};
        }
        System.out.println(list);  // [Ljava.util.List;@34b7bfc0
        System.out.println(Arrays.toString(list));  // [[1, 2], [1, 2], [1, 2]]


    }
}
