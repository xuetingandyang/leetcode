package hirevue;
/* Given a number n, count # of ways that:
*    sum of each pair of two neighbor num is a prime number
*  eg: num = 4
*  possible combinations are 2: 1,2,3,4  &  1,4,3,2
*  Note: first and last num are also "neighbors"
*
*  2 <= num <= 18, num is a even number
* */

import javax.lang.model.util.Types;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ConsecutivePrimes {
    // get all possible primes (largest sum = 18+17 = 35)
    // => find all prime numbers in [2, 35]
    private Set<Integer> primes = new HashSet<>(Arrays.asList(2,3,5,7,11,13,17,19,23,29,31));
    private int result;
    private int helper(int[] nums, int idx) {
        // return ways of consecutive primes for nums[idx:]
        if (nums.length == idx) { // reach the last one
            if (primes.contains(1 + nums[nums.length - 1])) return 1;
            else return 0;
        }
        result = 0;
        // if sum of (curt, prev) is prime number => recursively go to next one
        if (primes.contains(nums[idx] + nums[idx - 1])) {
            result += helper(nums, idx + 1);
        }
        // check other numbers: if prev = odd the next should be even
        // if prev = even, next should be odd
        // => +2 to skip in our loop
        for (int i = idx + 2; i < nums.length; i += 2 ) {
            if (primes.contains(nums[idx - 1] + nums[i])) {
                // swap [i] and [idx]
                int temp = nums[i];
                nums[i] = nums[idx];
                nums[idx] = temp;

                result += helper(nums, idx + 1);
                // swap back after recursive call
                temp = nums[i];
                nums[i] = nums[idx];
                nums[idx] = temp;
            }
        }
        return result;
    }

    public int solution(int num) {
        // if num is odd => not possible
        // 1,2,3,4,5
        int[] nums = new int[num];
        for (int i = 0; i < num; i ++) {
            nums[i] = i + 1;
        }
        if (num % 2 == 1) return 0;
        else return helper(nums, 1);
    }

    public static void main(String[] args) {
        ConsecutivePrimes obj = new ConsecutivePrimes();
        System.out.println(obj.solution(2)); // 1
        System.out.println(obj.solution(4)); // 2
        System.out.println(obj.solution(5)); // 0
        System.out.println(obj.solution(16)); // 81024
        System.out.println(obj.solution(18)); // 770144

    }
}
