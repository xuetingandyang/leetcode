package codesignal;

//让你 rotate 一个 matrix k 次(两个对角线上的点不rotate)参照leetcode 48
// 给一个正方形的matrix，绕着中点顺时针旋转k*90°(k为给定)，对角线不变，输出旋转以后的matrix。
//输入一个矩阵m和旋转次数k，矩阵对角线上的数不动，其他数字按顺时针方向进行旋转k次。
//例如：
// input：1,2,3     k = 1;  output: 1,4,3
//        4,5,6                     8,5,2
//        7,8,9                     7,6,9

// 矩阵m = [ [1   2   3   4   5]，
//          [6   7   8   9  10],
//          [11 12  13  14 15],
//          [16 17  18  19 20],
//          [21 22  23  24 25]]
// k = 1，翻转一次的话变成 [[1  16  11  6   5]，
//                      [22  7   12  9   2],
//                      [23  18  13  8   3],
//                      [24  17  14  19  4],
//                      [21  20  15  10  25]]。


public class RotateWithKTimes {

    private void rotate90Degree(int[][] matrix){
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = i + 1; j < n - i - 1; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        return;
    }

    private void rotate180Degree(int[][] matrix){
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = i + 1; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = temp;

                temp = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
                System.out.println("test");
            }
        }
        return;
    }

    private void rotate270Degree(int[][] matrix){
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = i + 1; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[n - j - 1][i];

                matrix[n - j - 1][i] = temp;
            }
        }
        return;
    }

    public void rotate(int[][] matrix, int k){
        int n = matrix.length;
        switch (k % 4) {
            case 1: {
                rotate90Degree(matrix);
                return;
            }
            case 2: {
                rotate180Degree(matrix);
                return;
            }
            case 3: {
                rotate270Degree(matrix);
                return;
            }
            default:{
                return;
            }
        }
    }

    public static void main(String[] args) {
        RotateWithKTimes rotateWithKTimes = new RotateWithKTimes();
        int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        int[][] matrix2 = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25},
        };
        int[][] matrix3 = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };
        int k = 3;
        rotateWithKTimes.rotate(matrix3, k);
    }
}
