package dp;

public class BestTimetoBuyandSellStockII {
    /* K = Integer.MAX_VALUE -> k will not affect our choice, so we can skip k
     * base cases:
     *     dp[0][0] = 0, dp[0][1] = MIN_VALUE
     * dp[i][0] = MAX(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = MAX(dp[i-1][1], dp[i-1][0] - prices[i])
     */
    public int maxProfit(int[] prices) {
        // just keep yesterday's states (2 vars) are enough
        int dp0 = 0, dp1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i ++) {
            int temp = dp0;
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, temp - prices[i]);
        }
        return dp0;
    }
}
