package codesignal;

import java.util.Comparator;
import java.util.PriorityQueue;

// sort all nodes in matrix in descending order
// i.e. matrix[0][0] is max, matrix[m-1][n-1] is min

public class SortMatrix {
    public void sortMatrix(int[][] matrix){
        if(matrix == null || matrix.length ==0 || matrix[0].length == 0){
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        // order in descending order
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++){
                pq.offer(matrix[i][j]);
            }
        }
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = pq.poll().intValue();
            }
        }
        return;
    }

    public static void main(String[] args) {
//        int[][] matrix = {
//            {41, 45, 20, 21},
//            {1 ,2, 3, 4},
//            {30, 42, 43, 29 },
//            {16, 17, 19, 10}
//        };
        int[][] matrix ={
                {2,2,3,5,5},
                {6,8,8,9,20},
                {11,22,14,14,35},
                {18,17,28,11,20},
                {22,22,43,26,25}
        };
        SortMatrix sortMatrix = new SortMatrix();
        sortMatrix.sortMatrix(matrix);
    }
}
