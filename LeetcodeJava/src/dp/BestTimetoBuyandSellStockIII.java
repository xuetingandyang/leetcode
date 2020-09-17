package dp;
//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
//Note: You may not engage in multiple transactions at the same time
// (i.e., you must sell the stock before you buy again).
//Input: prices = [3,3,5,0,0,3,1,4]
//Output: 6
//Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

//Input: prices = [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//Note that you cannot buy on day 1, buy on day 2 and sell them later,
// as you are engaging multiple transactions at the same time. You must sell before buying again.
public class BestTimetoBuyandSellStockIII {
    /* At most 2 transactions: Knums = 2
dp[i][k][0] = MAX(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][0] = MAX(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
*/
    public int maxProfit(int[] prices) {
//         int maxTrans = 2, n = prices.length;
//         int[][][] dp = new int[n + 1][maxTrans + 1][2];
//         // base cases
//         // dp[0][k][0] = 0, dp[0][k][1] = MIN_VALUE
//         // dp[i][0][0] = 0, dp[i][0][1] = MIN_VALUE
//         for (int k = 0; k <= maxTrans; k ++) {
//             dp[0][k][0] = 0;
//             dp[0][k][1] = Integer.MIN_VALUE;
//         }
//         for (int i = 0; i <= n; i ++) {
//             dp[i][0][0] = 0;
//             dp[i][0][1] = Integer.MIN_VALUE;
//         }

//         for (int i = 1; i <= n; i ++) {
//             for (int k = 1; k <= maxTrans; k ++) {
//                 dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i - 1]);
//                 dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k - 1][0] - prices[i - 1]);
//             }
//         }
//         return dp[n][maxTrans][0];

        // since maxTrans = 2 (small), we can just list all possible k
        int dp10 = 0, dp20 = 0;
        int dp11 = Integer.MIN_VALUE, dp21 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i ++) {
            dp10 = Math.max(dp10, dp11 + prices[i]);
            dp11 = Math.max(dp11, - prices[i]); // dp00 = 0
            dp20 = Math.max(dp20, dp21 + prices[i]);
            dp21 = Math.max(dp21, dp10 - prices[i]);
        }

        return dp20;
    }
}
