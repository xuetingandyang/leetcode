package dp;
//Say you have an array for which the i-th element is the price of a given stock on day i.
//Design an algorithm to find the maximum profit. You may complete at most k transactions.
//Note:
//You may not engage in multiple transactions at the same time
// (ie, you must sell the stock before you buy again).
public class BestTimetoBuyandSellStockIV {
    // to avoid memory limit exceed
    // since a transaction contains 1 buy & 1 sell = 2 days
    // so actual k <= n / 2 (n: days number)
    // if k > n/2, we can skip k, since it has no effect on dp[]

    // Time: O(nk) for k <= n/2, O(n) for k > n/2
    // space: O(nk)
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        if (k > n / 2) return maxProfitInfiniteK(prices);

        int[][][] dp = new int[n + 1][k + 1][2];
        // base cases
        // dp[i][0][0] = 0, dp[i][0][1] = MIN_VALUE;
        // dp[0][j][0] = 0, dp[0][j][1] = MIN_VALUE;
        for (int i = 0; i <= n; i ++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        for (int j = 0; j <= k; j ++) {
            dp[0][j][0] = 0;
            dp[0][j][1] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= n; i ++) {
            for (int j = 1; j <= k; j ++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i-1]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i-1]);
            }
        }
        return dp[n][k][0];
    }

    private int maxProfitInfiniteK(int[] prices) {
        // dp[0][0] = 0, dp[0][1] = MIN_VALUE
        // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
        // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
        int dp0 = 0, dp1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i ++) {
            int temp = dp0;
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, temp - prices[i]);
        }
        return dp0;
    }
}
