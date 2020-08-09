// Write a program to find the n-th ugly number.
//    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
//    Input: n = 10
//    Output: 12
//    Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
//  Note:
//    1 is typically treated as an ugly number.
//    n does not exceed 1690.
//    14 is not ugly number, since 14 = 2*7, not only include [2, 3, 5]

import java.util.*;


public class UglyNumberII {
    // byte: 8-bit, -256 ~ +256
    // int: 4 bytes, 32-bit: -2^31 ~ 2^31 -1
    // long: 8 bytes, 64-bit: -2^63 ~ 2^63 - 1
    public int nthUglyNumber(int n) {
        // priorityQueue: O(logN) add, O(N) remove, O(1) find Min
        // [1] => take out 1 to multiply (2,3,5)
        // 1*2, 1*3, 1*5, => [2, 3, 5] => take 2 to *(2,3,5)
        // [2*2, 2*3, 2*5, 3, 5] => take minimum (3) to *(2,3,5)
        // [3*2, 3*3, 3*5, 4, 6, 10, 5] => take minimum(4), and remove duplicates

        // use PriorityQueue(MinHeap) to take current queue
        // HashSet to keep all ugly numbers
        if (n <= 0) return -1;

        Queue<Long> queue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        Long[] factors = new Long[]{2L, 3L, 5L};

        for (int i = 0; i < 3; i ++) {
            queue.add(factors[i]);
            set.add(factors[i]);
        }

        Long minUglyNum = 1L;
        // get minUglyNumber (n-1) times -> last time is the n-th ugly number
        for (int i = 1; i < n; i ++) {
            // get the head and remove it (the min)
            minUglyNum = queue.poll();
            for (int j = 0; j < 3; j ++) {
                Long newUglyNum = minUglyNum * factors[j];
                if (! set.contains(newUglyNum)) {
                    queue.add(newUglyNum);
                    set.add(newUglyNum);
                }
            }
        }
        return minUglyNum.intValue();
    }
    // O(nlogn) time, O(n) space

    public static void main(String[] args) {
        UglyNumberII obj = new UglyNumberII();
        System.out.println(obj.nthUglyNumber(100));
    }
}
