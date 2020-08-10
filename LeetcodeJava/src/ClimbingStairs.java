// You are climbing a stair case.
// It takes n steps to reach to the top.
//    Each time you can either climb 1 or 2 steps.
//    In how many distinct ways can you climb to the top?

//    Input: 2
//    Output: 2
//    Explanation: There are two ways to climb to the top.
//    1. 1 step + 1 step
//    2. 2 steps

//    Input: 3
//    Output: 3
//    Explanation: There are three ways to climb to the top.
//    1. 1 step + 1 step + 1 step
//    2. 1 step + 2 steps
//    3. 2 steps + 1 step

public class ClimbingStairs {
    public int climbStairs(int n) {
        // DP.    To reach i-th step:
        // 1. take one step from (i-1)th
        // 2. take 2 steps from (i-2)th
        // unique steps in (i) = unique steps(i-1) + unique steps(i-2)
        if (n <= 0) return -1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i ++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    public int climbStairsFibonacci(int n) {
        // dp[i] = dp[i-1] + dp[i-2]
        // actually it is Fibonacci Number
        if (n == 1) return 1;
        if (n == 2) return 2;
        int first = 1, second = 2, result = 0;
        for (int i = 3; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }
}

