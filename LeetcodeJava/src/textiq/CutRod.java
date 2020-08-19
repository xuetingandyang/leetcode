package textiq;

// Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
// Determine the maximum value obtainable by cutting up the rod and selling the pieces.

// For example, if length of the rod is 8 and the values of different pieces are given as following,
// then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)

// length (n)   | 0  1   2   3   4   5   6   7   8
// --------------------------------------------
// price (v)    | 0  1   5   8   9  10  17  17  20

// rodcutting(n, v) = 22

// And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight pieces of length 1)

// length (n)  | 0  1   2   3   4   5   6   7   8
// --------------------------------------------
// price (v)   | 0  3   5   8   9  10  17  17  20

// rodcutting(n, v) = 24

// Notice: v.length = n+1, v[0] = 0

public class CutRod {
    static int rodCutting(int n, int[] v) {
        // dp[i]: maxRevenue for a rod of length i
        // dp[j] = max(v[j] + dp[i-j-1]) for all j in [0, i-1]
        if (n == 1) return v[1];

        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i ++) {
            int maxRevenue = Integer.MIN_VALUE;
            for (int j = 0; j < i; j ++) {
                maxRevenue = Math.max(maxRevenue, v[j + 1] + dp[i - j - 1]);
            }
            dp[i] = maxRevenue;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 8;
        int[] v = {0,3,5,8,9,10,17,17,20};

        int result = rodCutting(n ,v);
        System.out.println(result);
    }
}
