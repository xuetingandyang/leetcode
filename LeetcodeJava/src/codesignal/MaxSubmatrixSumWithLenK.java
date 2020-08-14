package codesignal;

import java.util.HashSet;
import java.util.Set;

// 给一个Matrix和一个Int k，在这个matrix上找到所有k x k大小的submatrix
// 并计算这个submatrix里面所有值的和，
// 如果这个submatrix的和是最大的话, 则将该submatrix里面所有distinct的value想加，
// 如果存在 "和相同" 的submatrix，则将这个submatrix里面的值 同样加到distinct value上
// 最后返回所有distinct value的和。
// matrix：[[1,2,3],
//         [4,2,2]],   k = 2
// submatrix有 [[1,2], [4,2]] 和 [[2,3], [2,2]],
// 两个submatrix的和都为最大值9，
// 所以最后能找到的distinct value有[1,2,3,4]，输出的结果为10

public class MaxSubmatrixSumWithLenK {
    public int getMaxSum(int[][] matrix, int k){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0
                || matrix.length < k || matrix[0].length < k) {
            return -1;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m - k + 1][n - k + 1];
        Set<Integer> set = new HashSet<>();

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < m - k + 1; i++){
            for(int j = 0; j < n - k + 1; j++) {
                if (i == 0 && j == 0) {
                    int sum = 0;
                    for (int a = i; a < i + k; a++) {
                        for (int b = j; b < j + k; b++) {
                            sum += matrix[a][b];
                        }
                    }
                    dp[i][j] = sum;
                } else if (i == 0) {
                    // dp[0][j] = dp[0][j-1] - (j-1)th-columnSum + jth-columnSum
                    int sum = dp[i][j - 1];
                    for (int a = i; a < i + k; a++) {
                        sum -= matrix[a][j - 1];
                        sum += matrix[a][j + k - 1];
                    }
                    dp[i][j] = sum;
                } else {
                    // dp[i][j] = dp[i-1][j] - (i-1)th-rowSum + ith-rowSum
                    int sum = dp[i - 1][j];
                    for (int a = j; a < j + k; a++) {
                        sum -= matrix[i - 1][a];
                        sum += matrix[i + k - 1][a];
                    }
                    dp[i][j] = sum;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        for(int i = 0; i < m - k + 1; i++){
            for(int j = 0; j < n - k + 1; j++) {
                if (dp[i][j] == max) {
                    for (int a = i; a < i + k; a++) {
                        for (int b = j; b < j + k; b++) {
                            set.add(matrix[a][b]);
                        }
                    }
                }
            }
        }
        return set.stream().mapToInt(i -> i).sum();
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,5,3},
                {4,2,2,3},
                {1,2,1,4}
        };
        int k = 2;
        MaxSubmatrixSumWithLenK maxSubmatrixSumWithLenK = new MaxSubmatrixSumWithLenK();
        int rst = maxSubmatrixSumWithLenK.getMaxSum(matrix, k);
        System.out.println(rst);
    }
}
