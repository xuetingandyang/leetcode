package codesignal;

import java.util.*;

// Leetcode 1329:
//    int fun(int[][] a), 把 matrix 按斜线顺序重排
//    [8, 4, 1]     [4, 1, 1]
//    [4, 4, 1] --> [4, 8, 4]
//    [4, 8, 9]     [4, 8, 9]
//    Solution:
//    Elements A[i][j] on a same diagonal ensure the same value of 'i - j'
//    For each diagonal,
//    put its elements together in PriorityQueue, sort, and set them back (poll it).
//
//    Complexity
//    Time O(MNlogD), where D is the length of diagonal with D = min(M,N).
//    Space O(MN)

public class SortMatrixDiagonally {

    public int[][] diagonalSort(int[][] A) {
        int m = A.length, n = A[0].length;
        HashMap<Integer, PriorityQueue<Integer>> d = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                d.putIfAbsent(i - j, new PriorityQueue<>());
                d.get(i - j).add(A[i][j]);
            }
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = d.get(i - j).poll();
        return A;
    }


//    public void helper(int[][] matrix, int[] cur, int[] dir){
//        while (cur[0] < matrix.length && cur[1] < matrix[0].length) {
//            List<Integer> tmp = new ArrayList<>();
//            int[] tmpCur = cur.clone();
//            while (tmpCur[0] < matrix.length && tmpCur[1] < matrix[0].length) {
//                tmp.add(matrix[tmpCur[0]][tmpCur[1]]);
//                tmpCur[0]++;
//                tmpCur[1]++;
//            }
//            tmp.sort(new Comparator<Integer>() {
//                @Override
//                public int compare(Integer o1, Integer o2) {
//                    return o1 - o2;
//                }
//            });
//            tmpCur = cur.clone();
//            int i = 0;
//            while(tmpCur[0] < matrix.length && tmpCur[1] < matrix[0].length){
//                matrix[tmpCur[0]][tmpCur[1]] = tmp.get(i++);
//                tmpCur[0]++;
//                tmpCur[1]++;
//            }
//            cur[0] += dir[0];
//            cur[1] += dir[1];
//        }
//    }
//    public int[][] diagonalSort(int[][] matrix){
//        int[] start = {0, 0};
//        int[] dir1 = {0, 1};
//        int[] dir2 = {1, 0};
//        int[] cur1 = start.clone(), cur2 = start.clone();
//        helper(matrix, cur1, dir1);
//        helper(matrix, cur2, dir2);
//        return matrix;
//    }

    public static void main(String[] args) {
//        int[][] matrix = {
//                {8,4,1},
//                {4,4,1},
//                {4,8,9}
//        };
//        int[][] matrix ={
//                {2,2,3,5,5},
//                {6,8,8,9,20},
//                {11,22,14,14,35},
//                {18,17,28,11,20},
//                {22,22,43,26,25}
//        };
        int[][] matrix = {
                {1,2,3,4,5},
                {3,7,6,4,9},
                {8,2,7,3,6},
                {9,2,7,3,4}
        };
        SortMatrixDiagonally diagonalSort = new SortMatrixDiagonally();
        int[][] rst = diagonalSort.diagonalSort(matrix);
        System.out.println(rst);
    }
}
