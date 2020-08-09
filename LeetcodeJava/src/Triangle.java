// Given a triangle, find the minimum path sum from top to bottom.
// Each step you may move to adjacent numbers on the row below.
//
//    Bonus point if you are able to do this using only O(n) extra space,
//    where n is the total number of rows in the triangle.

//    Input the following triangle:
//    [
//    [2],
//    [3,4],
//    [6,5,7],
//    [4,1,8,3]
//    ]
//    Output: 11
//    Explanation: The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
//
//    Input the following triangle:
//    [
//    [2],
//    [3,2],
//    [6,5,7],
//    [4,4,8,1]
//    ]
//    Output: 12
//    Explanation: The minimum path sum from top to bottom is 12 (i.e., 2 + 2 + 7 + 1 = 12).

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        // overlapping subproblems
        // suppose x and y are 'children' of k.
        // Once minimum paths from x and y to the bottom are known,
        // the minimum path starting from k can be decided in O(1),
        // that is optimal substructure.
        // => dynamic programming would be the best solution

        // top-bottom
        // 2 special cases: since left-most and right-most nodes only have 1 'parent'
        int rowNum = triangle.size();
        int colNum = triangle.get(rowNum - 1).size();
        int[][] dp = new int[rowNum][colNum];

        // initialize: dp[0][0]
        dp[0][0] = triangle.get(0).get(0);

        // function: dp[i][j] = min( dp[i-1][j], dp[i-1][j-1] ) + triangle[i][j]
        for (int i = 1; i < rowNum; i ++) {
            // special case1: left-most node
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);

            for (int j = 1; j < i; j ++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + triangle.get(i).get(j);
            }

            // special case2: right-most node
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }

        // result: min( dp[rowNum - 1][i] )
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < colNum; i ++) {
            result = Math.min(result, dp[rowNum - 1][i]);
        }
        return result;
    }

    public int minimumTotalBottomUp(List<List<Integer>> triangle) {
        // Bottom-up DP
        int rowNum = triangle.size();
        int colNum = triangle.get(rowNum - 1).size();
        // int[][] dp = new int[rowNum][colNum];
        // we can even make it from 2D to 1D
        int[] dp = new int[colNum];

        // initialize
        for (int i =  0; i < colNum; i ++ ) {
            // dp[rowNum - 1][i] = triangle.get(rowNum - 1).get(i);
            dp[i] = triangle.get(rowNum - 1).get(i);
        }

        // function
        // No special cases, since each node in previous level has 2 'child' nodes
        for (int i = rowNum - 2; i >= 0; i --) {
            for (int j = 0; j <= i; j ++) {
                // dp[i][j] = Math.min( dp[i+1][j], dp[i+1][j+1] ) + triangle.get(i).get(j);
                dp[j] = Math.min( dp[j], dp[j+1] ) + triangle.get(i).get(j);
            }
        }
//        return dp[0][0];
        return dp[0];
    }
}
