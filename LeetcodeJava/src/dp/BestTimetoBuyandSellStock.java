package dp;

public class BestTimetoBuyandSellStock {
    /* Stock problem: 3 states, 2 choices
     * dp[i][k][0 or 1]: profit of i-th day, have conducted AT MOST k transactions so far,
     *                   0: no stocks, 1: have stocks
     * Base cases:
     *   dp[0][k][0] = dp[i][0][0] = 0
     *   dp[0][k][1] = dp[i][0][1] = Integer.MIN_VALUE (means: impossible)
     * State transform Function:
     *   dp[i][k][0] = MAX(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *     day-(i-1) no stocks or sell at day-i, Note: only change k for 'buy'
     *   dp[i][k][1] = MAX(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *         i-1   have stocks or but at day-i
     * Return:
     * dp[n][Knums][0] - profit of last day with 'Knums' transactions so far, with out stocks
     *
     * for this problem, K = 1, dp[i-1][0][0] = 0
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     *             =                  , - prices[i])
     * we can find all k is 1, so get rid of k
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], - prices[i])
     */
    public int maxProfit(int[] prices) {
//         int n = prices.length;
//         int[][] dp = new int[n + 1][2];
//         // base cases
//         dp[0][0] = 0;
//         dp[0][1] = Integer.MIN_VALUE;

//         for (int i = 1; i <= n; i ++) {
//             // prices start with 0-index
//             dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-1]);
//             dp[i][1] = Math.max(dp[i-1][1], - prices[i-1]);
//         }
//         return dp[n][0];

        // Actually, we only neet 2 previous var to keep track of yesterday's states
        int dp0 = 0, dp1 = Integer.MIN_VALUE;
        for (int i = 1; i <= prices.length; i ++) {
            dp0 = Math.max(dp0, dp1 + prices[i - 1]);
            dp1 = Math.max(dp1, - prices[i - 1]);
        }
        return dp0;
    }
}
