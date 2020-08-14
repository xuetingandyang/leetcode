package codesignal;

// sort elements in a matrix then re-organize the elements
// sort: 1. by frequency of elements 2. by value of the element
// re-organize: 矩阵里数字代表Order后element 所处位置，如order后顺序为[-2,-2,1,1,4,4,3,3,3],
//对应的index为[1,2,3,4,5,6,7,8,9]
// [9, 8, 6],
// [7, 5, 3],
// [4, 2, 1]]
//example:
//    {1,  4, -2},       {3, 3,  4}
//    {-2, 3, 4},  =>    {3, 4,  1}
//    {3,  1, 3}         {1, -2, -2}

import java.util.*;

public class SortMatrixByOccurrences {
    public void sortMatrixByOccurrences(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                map.put(matrix[i][j], map.getOrDefault(matrix[i][j], 0) + 1);
            }
        }
        // order in increasing frequency and original value
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : a.getValue() - b.getValue()
        );
        pq.addAll(map.entrySet());

        // save [-2,-2,1,1,4,4,3,3,3] into stack FILO,
        // so we follow the (rightTop -> leftBottom) diagonal order to fill the matrix
        Stack<Integer> nums = new Stack<>();
        while ( ! pq.isEmpty()) {
            Map.Entry<Integer, Integer> e = pq.poll();
            for (int i = 0; i < e.getValue(); i ++) {
                nums.add(e.getKey());
            }
        }

        // insert sorted nums into matrix following (leftTop -> rightBottom) diagonally
        // there are (m+n-1) lines(diagonals)
        for (int line = 0; line < (m+n-1); line ++) {
            int start = line < n ? 0 : line - n + 1;
            int end = line < m ? line - start + 1 : m;
            for (int i = start; i < end; i ++) {
                matrix[i][line - i] = nums.pop();
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,  4, -2, 2},
            {-2, 3, 4, 2},
            {3,  1,  3, 2}
        };
        SortMatrixByOccurrences obj = new SortMatrixByOccurrences();
        obj.sortMatrixByOccurrences(matrix);
    }
}
