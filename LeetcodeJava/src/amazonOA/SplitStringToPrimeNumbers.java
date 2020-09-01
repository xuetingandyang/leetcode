package amazonOA;
// Given a numeric string str, the task is to count the number of ways
// the given string can be split, such that each segment is a prime number.
// Since the answer can be large, return the answer modulo 10^9 + 7.
//    Note: A split that contains numbers with leading zeroes will be invalid
//    and the initial string does not contain leading zeroes.
//
//    Input: str = “3175”
//    Output: 3
//    Explanation:
//    There are 3 ways to split this string into prime numbers which are (31, 7, 5), (3, 17, 5), (317, 5).
//
//    Input: str = “11373”
//    Output: 6
//    Explanation:
//    There are 6 ways to split this string into prime numbers which are (11, 3, 7, 3), (113, 7, 3), (11, 37, 3), (11, 3, 73), (113, 73) and (11, 373).

import java.util.ArrayList;
import java.util.List;

public class SplitStringToPrimeNumbers {
    private final int MOD = 1000000007;

    private boolean isPrimeNum(String s) {
        int num = Integer.parseInt(s);
        if (num <= 1) return false;

        for (int i = 2; i * i <= num; i ++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private void traverseConcrete(String s, int start, List<List<String>> rst, List<String> temp) {
        // count # of prime number of s[start:]
        // recursion exit - partition should contains ALL character
        if (start == s.length()) {
            rst.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < s.length(); i ++) {
            String sub = s.substring(start, i + 1);
            if (s.charAt(start) != '0' && isPrimeNum(sub)) {
                temp.add(sub);
                traverseConcrete(s, i + 1, rst, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private int count = 0;
    private void traverseCount(String s, int start) {
        if (start == s.length()) {
            count++;
            return;
        };
        for (int i = start; i < s.length(); i ++) {
            if (s.charAt(start) != '0' && isPrimeNum(s.substring(start, i + 1)))
                traverseCount(s, i + 1);
        }
    }


    public int countWaysToPrimeNumber(String s) {
        // prime number: i can only be split to 1*i.
        // proof: in [2, sqrt(n)], cannot find a number that can be divided by 'i'
        // count all solutions => DFS-recursion

        if (s == null || s.length() == 0) return 0;
//        List<List<String>> rst = new ArrayList<>();
//        // can find all the partitions
//        traverseConcrete(s, 0, rst, new ArrayList<>());
//        return rst.size();

        // Or just count the number of solutions
        traverseCount(s, 0);
        return count;
    }

//    public int countWaysToPrimeNumberDP(String s) {
//        // count number of solutions, not concrete solution => DP
//        // dp[i]: number of prime numbers, string s[:i]
//    }

    public static void main(String[] args) {
        String s = "3175";
        System.out.println(new SplitStringToPrimeNumbers().countWaysToPrimeNumber(s));
        s = "11373";
        System.out.println(new SplitStringToPrimeNumbers().countWaysToPrimeNumber(s));
    }
}
