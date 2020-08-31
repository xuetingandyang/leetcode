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

public class SplitStringToPrimeNumbers {
    private final int MOD = 1000000007;
    private int count;

    private boolean isPrimeNum(String s) {
        int num = Integer.parseInt(s);
        if (num <= 1) return false;

        for (int i = 2; i * i <= num; i ++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private void traverse()

    public int countWaysToPrimeNumber(String s) {
        // prime number: i can only be split to 1*i.
        // proof: in [2, sqrt(n)], cannot find a number that can be divided by 'i'
        // count all solutions => DFS-recursion
        count = 0;
        if (s == null || s.length() == 0) return count;
        int n = s.length();
        for (int i = 1; i < n; i ++) {
            if (s.charAt(n - i) != '0' && isPrimeNum(s.substring(n - i, n))) {
                count += countWaysToPrimeNumber(s.substring(i));
//                count %= MOD;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "3175";
        System.out.println(new SplitStringToPrimeNumbers().countWaysToPrimeNumber(s));
        s = "11373";
        System.out.println(new SplitStringToPrimeNumbers().countWaysToPrimeNumber(s));
    }
}
