package amazonOA;

// Given a string str that represents a large number,
// the task is to find the minimum number of segments the given string can be divided
// such that each segment is a prime number in the range of 1 to 106.
//    Examples:
//    Input: str = “13499315”
//    Output: 3
//    Explanation:
//    The number can be segmented as [13499, 31, 5]
//
//    Input: str = “43”
//    Output: 1
//    Explanation:
//    The number can be segmented as [43]

public class SplitStringToMinPrimes {
    private boolean isPrime(String s) {
        int num = Integer.parseInt(s);
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i ++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public int splitToPrime(String s) {
        int n = s.length(), minSeg = Integer.MAX_VALUE;
        // recursion exit
        if (isPrime(s)) return 1;

        for (int i = 1; i < n; i ++) {
            if (isPrime(s.substring(0, i))) {
                // recursively check remaining string
                int count = splitToPrime(s.substring(i));
                if (count != -1) minSeg = Math.min(minSeg, count + 1);
            }
        }
        return minSeg == Integer.MAX_VALUE ? -1 : minSeg;
    }

    public int splitToPrimeDP(String s) {
        // dp[i]: min number of segments of s[:i] satisfying prime numbers
        // dp[i] = min(dp[k], k in [0, i-1]) + 1, for s[k : i] is prime number
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i ++) {
            if (isPrime(s.substring(0, i))) {
                // if s[:i] is prime number, then the min # of segments: dp[i] = 1
                dp[i] = 1;
            } else {
                int minSeq = Integer.MAX_VALUE;
                for (int k = 0; k < i; k ++) {
                    // if s[k:i] is prime number && s[:k] can be split to prime numbers
                    if (isPrime(s.substring(k, i)) && dp[k] != 0) {
                        minSeq = Math.min(minSeq, dp[k]);
                    }
                }
                dp[i] = minSeq == Integer.MAX_VALUE ? 0 : minSeq + 1;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        String s = "13499315";
        System.out.println(new SplitStringToMinPrimes().splitToPrime(s));
        System.out.println(new SplitStringToMinPrimes().splitToPrimeDP(s));
        s = "43";
        System.out.println(new SplitStringToMinPrimes().splitToPrime(s));
        System.out.println(new SplitStringToMinPrimes().splitToPrimeDP(s));
        s = "3175"; // 317, 5
        System.out.println(new SplitStringToMinPrimes().splitToPrime(s));
        System.out.println(new SplitStringToMinPrimes().splitToPrimeDP(s));
        s = "11373"; // 113, 73 or 11, 373
        System.out.println(new SplitStringToMinPrimes().splitToPrime(s));
        System.out.println(new SplitStringToMinPrimes().splitToPrimeDP(s));
    }
}
