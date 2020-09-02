package amazonOA;

// Given a matrix with r rows and c columns, find the maximum score of
// a path starting at [0, 0] and ending at [r-1, c-1].
// The score of a path is the minimum value in that path.
// For example, the score of the path 8 → 4 → 5 → 9 is 4.
// Don't include the first or final entry. You can only move either down or right at any point in time.

//       [[1, 2, 3]
//        [4, 5, 1]]
//        Output: 4
//        Explanation:
//        Possible paths:
//        1-> 2 -> 3 -> 1
//        1-> 2 -> 5 -> 1
//        1-> 4 -> 5 -> 1
//        So min of all the paths = [2, 2, 4]. Note that we don't include the first and final entry.
//        Return the max of that, so 4.

public class MaxOfMinAltitudes {
    public static int maxScore2D(int[][] grid) {
        // dp[i,j]: max of min of path from (0,0) to (i, j)
        // dp[i,j] = max( min(dp[i-1, j], grid[i][j]), min(dp[i, j-1], grid[i][j]) )
        // Note: process dp[0][0], dp[m-1][n-1] separately
        if (grid == null || grid.length == 0) return -1;
        int m = grid.length, n = grid[0].length;
        if ((m == 1 && n == 1) || (m == 1 && n == 2) || (m == 2 && n == 1)) return -1;

        int[][] dp = new int[m][n];
        // init cannot reach elements: dp[0,0], dp[0,1], dp[1,0]
        dp[0][0] = grid[0][0];
        dp[1][0] = grid[1][0];
        dp[0][1] = grid[0][1];
        // init: 0-col
        for (int i = 2; i < m; i ++) {
            dp[i][0] = Math.min(grid[i][0], dp[i - 1][0]);
        }
        // init: 0-row
        for (int j = 2; j < n; j ++) {
            dp[0][j] = Math.min(grid[0][j], dp[0][j - 1]);
        }

        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                if (i == m - 1 && j == n - 1) {
                    // for the last element, gird[m-1][n-1] should not be considered
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    dp[i][j] = Math.max(Math.min(dp[i - 1][j], grid[i][j]), Math.min(dp[i][j - 1], grid[i][j]));
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] num1=new int[][]{{5, 1}, {4, 5}};
        int[][] num2=new int[][]{{5, 1, 7}, {4, 8, 5}};
        int[][] num3=new int[][]{{1, 9, 9}, {9, 9, 9}, {9, 9, 9}};
        int[][] num4=new int[][] {{10, 7, 3}, {12, 11, 9}, {1, 2, 8}};
        int[][] num5=new int[][]{{20, 20, 3}, {20, 3, 20}, {3, 20, 20}};
        int[][] num6=new int[][]{{1, 2, 3}, {4, 5, 1}};
        int[][] num7=new int[][]{{5, 1}};
        int[][] num8=new int[][]{{5, 1}};
        int[][] num9=new int[][]{{5}, {1}};
        int[][] num10=new int[][]{{1}};
        int[][] num11=new int[][]{};
        System.out.println(4 == maxScore2D(num1));
        System.out.println(4 == maxScore2D(num2));
        System.out.println(9 == maxScore2D(num3));
        System.out.println(9 == maxScore2D(num4));
        System.out.println(3 == maxScore2D(num5));
        System.out.println(4 == maxScore2D(num6));
        System.out.println(-1 == maxScore2D(num7));
        System.out.println(-1 == maxScore2D(num8));
        System.out.println(-1 == maxScore2D(num9));
        System.out.println(-1 == maxScore2D(num10));
        System.out.println(-1 == maxScore2D(num11));
    }
}
