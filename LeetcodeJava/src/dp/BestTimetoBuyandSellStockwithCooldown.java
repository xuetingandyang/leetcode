package dp;
//Say you have an array for which the ith element is the price of a given stock on day i.
//    Design an algorithm to find the maximum profit.
//    You may complete as many transactions as you like
//    (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
//    You may not engage in multiple transactions at the same time
//    (ie, you must sell the stock before you buy again).
//    After you sell your stock, you cannot buy stock on next day.
//    (ie, cooldown 1 day)
//    Input: [1,2,3,0,2]
//    Output: 3
//    Explanation: transactions = [buy, sell, cooldown, buy, sell]
public class BestTimetoBuyandSellStockwithCooldown {
    /* Cooldown: only chnages dp[i][k][1]
    dp[i][k][1] = MAX(dp[i-1][k][1], dp[i-2][k-1][0] - prices[i])
    (i-2 not i-1)
    since we can have infinity transactions, we can skip k
    */
    public int maxProfit(int[] prices) {
        int dp0 = 0, dp1 = Integer.MIN_VALUE;
        int prev0 = 0; // record dp[i-2][0]
        for (int i = 0; i < prices.length; i ++) {
            int temp = dp0;
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, prev0 - prices[i]);
            // update dp[i-2][0]
            prev0 = temp;
        }
        return dp0;
    }
}
