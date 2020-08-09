// A robot is located at the top-left corner of a m x n grid
//    The robot can only move either down or right at any point in time.
//    The robot is trying to reach the bottom-right corner of the grid
//    How many possible unique paths are there?
//    Input: m = 3, n = 2
//    Output: 3
//  Explanation:
//   From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
//    1. Right -> Right -> Down
//    2. Right -> Down -> Right
//    3. Down -> Right -> Right

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        // dp, top-bottom
        int count = 0;
        int[][] dp = new int[m][n]; // save # of unique paths

        // initialize
        dp[0][0] = 0;
        // dp[i][0], dp[0][j] = 1
        for (int i = 0; i < m; i ++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j ++) {
            dp[0][j] = 1;
        }

        // function: dp[i][j] = dp[i-1][j] + dp[i][j-1]
        // (i,j) = unique paths in (i-1, j) and (i, j-1)
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
