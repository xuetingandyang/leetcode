// Given a m x n grid filled with non-negative numbers,
// find a path from top left to bottom right
// which minimizes the sum of all numbers along its path.
//
//    Note: You can only move either down or right at any point in time.
//    Input:
//    [
//    [1,3,1],
//    [1,5,1],
//    [4,2,1]
//    ]
//    Output: 7
//    Explanation: Because the path 1→3→1→1→1 minimizes the sum.

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        // dp, top-bottom
        int rowNum = grid.length;
        int colNum = grid[0].length;
        int[][] dp = new int[rowNum][colNum];

        // initialize
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rowNum; i ++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < colNum; j ++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        // function dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        for (int i = 1; i < rowNum; i ++) {
            for (int j = 1; j < colNum; j ++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[rowNum - 1][colNum - 1];



        // no extra space, directly modify 'grid' array
//        int rowNum = grid.length;
//        int colNum = grid[0].length;
//        // initialize
//        for (int i = 1; i < rowNum; i ++) {
//            grid[i][0] += grid[i - 1][0];
//        }
//        for (int j = 1; j < colNum; j ++) {
//            grid[0][j] += grid[0][j-1];
//        }
//
//        // function dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
//        for (int i = 1; i < rowNum; i ++) {
//            for (int j = 1; j < colNum; j ++) {
//                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
//            }
//        }
//        return grid[rowNum - 1][colNum - 1];
    }

    public int minPathSumBottomUp(int[][] grid) {
        // dp, bottom-up
        int rowNum = grid.length;
        int colNum = grid[0].length;
        int[][] dp = new int[rowNum][colNum];

        // initialize: dp[i][0], dp[0][j]
        dp[rowNum - 1][colNum - 1] = grid[rowNum - 1][colNum - 1];

        for (int i = rowNum - 2; i >= 0; i --) {
            dp[i][colNum-1] = dp[i + 1][colNum-1] + grid[i][colNum-1];
        }
        for (int j = colNum - 2; j >= 0; j --) {
            dp[rowNum-1][j] = dp[rowNum-1][j + 1] + grid[rowNum-1][j];
        }

        // function
        for (int i = rowNum - 2; i >= 0; i --) {
            for (int j = colNum - 2; j >= 0; j --) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i][j+1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }
}
