package codesignal;

import java.util.*;

// Suppose we have array a and b (no duplicates & sorted)
//    a = [0,4,8,20]
//    b = [5,7,12,16,22]
//   Suppose you can pick any number of element from b (could be 0),
//   and you want to insert them into array a such that all elements in a are increasing by certain number,
//    so in this example, we can pick "12, 16" from b and append into a
//    such that a = [0,4,8,12,16,20], which increase by 4 for each element
//
//    write a function to return the maximum number of element in a
//    after appending elements from b (in the example above the result is 6),
//    if there is no such case, return -1


public class MaxArithmeticLen {
    private int gcd(int a, int b) {
        // get Greatest common divisor of a, b
        if(a == 0 || b == 0) return a + b; // base case
        return gcd(b,a % b);
    }

    // since there are no duplicates in a and b, this function will never be called
//    private boolean checkPossible (int[] a) {
//        // if a= [1,1,1,2,2,3] then return -1
//        // return true if there are >= 1 pair of same numbers
//        Map<Integer, Integer> unique = new HashMap<>();
//        for (int A : a) {
//            unique.put(A, unique.getOrDefault(A, 0) + 1);
//        }
//        for (int num : unique.keySet()) {
//            if (unique.get(num) > 1 ) return false;
//        }
//        return true;
//    }

    public int maxArithmeticLengthGCD(int[] a, int[] b) {
//        if (! checkPossible(a)) return -1;
        // for loop each factors in [1, gcd(diff)]
        Set<Integer> factorsOfDiff = new HashSet<>();
        // a, b are already sorted, and no duplicates
//        Arrays.sort(a);
//        Arrays.sort(b);

        if (a.length > 1) {
            int num = a[1] - a[0];
            for (int i = 2; i < a.length; i ++) {
                num = gcd(num, a[i] - a[i-1]);
            }
            for (int i = 1; i < num + 1; i ++) {
                if (num % i == 0) factorsOfDiff.add(i);
            }
        }

        int maxLen = -1;
        for (int factor : factorsOfDiff) {
            int cur = a[0] + factor;
            int length = 1;

            for (int A : a) {
                for (int B : b) {
                    if (cur == A || cur == B) {
                        length ++;
                        cur += factor;
                    }
                }
            }

            if (cur >= a[a.length - 1]) {
                cur = a[0] - factor;
                for (int B : b) {
                    if (cur == B) {
                        length ++;
                        cur -= factor;
                    }
                }
                maxLen = Math.max(maxLen, length);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
//        int[] a = {0,8,12,16};
//        int[] b = {0,2,4,12,14,20};
//        int[] a = {0,4,8,20};
//        int[] b = {5,7,12,16,22};

        int[] a = {1,2,3,4};
        int[] b = {6, 8, 10};
        MaxArithmeticLen maxArithmeticLen = new MaxArithmeticLen();
        int rst = maxArithmeticLen.maxArithmeticLengthGCD(a, b);
        System.out.println(rst);
    }
}
